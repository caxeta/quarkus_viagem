package travelorder;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

import static io.quarkus.arc.impl.UncaughtExceptions.LOGGER;


@Path("travelorder/")
public class TravelOrderResource extends PanacheEntity {

    @RestClient
    FlightService flightService;

    @RestClient
    HotelService hotelResource;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TravelOrderDTO> orders() {
        return TravelOrder.<TravelOrder>listAll()
                .stream()
                .map(order -> {
                    Flight flight = flightService.findByTravelOrderId(order.id);
                    if (flight == null) {
                        LOGGER.error("Flight is null for TravelOrder ID: " + order.id);
                        // Cria uma instância padrão de Flight
                        flight = new Flight(null, 0L, "UNKNOWN", "UNKNOWN");
                    }

                    Hotel hotel = hotelResource.findByTravelOrderId(order.id);
                    if (hotel == null) {
                        LOGGER.error("Hotel is null for TravelOrder ID: " + order.id);
                        // Cria uma instância padrão de Hotel
                        hotel = new Hotel(0L, order.id, 0L);
                    }

                    return new TravelOrderDTO(
                            flight.fromAirport(),
                            flight.toAirport(),
                            hotel.nights()
                    );
                })
                .toList();
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TravelOrderDTO findById(@PathParam("id") Long id) {

        TravelOrder travelOrder = TravelOrder.findById(id);
        travelorder.Flight flight = flightService.findByTravelOrderId(travelOrder.id);
        travelorder.Hotel hotel = hotelResource.findByTravelOrderId(travelOrder.id);
        return TravelOrderDTO.of(
                travelOrder,
                flight,
                hotel
        );
    }

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TravelOrder newTravelOrder(TravelOrderDTO travelOrderDTO) {
        TravelOrder travelOrder = new TravelOrder();
        TravelOrder.persist(travelOrder);

        travelorder.Flight flight = new travelorder.Flight(null, travelOrder.id, travelOrderDTO.toAirport(), travelOrderDTO.fromAirport());
        flightService.newFlight(flight);

        travelorder.Hotel hotel = new travelorder.Hotel(null, travelOrderDTO.nights(), travelOrder.id);
        hotelResource.newHotel(hotel);

        return travelOrder;
    }
}
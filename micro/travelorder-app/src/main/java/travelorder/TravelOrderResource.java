package travelorder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import flight.Flight;
import hotel.Hotel;

import java.util.List;

import static io.quarkus.arc.impl.UncaughtExceptions.LOGGER;

@Path("travelorder/")
@ApplicationScoped
public class TravelOrderResource extends PanacheEntity {

    @Inject
    @RestClient
    private FlightService flightService;

    @Inject
    @RestClient
    private HotelService hotelService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TravelOrderDTO> orders() {
        return TravelOrder.<TravelOrder>listAll()
                .stream()
                .map(order -> {
                    Flight flight = flightService.findByTravelOrderId(order.id);
                    if (flight == null) {
                        LOGGER.error("Flight is null for TravelOrder ID: " + order.id);
                        flight = new Flight();
                        flight.travelOrderId = order.id;
                        flight.fromAirport = "defaultFromAirport";
                        flight.toAirport = "defaultToAirport";
                        flight.id = null;
                    }

                    Hotel hotel = hotelService.findByTravelOrderId(order.id);
                    if (hotel == null) {
                        LOGGER.error("Hotel is null for TravelOrder ID: " + order.id);
                        hotel = new Hotel();
                        hotel.travelOrderId = order.id;
                        hotel.nights = -1L;
                        hotel.id = null;
                    }

                    return new TravelOrderDTO(
                            flight.fromAirport,
                            flight.toAirport,
                            hotel.nights
                    );
                })
                .toList();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TravelOrderDTO findById(@PathParam("id") Long id) {

        TravelOrder travelOrder = TravelOrder.findById(id);
        Flight flight = flightService.findByTravelOrderId(travelOrder.id);
        Hotel hotel = hotelService.findByTravelOrderId(travelOrder.id);
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

        Flight flight = new Flight();
        flight.fromAirport = travelOrderDTO.fromAirport();
        flight.toAirport = travelOrderDTO.toAirport();
        flight.travelOrderId = travelOrder.id;
        flightService.newFlight(flight);

        Hotel hotel = new Hotel();
        hotel.travelOrderId = travelOrder.id;
        hotel.nights = travelOrderDTO.nights();
        hotelService.newHotel(hotel);

        return travelOrder;
    }
}
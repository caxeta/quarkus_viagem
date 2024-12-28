package travelorder;

import jakarta.inject.Inject;
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

    @Inject
    @RestClient
    FlightService flightService;

    @Inject
    @RestClient
    HotelService hotelService;

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
                        flight.setTravelOrderId(order.id);
                        flight.setFromAirport("defaultFromAirport");
                        flight.setToAirport("defaultToAirport");
                        flight.setId(null);
                    }

                    Hotel hotel = hotelService.findByTravelOrderId(order.id);
                    if (hotel == null) {
                        LOGGER.error("Hotel is null for TravelOrder ID: " + order.id);
                        hotel = new Hotel();
                        hotel.setTravelOrderId(order.id);
                        hotel.setNights(-1L);
                        hotel.setId(null);
                    }

                    return new TravelOrderDTO(
                            flight.getFromAirport(),
                            flight.getToAirport(),
                            hotel.getNights()
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
        return new TravelOrderDTO(
                flight.getFromAirport(),
                flight.getToAirport(),
                hotel.getNights()
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
        flight.setFromAirport(travelOrderDTO.fromAirport());
        flight.setToAirport(travelOrderDTO.toAirport());
        flight.setTravelOrderId(travelOrder.id);
        flightService.newFlight(flight);

        Hotel hotel = new Hotel();
        hotel.setTravelOrderId(travelOrder.id);
        hotel.setNights(travelOrderDTO.nights());
        hotelService.newHotel(hotel);

        return travelOrder;
    }
}
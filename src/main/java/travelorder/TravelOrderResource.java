package travelorder;

import flight.Flight;
import flight.FlightResource;
import hotel.Hotel;
import hotel.HotelResource;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.stream.Collectors;


@Path("travelorder/")
public class TravelOrderResource extends PanacheEntity {

    @Inject
    FlightResource flightResource;

    @Inject
    HotelResource hotelResource;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TravelOrderDTO> orders() {
        return TravelOrder.<TravelOrder>listAll()
                .stream()
                .map(order -> {
                    Flight flight = flightResource.findByTravelOrderId(order.id);
                    Hotel hotel = hotelResource.findByTravelOrderId(order.id);
                    return TravelOrderDTO.of(
                            order,
                            flight != null ? flight : new Flight(),  // Passar valores padrão
                            hotel != null ? hotel : new Hotel()            // Passar valores padrão
                    );
                })
                .collect(Collectors.toList());
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TravelOrder findById(@PathParam("id") Long id) {
        return TravelOrder.findById(id);
    }

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TravelOrder newTravelOrder(TravelOrderDTO travelOrderDTO) {
        TravelOrder travelOrder = new TravelOrder();
        TravelOrder.persist(travelOrder);

        Flight flight = new Flight();
        flight.toAirport = travelOrderDTO.toAirport();
        flight.fromAirport = travelOrderDTO.fromAirport();
        flight.travelOrderId = travelOrder.id;
        flightResource.newFlight(flight);

        Hotel hotel = new Hotel();
        hotel.nights = travelOrderDTO.nights();
        hotel.travelOrderId = travelOrder.id;
        hotelResource.newHotel(hotel);

        return travelOrder;
    }
}
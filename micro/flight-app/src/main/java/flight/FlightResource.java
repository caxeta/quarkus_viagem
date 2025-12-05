package flight;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/flights")
@ApplicationScoped
public class FlightResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Flight> getFlights() {
        return Flight.listAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Flight getFlight(@PathParam("id") Long id) {
        return Flight.findById(id);
    }

    @GET
    @Path("/findByTravelOrderId/{travelOrderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Flight findByTravelOrderId(@PathParam("travelOrderId") Long travelOrderId) {
        return Flight.findByTravelOrderId(travelOrderId);
    }

    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Flight newFlight(Flight flight) {
        flight.id = null;
        Flight.persist(flight);
        return flight;
    }
}

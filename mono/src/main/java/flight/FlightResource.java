package flight;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

import static io.quarkus.hibernate.orm.panache.PanacheEntityBase.*;

@Path("/flights")
public class FlightResource {

    @Inject
    Flight flight;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Flight> getFlights(){
        return listAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Flight getFlight(@PathParam("id") Long id){
        return findById(id);
    }

    @GET
    @Path("/findByTravelOrderId/{travelOrderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Flight findByTravelOrderId(@PathParam("travelOrderId") Long travelOrderId){
        return Flight.findByTravelOrderId(travelOrderId);
    }

    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Flight newFlight(Flight flight){
        flight.id = null;
        persist(flight);
        return flight;
    }
}

package travelorder;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8081/flights")
public interface FlightService {


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Flight getFlight(@PathParam("id") Long id) ;

    @GET
    @Path("/findByTravelOrderId/{travelOrderId}")
    @Produces(MediaType.APPLICATION_JSON)
    Flight findByTravelOrderId(@PathParam("travelOrderId") Long travelOrderId) ;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Flight newFlight(Flight flight) ;

}
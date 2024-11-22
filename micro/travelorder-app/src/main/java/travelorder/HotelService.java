package travelorder;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@RegisterRestClient(baseUri = "http://localhost:8082/hotels")
public interface HotelService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    Hotel getHotel(@PathParam("id") Long id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/findByTravelOrderId/{travelOrderId}")
    Hotel findByTravelOrderId(@PathParam("travelOrderId") Long travelOrderId);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Hotel newHotel(Hotel hotel);
}

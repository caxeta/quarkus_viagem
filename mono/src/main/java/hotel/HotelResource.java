package hotel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@ApplicationScoped
@Path("/hotels")
public class HotelResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hotel> getHotels(){
        return Hotel.listAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Hotel getHotel(@PathParam("id") Long id){
        return Hotel.findById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/findByTravelOrderId/{travelOrderId}")
    public Hotel findByTravelOrderId(@PathParam("travelOrderId") Long travelOrderId){
        return Hotel.findByTravelOrderId(travelOrderId);
    }

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Hotel newHotel(Hotel hotel){
        hotel.id = null;
        hotel.persist(hotel);
        return hotel;
    }
}

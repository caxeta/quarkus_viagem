package travelorder;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.time.temporal.ChronoUnit;

@RegisterRestClient(baseUri = "http://hotel-app-analistacaxeta-dev.apps.rm3.7wse.p1.openshiftapps.com/hotels")
public interface HotelService {
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Hotel findById(@PathParam("id") Long id);

    @GET
    @Path("/findByTravelOrderId/{travelOrderId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Timeout(unit = ChronoUnit.SECONDS, value = 2)
    @Fallback(fallbackMethod = "fallback")
    @CircuitBreaker(
            requestVolumeThreshold = 4,
            failureRatio = 0.6, //default 0.5
            delay = 5000,
            successThreshold = 2
    )
    Hotel findByTravelOrderId(@PathParam("travelOrderId") long travelOrderId);

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    Hotel newHotel(Hotel hotel);

    @SuppressWarnings("unused")
    default Hotel fallback(long travelOrderId) {
        Hotel hotel = new Hotel();
        hotel.setId(null);
        hotel.setTravelOrderId(travelOrderId);
        hotel.setNights(-1L);
        return hotel;
    }
}
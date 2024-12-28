package travelorder;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.time.temporal.ChronoUnit;

@RegisterRestClient(baseUri = "http://flight-app-analistacaxeta-dev.apps.rm3.7wse.p1.openshiftapps.com/flights")
public interface FlightService {
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Flight findById(@PathParam("id") long id);

    @GET
    @Path("/findByTravelOrderId/{travelOrderId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Timeout(unit = ChronoUnit.SECONDS, value = 2)
    @Fallback(fallbackMethod = "fallback")
    @CircuitBreaker(
            requestVolumeThreshold = 4,
            failureRatio = 0.9,
            delay = 5000,
            successThreshold = 2
    )
    Flight findByTravelOrderId(@PathParam("travelOrderId") long travelOrderId);

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    Flight newFlight(Flight flight);

    @SuppressWarnings("unused")
    default Flight fallback(long travelOrderId){
        Flight flight = new Flight();
        flight.setId(null);
        flight.setTravelOrderId(travelOrderId);
        flight.setFromAirport("defaultFromAirPort");
        flight.setToAirport("defaultToAirPort");
        return flight;
    }
}

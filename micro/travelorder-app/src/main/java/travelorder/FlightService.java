package travelorder;

import flight.Flight;
import hotel.Hotel;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.time.temporal.ChronoUnit;

@RegisterRestClient(baseUri = "http://localhost:8081/flights")
public interface FlightService {
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Flight findById(@PathParam("id") long id);

    @GET
    @Path("findByTravelOrderId")
    @Produces(MediaType.APPLICATION_JSON)
    @Timeout(unit = ChronoUnit.SECONDS, value = 2)
    @Fallback(fallbackMethod = "fallback")
    @CircuitBreaker(
            requestVolumeThreshold = 4,
            failureRatio = 0.5,
            delay = 5000,
            successThreshold = 2
    )
    public Flight findByTravelOrderId(@QueryParam("travelOrderId") long travelOrderId);

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Flight newFlight(Flight flight);

    default Flight fallback(long travelOrderId){
        Flight flight = new Flight();
        flight.id = null;
        flight.travelOrderId = travelOrderId;
        flight.fromAirport = "defaultFromAirport";
        flight.toAirport = "defaultToAirport";
        return flight;
    }
}

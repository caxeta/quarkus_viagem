package travelorder;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;


@Path("travelorder/")
public class TravelOrderResource extends PanacheEntity {

    @Inject
    TravelOrderRepository travelOrderRepository;


//    @Inject
//    public TravelOrderResource(TravelOrderRepository travelOrderRepository) {
//        this.travelOrderRepository = travelOrderRepository;
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TravelOrder> orders(){
        return travelOrderRepository.listAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TravelOrder findById(@PathParam("id") Long id){
        return travelOrderRepository.findById(id);
    }

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TravelOrder newTravelOrder(TravelOrder travelOrder){
        travelOrderRepository.persist(travelOrder);
        return travelOrder;
    }
}
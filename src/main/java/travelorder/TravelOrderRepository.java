package travelorder;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TravelOrderRepository implements PanacheRepository<TravelOrder> {

}

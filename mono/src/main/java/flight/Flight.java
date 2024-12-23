package flight;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
@ApplicationScoped
public class Flight extends PanacheEntityBase {
    @Id
    public Long id;
    public String fromAirport;
    public String toAirport;
    public Long travelOrderId;

    public static List<Flight> listAll() {
        return listAll();
    }

    public static Flight findById(Long id) {
        return findById(id);
    }

    public static Flight findByTravelOrderId(Long travelOrderId) {
        return find("travelOrderId", travelOrderId).firstResult();
    }

    public void persist(Flight flight) {
        flight.persist();
    }
}

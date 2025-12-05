package flight;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Flight extends PanacheEntityBase {
    @Id
    public Long id;
    public String fromAirport;
    public String toAirport;
    public Long travelOrderId;

    public static Flight findByTravelOrderId(Long travelOrderId) {
        return find("travelOrderId", travelOrderId).firstResult();
    }
}

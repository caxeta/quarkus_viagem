package hotel;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Hotel extends PanacheEntity {
    public Long id;
    public Long travelOrderId;
    public Long nights;

    public static Hotel findByTravelOrderId(Long travelOrderId) {
        return find("travelOrderId", travelOrderId).firstResult();
    }
}

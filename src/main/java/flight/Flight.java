package flight;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Flight extends PanacheEntity {
    public String fromAirport;
    public String toAirport;
    public Long travelOrderId;

}

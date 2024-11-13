package hotel;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Hotel extends PanacheEntity {
    public Long id;
    private String travelorderid;
    private String nights;

}

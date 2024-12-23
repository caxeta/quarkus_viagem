package travelorder;

public class Hotel {
    private Long id;
    private Long travelOrderId;
    private Long nights;

    @SuppressWarnings("unused")
    public Hotel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTravelOrderId() {
        return travelOrderId;
    }

    public void setTravelOrderId(Long travelOrderId) {
        this.travelOrderId = travelOrderId;
    }

    public Long getNights() {
        return nights;
    }

    public void setNights(Long nights) {
        this.nights = nights;
    }
}

package travelorder;

public record TravelOrderDTO(String fromAirport,
                             String toAirport,
                             Long nights) {

    public static TravelOrderDTO of(TravelOrder travelOrder, travelorder.Flight flight, travelorder.Hotel hotel) {

        return new TravelOrderDTO(flight.fromAirport(), flight.toAirport(), hotel.nights());
    }

    public static TravelOrderDTO of(String fromAirport, String toAirport, Long nights) {
        return new TravelOrderDTO(fromAirport, toAirport, nights);
    }
}

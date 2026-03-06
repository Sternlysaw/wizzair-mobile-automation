package models;

import java.time.LocalDate;

public class DeepLinkFlightData {
    public final String origin;
    public final String destination;
    public final LocalDate departureDate;

    public DeepLinkFlightData(String origin, String destination, LocalDate departureDate) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
    }
}
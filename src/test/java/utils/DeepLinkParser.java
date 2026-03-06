package utils;

import models.DeepLinkFlightData;

import java.net.URI;
import java.time.LocalDate;

public class DeepLinkParser {

    private DeepLinkParser() {}

    public static DeepLinkFlightData parseSelectFlight(String url) {
        try {
            URI uri = URI.create(url);
            String path = uri.getPath();

            // Example:
            // /en-gb/booking/select-flight/CRL/VAR/2026-03-28/null/1/0/0/null
            String[] parts = path.split("/");

            int index = -1;
            for (int i = 0; i < parts.length; i++) {
                if ("select-flight".equalsIgnoreCase(parts[i])) {
                    index = i;
                    break;
                }
            }

            if (index == -1 || parts.length <= index + 3) {
                throw new IllegalArgumentException("Deep link does not contain expected select-flight structure: " + url);
            }

            String origin = parts[index + 1];
            String destination = parts[index + 2];
            LocalDate departureDate = LocalDate.parse(parts[index + 3]);

            return new DeepLinkFlightData(origin, destination, departureDate);

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse select-flight deep link: " + url, e);
        }
    }
}
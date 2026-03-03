package utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class AppiumHealthCheck {

    private AppiumHealthCheck() {}

    public static void verifyServerUp(String appiumServerUrl) {
        try {
            String statusUrl = appiumServerUrl.endsWith("/")
                    ? appiumServerUrl + "status"
                    : appiumServerUrl + "/status";

            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(3))
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(statusUrl))
                    .timeout(Duration.ofSeconds(5))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new RuntimeException("Appium server responded with HTTP " + response.statusCode());
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Appium server is not reachable. Start it with: `appium` " +
                            "and verify it listens on: " + appiumServerUrl, e
            );
        }
    }
}
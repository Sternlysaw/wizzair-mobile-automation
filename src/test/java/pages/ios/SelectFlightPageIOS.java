package pages.ios;

import models.DeepLinkFlightData;
import pages.BasePage;
import pages.api.SelectFlightPageActions;
import utils.DeepLinkParser;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SelectFlightPageIOS extends BasePage implements SelectFlightPageActions {

    private UnsupportedOperationException notReady() {
        return new UnsupportedOperationException(
                "SelectFlightPageIOS is not implemented yet. Add iOS locators in pages/ios/SelectFlightPageIOS.java"
        );
    }

    @Override
    public boolean waitUntilDisplayed() {
        throw notReady();
    }

    @Override
    public int getVisibleFlightCount() {
        throw notReady();
    }

    @Override
    public String firstDepartureTime() {
        throw notReady();
    }

    @Override
    public boolean isDepartureTimeVisible(String time) {
        throw notReady();
    }

    @Override
    public boolean infiniteScrollLoadsMoreOrStops(int maxSwipes) {
        throw notReady();
    }

    @Override
    public void selectFirstFlight() {
        throw notReady();
    }

    @Override
    public boolean matchesDeepLink(String deepLinkUrl) {
        DeepLinkFlightData data = DeepLinkParser.parseSelectFlight(deepLinkUrl);

        String source = normalize(driver.getPageSource());

        boolean routeMatches =
                source.contains(normalize(data.origin)) &&
                        source.contains(normalize(data.destination));

        boolean dateMatches = containsAny(source,
                data.departureDate.toString(),
                data.departureDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH)),
                data.departureDate.format(DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH)),
                data.departureDate.format(DateTimeFormatter.ofPattern("dd MMM", Locale.ENGLISH)),
                data.departureDate.format(DateTimeFormatter.ofPattern("d MMM", Locale.ENGLISH)),
                data.departureDate.format(DateTimeFormatter.ofPattern("MMM dd", Locale.ENGLISH)),
                data.departureDate.format(DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH)),
                data.departureDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH)),
                data.departureDate.format(DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH))
        );

        return routeMatches && dateMatches;
    }

    private boolean containsAny(String source, String... candidates) {
        for (String candidate : candidates) {
            if (source.contains(normalize(candidate))) {
                return true;
            }
        }
        return false;
    }

    private String normalize(String value) {
        return value == null ? "" : value.toLowerCase(Locale.ROOT).replaceAll("\\s+", " ").trim();
    }
}
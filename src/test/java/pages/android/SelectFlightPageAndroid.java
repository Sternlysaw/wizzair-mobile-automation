package pages.android;

import core.DriverManager;
import io.appium.java_client.AppiumBy;
import models.DeepLinkFlightData;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.api.SelectFlightPageActions;
import utils.DeepLinkParser;
import utils.ScrollUtils;
import utils.WaitUtils;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class SelectFlightPageAndroid extends BasePage implements SelectFlightPageActions {

    private final By selectFlightHeader =
            AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Select flight\")");

    private final By departureTimes =
            AppiumBy.id("com.wizzair.WizzAirApp:id/flight_select_journey_departure_time");

    private final By scrollView =
            AppiumBy.className("android.widget.ScrollView");

    private final By firstCard =
            AppiumBy.id("com.wizzair.WizzAirApp:id/flight_select_journey_normal_price");

    @Override
    public boolean waitUntilDisplayed() {
        try {
            new WaitUtils(DriverManager.getDriver(), Duration.ofSeconds(45))
                    .visible(selectFlightHeader);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Override
    public int getVisibleFlightCount() {
        return driver.findElements(departureTimes).size();
    }

    private List<String> visibleSignature() {
        List<WebElement> els = driver.findElements(departureTimes);
        List<String> sig = new ArrayList<>();
        for (WebElement el : els) {
            String t = el.getText();
            if (t != null && !t.isBlank()) sig.add(t.trim());
        }
        return sig;
    }

    @Override
    public String firstDepartureTime() {
        List<String> sig = visibleSignature();
        if (sig.isEmpty()) throw new AssertionError("No flights visible");
        return sig.get(0);
    }

    @Override
    public boolean isDepartureTimeVisible(String time) {
        for (String t : visibleSignature()) {
            if (time.equals(t)) return true;
        }
        return false;
    }

    @Override
    public boolean infiniteScrollLoadsMoreOrStops(int maxSwipes) {
        Set<String> seen = new HashSet<>(visibleSignature());
        List<String> lastSig = new ArrayList<>(seen);

        for (int i = 0; i < maxSwipes; i++) {
            ScrollUtils.swipeUpInside(scrollView);
            List<String> sig = visibleSignature();

            for (String t : sig) {
                if (seen.add(t)) {
                    return true;
                }
            }

            if (sig.equals(lastSig)) {
                ScrollUtils.swipeUpInside(scrollView);
                List<String> sig2 = visibleSignature();
                return sig2.equals(sig);
            }

            lastSig = sig;
        }

        return false;
    }

    @Override
    public void selectFirstFlight() {
        List<WebElement> cards = driver.findElements(firstCard);
        if (cards.isEmpty()) {
            throw new AssertionError("No flight cards found to select");
        }
        cards.get(0).click();
    }

    @Override
    public boolean matchesDeepLink(String deepLinkUrl) {
        DeepLinkFlightData data = DeepLinkParser.parseSelectFlight(deepLinkUrl);

        String source = normalize(driver.getPageSource());

        boolean routeMatches =
                source.contains(normalize(data.origin)) &&
                        source.contains(normalize(data.destination));

        boolean dateMatches = containsAny(source,
                data.departureDate.toString(), // 2026-03-28
                data.departureDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH)), // 28 Mar 2026
                data.departureDate.format(DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH)),  // 28 Mar 2026
                data.departureDate.format(DateTimeFormatter.ofPattern("dd MMM", Locale.ENGLISH)),       // 28 Mar
                data.departureDate.format(DateTimeFormatter.ofPattern("d MMM", Locale.ENGLISH)),        // 28 Mar
                data.departureDate.format(DateTimeFormatter.ofPattern("MMM dd", Locale.ENGLISH)),       // Mar 28
                data.departureDate.format(DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH)),        // Mar 28
                data.departureDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH)),   // 28/03/2026
                data.departureDate.format(DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH))      // 28/3/2026
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
package pages;

import core.DriverManager;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import utils.WaitUtils;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import utils.ScrollUtils;
import java.time.Duration;
import java.util.Set;

public class SelectFlightPage extends BasePage {

    private final By selectFlightHeader =
            AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Select flight\")");
    private final By departureTimes =
            AppiumBy.id("com.wizzair.WizzAirApp:id/flight_select_journey_departure_time");
    private final By ScrollView = AppiumBy.className("android.widget.ScrollView");

    public boolean waitUntilDisplayed() {
        try {
            new WaitUtils(DriverManager.getDriver(), Duration.ofSeconds(45))
                    .visible(selectFlightHeader);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public int getVisibleFlightCount() {
        return driver.findElements(departureTimes).size();
    }

    /** Snapshot of what's currently visible; used to detect changes after scroll. */
    private List<String> visibleSignature() {
        List<WebElement> els = driver.findElements(departureTimes);
        List<String> sig = new ArrayList<>();
        for (WebElement el : els) {
            String t = el.getText();
            if (t != null && !t.isBlank()) sig.add(t.trim());
        }
        return sig;
    }

    /** "Specific flight" = first visible departure time. */
    public String firstDepartureTime() {
        List<String> sig = visibleSignature();
        if (sig.isEmpty()) throw new AssertionError("No flights visible");
        return sig.get(0);
    }

    public boolean isDepartureTimeVisible(String time) {
        for (String t : visibleSignature()) {
            if (time.equals(t)) return true;
        }
        return false;
    }

    /**
     * Proves infinite-scroll behavior:
     * - if new items appear after scrolling -> PASS
     * - else if we can demonstrate we reached the end (view stops changing) -> PASS
     */
    public boolean infiniteScrollLoadsMoreOrStops(int maxSwipes) {

        Set<String> seen = new HashSet<>(visibleSignature());
        List<String> lastSig = new ArrayList<>(seen);

        for (int i = 0; i < maxSwipes; i++) {
            ScrollUtils.swipeUpInside(ScrollView);
            List<String> sig = visibleSignature();
            // new item appeared => dynamic loading / more items exist
            for (String t : sig) {
                if (seen.add(t)) {
                    return true;
                }
            }
            // If the signature didn't change compared to previous swipe, we might be at the end.
            if (sig.equals(lastSig)) {
                // do one more swipe to confirm "hard end"
                ScrollUtils.swipeUpInside(ScrollView);
                List<String> sig2 = visibleSignature();
                return sig2.equals(sig);
            }

            lastSig = sig;
        }

        // Nothing new found, and we didn't conclusively prove end; treat as not meeting requirement
        return false;
    }
    public void selectFirstFlight() {
        By firstCard = AppiumBy.id("com.wizzair.WizzAirApp:id/flight_select_journey_normal_price");
        driver.findElements(firstCard).get(0).click();
    }
}
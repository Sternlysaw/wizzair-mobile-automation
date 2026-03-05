package pages.android;

import core.DriverManager;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.api.SelectFlightPageActions;
import utils.ScrollUtils;
import utils.WaitUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

            // new item appeared => dynamic loading / more items exist
            for (String t : sig) {
                if (seen.add(t)) {
                    return true;
                }
            }

            // If signature didn't change, we might be at the end. Confirm with one more swipe.
            if (sig.equals(lastSig)) {
                ScrollUtils.swipeUpInside(scrollView);
                List<String> sig2 = visibleSignature();
                return sig2.equals(sig);
            }

            lastSig = sig;
        }

        // Nothing new found, and we didn't conclusively prove end
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
}
package pages;

import core.DriverManager;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import utils.WaitUtils;

import java.time.Duration;

public class SelectFlightPage extends BasePage {

    private final By selectFlightHeader =
            AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Select flight\")");

    public boolean waitUntilDisplayed() {
        try {
            new WaitUtils(DriverManager.getDriver(), Duration.ofSeconds(45))
                    .visible(selectFlightHeader);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class TripSummaryPage extends BasePage {

    private final By yourTripTitle =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"your trip\")");

    public boolean isDisplayed() {
        return wait.visible(yourTripTitle).isDisplayed();
    }
}
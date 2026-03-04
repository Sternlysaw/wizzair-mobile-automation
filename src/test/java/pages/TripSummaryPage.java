package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class TripSummaryPage extends BasePage {

    private final By yourTripTitle =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"your trip\")");
    private static final By next = AppiumBy.androidUIAutomator("new UiSelector().text(\"NEXT\")");
    public boolean isDisplayed() {
        return wait.visible(yourTripTitle).isDisplayed();
    }
    public static void tapNext() {
        click(next);
    }
}
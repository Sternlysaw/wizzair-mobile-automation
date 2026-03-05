package pages.android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.api.TripSummaryPageActions;

public class TripSummaryPageAndroid extends BasePage implements TripSummaryPageActions {

    // Same selector value as your original file, better name
    private final By tripTitle =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"your trip\")");

    // Same selector value as your original file, better name
    private final By nextButton =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"NEXT\")");

    @Override
    public boolean isDisplayed() {
        return wait.visible(tripTitle).isDisplayed();
    }

    @Override
    public void tapNext() {
        click(nextButton);
    }
}
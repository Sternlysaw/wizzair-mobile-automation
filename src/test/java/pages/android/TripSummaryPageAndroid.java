package pages.android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.api.TripSummaryPageActions;

public class TripSummaryPageAndroid extends BasePage implements TripSummaryPageActions {

    private final By tripTitle =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"your trip\")");

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
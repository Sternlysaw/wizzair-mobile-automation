package pages.android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.api.SeatsPageActions;

public class SeatsPageAndroid extends BasePage implements SeatsPageActions {

    private final By title =
            AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Seats\")");

    private final By chooseLaterButton =
            AppiumBy.id("com.wizzair.WizzAirApp:id/srf_no_seat");

    private final By nextButton =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"NEXT\")");

    @Override
    public void waitForPage() {
        wait.visible(title);
    }

    @Override
    public void chooseSeatLater() {
        wait.clickable(chooseLaterButton);
        click(chooseLaterButton);
    }

    @Override
    public void tapNext() {
        click(nextButton);
    }
}
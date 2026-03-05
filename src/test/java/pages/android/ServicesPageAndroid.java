package pages.android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import pages.BasePage;
import pages.api.ServicesPageActions;
import utils.WaitUtils;

import java.time.Duration;

public class ServicesPageAndroid extends BasePage implements ServicesPageActions {

    private final By title =
            AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Services\")");

    private final By next =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"NEXT\")");

    @Override
    public void waitForPage() {
        wait.visible(title);
    }
    @Override
    public void tapNextStable() {
        waitForPage();

        WaitUtils longWait = new WaitUtils(driver, Duration.ofSeconds(10));

        // Small retry for transient stale element during UI transition
        for (int attempt = 0; attempt < 2; attempt++) {
            try {
                longWait.clickable(next).click();
                return;
            } catch (StaleElementReferenceException ignored) {
                // retry once
            } catch (TimeoutException e) {
                throw new TimeoutException("NEXT button was not clickable on Services page within extended timeout", e);
            }
        }
    }
}
package pages.android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import pages.BasePage;
import pages.api.DeclarationPageActions;
import utils.WaitUtils;

import java.time.Duration;

public class DeclarationPageAndroid extends BasePage implements DeclarationPageActions {
    private final By title =
            AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Declaration\")");
    private final By confirmButton =
            AppiumBy.id("com.wizzair.WizzAirApp:id/insurance_declaration_confirm_sticky");

    @Override
    public void waitForPage() {
        wait.visible(title);
    }
    @Override
    public void tapConfirmStable() {
        waitForPage();

        WaitUtils longWait = new WaitUtils(driver, Duration.ofSeconds(10));

        // Small retry for transient stale element during UI transition
        for (int attempt = 0; attempt < 2; attempt++) {
            try {
                longWait.clickable(confirmButton).click();
                return;
            } catch (StaleElementReferenceException ignored) {
                // retry once
            } catch (TimeoutException e) {
                throw new TimeoutException("Confirm button was not clickable on Services page within extended timeout", e);
            }
        }
    }
}

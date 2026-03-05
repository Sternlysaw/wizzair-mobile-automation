package pages.Android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import pages.BasePage;

import java.util.List;

public class PriceChangeDialog extends BasePage {

    /**
     * IMPORTANT:
     * We detect specifically for "price changed" style wording.
     */
    private final By marker = AppiumBy.androidUIAutomator(
            "new UiSelector().textMatches(\"(?i).*price.*(changed).*\" )"
    );

    // Common button texts when a price-change prompt appears
    private final List<By> actionButtons = List.of(
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)continue\")"),
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)ok\")"),
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)accept\")"),
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)confirm\")"),
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)got it\")"),
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)understood\")"),
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)yes\")")
    );

    public void acceptIfPresent() {
        // If the specific marker isn't there, it's not the price-change dialog.
        if (driver.findElements(marker).isEmpty()) {
            return;
        }

        for (By btn : actionButtons) {
            if (!driver.findElements(btn).isEmpty()) {
                click(btn);
                return;
            }
        }

        throw new AssertionError("Price change dialog shown but no known button found");
    }
}
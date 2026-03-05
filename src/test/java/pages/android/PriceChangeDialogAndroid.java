package pages.android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.api.PriceChangeDialogActions;

import java.util.List;

public class PriceChangeDialogAndroid extends BasePage implements PriceChangeDialogActions {

    private final By marker =
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i).*price.*(changed).*\" )");

    private final List<By> actionButtons = List.of(
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)continue\")"),
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)ok\")"),
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)accept\")"),
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)confirm\")"),
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)got it\")"),
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)understood\")"),
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)yes\")")
    );

    @Override
    public void acceptIfPresent() {
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
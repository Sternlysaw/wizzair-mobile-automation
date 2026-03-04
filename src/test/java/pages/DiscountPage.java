package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import utils.ScrollUtils;

public class DiscountPage extends BasePage {
    private final By title = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Join WIZZ Discount Club\")");
    private final By ScrollView = AppiumBy.className("android.widget.ScrollView");
    private final By noThankYou = AppiumBy.id("com.wizzair.WizzAirApp:id/wdc_no_or_back_button");

    public void waitForPage() {
        wait.visible(title);
    }

    public void tapNoThankYou() {
        for (int i = 0; i < 5; i++) {
            if (!driver.findElements(noThankYou).isEmpty()) {
                wait.clickable(noThankYou);
                click(noThankYou);
                return;
            }
            ScrollUtils.swipeUpInside(ScrollView);
        }
        throw new AssertionError("No thank you can not be found after swiping");
    }
}

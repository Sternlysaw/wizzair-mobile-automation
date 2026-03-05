package pages.android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.api.DiscountPageActions;
import utils.ScrollUtils;

public class DiscountPageAndroid extends BasePage implements DiscountPageActions {

    private final By joinDiscountTitle =
            AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Join WIZZ Discount Club\")");

    private final By scrollView =
            AppiumBy.className("android.widget.ScrollView");

    private final By noThankYouButton =
            AppiumBy.id("com.wizzair.WizzAirApp:id/wdc_no_or_back_button");

    @Override
    public void waitForPage() {
        wait.visible(joinDiscountTitle);
    }

    @Override
    public void declineDiscount() {
        for (int i = 0; i < 5; i++) {
            if (!driver.findElements(noThankYouButton).isEmpty()) {
                wait.clickable(noThankYouButton);
                click(noThankYouButton);
                return;
            }
            ScrollUtils.swipeUpInside(scrollView);
        }
        throw new AssertionError("No thank you can not be found after swiping");
    }
}
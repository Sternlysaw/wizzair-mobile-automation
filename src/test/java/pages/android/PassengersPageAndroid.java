package pages.android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.api.PassengersPageActions;
import utils.ScrollUtils;

public class PassengersPageAndroid extends BasePage implements PassengersPageActions {

    private final By title =
            AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Passengers\")");

    private final By nextButton =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"NEXT\")");

    private final By scrollView =
            AppiumBy.className("android.widget.ScrollView");

    private final By noAssistance =
            AppiumBy.id("com.wizzair.WizzAirApp:id/passengerAddNameFragment_prm_no_container");

    @Override
    public boolean isDisplayed() {
        try {
            return wait.visible(title).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void tapNoSpecialAssistance() {
        wait.clickable(noAssistance);
        click(noAssistance);
    }

    @Override
    public void tapNext() {
        // Next can be off-screen sometimes, so do the same safe pattern you use elsewhere
        for (int i = 0; i < 5; i++) {
            if (!driver.findElements(nextButton).isEmpty()) {
                wait.clickable(nextButton);
                click(nextButton);
                return;
            }
            ScrollUtils.swipeUpInside(scrollView);
        }
        throw new AssertionError("NEXT button not found after swiping on Passengers page");
    }
}
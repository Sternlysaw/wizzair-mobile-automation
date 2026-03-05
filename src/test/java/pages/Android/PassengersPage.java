package pages.Android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import utils.ScrollUtils;

public class PassengersPage extends BasePage{
    private final By title = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Passengers\")");
    private final By next = AppiumBy.androidUIAutomator("new UiSelector().text(\"NEXT\")");
    private final By ScrollView = AppiumBy.className("android.widget.ScrollView");
    private final By noAssistance = AppiumBy.id("com.wizzair.WizzAirApp:id/passengerAddNameFragment_prm_no_container");

    public void waitForPage() {
        wait.visible(title);
    }

    public void tapNoAssistance() {
        waitForPage();
        for (int i = 0; i < 5; i++) {
            if (!driver.findElements(noAssistance).isEmpty()) {
                wait.clickable(noAssistance);
                click(noAssistance);
                return;
            }
            ScrollUtils.swipeUpInside(ScrollView);
        }
        throw new AssertionError("No Assistance can not be found after swiping");
    }
    public void tapNext() {
        click(next);
    }
}

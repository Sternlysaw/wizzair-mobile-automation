package pages.Android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import utils.ScrollUtils;

public class SeatsPage extends BasePage {
    private final By title = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Seats\")");
    private final By chooseSeatLater = AppiumBy.id("com.wizzair.WizzAirApp:id/srf_no_seat");
    private final By next = AppiumBy.androidUIAutomator("new UiSelector().text(\"NEXT\")");
    private final By ScrollView = AppiumBy.className("android.widget.ScrollView");

    public void waitForPage() {
        wait.visible(title);
    }

    public void chooseSeatLater() {
        for (int i = 0; i < 5; i++) {
            if (!driver.findElements(chooseSeatLater).isEmpty()) {
                click(chooseSeatLater);
                return;
            }
            ScrollUtils.swipeUpInside(ScrollView);
        }
        throw new AssertionError("Choose seat later can not be found after swiping");
    }
    public void clickNext() {
        click(next);
    }
}

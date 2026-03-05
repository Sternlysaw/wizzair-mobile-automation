package pages.Android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import utils.ScrollUtils;


public class BaggagesPage extends BasePage {
    private final By title = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Baggages\")");
    private final By ScrollView = AppiumBy.className("android.widget.ScrollView");
    private final By smallCheckedInBaggage = AppiumBy.id("com.wizzair.WizzAirApp:id/baggageCheckedInView_smallCheckedInBaggage");
    private final By next = AppiumBy.androidUIAutomator("new UiSelector().text(\"NEXT\")");
    private final By sportEquipment = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Sport equipment\")");
    private final By twoCabinBagsAndPriority = AppiumBy.id("com.wizzair.WizzAirApp:id/baggageListItemView_topCabinBaggage");


    public void waitForPage() {
        wait.visible(title);
    }

    public void chooseSmallCheckedInBaggage() {
        for (int i = 0; i < 5; i++) {
            if (!driver.findElements(smallCheckedInBaggage).isEmpty()) {
                wait.clickable(smallCheckedInBaggage);
                click(smallCheckedInBaggage);
                return;
            }
            ScrollUtils.swipeUpInside(ScrollView);
        }
        throw new AssertionError("Small checked in baggage can not be found after swiping");
    }
    public void sportEquipmentIsVisible() {
        for (int i = 0; i < 5; i++) {
            if (!driver.findElements(sportEquipment).isEmpty()) {
                wait.visible(sportEquipment);
                return;
            }
            ScrollUtils.swipeUpInside(ScrollView);
        }
        throw new AssertionError("sport equipment can not be found after swiping");
    }
    public void clickNext() {
        click(next);
    }
    public void clickTwoCabinBagsAndPriority() {
        for (int i = 0; i < 5; i++) {
            if (!driver.findElements(twoCabinBagsAndPriority).isEmpty()) {
                click(twoCabinBagsAndPriority);
                return;
            }
            ScrollUtils.swipeUpInside(ScrollView);
        }
        throw new AssertionError("Cabin baggage can not be found after swiping");
    }
}

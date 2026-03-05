package pages.android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.api.BaggagesPageActions;
import utils.ScrollUtils;

public class BaggagesPageAndroid extends BasePage implements BaggagesPageActions {

    private final By pageTitle =
            AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Baggages\")");

    private final By scrollView =
            AppiumBy.className("android.widget.ScrollView");

    private final By smallCheckedInBaggageOption =
            AppiumBy.id("com.wizzair.WizzAirApp:id/baggageCheckedInView_smallCheckedInBaggage");

    private final By nextButton =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"NEXT\")");

    private final By sportEquipmentLabel =
            AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Sport equipment\")");

    private final By twoCabinBagsAndPriorityOption =
            AppiumBy.id("com.wizzair.WizzAirApp:id/baggageListItemView_topCabinBaggage");

    @Override
    public void waitForPage() {
        wait.visible(pageTitle);
    }

    @Override
    public void chooseSmallCheckedInBaggage() {
        for (int i = 0; i < 5; i++) {
            if (!driver.findElements(smallCheckedInBaggageOption).isEmpty()) {
                wait.clickable(smallCheckedInBaggageOption);
                click(smallCheckedInBaggageOption);
                return;
            }
            ScrollUtils.swipeUpInside(scrollView);
        }
        throw new AssertionError("Small checked in baggage can not be found after swiping");
    }

    @Override
    public void verifySportEquipmentVisible() {
        for (int i = 0; i < 5; i++) {
            if (!driver.findElements(sportEquipmentLabel).isEmpty()) {
                wait.visible(sportEquipmentLabel);
                return;
            }
            ScrollUtils.swipeUpInside(scrollView);
        }
        throw new AssertionError("sport equipment can not be found after swiping");
    }

    @Override
    public void tapNext() {
        click(nextButton);
    }

    @Override
    public void chooseTwoCabinBagsAndPriority() {
        for (int i = 0; i < 5; i++) {
            if (!driver.findElements(twoCabinBagsAndPriorityOption).isEmpty()) {
                click(twoCabinBagsAndPriorityOption);
                return;
            }
            ScrollUtils.swipeUpInside(scrollView);
        }
        throw new AssertionError("Cabin baggage can not be found after swiping");
    }
}
package pages.Android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import pages.BasePage;
import utils.ScrollUtils;

public class InsurencesPage extends BasePage {
    private final By title = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Select Insurance\")");
    private final By ScrollView = AppiumBy.className("android.widget.ScrollView");
    private final By insurenceTravel = AppiumBy.id("com.wizzair.WizzAirApp:id/insurance_travel");
    private final By select = AppiumBy.id("com.wizzair.WizzAirApp:id/insurance_doneButtonSticky");

    public void waitForPage() {
        wait.visible(title);
    }

    public void selectTravelInsurance() {
        waitForPage();

        for (int i = 0; i < 5; i++) {
            if (!driver.findElements(insurenceTravel).isEmpty()) {
                wait.clickable(insurenceTravel);
                click(insurenceTravel);
                wait.clickable(select);
                return;
            }
            ScrollUtils.swipeUpInside(ScrollView);
        }
        throw new AssertionError("Travel insurance not found after swiping");
    }
    public void tapSelect(){
        click(select);
    }
}

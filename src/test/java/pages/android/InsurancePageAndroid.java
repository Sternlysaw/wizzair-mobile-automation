package pages.android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import pages.BasePage;
import pages.api.InsurancePageActions;
import utils.ScrollUtils;
import utils.WaitUtils;

import java.time.Duration;

public class InsurancePageAndroid extends BasePage implements InsurancePageActions {

    // ✅ RHS unchanged
    private final By title =
            AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Select Insurance\")");

    // ✅ RHS unchanged
    private final By scrollView =
            AppiumBy.className("android.widget.ScrollView");

    // ✅ RHS unchanged
    private final By travelInsuranceOption =
            AppiumBy.id("com.wizzair.WizzAirApp:id/insurance_travel");

    // ✅ RHS unchanged
    private final By selectButton =
            AppiumBy.id("com.wizzair.WizzAirApp:id/insurance_doneButtonSticky");

    @Override
    public void waitForPage() {
        wait.visible(title);
    }

    @Override
    public void selectTravelInsurance() {

        waitForPage();

        for (int i = 0; i < 5; i++) {
            if (!driver.findElements(travelInsuranceOption).isEmpty()) {

                wait.clickable(travelInsuranceOption);
                click(travelInsuranceOption);
                waitSelectClickableLong();
                return;
            }

            ScrollUtils.swipeUpInside(scrollView);
        }

        throw new AssertionError("Travel insurance not found after swiping");
    }

    @Override
    public void tapSelectStable() {
        waitForPage();
        waitSelectClickableLong();
        click(selectButton);
    }

    private void waitSelectClickableLong() {
        try {
            new WaitUtils(driver, Duration.ofSeconds(35)).clickable(selectButton);
        } catch (TimeoutException e) {
            throw new TimeoutException("Select button was not clickable within extended timeout on Insurance page", e);
        }
    }
}
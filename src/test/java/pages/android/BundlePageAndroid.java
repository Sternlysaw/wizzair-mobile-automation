package pages.android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.api.BundlePageActions;
import utils.ScrollUtils;

public class BundlePageAndroid extends BasePage implements BundlePageActions {

    private final By title =
            AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Please select your bundle\")");

    private final By quickTravel =
            AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Quick Travel\")");

    private final By packAndSave =
            AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Pack & Save\")");

    private final By next =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"NEXT\")");

    private final By scrollView =
            AppiumBy.className("android.widget.ScrollView");

    @Override
    public void waitForPage() {
        wait.visible(title);
    }

    @Override
    public void chooseQuickTravel() {
        waitForPage();

        for (int i = 0; i < 5; i++) {
            if (!driver.findElements(quickTravel).isEmpty()) {
                wait.clickable(quickTravel);
                click(quickTravel);
                wait.clickable(next);
                return;
            }
            ScrollUtils.swipeLeftInside(scrollView);
        }

        throw new AssertionError("Quick Travel bundle not found after swiping");
    }

    @Override
    public void choosePackAndSave() {
        waitForPage();

        for (int i = 0; i < 8; i++) {
            if (!driver.findElements(packAndSave).isEmpty()) {
                wait.clickable(packAndSave);
                click(packAndSave);
                wait.clickable(next);
                return;
            }
            ScrollUtils.swipeLeftInside(scrollView);
        }

        throw new AssertionError("Pack & Save bundle not found after swiping");
    }

    @Override
    public void tapNext() {
        click(next);
        // Handle optional price change popup
        new PriceChangeDialog().acceptIfPresent();
    }
}
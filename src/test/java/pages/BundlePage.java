package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import utils.ScrollUtils;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BundlePage extends BasePage {

    private final By title = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Please select your bundle\")");
    private final By quickTravel = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Quick Travel\")");
    private final By packAndSave = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Pack & Save\")");
    private final By next = AppiumBy.androidUIAutomator("new UiSelector().text(\"NEXT\")");
    private final By ScrollView = AppiumBy.className("android.widget.ScrollView");

    public boolean isDisplayed() {
        return !driver.findElements(title).isEmpty();
    }
    public void waitForPage() {
        wait.visible(title);
    }
    public void chooseQuickTravel() {
        waitForPage();

        for (int i = 0; i < 5; i++) {
            if (!driver.findElements(quickTravel).isEmpty()) {
                wait.clickable(quickTravel);
                click(quickTravel);
                wait.clickable(next);
                return;
            }
            ScrollUtils.swipeLeftInside(ScrollView);
        }
        throw new AssertionError("Quick Travel bundle not found after swiping");
    }
    public void choosePackAndSave() {
        waitForPage();

        for (int i = 0; i < 8; i++) {
            if (!driver.findElements(packAndSave).isEmpty()) {
                wait.clickable(packAndSave);
                click(packAndSave);
                wait.clickable(next);
                return;
            }
            ScrollUtils.swipeLeftInside(ScrollView);
        }
        throw new AssertionError("Pack & Save bundle not found after swiping");
    }

    public void tapNext() {
        click(next);
        // Handle optional price change popup
        new PriceChangeDialog().acceptIfPresent();
    }
}
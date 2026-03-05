package pages.android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.api.BillingDetailsPageActions;
import utils.ScrollUtils;
import utils.WaitUtils;

import java.time.Duration;

import models.BillingDetailsData;

public class BillingDetailsPageAndroid extends BasePage implements BillingDetailsPageActions {

    private final By title =
            AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Billing details\")");

    private final By country =
            AppiumBy.id("com.wizzair.WizzAirApp:id/editBillingAddress_country_card");

    private final By address =
            AppiumBy.id("com.wizzair.WizzAirApp:id/editBillingAddress_address_textInputEditText");

    private final By city =
            AppiumBy.id("com.wizzair.WizzAirApp:id/editBillingAddress_city_textInputEditText");

    private final By postcode =
            AppiumBy.id("com.wizzair.WizzAirApp:id/editBillingAddress_postcode_textInputEditText");

    private final By saveBillingDetails =
            AppiumBy.id("com.wizzair.WizzAirApp:id/editBillingAddress_save");

    private final By scrollView =
            AppiumBy.className("android.widget.ScrollView");

    @Override
    public boolean isDisplayedQuick() {
        try {
            return new WaitUtils(driver, Duration.ofSeconds(2)).isVisible(title, Duration.ofSeconds(2));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void waitForPage() {
        wait.visible(title);
    }

    @Override
    public void fillAndSaveIfNeeded(BillingDetailsData data) {
        if (!isDisplayedQuick()) return;
        fillAndSave(data);
    }

    @Override
    public void fillAndSave(BillingDetailsData data) {
        waitForPage();

        selectCountry(data.country);

        clearAndType(address, data.address);
        clearAndType(city, data.city);
        clearAndType(postcode, data.postcode);
        dismissKeyboardIfPresent();
        new WaitUtils(driver, Duration.ofSeconds(25)).clickable(saveBillingDetails);
        click(saveBillingDetails);

    }

    private void selectCountry(String countryName) {
        click(country);

        By countryOption =
                AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + escape(countryName) + "\")");

        // Scroll until we see the country and click it
        for (int i = 0; i < 12; i++) {
            if (!driver.findElements(countryOption).isEmpty()) {
                click(countryOption);
                return;
            }
            ScrollUtils.swipeUpInside(scrollView);
        }

        throw new AssertionError("Country '" + countryName + "' not found after scrolling");
    }
    private void dismissKeyboardIfPresent() {
        try {
            if (driver instanceof io.appium.java_client.android.AndroidDriver androidDriver) {
                androidDriver.hideKeyboard();
                return;
            }
        } catch (Exception ignored) {
        }

        try {
            driver.navigate().back();
        } catch (Exception ignored) {
        }
    }

    private String escape(String s) {
        // for building UiSelector strings safely
        return s.replace("\"", "\\\"");
    }
}
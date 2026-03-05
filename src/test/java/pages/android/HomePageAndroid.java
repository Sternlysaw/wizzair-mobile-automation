package pages.android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import pages.BasePage;
import pages.api.HomePageActions;
import utils.DateUtils;
import utils.ScrollUtils;

import java.time.LocalDate;

public class HomePageAndroid extends BasePage implements HomePageActions {

    private final By homeSearchSection =
            AppiumBy.id("com.wizzair.WizzAirApp:id/home_searchSection");

    private final By searchFlightsButton =
            AppiumBy.id("com.wizzair.WizzAirApp:id/home_search_searchButton");

    private final By scrollView = AppiumBy.className("android.widget.ScrollView");

    private final By leavingFromField =
            AppiumBy.id("com.wizzair.WizzAirApp:id/home_search_leavingStation");

    private final By goingToField =
            AppiumBy.id("com.wizzair.WizzAirApp:id/home_search_arrivalStation");

    private final By datesOpen =
            AppiumBy.id("com.wizzair.WizzAirApp:id/home_searchDates_inner");

    private final By datesOk =
            AppiumBy.id("com.wizzair.WizzAirApp:id/btn_select_date");

    private final By eindhoven =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Eindhoven\")");

    private final By budapest =
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Budapest\")");

    @Override
    public boolean isDisplayed() {
        try {
            $(homeSearchSection);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Override
    public void tapSearchFlights() {
        for (int i = 0; i < 5; i++) {
            if (!driver.findElements(searchFlightsButton).isEmpty()) {
                click(searchFlightsButton);
                return;
            }
            ScrollUtils.swipeUpInside(scrollView);
        }
        throw new AssertionError("Search flight button not found after swiping");
    }

    @Override
    public void setLeavingFrom() {
        click(leavingFromField);
        click(eindhoven);
    }

    @Override
    public void setGoingTo() {
        click(goingToField);
        click(budapest);
    }

    @Override
    public void setDates(LocalDate depart, LocalDate ret) {
        click(datesOpen);

        // Tap departure date
        driver.findElement(AppiumBy.accessibilityId("date-" + depart)).click();

        // Tap return date
        driver.findElement(AppiumBy.accessibilityId("date-" + ret)).click();

        click(datesOk);
    }

    @Override
    public void setNextFridayToNextWeekFriday() {
        LocalDate depart = DateUtils.nextOrSameFriday();
        LocalDate ret = DateUtils.nextWeekFriday(depart);
        setDates(depart, ret);
    }
}
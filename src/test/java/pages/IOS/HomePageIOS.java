package pages.IOS;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import pages.BasePage;
import pages.api.HomePageActions;
import utils.DateUtils;

import java.time.LocalDate;

public class HomePageIOS extends BasePage implements HomePageActions {

    // TODO: Replace these with actual iOS locators (accessibility ids / predicates)
    private final By homeSearchSection = AppiumBy.accessibilityId("home_searchSection");
    private final By searchFlightsButton = AppiumBy.accessibilityId("home_search_searchButton");
    private final By leavingFromField = AppiumBy.accessibilityId("home_search_leavingStation");
    private final By goingToField = AppiumBy.accessibilityId("home_search_arrivalStation");
    private final By datesOpen = AppiumBy.accessibilityId("home_searchDates_inner");
    private final By datesOk = AppiumBy.accessibilityId("btn_select_date");

    // TODO: Replace with proper selectors (could be text search or accessibility ids)
    private final By eindhoven = AppiumBy.iOSNsPredicateString("label == 'Eindhoven'");
    private final By budapest = AppiumBy.iOSNsPredicateString("label == 'Budapest'");

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
        click(searchFlightsButton);
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

        // You might need a different selector strategy on iOS depending on calendar widget.
        driver.findElement(AppiumBy.accessibilityId("date-" + depart)).click();
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
package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import utils.DateUtils;
import utils.ScrollUtils;
import java.time.LocalDate;

public class HomePage extends BasePage {

    private final By homeSearchSection =
            AppiumBy.id("com.wizzair.WizzAirApp:id/home_searchSection");
    private final By searchFlightsButton =
            AppiumBy.id("com.wizzair.WizzAirApp:id/home_search_searchButton");
    private final By homeScrollView = AppiumBy.className("android.widget.ScrollView");
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
    public boolean isDisplayed() {
        try {
            $(homeSearchSection); // uses wait.visible under the hood
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    public void tapSearchFlights() {
        ScrollUtils.swipeUpInside(homeScrollView);
        tapCenter(searchFlightsButton);
    }

    public void setLeavingFrom() {
        click(leavingFromField);
        click(eindhoven);
    }

    public void setGoingTo() {
        click(goingToField);
        click(budapest);
    }
    public void setDates(LocalDate depart, LocalDate ret) {

        click(datesOpen);

        // Tap departure date
        driver.findElement(AppiumBy.accessibilityId("date-" + depart)).click();

        // Tap return date
        driver.findElement(AppiumBy.accessibilityId("date-" + ret)).click();

        click(datesOk);
    }
    public void setNextFridayToNextWeekFriday() {
        LocalDate depart = DateUtils.nextOrSameFriday();
        LocalDate ret = DateUtils.nextWeekFriday(depart);
        setDates(depart, ret);
    }
}
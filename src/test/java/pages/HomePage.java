package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import utils.ScrollUtils;
public class HomePage extends BasePage {

    private final By homeSearchSection =
            AppiumBy.id("com.wizzair.WizzAirApp:id/home_searchSection");
    private final By searchFlightsButton =
            AppiumBy.id("com.wizzair.WizzAirApp:id/home_search_searchButton");
    private final By homeScrollView = AppiumBy.className("android.widget.ScrollView");

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
}
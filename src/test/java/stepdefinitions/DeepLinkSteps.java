package stepdefinitions;

import core.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.Pages;
import pages.api.SelectFlightPageActions;
import utils.DeepLinkUtils;
import utils.PermissionHandler;

public class DeepLinkSteps {

    private SelectFlightPageActions selectFlight() {
        return Pages.selectFlight();
    }

    @When("I open the select flight deep link")
    public void i_open_the_select_flight_deep_link() {
        DeepLinkUtils.open(ConfigReader.get("deepLinkSelectFlight"));
    }

    @When("I open the select flight deep link on first launch")
    public void i_open_the_select_flight_deep_link_on_first_launch() {
        PermissionHandler.handleAndroidFirstLaunchDialogs();
        DeepLinkUtils.open(ConfigReader.get("deepLinkSelectFlight"));
        PermissionHandler.handleAndroidFirstLaunchDialogs();
    }

    @Then("the select flight screen should be displayed")
    public void the_select_flight_screen_should_be_displayed() {
        Assert.assertTrue(
                "Select flight screen was not displayed",
                selectFlight().waitUntilDisplayed()
        );
    }

    @Then("the select flight screen should match the deep link")
    public void the_select_flight_screen_should_match_the_deep_link() {
        String deepLink = ConfigReader.get("deepLinkSelectFlight");

        Assert.assertTrue(
                "Select flight screen did not match route/date from deep link: " + deepLink,
                selectFlight().matchesDeepLink(deepLink)
        );
    }
}
package stepdefinitions;

import core.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Android.SelectFlightPage;
import utils.DeepLinkUtils;

public class DeepLinkSteps {
    @When("I open the select flight deep link")
    public void i_open_the_select_flight_deep_link() {
        DeepLinkUtils.open(ConfigReader.get("deepLinkSelectFlight"));
    }

    @Then("the select flight screen should be displayed")
    public void the_select_flight_screen_should_be_displayed() {
        SelectFlightPage page = new SelectFlightPage();
        org.junit.Assert.assertTrue(
                "Select flight screen was not displayed",
                page.waitUntilDisplayed()
        );
    }
}
package stepdefinitions;

import core.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.Pages;
import pages.api.SelectFlightPageActions;
import utils.DeepLinkUtils;

public class DeepLinkSteps {

    private SelectFlightPageActions selectFlight() {
        return Pages.selectFlight();
    }

    @When("I open the select flight deep link")
    public void i_open_the_select_flight_deep_link() {
        DeepLinkUtils.open(ConfigReader.get("deepLinkSelectFlight"));
    }

    @Then("the select flight screen should be displayed")
    public void the_select_flight_screen_should_be_displayed() {
        Assert.assertTrue(
                "Select flight screen was not displayed",
                selectFlight().waitUntilDisplayed()
        );
    }
}
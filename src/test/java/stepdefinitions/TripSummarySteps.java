package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.Android.TripSummaryPage;

public class TripSummarySteps {

    TripSummaryPage tripSummaryPage = new TripSummaryPage();
    @When("I continue from summary")
    public void i_continue_from_summary() {
        TripSummaryPage.tapNext();
    }
    @Then("the trip summary screen should be displayed")
    public void the_trip_summary_screen_should_be_displayed() {
        Assert.assertTrue(
                "Trip summary screen was not displayed",
                tripSummaryPage.isDisplayed()
        );
    }
}
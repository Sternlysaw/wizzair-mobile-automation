package stepdefinitions;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.TripSummaryPage;

public class TripSummarySteps {

    TripSummaryPage tripSummaryPage = new TripSummaryPage();

    @Then("the trip summary screen should be displayed")
    public void the_trip_summary_screen_should_be_displayed() {
        Assert.assertTrue(
                "Trip summary screen was not displayed",
                tripSummaryPage.isDisplayed()
        );
    }
}
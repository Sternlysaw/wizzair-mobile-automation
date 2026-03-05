package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.Android.SelectFlightPage;

public class SelectFlightSteps {
    private String specificDepartureTime;

    @Then("at least one flight result is shown")
    public void at_least_one_flight_result_is_shown() {
        SelectFlightPage page = new SelectFlightPage();
        Assert.assertTrue("No flight results were shown", page.getVisibleFlightCount() > 0);
    }

    @Then("I store a specific flight from the results")
    public void i_store_a_specific_flight_from_the_results() {
        SelectFlightPage page = new SelectFlightPage();
        specificDepartureTime = page.firstDepartureTime();
        Assert.assertNotNull("Specific flight time should not be null", specificDepartureTime);
    }

    @Then("the specific flight should appear in the list")
    public void the_specific_flight_should_appear_in_the_list() {
        SelectFlightPage page = new SelectFlightPage();
        Assert.assertTrue(
                "Specific flight '" + specificDepartureTime + "' was not visible",
                page.isDepartureTimeVisible(specificDepartureTime)
        );
    }

    @Then("infinite scroll should load more results or reach the end")
    public void infinite_scroll_should_load_more_results_or_reach_the_end() {
        SelectFlightPage page = new SelectFlightPage();
        Assert.assertTrue(
                "Scrolling neither loaded new items nor reached a stable end state",
                page.infiniteScrollLoadsMoreOrStops(6)
        );
    }
    @When("I select the first available flight")
    public void i_select_the_first_available_flight() {
        new SelectFlightPage().selectFirstFlight();
    }
}
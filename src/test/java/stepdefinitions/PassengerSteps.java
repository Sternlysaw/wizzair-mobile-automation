package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Android.PassengersPage;

public class PassengerSteps {
    private final PassengersPage passengersPage = new PassengersPage();
    @Then("I am on the passenger screen")
    public void i_am_on_the_passenger_screen() {
        passengersPage.waitForPage();
    }
    @When("I click no special assistance")
    public void i_click_no_special_assistance() {
        passengersPage.tapNoAssistance();
    }
    @When("I click next on the passenger screen")
    public void i_click_next_on_the_passenger_screen() {
        passengersPage.tapNext();
    }
}

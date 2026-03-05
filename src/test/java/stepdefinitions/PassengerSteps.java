package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.Pages;
import pages.api.PassengersPageActions;

public class PassengerSteps {

    private PassengersPageActions passengers() {
        return Pages.passengers();
    }

    @Then("I am on the passenger screen")
    public void i_am_on_the_passenger_screen() {
        Assert.assertTrue("Passenger screen was not displayed", passengers().isDisplayed());
    }

    @When("I click no special assistance")
    public void i_click_no_special_assistance() {
        passengers().tapNoSpecialAssistance();
    }

    @When("I click next on the passenger screen")
    public void i_click_next_on_the_passenger_screen() {
        passengers().tapNext();
    }
}
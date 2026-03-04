package stepdefinitions;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.HomePage;
import io.cucumber.java.en.When;
public class HomeSteps {

    private final HomePage homePage = new HomePage();
    @When("I set leaving from to")
    public void i_set_leaving_from() {
        new HomePage().setLeavingFrom();
    }

    @When("I set going to to")
    public void i_set_going_to() {
        new HomePage().setGoingTo();
    }
    @When("I search for flights")
    public void i_search_for_flights() {
        HomePage homePage = new HomePage();
        homePage.tapSearchFlights();
    }
    @When("I set travel dates from next Friday to next week Friday")
    public void i_set_travel_dates_next_fridays() {
        new HomePage().setNextFridayToNextWeekFriday();
    }
    @Then("I should be on the home screen")
    public void i_should_be_on_the_home_screen() {
        Assert.assertTrue("Home screen was not displayed", homePage.isDisplayed());
    }
}
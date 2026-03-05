package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Pages;
import pages.api.SeatsPageActions;

public class SeatsSteps {

    private SeatsPageActions seats() {
        return Pages.seats();
    }

    @Then("I am on the seats page")
    public void i_am_on_the_seats_page() {
        seats().waitForPage();
    }

    @When("I choose seat later")
    public void i_choose_seat_later() {
        seats().chooseSeatLater();
    }

    @When("I click next on the seats page")
    public void i_click_next_on_the_seats_page() {
        seats().tapNext();
    }
}
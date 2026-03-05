package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SeatsPage;

public class SeatsSteps {
    SeatsPage seatsPage = new SeatsPage();
    @Then("I am on the seats page")
    public void i_am_on_the_seats_page() {
        seatsPage.waitForPage();
    }
    @When("I choose seat later")
    public void i_choose_seat_later() {
        seatsPage.chooseSeatLater();
    }
    @When("I click next on the seats page")
    public void i_click_next_on_the_seats_page() {
        seatsPage.clickNext();
    }
}

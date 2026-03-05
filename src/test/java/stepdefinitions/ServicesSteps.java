package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Pages;
import pages.api.ServicesPageActions;

public class ServicesSteps {

    private ServicesPageActions services() {
        return Pages.services();
    }

    @Then("I am on the services page")
    public void i_am_on_the_services_page() {
        services().waitForPage();
    }

    @When("I click next in the services page")
    public void i_click_next_in_the_services_page() {
        services().tapNextStable();
    }
}
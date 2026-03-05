package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.android.ServicesPage;

public class ServicesSteps {
    ServicesPage servicesPage = new ServicesPage();
    @Then("I am on the services page")
    public void i_am_on_the_services_page() {
        servicesPage.waitForPage();
    }
    @When("I click next in the services page")
    public void i_click_next_in_the_services_page() {
        servicesPage.clickNext();
    }
}

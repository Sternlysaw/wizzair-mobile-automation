package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.android.InsurencesPage;

public class InsuranceSteps {
    InsurencesPage insurencesPage = new InsurencesPage();

    @Then("I am on the insurance page")
    public void I_am_on_the_insurance_page() {
        insurencesPage.waitForPage();
    }
    @When("I select travel insurance")
    public void I_select_travel_insurance() {
        insurencesPage.selectTravelInsurance();
    }
    @When("I click select on the insurance page")
    public void I_click_select_on_the_insurance_page() {
        insurencesPage.tapSelect();
    }
}

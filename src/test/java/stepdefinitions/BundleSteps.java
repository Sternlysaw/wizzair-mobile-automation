package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Pages;
import pages.api.BundlePageActions;

public class BundleSteps {

    private BundlePageActions bundle() {
        return Pages.bundle();
    }

    @Then("the bundle selection screen should be displayed")
    public void bundle_selection_screen_should_be_displayed() {
        bundle().waitForPage();
    }

    @When("I select the Quick Travel bundle")
    public void i_select_the_quick_travel_bundle() {
        bundle().chooseQuickTravel();
    }

    @When("I continue from bundle selection")
    public void i_continue_from_bundle_selection() {
        bundle().tapNext();
    }

    @When("I select the Pack & Save bundle")
    public void i_select_pack_and_save_bundle() {
        bundle().choosePackAndSave();
    }
}
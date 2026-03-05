package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Pages;
import pages.api.DeclarationPageActions;

public class DeclarationSteps {
    private DeclarationPageActions declaration(){
        return Pages.declaration();
    }
    @Then("I am on the declaration page")
    public void I_am_on_the_declaration_page() {
        declaration().waitForPage();
    }
    @When("I click confirm on the declaration page")
    public void I_click_confirm_on_the_declaration_page() {
        declaration().tapConfirmStable();
    }
}

package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DiscountPage;
import pages.HomePage;

public class DiscountSteps {
    private final DiscountPage discountPage = new DiscountPage();
    @When("I see want a discount page")
    public void i_see_want_a_discount_page() {
        discountPage.waitForPage();
    }
    @Then("I do not want a discount")
        public void i_do_not_want_a_discount(){
            discountPage.tapNoThankYou();
        }
}

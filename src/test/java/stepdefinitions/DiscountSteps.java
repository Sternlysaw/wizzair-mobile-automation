package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Pages;
import pages.api.DiscountPageActions;

public class DiscountSteps {

    private DiscountPageActions discount() {
        return Pages.discount();
    }

    @When("I see want a discount page")
    public void i_see_want_a_discount_page() {
        discount().waitForPage();
    }

    @When("I do not want a discount")
    public void i_do_not_want_a_discount() {
        discount().declineDiscount();
    }
}
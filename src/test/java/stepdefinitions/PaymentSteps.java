package stepdefinitions;

import io.cucumber.java.en.When;
import models.CardDetails;
import pages.Pages;
import pages.api.PaymentPageActions;

public class PaymentSteps {

    private PaymentPageActions payment() {
        return Pages.payment();
    }

    @When("I pay by card")
    public void i_pay_by_card() {
        CardDetails card = new CardDetails(
                "4111111111111111",
                "TEST USER",
                "12/30",
                "123"
        );

        payment().payByCard(card);
    }
}
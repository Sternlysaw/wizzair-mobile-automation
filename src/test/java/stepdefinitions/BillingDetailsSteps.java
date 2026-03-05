package stepdefinitions;

import io.cucumber.java.en.When;
import models.BillingDetailsData;
import pages.Pages;
import pages.api.BillingDetailsPageActions;

public class BillingDetailsSteps {

    private BillingDetailsPageActions billing() {
        return Pages.billingDetails();
    }

    @When("I fill billing details if required")
    public void i_fill_billing_details_if_required() {
        // You can parameterize these later via feature file or config
        BillingDetailsData data = new BillingDetailsData(
                "Belgium",
                "Tester street 12",
                "Brussels",
                "1000"
        );

        billing().fillAndSaveIfNeeded(data);
    }
}
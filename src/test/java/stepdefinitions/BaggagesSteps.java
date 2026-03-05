package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Android.BaggagesPage;

public class BaggagesSteps {
    private BaggagesPage baggagesPage = new BaggagesPage();
    @Then("I am on the Baggages screen")
    public void i_am_on_the_baggages_screen() {
        baggagesPage.waitForPage();
    }
    @When("I click small checked in baggage")
    public void i_click_small_checked_in_baggage() {
        baggagesPage.chooseSmallCheckedInBaggage();
    }
    @When("I scroll until I see sport equipment")
    public void i_scroll_until_i_see_sport_equipment() {
        baggagesPage.sportEquipmentIsVisible();
    }
    @When("I click two cabin bags and priority")
    public void i_click_two_cabin_bags_and_priority() {
        baggagesPage.clickTwoCabinBagsAndPriority();
    }
    @When("I click next on the Baggages page")
    public void i_click_next_on_the_baggages_page() {
        baggagesPage.clickNext();
    }
}

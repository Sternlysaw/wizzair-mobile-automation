package stepdefinitions;

import io.cucumber.java.en.When;
import utils.AppStateUtils;

public class AppStateSteps {

    @When("I send the app to the background for {int} seconds")
    public void i_send_the_app_to_the_background_for_seconds(Integer seconds) {
        AppStateUtils.sendAppToBackgroundForSeconds(seconds.longValue());
    }
}
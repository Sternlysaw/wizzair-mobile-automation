package stepdefinitions;

import core.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class SmokeSteps {

    @Given("the app is launched")
    public void the_app_is_launched() {
        Assert.assertNotNull("Driver should be started by Hooks", DriverManager.getDriver());
    }

    @Then("the driver session should be active")
    public void the_driver_session_should_be_active() {
        AppiumDriver driver = DriverManager.getDriver();
        Assert.assertNotNull("Session ID should not be null", driver.getSessionId());
    }
}
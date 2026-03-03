package stepdefinitions;

import core.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class SmokeSteps {

    @Given("the appium driver is started")
    public void the_appium_driver_is_started() {
        DriverManager.initDriver();
    }

    @Then("the driver session should be active")
    public void the_driver_session_should_be_active() {
        AppiumDriver driver = DriverManager.getDriver();
        Assert.assertNotNull("Driver should not be null", driver);
        Assert.assertNotNull("Session ID should not be null", driver.getSessionId());
    }
}
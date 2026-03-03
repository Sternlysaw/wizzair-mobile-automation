package stepdefinitions;

import core.ConfigReader;
import core.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.DeepLinkUtils;
import io.appium.java_client.android.AndroidDriver;


public class DeepLinkSteps {

    @When("I open the home deep link")
    public void i_open_the_home_deep_link() {
        DeepLinkUtils.open(ConfigReader.get("deepLinkHome"));
    }

    @Then("the app should be opened")
    public void the_app_should_be_opened() {
        io.appium.java_client.android.AndroidDriver d =
            (io.appium.java_client.android.AndroidDriver) DriverManager.getDriver();

        org.junit.Assert.assertEquals("com.wizzair.WizzAirApp", d.getCurrentPackage());
        org.junit.Assert.assertNotNull("Session should exist", d.getSessionId());
    }
}
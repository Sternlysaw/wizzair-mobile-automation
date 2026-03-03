package stepdefinitions;

import core.ConfigReader;
import core.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.DeepLinkUtils;

public class DeepLinkSteps {

    @When("I open the home deep link")
    public void i_open_the_home_deep_link() {
        DeepLinkUtils.open(ConfigReader.get("deepLinkHome"));
    }

    @Then("the app should be opened")
    public void the_app_should_be_opened() {
        Assert.assertNotNull("Driver should exist", DriverManager.getDriver());
        Assert.assertNotNull("Session should exist", DriverManager.getDriver().getSessionId());
    }
}
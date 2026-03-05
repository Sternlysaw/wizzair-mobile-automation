package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions", "hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-ios.html",
                "json:target/cucumber-ios.json"
        },
        monochrome = true
)
public class IosRunner {
    static {
        System.setProperty("platform", "ios");
    }
}
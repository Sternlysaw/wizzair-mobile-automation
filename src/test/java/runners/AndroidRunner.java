package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions", "hooks"},
        plugin = {"pretty", "html:target/cucumber-android.html", "json:target/cucumber-android.json"},
        monochrome = true
)
public class AndroidRunner {
        static {
                System.setProperty("platform", "android");
        }
}
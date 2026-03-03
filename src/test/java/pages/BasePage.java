package pages;

import core.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

import java.time.Duration;

public abstract class BasePage {

    protected final AppiumDriver driver;
    protected final WaitUtils wait;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WaitUtils(driver, Duration.ofSeconds(15));
    }

    protected WebElement $(By locator) {
        return wait.visible(locator);
    }

    protected void click(By locator) {
        wait.clickable(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement el = wait.visible(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected String text(By locator) {
        return wait.visible(locator).getText();
    }
}
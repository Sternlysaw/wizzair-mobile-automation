package pages;

import core.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import java.time.Duration;
import java.util.Collections;

public abstract class BasePage {

    protected final AppiumDriver driver;
    protected static WaitUtils wait = null;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WaitUtils(driver, Duration.ofSeconds(15));
    }

    protected WebElement $(By locator) {
        return wait.visible(locator);
    }

    protected static void click(By locator) {
        wait.clickable(locator).click();
    }

    protected void tapCenter(By locator) {
        WebElement el = wait.visible(locator);
        Rectangle r = el.getRect();
        int centerX = r.getX() + (r.getWidth() / 2);
        int centerY = r.getY() + (r.getHeight() / 2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 0);
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(tap));
    }

    protected void type(By locator, String text) {
        WebElement el = wait.visible(locator);
        el.sendKeys(text);
    }

    protected void clear(By locator) {
        WebElement el = wait.visible(locator);
        el.clear();
    }

    protected void clearAndType(By locator, String text) {
        WebElement el = wait.visible(locator);
        el.click();
        el.clear();
        el.sendKeys(text);
    }
}
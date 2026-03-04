package utils;

import core.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.interactions.Pause;

import java.time.Duration;
import java.util.Collections;
public class ScrollUtils {

    public static void swipeUpInside(By containerLocator) {
        AppiumDriver driver = DriverManager.getDriver();

        WebElement container = driver.findElement(containerLocator);
        Rectangle r = container.getRect();

        int startX = r.x + r.width / 2;
        int startY = r.y + (int)(r.height * 0.75);
        int endY   = r.y + (int)(r.height * 0.25);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }
    public static void swipeLeftInside(By containerLocator) {
        AppiumDriver driver = DriverManager.getDriver();

        WebElement container = driver.findElement(containerLocator);
        Rectangle r = container.getRect();

        int startY = r.y + r.height / 2;

        int startX = r.x + (int)(r.width * 0.75);
        int endX   = r.x + (int)(r.width * 0.25);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, startY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    private ScrollUtils() {}

    private static String escapeQuotes(String s) {
        return s.replace("\"", "\\\"");
    }
}
package utils;

import core.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.interactions.Pause;

import java.time.Duration;
import java.util.Collections;
public class ScrollUtils {

    public static void swipeUpInside(By containerLocator) { // content goes DOWN
        swipeInside(containerLocator, 0.80, 0.20);
    }

    public static void swipeDownInside(By containerLocator) { // content goes UP
        swipeInside(containerLocator, 0.20, 0.80);
    }

    private static void swipeInside(By containerLocator, double startPct, double endPct) {
        AppiumDriver driver = DriverManager.getDriver();
        WebElement container = driver.findElement(containerLocator);
        Rectangle r = container.getRect();

        int x = r.getX() + r.getWidth() / 2;

        int startY = r.getY() + (int)(r.getHeight() * startPct);
        int endY   = r.getY() + (int)(r.getHeight() * endPct);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 0);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(new Pause(finger, Duration.ofMillis(250)));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(850), PointerInput.Origin.viewport(), x, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    private ScrollUtils() {}

    /**
     * Android-only: scroll in a scrollable container until text is visible.
     * Uses UiScrollable which is reliable for long lists.
     */
    public static WebElement androidScrollToText(String text) {
        AppiumDriver driver = DriverManager.getDriver();

        if (!(driver instanceof AndroidDriver)) {
            throw new IllegalStateException("androidScrollToText can only be used with AndroidDriver");
        }

        String uiAutomator = "new UiScrollable(new UiSelector().scrollable(true))"
                + ".scrollIntoView(new UiSelector().textContains(\"" + escapeQuotes(text) + "\"))";

        return driver.findElement(AppiumBy.androidUIAutomator(uiAutomator));
    }

    private static String escapeQuotes(String s) {
        return s.replace("\"", "\\\"");
    }
}
package utils;

import core.DriverManager;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Map;

public class AppStateUtils {

    private AppStateUtils() {}

    public static void sendAppToBackgroundForSeconds(long seconds) {

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        js.executeScript("mobile: backgroundApp",
                Map.of("seconds", seconds));
    }
}
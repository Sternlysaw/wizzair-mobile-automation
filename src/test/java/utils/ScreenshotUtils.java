package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {

    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    public static Path takeScreenshot(AppiumDriver driver, String scenarioName) {
        try {
            String safeName = scenarioName.replaceAll("[^a-zA-Z0-9-_\\.]", "_");
            String timestamp = LocalDateTime.now().format(FORMAT);

            Path screenshotsDir = Path.of("target", "screenshots");
            Files.createDirectories(screenshotsDir);

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path dest = screenshotsDir.resolve(safeName + "_" + timestamp + ".png");

            Files.copy(src.toPath(), dest);
            return dest;
        } catch (Exception e) {
            throw new RuntimeException("Failed to take screenshot", e);
        }
    }
}
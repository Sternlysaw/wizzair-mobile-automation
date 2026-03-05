package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {

    private ScreenshotUtils() {}

    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    public static final class ScreenshotResult {
        private final Path path;
        private final byte[] bytes;

        private ScreenshotResult(Path path, byte[] bytes) {
            this.path = path;
            this.bytes = bytes;
        }

        public Path getPath() {
            return path;
        }

        public byte[] getBytes() {
            return bytes;
        }
    }

    /**
     * Captures a screenshot as PNG bytes and saves it to target/screenshots.
     * Returns both the saved Path and the raw bytes (useful for Cucumber attach()).
     */
    public static ScreenshotResult capture(AppiumDriver driver, String scenarioName) {
        try {
            String safeName = (scenarioName == null ? "scenario" : scenarioName)
                    .replaceAll("[^a-zA-Z0-9-_\\.]", "_");

            String timestamp = LocalDateTime.now().format(FORMAT);

            Path screenshotsDir = Path.of("target", "screenshots");
            Files.createDirectories(screenshotsDir);

            byte[] pngBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            Path dest = screenshotsDir.resolve(safeName + "_" + timestamp + ".png");
            Files.write(dest, pngBytes);

            return new ScreenshotResult(dest, pngBytes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to take screenshot", e);
        }
    }

    /** Backwards-compatible: only returns the saved screenshot path. */
    public static Path takeScreenshot(AppiumDriver driver, String scenarioName) {
        return capture(driver, scenarioName).getPath();
    }

    /** Convenience: only returns screenshot bytes (PNG). */
    public static byte[] takeScreenshotBytes(AppiumDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            throw new RuntimeException("Failed to take screenshot bytes", e);
        }
    }
}
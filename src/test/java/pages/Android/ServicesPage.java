package pages.Android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import pages.BasePage;

public class ServicesPage extends BasePage {
    private final By title = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Services\")");
    private final By next = AppiumBy.androidUIAutomator("new UiSelector().text(\"NEXT\")");

    public void waitForPage() {
        wait.visible(title);
    }

    public void clickNext() {
        click(next);
    }
}

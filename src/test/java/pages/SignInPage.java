package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SignInPage extends BasePage {

    private final By title = AppiumBy.androidUIAutomator("new UiSelector().text(\"SIGN IN\")");
    private final By emailInput = By.id("com.wizzair.WizzAirApp:id/sign_in_email");

    // password id is not confirmed; keep robust
    private final By passwordInputById = By.id("com.wizzair.WizzAirApp:id/sign_in_password");
    private final By passwordInputByHint = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.widget.EditText\").textContains(\"Password\")"
    );

    private final By signInButton = By.id("com.wizzair.WizzAirApp:id/sign_in_btn");

    public boolean isDisplayed() {
        return !driver.findElements(title).isEmpty();
    }

    public void waitForPage() {
        wait.visible(title);
        wait.visible(emailInput);
        wait.visible(signInButton);
    }

    public void typeEmail(String email) {
        clearAndType(emailInput, email);
    }

    private By resolvePasswordLocator() {
        if (!driver.findElements(passwordInputById).isEmpty()) return passwordInputById;
        if (!driver.findElements(passwordInputByHint).isEmpty()) return passwordInputByHint;
        return AppiumBy.className("android.widget.EditText");
    }

    public void typePassword(String password) {
        By pwd = resolvePasswordLocator();

        if (pwd.equals(AppiumBy.className("android.widget.EditText"))) {
            List<WebElement> edits = driver.findElements(pwd);
            if (edits.size() < 2) throw new AssertionError("Could not find password field on Sign In screen");

            WebElement passwordEl = edits.get(1);
            passwordEl.click();
            passwordEl.clear();
            passwordEl.sendKeys(password);
        } else {
            clearAndType(pwd, password);
        }
    }

    public void tapSignIn() {
        click(signInButton);
    }

    public void login(String email, String password) {
        waitForPage();
        typeEmail(email);
        typePassword(password);
        tapSignIn();
    }
}
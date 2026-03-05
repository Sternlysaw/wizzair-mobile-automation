package pages.android;

import org.openqa.selenium.By;
import pages.BasePage;

import java.time.Duration;

public class SignInPage extends BasePage {

    private final By title = By.id("com.wizzair.WizzAirApp:id/toolbar"); // or keep text("SIGN IN") if you prefer
    private final By email = By.id("com.wizzair.WizzAirApp:id/sign_in_email");
    private final By password = By.id("com.wizzair.WizzAirApp:id/sign_in_password");
    private final By signInBtn = By.id("com.wizzair.WizzAirApp:id/sign_in_btn");


    public void waitForPage() {
        wait.visible(email);
        wait.visible(password);
        wait.visible(signInBtn);
    }

    public boolean isDisplayedQuick() {
        // short check so we don't wait 15s when it's not present
        try {
            return wait.isVisible(signInBtn, Duration.ofSeconds(2));
        } catch (Exception e) {
            return false;
        }
    }

    public void loginIfDisplayed(String email, String password) {
        if (!isDisplayedQuick()) {
            return; // already logged in -> no sign in screen
        }
        login(email, password);
        tapSignIn();
    }

    public void typeEmail(String value) {
        clearAndType(email, value);
    }

    public void typePassword(String value) {
        clearAndType(password, value);
    }

    public void tapSignIn() {
        click(signInBtn);
    }

    public void login(String emailValue, String passwordValue) {
        waitForPage();
        typeEmail(emailValue);
        typePassword(passwordValue);
        tapSignIn();
    }
}
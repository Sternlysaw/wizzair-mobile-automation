package pages.android;

import org.openqa.selenium.By;
import pages.BasePage;
import pages.Pages;
import pages.api.SignInPageActions;

import java.time.Duration;

public class SignInPageAndroid extends BasePage implements SignInPageActions {

    // SAME locator values, just cleaner variable names
    private final By toolbarTitle =
            By.id("com.wizzair.WizzAirApp:id/toolbar");

    private final By emailField =
            By.id("com.wizzair.WizzAirApp:id/sign_in_email");

    private final By passwordField =
            By.id("com.wizzair.WizzAirApp:id/sign_in_password");

    private final By signInButton =
            By.id("com.wizzair.WizzAirApp:id/sign_in_btn");

    @Override
    public void waitForPage() {
        wait.visible(emailField);
        wait.visible(passwordField);
        wait.visible(signInButton);
    }

    @Override
    public boolean isDisplayedQuick() {
        try {
            return wait.isVisible(signInButton, Duration.ofSeconds(2));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void loginIfDisplayed(String email, String password) {
        if (!isDisplayedQuick()) {
            return;
        }

        login(email, password);
        tapSignIn();
    }

    @Override
    public void typeEmail(String value) {
        clearAndType(emailField, value);
    }

    @Override
    public void typePassword(String value) {
        clearAndType(passwordField, value);
    }

    @Override
    public void tapSignIn() {
        driver.findElement(signInButton).click();
        // wait for next page instead of waiting for old button
        Pages.discount().waitForPage();
    }

    @Override
    public void login(String emailValue, String passwordValue) {
        waitForPage();
        typeEmail(emailValue);
        typePassword(passwordValue);
        tapSignIn();
    }
}
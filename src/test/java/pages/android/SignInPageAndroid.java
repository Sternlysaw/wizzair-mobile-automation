package pages.android;

import org.openqa.selenium.By;
import pages.BasePage;
import pages.Pages;
import pages.api.SignInPageActions;

import java.time.Duration;

public class SignInPageAndroid extends BasePage implements SignInPageActions {

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

        // Fill credentials
        waitForPage();
        typeEmail(email);
        typePassword(password);

        // Click sign in WITHOUT waiting for clickable afterwards
        driver.findElement(signInButton).click();

        // Now wait for progress: either Sign In disappears OR Discount appears
        waitForPostLogin();
    }
    private void waitForPostLogin() {

        long end = System.currentTimeMillis() + 25000; // 25s

        while (System.currentTimeMillis() < end) {
            try {
                // If sign in screen is gone, we're good
                if (driver.findElements(signInButton).isEmpty()) {
                    return;
                }

                // If discount screen appears, we're good (do not throw if not converted yet)
                try {
                    if (!driver.findElements(
                            io.appium.java_client.AppiumBy.androidUIAutomator(
                                    "new UiSelector().textContains(\"Join WIZZ Discount Club\")"
                            )
                    ).isEmpty()) {
                        return;
                    }
                } catch (Exception ignored) { }

                Thread.sleep(250);
            } catch (Exception ignored) {
                // transient errors during transition
            }
        }

        // If we reach here, we didn't observe a transition
        throw new org.openqa.selenium.TimeoutException(
                "Login clicked but app did not transition away from Sign In within timeout"
        );
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
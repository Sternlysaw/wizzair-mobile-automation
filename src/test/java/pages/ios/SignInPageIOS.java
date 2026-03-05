package pages.ios;

import pages.BasePage;
import pages.api.SignInPageActions;

public class SignInPageIOS extends BasePage implements SignInPageActions {

    private UnsupportedOperationException notReady() {
        return new UnsupportedOperationException(
                "SignInPageIOS not implemented yet"
        );
    }

    @Override
    public void waitForPage() {
        throw notReady();
    }

    @Override
    public boolean isDisplayedQuick() {
        throw notReady();
    }

    @Override
    public void loginIfDisplayed(String email, String password) {
        throw notReady();
    }

    @Override
    public void typeEmail(String value) {
        throw notReady();
    }

    @Override
    public void typePassword(String value) {
        throw notReady();
    }

    @Override
    public void tapSignIn() {
        throw notReady();
    }

    @Override
    public void login(String emailValue, String passwordValue) {
        throw notReady();
    }
}
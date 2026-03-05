package pages.api;

public interface SignInPageActions {

    void waitForPage();

    boolean isDisplayedQuick();

    void loginIfDisplayed(String email, String password);

    void typeEmail(String value);

    void typePassword(String value);

    void tapSignIn();

    void login(String emailValue, String passwordValue);
}
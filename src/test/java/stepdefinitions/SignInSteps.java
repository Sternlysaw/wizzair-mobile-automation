package stepdefinitions;

import io.cucumber.java.en.When;
import pages.android.SignInPage;

public class SignInSteps {

    private final SignInPage signInPage = new SignInPage();

    @When("I sign in if prompted with email {string} and password {string}")
    public void i_sign_in_if_prompted(String email, String password) {
        signInPage.loginIfDisplayed(email, password);
    }
}
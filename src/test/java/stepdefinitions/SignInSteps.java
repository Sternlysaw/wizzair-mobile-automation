package stepdefinitions;

import io.cucumber.java.en.When;
import pages.Pages;
import pages.api.SignInPageActions;

public class SignInSteps {

    private SignInPageActions signIn() {
        return Pages.signIn();
    }

    @When("I sign in if prompted with email {string} and password {string}")
    public void i_sign_in_if_prompted(String email, String password) {
        signIn().loginIfDisplayed(email, password);
    }
}
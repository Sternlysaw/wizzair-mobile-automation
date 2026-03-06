package pages.android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.api.PaymentPageActions;
import utils.GoogleSaveCardDialog;
import utils.ScrollUtils;
import utils.WaitUtils;

import java.time.Duration;

import models.CardDetails;

public class PaymentPageAndroid extends BasePage implements PaymentPageActions {
    public final By title =
            AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Payment\")");
    public final By paymentOptionsTitle =
            AppiumBy.id("com.wizzair.WizzAirApp:id/paymentFragment_paymentOptions_title");
    public final By payWithCard =
            AppiumBy.id("com.wizzair.WizzAirApp:id/paymentFragment_paymentOptions_creditCard");
    public final By cardNumber =
            AppiumBy.id("com.wizzair.WizzAirApp:id/creditCard_cardNumber_textInputEditText");
    public final By cardHoldersName =
            AppiumBy.id("com.wizzair.WizzAirApp:id/creditCard_cardHolderName_textInputEditText");
    public final By cardExpiryDate =
            AppiumBy.id("com.wizzair.WizzAirApp:id/creditCard_expiry_textInputEditText");
    public final By cardCvc =
            AppiumBy.id("com.wizzair.WizzAirApp:id/creditCard_cvc_textInputEditText");
    public final By cardDone =
            AppiumBy.id("com.wizzair.WizzAirApp:id/creditCard_done");
    public final By iHaveReadTermsAndConditions =
            AppiumBy.id("com.wizzair.WizzAirApp:id/paymentFragment_ppgccOutgoing_checkBox");
    public final By backArrow =
            AppiumBy.accessibilityId("Navigate up");
    public final By paymentInProgressPopupTitle =
            AppiumBy.id("com.wizzair.WizzAirApp:id/alertTitle");
    public final By abandonButton =
            AppiumBy.id("android:id/button2");
    public final By payNow =
            AppiumBy.id("com.wizzair.WizzAirApp:id/paymentFragment_payButton");
    private final By scrollView =
            AppiumBy.className("android.widget.ScrollView");

    @Override
    public void waitForPage() {
        // Payment can load progressively, so wait for both title and options title
        wait.visible(title);
        wait.visible(paymentOptionsTitle);
    }

    @Override
    public void payByCard(CardDetails card) {
        waitForPage();
        selectCardOption();
        fillCardPopup(card);
        backOnPaymentPage();
        abandonPaymentFlow();
    }
    private void abandonPaymentFlow() {
        dismissKeyboardIfPresent();

        new WaitUtils(driver, Duration.ofSeconds(25)).clickable(backArrow);
        click(backArrow);
        new WaitUtils(driver, Duration.ofSeconds(35)).visible(paymentInProgressPopupTitle);
        new WaitUtils(driver, Duration.ofSeconds(25)).clickable(abandonButton);
        click(abandonButton);
    }

    private void selectCardOption() {
        new WaitUtils(driver, Duration.ofSeconds(25)).clickable(payWithCard);
        click(payWithCard);
    }

    private void fillCardPopup(CardDetails card) {
        // Wait for popup input fields
        new WaitUtils(driver, Duration.ofSeconds(25)).visible(cardNumber);
        clearAndType(cardNumber, card.number);

        clearAndType(cardHoldersName, card.holderName);
        clearAndType(cardExpiryDate, card.expiry);
        clearAndType(cardCvc, card.cvc);

        dismissKeyboardIfPresent();

        new WaitUtils(driver, Duration.ofSeconds(25)).clickable(cardDone);
        click(cardDone);
        GoogleSaveCardDialog.dismissIfPresent(driver);
    }

    private void backOnPaymentPage() {
        // After Done, it can take a moment to return/refresh the payment page
        new WaitUtils(driver, Duration.ofSeconds(25)).visible(paymentOptionsTitle);
    }
    /*
    Note the acceptTermsAndPay method below can be used as well but then the seat is reserved for 15-30 minutes
     */
    private void acceptTermsAndPay() {
        for (int i = 0; i < 8; i++) {
            if (!driver.findElements(iHaveReadTermsAndConditions).isEmpty()) {
                new WaitUtils(driver, Duration.ofSeconds(20)).clickable(iHaveReadTermsAndConditions);
                click(iHaveReadTermsAndConditions);
                break;
            }
            ScrollUtils.swipeUpInside(scrollView);
        }

        for (int i = 0; i < 8; i++) {
            if (!driver.findElements(payNow).isEmpty()) {
                dismissKeyboardIfPresent();
                new WaitUtils(driver, Duration.ofSeconds(35)).clickable(payNow);
                click(payNow);
                return;
            }
            ScrollUtils.swipeUpInside(scrollView);
        }

        throw new AssertionError("Pay Now button not found after scrolling");
    }

    private void dismissKeyboardIfPresent() {
        try {
            if (driver instanceof io.appium.java_client.android.AndroidDriver androidDriver) {
                androidDriver.hideKeyboard();
                return;
            }
        } catch (Exception ignored) {}

        try {
            driver.navigate().back();
        } catch (Exception ignored) {}
    }


}

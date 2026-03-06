package pages.ios;

import models.CardDetails;
import pages.BasePage;
import pages.api.PaymentPageActions;

public class PaymentPageIOS extends BasePage implements PaymentPageActions {

    private UnsupportedOperationException notReady() {
        return new UnsupportedOperationException(
                "PaymentPageIOS not implemented yet. Add iOS locators in pages/ios/PaymentPageIOS.java"
        );
    }

    @Override
    public void waitForPage() { throw notReady(); }

    @Override
    public void payByCard(CardDetails card) { throw notReady(); }
}
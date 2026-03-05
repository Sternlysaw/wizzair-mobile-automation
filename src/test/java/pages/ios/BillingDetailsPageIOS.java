package pages.ios;

import models.BillingDetailsData;
import pages.BasePage;
import pages.api.BillingDetailsPageActions;

public class BillingDetailsPageIOS extends BasePage implements BillingDetailsPageActions {

    private UnsupportedOperationException notReady() {
        return new UnsupportedOperationException(
                "BillingDetailsPageIOS not implemented yet. Add iOS locators in pages/ios/BillingDetailsPageIOS.java"
        );
    }

    @Override
    public boolean isDisplayedQuick() { throw notReady(); }

    @Override
    public void waitForPage() { throw notReady(); }

    @Override
    public void fillAndSave(BillingDetailsData data) { throw notReady(); }

    @Override
    public void fillAndSaveIfNeeded(BillingDetailsData data) { throw notReady(); }
}
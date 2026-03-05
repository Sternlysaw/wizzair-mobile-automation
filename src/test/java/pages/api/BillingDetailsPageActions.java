package pages.api;

import models.BillingDetailsData;

public interface BillingDetailsPageActions {
    boolean isDisplayedQuick();
    void waitForPage();

    void fillAndSave(BillingDetailsData data);

    /** Does nothing if Billing Details screen is not shown */
    void fillAndSaveIfNeeded(BillingDetailsData data);
}
package pages.ios;

import pages.BasePage;
import pages.api.TripSummaryPageActions;

public class TripSummaryPageIOS extends BasePage implements TripSummaryPageActions {

    private UnsupportedOperationException notReady() {
        return new UnsupportedOperationException(
                "TripSummaryPageIOS not implemented yet. Add iOS locators in pages/ios/TripSummaryPageIOS.java"
        );
    }

    @Override
    public boolean isDisplayed() {
        throw notReady();
    }

    @Override
    public void tapNext() {
        throw notReady();
    }
}
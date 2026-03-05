package pages.IOS;

import pages.BasePage;
import pages.api.SelectFlightPageActions;

public class SelectFlightPageIOS extends BasePage implements SelectFlightPageActions {

    private UnsupportedOperationException notReady() {
        return new UnsupportedOperationException(
                "SelectFlightPageIOS is not implemented yet. Add iOS locators in pages/ios/SelectFlightPageIOS.java"
        );
    }

    @Override
    public boolean waitUntilDisplayed() {
        throw notReady();
    }

    @Override
    public int getVisibleFlightCount() {
        throw notReady();
    }

    @Override
    public String firstDepartureTime() {
        throw notReady();
    }

    @Override
    public boolean isDepartureTimeVisible(String time) {
        throw notReady();
    }

    @Override
    public boolean infiniteScrollLoadsMoreOrStops(int maxSwipes) {
        throw notReady();
    }

    @Override
    public void selectFirstFlight() {
        throw notReady();
    }
}
package pages.ios;

import pages.BasePage;
import pages.api.SeatsPageActions;

public class SeatsPageIOS extends BasePage implements SeatsPageActions {

    private UnsupportedOperationException notReady() {
        return new UnsupportedOperationException(
                "SeatsPageIOS not implemented yet. Add iOS locators in pages/ios/SeatsPageIOS.java"
        );
    }

    @Override
    public void waitForPage() { throw notReady(); }

    @Override
    public void chooseSeatLater() { throw notReady(); }

    @Override
    public void tapNext() { throw notReady(); }
}
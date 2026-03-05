package pages.ios;

import pages.BasePage;
import pages.api.ServicesPageActions;

public class ServicesPageIOS extends BasePage implements ServicesPageActions {

    private UnsupportedOperationException notReady() {
        return new UnsupportedOperationException(
                "ServicesPageIOS not implemented yet. Add iOS locators in pages/ios/ServicesPageIOS.java"
        );
    }

    @Override
    public void waitForPage() { throw notReady(); }

    @Override
    public void tapNextStable() { throw notReady(); }
}
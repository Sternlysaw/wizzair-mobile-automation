package pages.IOS;

import pages.BasePage;
import pages.api.BundlePageActions;

public class BundlePageIOS extends BasePage implements BundlePageActions {

    private UnsupportedOperationException notReady() {
        return new UnsupportedOperationException(
                "BundlePageIOS is not implemented yet. Add iOS locators in pages/ios/BundlePageIOS.java"
        );
    }

    @Override
    public void waitForPage() {
        throw notReady();
    }

    @Override
    public void chooseQuickTravel() {
        throw notReady();
    }

    @Override
    public void choosePackAndSave() {
        throw notReady();
    }

    @Override
    public void tapNext() {
        throw notReady();
    }
}
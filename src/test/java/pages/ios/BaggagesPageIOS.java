package pages.ios;

import pages.BasePage;
import pages.api.BaggagesPageActions;

public class BaggagesPageIOS extends BasePage implements BaggagesPageActions {

    private UnsupportedOperationException notReady() {
        return new UnsupportedOperationException(
                "BaggagesPageIOS not implemented yet. Add iOS locators in pages/ios/BaggagesPageIOS.java"
        );
    }

    @Override
    public void waitForPage() { throw notReady(); }

    @Override
    public void chooseSmallCheckedInBaggage() { throw notReady(); }

    @Override
    public void verifySportEquipmentVisible() { throw notReady(); }

    @Override
    public void chooseTwoCabinBagsAndPriority() { throw notReady(); }

    @Override
    public void tapNext() { throw notReady(); }
}
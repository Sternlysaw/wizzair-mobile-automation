package pages.ios;

import pages.BasePage;
import pages.api.InsurancePageActions;

public class InsurancePageIOS extends BasePage implements InsurancePageActions {

    private UnsupportedOperationException notReady() {
        return new UnsupportedOperationException(
                "InsurancePageIOS not implemented yet. Add iOS locators in pages/ios/InsurancePageIOS.java"
        );
    }

    @Override
    public void waitForPage() { throw notReady(); }

    @Override
    public void selectTravelInsurance() { throw notReady(); }

    @Override
    public void tapSelectStable() { throw notReady(); }
}
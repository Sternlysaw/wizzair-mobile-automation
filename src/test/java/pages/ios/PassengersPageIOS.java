package pages.ios;

import pages.BasePage;
import pages.api.PassengersPageActions;

public class PassengersPageIOS extends BasePage implements PassengersPageActions {

    private UnsupportedOperationException notReady() {
        return new UnsupportedOperationException(
                "PassengersPageIOS not implemented yet. Add iOS locators in pages/ios/PassengersPageIOS.java"
        );
    }

    @Override
    public boolean isDisplayed() {
        throw notReady();
    }

    @Override
    public void tapNoSpecialAssistance() {
        throw notReady();
    }

    @Override
    public void tapNext() {
        throw notReady();
    }
}
package pages.ios;

import pages.BasePage;
import pages.api.DiscountPageActions;

public class DiscountPageIOS extends BasePage implements DiscountPageActions {

    private UnsupportedOperationException notReady() {
        return new UnsupportedOperationException(
                "DiscountPageIOS not implemented yet"
        );
    }

    @Override
    public void waitForPage() {
        throw notReady();
    }

    @Override
    public void declineDiscount() {
        throw notReady();
    }
}
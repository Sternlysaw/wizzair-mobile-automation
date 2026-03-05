package pages.ios;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.api.PriceChangeDialogActions;

import java.util.List;

public class PriceChangeDialogIOS extends BasePage implements PriceChangeDialogActions {

    // Best-effort: detect a modal that mentions "price" and "changed"
    private final By marker =
            AppiumBy.iOSNsPredicateString("label CONTAINS[c] 'price' AND label CONTAINS[c] 'chang'");

    private final List<By> actionButtons = List.of(
            AppiumBy.iOSNsPredicateString("label MATCHES[c] 'continue'"),
            AppiumBy.iOSNsPredicateString("label MATCHES[c] 'ok'"),
            AppiumBy.iOSNsPredicateString("label MATCHES[c] 'accept'"),
            AppiumBy.iOSNsPredicateString("label MATCHES[c] 'confirm'"),
            AppiumBy.iOSNsPredicateString("label MATCHES[c] 'got it'"),
            AppiumBy.iOSNsPredicateString("label MATCHES[c] 'understood'"),
            AppiumBy.iOSNsPredicateString("label MATCHES[c] 'yes'")
    );

    @Override
    public void acceptIfPresent() {
        if (driver.findElements(marker).isEmpty()) {
            return;
        }

        for (By btn : actionButtons) {
            if (!driver.findElements(btn).isEmpty()) {
                click(btn);
                return;
            }
        }

        // On iOS, sometimes buttons are inside alerts with different structure.
        // For now, fail loudly so you notice the dialog exists but isn't handled.
        throw new AssertionError("Price change dialog shown on iOS but no known button found");
    }
}
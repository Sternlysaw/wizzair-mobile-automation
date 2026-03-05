package pages.ios;

import pages.BasePage;
import pages.api.DeclarationPageActions;

public class DeclarationPageIOS extends BasePage implements DeclarationPageActions {
        private UnsupportedOperationException notReady() {
            return new UnsupportedOperationException(
                    "DeclarationPageIOS is not implemented yet. Add iOS locators in pages/ios/DeclarationPageIOS.java"
            );
        }
        @Override
        public void waitForPage() {throw notReady();}
        @Override
        public void tapConfirmStable() {
            throw notReady();
        }
}



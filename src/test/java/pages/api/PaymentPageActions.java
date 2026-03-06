package pages.api;

import models.CardDetails;

public interface PaymentPageActions {
    void waitForPage();
    void payByCard(CardDetails card);
}
package models;

public class CardDetails {
    public final String number;
    public final String holderName;
    public final String expiry; // e.g. "12/30"
    public final String cvc;    // e.g. "123"

    public CardDetails(String number, String holderName, String expiry, String cvc) {
        this.number = number;
        this.holderName = holderName;
        this.expiry = expiry;
        this.cvc = cvc;
    }
}
package models;

public class BillingDetailsData {
    public final String country;
    public final String address;
    public final String city;
    public final String postcode;

    public BillingDetailsData(String country, String address, String city, String postcode) {
        this.country = country;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
    }
}
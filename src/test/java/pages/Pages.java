package pages;

import core.ConfigReader;
import pages.android.*;
import pages.api.*;
import pages.ios.*;

public class Pages {

    private static String platform() {
        String p = ConfigReader.getOptional("platform");
        if (p == null) return "android";
        return p.trim().toLowerCase();
    }

    public static HomePageActions home() {
        return switch (platform()) {
            case "ios" -> new HomePageIOS();
            case "android" -> new HomePageAndroid();
            default -> throw new IllegalArgumentException("Unsupported platform: " + platform());
        };
    }

    public static SelectFlightPageActions selectFlight() {
        return switch (platform()) {
            case "ios" -> new SelectFlightPageIOS();
            case "android" -> new SelectFlightPageAndroid();
            default -> throw new IllegalArgumentException("Unsupported platform: " + platform());
        };
    }
    public static BundlePageActions bundle() {
        return switch (platform()) {
            case "ios" -> new BundlePageIOS();
            case "android" -> new BundlePageAndroid();
            default -> throw new IllegalArgumentException("Unsupported platform: " + platform());
        };
    }
    public static TripSummaryPageActions tripSummary() {
        return switch (platform()) {
            case "ios" -> new TripSummaryPageIOS();
            case "android" -> new TripSummaryPageAndroid();
            default -> throw new IllegalArgumentException("Unsupported platform: " + platform());
        };
    }
    public static SignInPageActions signIn() {
        return switch (platform()) {
            case "ios" -> new SignInPageIOS();
            case "android" -> new SignInPageAndroid();
            default -> throw new IllegalArgumentException("Unsupported platform: " + platform());
        };
    }
    public static DiscountPageActions discount() {
        return switch (platform()) {
            case "ios" -> new DiscountPageIOS();
            case "android" -> new DiscountPageAndroid();
            default -> throw new IllegalArgumentException("Unsupported platform: " + platform());
        };
    }
    public static PassengersPageActions passengers() {
        return switch (platform()) {
            case "ios" -> new PassengersPageIOS();
            case "android" -> new PassengersPageAndroid();
            default -> throw new IllegalArgumentException("Unsupported platform: " + platform());
        };
    }
    public static BaggagesPageActions baggages() {
        return switch (platform()) {
            case "ios" -> new BaggagesPageIOS();
            case "android" -> new BaggagesPageAndroid();
            default -> throw new IllegalArgumentException("Unsupported platform: " + platform());
        };
    }
    public static SeatsPageActions seats() {
        return switch (platform()) {
            case "ios" -> new SeatsPageIOS();
            case "android" -> new SeatsPageAndroid();
            default -> throw new IllegalArgumentException("Unsupported platform: " + platform());
        };
    }
    public static ServicesPageActions services() {
        return switch (platform()) {
            case "ios" -> new ServicesPageIOS();
            case "android" -> new ServicesPageAndroid();
            default -> throw new IllegalArgumentException("Unsupported platform: " + platform());
        };
    }
    public static InsurancePageActions insurance() {
        return switch (platform()) {
            case "ios" -> new InsurancePageIOS();
            case "android" -> new InsurancePageAndroid();
            default -> throw new IllegalArgumentException("Unsupported platform: " + platform());
        };
    }
    public static PriceChangeDialogActions priceChangeDialog() {
        return switch (platform()) {
            case "ios" -> new PriceChangeDialogIOS();
            case "android" -> new PriceChangeDialogAndroid();
            default -> throw new IllegalArgumentException("Unsupported platform: " + platform());
        };
    }
    public static DeclarationPageActions declaration() {
        return switch (platform()) {
            case "ios" -> new DeclarationPageIOS();
            case "android" -> new DeclarationPageAndroid();
            default -> throw new IllegalArgumentException("Unsupported platform: " + platform());
        };
    }
    public static BillingDetailsPageActions billingDetails() {
        return switch (platform()) {
            case "ios" -> new BillingDetailsPageIOS();
            case "android" -> new BillingDetailsPageAndroid();
            default -> throw new IllegalArgumentException("Unsupported platform: " + platform());
        };
    }
}
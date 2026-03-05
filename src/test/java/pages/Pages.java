package pages;

import core.ConfigReader;
import pages.ios.TripSummaryPageIOS;
import pages.android.TripSummaryPageAndroid;
import pages.api.HomePageActions;
import pages.api.SelectFlightPageActions;
import pages.android.HomePageAndroid;
import pages.android.SelectFlightPageAndroid;
import pages.ios.HomePageIOS;
import pages.ios.SelectFlightPageIOS;
import pages.api.BundlePageActions;
import pages.android.BundlePageAndroid;
import pages.ios.BundlePageIOS;
import pages.api.TripSummaryPageActions;
import pages.api.SignInPageActions;
import pages.android.SignInPageAndroid;
import pages.ios.SignInPageIOS;
import pages.api.DiscountPageActions;
import pages.android.DiscountPageAndroid;
import pages.ios.DiscountPageIOS;
import pages.api.PassengersPageActions;
import pages.android.PassengersPageAndroid;
import pages.ios.PassengersPageIOS;

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
}
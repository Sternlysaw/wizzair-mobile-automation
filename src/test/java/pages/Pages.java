package pages;

import core.ConfigReader;
import pages.api.HomePageActions;
import pages.api.SelectFlightPageActions;
import pages.android.HomePageAndroid;
import pages.android.SelectFlightPageAndroid;
import pages.IOS.HomePageIOS;
import pages.IOS.SelectFlightPageIOS;
import pages.api.BundlePageActions;
import pages.android.BundlePageAndroid;
import pages.IOS.BundlePageIOS;

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
}
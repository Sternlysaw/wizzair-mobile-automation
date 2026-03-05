package pages;

import core.ConfigReader;
import pages.api.HomePageActions;
import pages.android.HomePageAndroid;
import pages.IOS.HomePageIOS;

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
}
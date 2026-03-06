package utils;

import core.ConfigReader;
import utils.android.DeepLinkAndroid;
import utils.ios.DeepLinkIOS;

public class DeepLinkUtils {

    private DeepLinkUtils() {}

    public static void open(String url) {

        String platform = ConfigReader.getOptional("platform");
        if (platform == null) {
            platform = "android";
        }

        switch (platform.toLowerCase()) {
            case "android":
                DeepLinkAndroid.open(url);
                break;

            case "ios":
                DeepLinkIOS.open(url);
                break;

            default:
                throw new IllegalArgumentException("Unsupported platform: " + platform);
        }
    }
}
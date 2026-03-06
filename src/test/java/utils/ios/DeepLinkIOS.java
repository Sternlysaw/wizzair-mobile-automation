package utils.ios;

import core.DriverManager;

import java.util.HashMap;
import java.util.Map;

public class DeepLinkIOS {

    private DeepLinkIOS() {}

    public static void open(String url) {

        try {

            Map<String, Object> params = new HashMap<>();
            params.put("url", url);

            DriverManager.getDriver().executeScript("mobile: deepLink", params);

        } catch (Exception e) {
            throw new RuntimeException("Failed to open iOS deep link: " + url, e);
        }
    }
}
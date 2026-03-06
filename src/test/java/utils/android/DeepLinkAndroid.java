package utils.android;

import core.ConfigReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DeepLinkAndroid {

    private DeepLinkAndroid() {}

    public static void open(String url) {

        try {

            String udid = ConfigReader.getOptional("androidUdid");
            String appPackage = ConfigReader.get("androidAppPackage");

            List<String> command = new ArrayList<>();

            command.add("adb");

            if (udid != null && !udid.isBlank()) {
                command.add("-s");
                command.add(udid);
            }

            command.add("shell");
            command.add("am");
            command.add("start");
            command.add("-W");

            command.add("-a");
            command.add("android.intent.action.VIEW");

            command.add("-c");
            command.add("android.intent.category.BROWSABLE");

            command.add("-d");
            command.add(url);

            command.add(appPackage);

            Process process = new ProcessBuilder(command)
                    .redirectErrorStream(true)
                    .start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));

            while (reader.readLine() != null) {}

            process.waitFor();

            Thread.sleep(2000);

        } catch (Exception e) {
            throw new RuntimeException("Failed to open Android deep link: " + url, e);
        }
    }
}
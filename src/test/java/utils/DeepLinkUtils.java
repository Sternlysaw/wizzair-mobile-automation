package utils;

import core.ConfigReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DeepLinkUtils {

    private DeepLinkUtils() {}

    public static void open(String url) {
        try {
            String pkg = ConfigReader.get("androidAppPackage");

            Process process = new ProcessBuilder(
                    "adb", "shell", "am", "start", "-W",
                    "-n", pkg + "/" + ConfigReader.get("androidAppActivity"),
                    "-a", "android.intent.action.VIEW",
                    "-d", url
            ).redirectErrorStream(true).start();

            String output = readAll(process);
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                throw new RuntimeException("Deep link adb command failed.\nOutput:\n" + output);
            }

            // Extra safety: if Android prints "Error:" even with exitCode 0, catch it
            if (output.contains("Error:")) {
                throw new RuntimeException("Deep link could not be resolved.\nOutput:\n" + output);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to open deep link via adb: " + url, e);
        }
    }

    private static String readAll(Process p) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
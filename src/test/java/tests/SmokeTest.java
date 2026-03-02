package tests;

import core.DriverManager;

public class SmokeTest {
    public static void main(String[] args) {
        DriverManager.initDriver();
        try {
            System.out.println("Session started OK");
        } finally {
            DriverManager.quitDriver();
        }
    }
}
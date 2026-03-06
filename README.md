# WizzAir Mobile Automation

# Overview
- Mobile automation framework for the WizzAir mobile application
- Built using:
  - Java 21
  - Appium 2
  - Selenium
  - Cucumber
  - JUnit
  - Maven
- Automates a full booking flow
- Designed to support both Android and iOS using platform abstraction
# Prerequisites
- Java 21
- Maven 3.9+
- Node.js (for Appium)
- Appium installed globally
- Android Studio
- ANDROID_SDK_ROOT configured
- Android device with USB debugging enabled
# Appium Setup
- Install Appium globally
  - npm install -g appium
- Install the Android driver
  - appium driver install uiautomator2
- Start the Appium server
  - appium
# Add Your Device
- Connect your Android device
- Run: adb devices
- Example output:
  - List of devices attached
  - ExampleId device
- Add device configuration to config.properties:
  - platform=android
  - deviceName=MyAndroidDevice
  - udid=ExampleID
  - androidAppPackage=com.wizzair.WizzAirApp
  - appActivity=com.wizzair.app.MainActivityV2
- The UDID must match the device shown by adb devices
# Run Tests
- Using Maven:
  - mvn clean test "-Dtest=runners.AndroidRunner"
- Or run directly from IntelliJ:
  - runners.AndroidRunner
# Project Structure
- src/test/java
- ├── core            # Driver management and configuration
- ├── hooks           # Test setup and teardown
- ├── models           # Data models used during tests (for example flight data or booking information)
- ├── pages
- │   ├── api         # Page interfaces
- │   ├── android     # Android implementations
- │   └── ios         # iOS implementations
- ├── runners         # Android and iOS test runners
- ├── stepdefinitions # Cucumber step definitions
- └── utils           # Waits, scrolling, deep linking, screenshots
# Design Decisions
- Page Object Model separates test logic from UI interactions
- Page interfaces allow the same tests to run on Android and iOS
- Explicit waits and retry logic improve stability
- Utility classes provide shared functionality:
  - WaitUtils
  - ScrollUtils
  - DeepLinkUtils
  - PermissionHandler
  - ScreenshotUtils
# Test Report
- Latest Android execution report: reports/cucumber-android.html
- To regenerate the report:
  - mvn clean test "-Dtest=runners.AndroidRunner"
- Default generated location:
  - target/cucumber-android.html
# Assumptions
- A valid test account exists for login
- Flights are available for the selected route and date
- Payment is not completed to avoid creating real bookings
# Limitations
## Android emulator restriction
- The application terminates immediately when launched in an Android emulator
- The process exits with SIGKILL (signal 9)
- No crash trace or system kill reason is logged
- Investigation showed:
  - Cleared and monitored logcat
  - Checked for Java crashes (FATAL EXCEPTION)
  - Checked for native crashes (SIGSEGV, SIGABRT)
  - Investigated ActivityManager kill reasons
  - Checked Low Memory Killer events
  - Inspected the events buffer (am_kill, am_crash, am_anr)
  - Verified emulator system properties
- The application loads security-related native libraries early during startup.
- The same APK runs correctly on a physical Android device
- The behavior indicates emulator detection or runtime integrity checks
- Tests must therefore be executed on a real Android device
## iOS execution
- The framework includes iOS support through platform abstraction, but the tests were not executed on an iOS device due to lack of access to Apple hardware during development.
## Payment / WebView testing
The payment step in the booking flow normally opens a payment WebView after clicking Pay now.
To avoid interfering with production systems, the test stops after filling in the card details and does not proceed with the payment action.

Attempting to proceed with fake card details results in a declined payment and temporarily reserves the seat for 15–30 minutes in the production system.
Because this affects live booking data, the payment confirmation step was intentionally not executed.
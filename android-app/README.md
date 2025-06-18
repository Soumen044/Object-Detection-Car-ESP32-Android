# Android App Setup

This folder contains the Android Studio project for the control app.

## Requirements
- Android Studio (latest version).
- Android device with Wi-Fi support.

## Setup
1. Install Android Studio from [developer.android.com](https://developer.android.com/studio).
2. Download the repository and open the `android-app` folder in Android Studio.
3. Sync the project with Gradle to download dependencies (e.g., OkHttp).
4. Connect an Android device via USB or use an emulator.
5. Build and run the app, or install `app-release.apk` directly.
6. Connect to the ESP32’s Wi-Fi AP (SSID: `Savana Node 01`, Password: `iloveindia`) before launching the app.

## Notes
- Ensure the app’s HTTP requests target the ESP32’s IP (default: `192.168.4.1`).
- Test controls (forward, backward, left, right, stop) with the car powered on.

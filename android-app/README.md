# Android App Setup

This folder contains the Android Studio project for the control app, which sends HTTP GET requests to the ESP32 and uses Firebase for single-user authentication. The app provides a user-friendly interface for controlling the car’s movements (forward, backward, left, right, stop) and integrates with Firebase to ensure secure, exclusive control.

## Requirements
- **Android Studio**: Latest version, downloadable from [developer.android.com](https://developer.android.com/studio).
- **Android Device**: With Wi-Fi and internet connectivity (for Firebase authentication).
- **Firebase Project**: Configured for authentication (see [docs/firebase_setup.md](../../docs/firebase_setup.md)).
- **Dependencies**: OkHttp for HTTP requests, Firebase for authentication.

## Setup
1. Install Android Studio from [developer.android.com](https://developer.android.com/studio).
2. Download the repository and open the `android-app` folder in Android Studio.
3. Set up Firebase:
   - Create a Firebase project at [console.firebase.google.com](https://console.firebase.google.com/).
   - Add an Android app, download `google-services.json`, and place it in `android-app/app/`.
   - Enable Email/Password authentication in Firebase Console.
4. Sync the project with Gradle to download dependencies (OkHttp, Firebase).
5. Connect an Android device via USB or use an emulator.
6. Build and run the app, or install `app-release.apk` directly.
7. Connect to the ESP32’s Wi-Fi Access Point (SSID: `Savana Node 01`, Password: `9038790118`) before launching the app.

## Notes
- **HTTP Requests**: The app sends requests to `http://192.168.4.1/` (ESP32’s default IP).
- **Firebase Login**: Use a registered email/password for single-user access.
- **Testing**: Ensure the car is powered on and verify controls (forward, backward, left, right, stop).
- **Screenshots**: See [Application Images](../android-app/application-images/) for app interface examples.
- **Troubleshooting**:
  - Check Wi-Fi connection to ESP32.
  - Verify Firebase configuration.
  - Ensure internet access for initial login.

For detailed Firebase setup, refer to [Firebase Setup](../docs/firebase_setup.markdown).

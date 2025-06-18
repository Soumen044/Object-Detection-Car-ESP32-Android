# Firebase Setup

The Android app uses Firebase for single-user authentication, ensuring secure and exclusive control of the Object Detection Car. This section provides detailed instructions for setting up Firebase, as mentioned in the project documentation.

## Overview

- **Purpose**: Authenticate users via Email/Password to prevent unauthorized access.
- **Benefit**: Ensures only one user controls the car at a time.
- **Platform**: Firebase Authentication, integrated with the Android app.

## Setup Steps

1. **Create a Firebase Project**:

   - Go to console.firebase.google.com and sign in.
   - Click “Add project” and name it (e.g., `Object-Detection-Car`).
   - Disable Google Analytics (optional) and create the project.

2. **Add Android App**:

   - In the Firebase Console, click the Android icon to add an app.
   - Enter the Android package name (e.g., `com.example.carcontrol`).
   - Provide an optional App nickname (e.g., `CarControl`).
   - Download `google-services.json` and save it.

3. **Integrate google-services.json**:

   - Place `google-services.json` in the `android-app/app/` folder of the Android Studio project.
   - Ensure the file is in the correct location for Gradle to detect it.

4. **Add Firebase Dependencies**:

   - Open `android-app/build.gradle` (project-level) and add:

     ```gradle
     classpath 'com.google.gms:google-services:4.3.15'
     ```

   - Open `android-app/build.gradle` (app-level) and add:

     ```gradle
     apply plugin: 'com.google.gms.google-services'
     implementation 'com.google.firebase:firebase-auth:22.0.0'
     ```

   - Sync the project with Gradle.

5. **Enable Authentication**:

   - In Firebase Console, go to Authentication &gt; Sign-in method.
   - Enable Email/Password authentication.
   - Optionally, add test users via the Users tab.

6. **Test Authentication**:

   - Build and run the Android app in Android Studio.
   - Use a registered email/password to log in.
   - Verify that only one user can control the car at a time.

## Notes

- **Package Name**: Must match the app’s `applicationId` in `build.gradle`.
- **Internet Access**: Required for initial Firebase login.
- **Security**: Store `google-services.json` securely; do not commit sensitive API keys to public repositories.
- **Troubleshooting**:
  - Check Firebase Console for error logs.
  - Ensure `google-services.json` is correctly placed.
  - Verify internet connectivity during login.

## Code Integration

The Android app uses Firebase Authentication APIs to:

- Authenticate users with email/password.
- Maintain a single active session.
- Prevent unauthorized control.

For app setup, see [README](../android-app/README.md).

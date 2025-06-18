Object Detection Car Controlled by Android Application

A Wi-Fi-enabled, remote-controlled car with obstacle detection, powered by an ESP32 microcontroller, HC-SR04 ultrasonic sensor, and an Android application. The car detects obstacles within 60 cm, automatically reversing and stopping to avoid collisions, and is controlled via a smartphone app over Wi-Fi using HTTP GET requests. Firebase integration ensures secure single-user control, enhancing the user experience. This project demonstrates a cost-effective, scalable solution for robotic navigation, suitable for applications like automated vehicles and security robots.
Developed by Soumen Mishra, Nabanita Maity, Triyasa Dey, Pousali Pal, and Anirudha Roy as a final-year project (Session: 2022–2025) at Dasnagar Government Polytechnic, Howrah, West Bengal, under the guidance of Shri Partha Sarathi Banerjee and Shri Achintya Gopal Mondal. Affiliated with the West Bengal State Council of Technical & Vocational Education and Skill Development, this project showcases expertise in Internet of Things (IoT), embedded systems, sensor integration, and Android app development.

Table of Contents

Introduction
Features
Demo
Hardware Requirements
Software Requirements
Installation
Usage
Screenshots
Documentation
Project Presentation
Contributing
License
Acknowledgements
Connect with Us

Introduction
The Object Detection Car Controlled by Android Application is an innovative robotic system designed to navigate environments safely using obstacle detection and remote control capabilities. The car employs an ESP32 microcontroller to establish a Wi-Fi Access Point (AP), allowing seamless communication with an Android smartphone app. The HC-SR04 ultrasonic sensor continuously monitors the environment, detecting obstacles within a 60 cm range and triggering automatic collision avoidance maneuvers (backward movement for 500 ms, followed by a stop). The Android app, integrated with Firebase for single-user authentication, sends HTTP GET requests to control the car’s movements (forward, backward, left, right, stop). Two LEDs provide visual feedback on movement direction, enhancing user interaction.
This project addresses the growing need for cost-effective, reliable robotic systems in applications such as smart homes, warehouses, delivery services, and security patrolling. By combining affordable hardware (ESP32, HC-SR04, L293D motor driver) with robust software (Arduino, Android Studio, Firebase), the system offers real-time response, scalability, and ease of use. The project was developed by a team of five students—Soumen Mishra, Nabanita Maity, Triyasa Dey, Pousali Pal, and Anirudha Roy—at Dasnagar Government Polytechnic, showcasing their skills in hardware design, embedded programming, and mobile app development.
Features

Obstacle Detection: Utilizes the HC-SR04 ultrasonic sensor to detect objects within 60 cm, ensuring safe navigation.
Automatic Collision Avoidance: Automatically reverses for 500 ms and stops when an obstacle is detected, overriding manual commands.
Wi-Fi Remote Control: Controlled via an Android app connected to the ESP32’s Wi-Fi AP (SSID: Savana Node 01, Password: 9038790118).
Real-Time Response: Minimal latency in command execution and sensor feedback for smooth operation.
User-Friendly Interface: Android app with intuitive controls (forward, backward, left, right, stop) and Firebase-based single-user authentication.
LED Indicators: Two LEDs (pins 14, 27) show movement direction:
Both on for backward movement.
Left LED for left turn.
Right LED for right turn.
Off for forward or stop.


Cost-Effective Design: Built with affordable components, making it accessible for educational and hobbyist projects.
Firebase Integration: Ensures secure, single-user control via email/password authentication.
Scalable Architecture: Modular design supports future enhancements like computer vision or AI.

Demo
Watch the project in action:
[]([YouTube Video Link])

The demo showcases the car navigating a test environment, responding to Android app commands, and avoiding obstacles using the HC-SR04 sensor.
Hardware Requirements
The following components are used to build the car, as detailed in the project presentation (Page 6) and Arduino code pin definitions:



Component
Quantity
Description



ESP32 Dev Module
1
Wi-Fi-enabled microcontroller (core processing unit)


HC-SR04 Sensor
1
Ultrasonic distance sensor (pins 5, 18) for obstacle detection


L293D Motor Driver
1
Controls BO DC motors for movement


BO DC Motors
2
Drives the car’s wheels (pins 21, 19, 23, 22)


LEDs
2
Direction indicators (pins 14, 27)


7.4V Li-ion Battery
1
Power supply for the car


LM2596 Voltage Regulator
1
Ensures stable power supply


Chassis and Wheels
1
Mechanical structure of the car


Jumper Wires
~20
Circuit connections


Breadboard/PCB
1
Circuit assembly platform


For a detailed list with approximate costs and purchase links, see Bill of Materials.
Software Requirements

Arduino IDE: For uploading the ESP32 code. Requires the ESP32 board manager.
Android Studio: For building and running the Android app. Includes OkHttp library for HTTP requests.
Firebase: For single-user authentication in the Android app.
Web Browser: To access Firebase Console for setup.

Installation
Follow these steps to set up the project, as outlined in the project documentation and presentation.
Android App

Download the repository from github.com/yourusername/Object-Detection-Car-ESP32-Android.
Open the android-app folder in Android Studio.
Place google-services.json (from Firebase setup) in android-app/app/.
Sync the project with Gradle to download dependencies (OkHttp, Firebase).
Build and run on an Android device (API 21+), or install android-app/app-release.apk directly.
See android-app/README.md for detailed instructions.

ESP32 Code

Install Arduino IDE from arduino.cc.
Add ESP32 board manager:
Go to File > Preferences.
Add https://dl.espressif.com/dl/package_esp32_index.json to Additional Boards Manager URLs.
Go to Tools > Board > Boards Manager, search esp32, and install.


Open esp32-code/CarControl.ino in Arduino IDE.
Select ESP32 Dev Module under Tools > Board.
Connect the ESP32 via USB, select the correct port, and click Upload.
See esp32-code/README.md for details.

Hardware Setup

Assemble the circuit as shown in circuit_diagram.png.
Connect the 7.4V Li-ion battery via the LM2596 regulator.
Verify connections using bill_of_materials.md.
Power on the car and check LED functionality.

Firebase Setup

Create a Firebase project at console.firebase.google.com.
Add an Android app, download google-services.json, and place it in android-app/app/.
Enable Email/Password authentication in Firebase Console.
See docs/firebase_setup.md for step-by-step instructions.

Usage

Power on the car; the ESP32 creates a Wi-Fi AP (SSID: Savana Node 01, Password: 9038790118, IP: 192.168.4.1).
Connect your Android device to the ESP32’s Wi-Fi network.
Open the Android app and log in using Firebase authentication (email/password).
Use the app’s buttons to control the car:
Forward: Moves straight (both motors forward, LEDs off).
Backward: Moves reverse (both motors backward, both LEDs on).
Left: Turns left (left motor forward, right motor backward, left LED on).
Right: Turns right (right motor forward, left motor backward, right LED on).
Stop: Halts movement (all motors off, LEDs off).


The car automatically reverses for 500 ms and stops if an obstacle is detected within 60 cm, overriding manual commands.
Monitor Serial Monitor (115200 baud) in Arduino IDE for debug logs (e.g., distance, IP address).

Screenshots

Additional screenshots are available in media/screenshots/.
Documentation
Detailed documentation is available in the docs/ folder, covering all aspects of the project as presented in the PPT (Pages 4–18):

Introduction: Project overview, objectives, and team details.
Types of Obstacles: Common obstacles and detection challenges.
Solutions: Technical approaches to obstacle avoidance.
Working Methodology: Detailed system operation and data flow.
Key Features: Core functionalities and benefits.
Applications: Potential use cases and advantages/disadvantages.
Challenges Faced: Development hurdles and resolutions.
Conclusion: Project outcomes, achievements, and learnings.
Future Scope: Potential enhancements and upgrades.
Firebase Setup: Guide for configuring Firebase authentication.
References: Resources and datasheets used.

Project Presentation
View the complete project presentation (19 pages) for an in-depth overview:

Project-ppt.pdf

Contributing
We welcome contributions to enhance the project! Please follow the guidelines in CONTRIBUTING.md for bug reports, feature suggestions, or code submissions.
License
This project is licensed under the MIT License. See LICENSE for details.
Acknowledgements

Team Members:
Soumen Mishra
Nabanita Maity
Triyasa Dey
Pousali Pal
Anirudha Roy


Project Guides:
Shri Partha Sarathi Banerjee
Shri Achintya Gopal Mondal


Institution: Dasnagar Government Polytechnic, Howrah, West Bengal
Head of Department: Shri Aniruddha Bhaduri
Principal: Dr. Manas Kumar Saha
Affiliation: West Bengal State Council of Technical & Vocational Education and Skill Development
Tools and Resources: Arduino IDE, Android Studio, Firebase, Espressif Systems, SparkFun, STMicroelectronics, Texas Instruments

Connect with Us
Follow our journey and connect with us on social media for updates and more projects:

LinkedIn: your.linkedin.handle
Instagram: your.instagram.handle
Facebook: your.facebook.handle
Email: your.email@example.com
GitHub: yourusername
YouTube: [YouTube Video Link]([YouTube Video Link])

We’d love to hear your feedback and explore collaboration opportunities!

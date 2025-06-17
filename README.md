# Object Detection Car Controlled by Android Application

A Wi-Fi-enabled, remote-controlled car with obstacle detection using an ESP32 microcontroller, HC-SR04 ultrasonic sensor, and an Android application. The car navigates safely by detecting obstacles within 60 cm and can be controlled via a smartphone app over Wi-Fi using HTTP requests.

Developed as a group project by Soumen Mishra, Nabanita Maity, Triyasa Dey, Pousali Pal, and Anirudha Roy at Dasnagar Government Polytechnic, this project demonstrates skills in IoT, embedded systems, and Android development.

## Features
- **Obstacle Detection**: Detects objects within 60 cm using an HC-SR04 ultrasonic sensor and automatically reverses/stops to avoid collisions.
- **Wi-Fi Control**: Controlled remotely via an Android app over a Wi-Fi network created by the ESP32.
- **Real-Time Response**: Minimal delay in command execution and sensor feedback.
- **User-Friendly Interface**: Simple Android app for movement controls (forward, backward, left, right, stop).
- **Collision Prevention**: Ensures safe navigation in robotic applications.

## Demo
[Insert link to YouTube video or embed GIF if available]

## Hardware Requirements
| Component              | Description                              |
|------------------------|------------------------------------------|
| ESP32 Dev Module       | Microcontroller with Wi-Fi capabilities  |
| HC-SR04                | Ultrasonic sensor for distance measurement |
| L293D                  | Motor driver IC for controlling BO motors |
| BO DC Motors (x2)      | Drive the car’s wheels                   |
| LEDs (x2)              | Indicators for movement direction        |
| 7.4V Li-ion Battery    | Power supply for the car                 |
| LM2596                 | Voltage regulator for stable power       |

See [Bill of Materials](hardware/bill_of_materials.md) for details.

## Software Requirements
- **Android Studio**: To build and run the Android app.
- **Arduino IDE**: To upload the ESP32 code.
- **OkHttp Library**: Included in the Android project for HTTP requests.

## Installation
### Android App
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/Object-Detection-Car-ESP32-Android.git

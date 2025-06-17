# Working Methodology

The system operates as follows:
1. **Wi-Fi Setup**: The ESP32 creates a Wi-Fi Access Point (AP) with SSID `Savana Node 01` and password `9038790118`.
2. **App Connection**: The Android app connects to the ESP32’s AP and sends HTTP GET requests (e.g., `/forward`, `/stop`) to control the car.
3. **Command Processing**: The ESP32 receives and processes requests, executing corresponding motor control functions.
4. **Obstacle Detection**: The HC-SR04 sensor measures distance every second. If an obstacle is within 60 cm, the car moves backward for 500 ms and stops.
5. **Response**: The ESP32 sends an HTTP response to the app, confirming the executed command.

The L293D motor driver controls two BO DC motors for movement, while LEDs indicate direction (e.g., both LEDs on for backward movement).

![Block Diagram](../hardware/block_diagram.png)

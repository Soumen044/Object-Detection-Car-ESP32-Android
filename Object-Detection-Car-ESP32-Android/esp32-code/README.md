# ESP32 Code Setup

This folder contains the Arduino code for the ESP32 microcontroller.

## Requirements
- Arduino IDE with ESP32 board support.
- ESP32 Dev Module connected via USB.

## Setup
1. Install Arduino IDE from [arduino.cc](https://www.arduino.cc/en/software).
2. Add ESP32 board manager:
   - Go to `File > Preferences`, add `https://dl.espressif.com/dl/package_esp32_index.json` to Additional Boards Manager URLs.
   - Go to `Tools > Board > Boards Manager`, search for `esp32`, and install.
3. Open `CarControl.ino` in Arduino IDE.
4. Select `ESP32 Dev Module` under `Tools > Board`.
5. Connect the ESP32, select the correct port, and click `Upload`.
6. Open Serial Monitor (115200 baud) to view the IP address and debug logs.

## Notes
- Modify `ssid` and `password` in `CarControl.ino` for security.
- Ensure the circuit matches [circuit_diagram.png](../../hardware/circuit_diagram.png).

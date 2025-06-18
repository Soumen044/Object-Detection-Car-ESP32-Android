# ESP32 Code Setup

This folder contains the Arduino code (`CarControl.ino`) for the ESP32 microcontroller, which manages the car’s movement, obstacle detection, Wi-Fi communication, and LED indicators. The code creates a Wi-Fi Access Point, processes HTTP GET requests from the Android app, and uses the HC-SR04 sensor to detect obstacles.

## Requirements
- **Arduino IDE**: Download from [arduino.cc](https://www.arduino.cc/en/software).
- **ESP32 Dev Module**: Connected via USB.
- **ESP32 Board Manager**: For Arduino IDE compatibility.

## Setup
1. Install Arduino IDE from [arduino.cc](https://www.arduino.cc/en/software).
2. Add ESP32 board manager:
   - Go to `File > Preferences`.
   - Add `https://dl.espressif.com/dl/package_esp32_index.json` to Additional Boards Manager URLs.
   - Go to `Tools > Board > Boards Manager`, search `esp32`, and install.
3. Open `CarControl.ino` in Arduino IDE.
4. Select `ESP32 Dev Module` under `Tools > Board`.
5. Connect the ESP32 via USB, select the correct port, and click `Upload`.
6. Open Serial Monitor (115200 baud) to view:
   - Wi-Fi AP details (SSID: `Savana Node 01`, IP: `192.168.4.1`).
   - Distance readings from HC-SR04.
   - Received HTTP commands.

## Security Note
- **Wi-Fi Credentials**: Update `ssid` and `password` in `CarControl.ino` before deploying in public settings. Default credentials (`Savana Node 01`, `9038790118`) are for testing only.
- **Hardcoded IPs**: Requests are processed at `http://192.168.4.1/`. Ensure the Android app targets this IP.

## Notes
- **Circuit Verification**: Ensure connections match [circuit_diagram.png](../../hardware/circuit_diagram.png).
- **Pin Configuration**:
  - HC-SR04: Trigger (pin 5), Echo (pin 18).
  - LEDs: Left (pin 14), Right (pin 27).
  - Motors (via L293D): M1_A (pin 21), M1_B (pin 19), M2_A (pin 23), M2_B (pin 22).
- **Testing**: Connect the Android app to the ESP32’s Wi-Fi AP and test all movements.
- **Debugging**: Use Serial Monitor to troubleshoot sensor readings or Wi-Fi issues.

For hardware details, see [bill_of_materials.md](../../hardware/bill_of_materials.md).

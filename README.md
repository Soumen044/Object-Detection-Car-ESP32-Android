# Object Detection Car Controlled by Android Application

A Wi-Fi-enabled, remote-controlled car with obstacle detection using an ESP32 microcontroller, HC-SR04 ultrasonic sensor, and an Android application. The car navigates safely by detecting obstacles within 60 cm and can be controlled via a smartphone app over Wi-Fi using HTTP requests.

Developed as a group project by Soumen Mishra, Nabanita Maity, Triyasa Dey, Pousali Pal, and Anirudha Roy at Dasnagar Government Polytechnic, this project demonstrates skills in IoT, embedded systems, and Android development.

## Features
- **Obstacle Detection**: Detects objects within 60 cm using an HC-SR04 ultrasonic sensor and automatically reverses/stops to avoid collisions.
- **Wi-Fi Control**: Controlled remotely via an Android app over a Wi-Fi network created by the ESP32.
- **Real-Time Response**: Minimal delay in command execution and sensor feedback.
- **User-Friendly Interface**: Simple app interface for movement controls (forward, backward, left, right, stop).
- **Collision Prevention**: Ensures safe navigation in robotic applications.

## Demo
[Insert YouTube link or embed GIF, e.g., ![Demo GIF](media/demo_video.gif)]

## Hardware Requirements
| Component              | Description                              |
|------------------------|------------------------------------------|
| ESP32 Dev Module       | Wi-Fi-enabled microcontroller            |
| HC-SR04 Sensor         | Ultrasonic distance sensor               |
| L293D Motor Driver     | Controls BO DC motors                    |
| BO DC Motors (x2)      | Drives the car’s wheels                  |
| LEDs (x2)              | Movement direction indicators            |
| 7.4V Li-ion Battery    | Power supply for the car                 |
| LM2596                 | Voltage regulator for stable power       |

See [Bill of Materials](hardware/bill_of_materials.md) for details.

## Software Requirements
- **Android Studio**: To build and run the Android app.
- **Arduino IDE**: To upload the ESP32 code.
- **OkHttp Library**: Included in the Android project for HTTP requests.

## Installation
### Android App
1. Clone or download the repository from GitHub.
2. Open the `android-app` folder in Android Studio.
3. Build the project and run it on an Android device, or install `android-app/app-release.apk`.
4. See [android-app/README.md](android-app/README.md) for detailed setup.

### ESP32 Code
1. Open `esp32-code/CarControl.ino` in Arduino IDE.
2. Install the ESP32 board manager via Boards Manager.
3. Connect the ESP32, select the correct board/port, and upload the sketch.
4. See [esp32-code/README.md](esp32-code/README.md) for detailed setup.

### Hardware Setup
1. Assemble the circuit as shown in [circuit_diagram.png](hardware/circuit_diagram.png).
2. Connect the battery and power on the car.
3. Verify connections using the [Bill of Materials](hardware/bill_of_materials.md).

## Usage
1. Power on the car; the ESP32 creates a Wi-Fi Access Point (SSID: `Savana Node 01`, Password: `9038790118`).
2. Connect your Android device to the ESP32’s Wi-Fi network.
3. Open the Android app and use the buttons to control the car (forward, backward, left, right, stop).
4. The car automatically reverses and stops if an obstacle is detected within 60 cm.

## Documentation
- [Introduction](docs/introduction.md): Project overview and objectives.
- [Working Methodology](docs/working_methodology.md): How the system operates.
- [Key Features](docs/key_features.md): Core functionalities.
- [Applications](docs/applications.md): Potential use cases.
- [Conclusion](docs/conclusion.md): Project outcomes and learnings.
- [Future Scope](docs/future_scope.md): Potential enhancements.
- [References](docs/references.md): Resources used.

## Project Presentation
View the full project presentation: [Project-ppt.pdf](presentation/Project-ppt.pdf)

## Contributing
Contributions are welcome! Please open an issue or submit a pull request for bug fixes or enhancements. Follow the guidelines in [CONTRIBUTING.md](CONTRIBUTING.md).

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements
- **Team**: Soumen Mishra, Nabanita Maity, Triyasa Dey, Pousali Pal, Anirudha Roy.
- **Guides**: Shri Partha Sarathi Banerjee, Shri Achintya Gopal Mondal.
- **Institution**: Dasnagar Government Polytechnic, Howrah, West Bengal.
- **Head of Department**: Shri Aniruddha Bhaduri.
- **Principal**: Dr. Manas Kumar Saha.

## Contact
For inquiries, reach out via [LinkedIn](https://www.linkedin.com/in/04-soumen-mishra) or email at soumenmishra187@gmail.com .

# Object Detection Car Controlled by Android Application

![Project Logo](media/project_logo.png)

A Wi-Fi-enabled, remote-controlled car with obstacle detection, powered by an ESP32 microcontroller, HC-SR04 ultrasonic sensor, and an Android application. The car detects obstacles within 60 cm, automatically reversing and stopping to avoid collisions, and is controlled via a smartphone app over Wi-Fi using HTTP GET requests. Firebase integration ensures secure single-user control, enhancing the user experience.

Developed as a final-year project (Session: 2022–2025) by Soumen Mishra, Nabanita Maity, Triyasa Dey, Pousali Pal, and Anirudha Roy at Dasnagar Government Polytechnic, Howrah, West Bengal, under the guidance of Shri Partha Sarathi Banerjee and Shri Achintya Gopal Mondal. Affiliated with the West Bengal State Council of Technical & Vocational Education and Skill Development, this project showcases expertise in IoT, embedded systems, Android development, and sensor integration.

## Table of Contents
- [Demo](#demo)
- [Features](#features)
- [Hardware Requirements](#hardware-requirements)
- [Software Requirements](#software-requirements)
- [Installation](#installation)
- [Usage](#usage)
- [Documentation](#documentation)
- [Project Presentation](#project-presentation)
- [Contributing](#contributing)
- [Code of Conduct](#code-of-conduct)
- [License](#license)
- [Acknowledgements](#acknowledgements)
- [Contact](#contact)

## Demo
[![Demo Video]((ObjectDetectionCar.demo.mp4))]([Your YouTube Link](https://youtube.com/shorts/29JZEBwUVIw?si=bS2NoO9rE357zt9-))
![Demo GIF](media/ObjectDetectionCar.demo.gif).

Watch the car navigate and avoid obstacles in our YouTube demo video, showcasing real-time control via the Android app and obstacle detection capabilities.

## Features
- **Obstacle Detection**: Detects objects within 60 cm using the HC-SR04 ultrasonic sensor, ensuring safe navigation.
- **Automatic Collision Avoidance**: Reverses for 500 ms and stops when obstacles are detected.
- **Wi-Fi Remote Control**: Controlled via an Android app over the ESP32’s Wi-Fi Access Point (AP).
- **Real-Time Response**: Minimal latency in command execution and sensor feedback.
- **User-Friendly Interface**: Android app with intuitive controls and Firebase-based single-user authentication.
- **Cost-Effective Design**: Built with affordable components for scalability.
- **LED Indicators**: Two LEDs provide visual feedback for movement direction.
- **Firebase Integration**: Ensures secure, exclusive control for a single user.

## Hardware Requirements
| Component              | Quantity | Description                              |
|------------------------|----------|------------------------------------------|
| ESP32 Dev Module       | 1        | Wi-Fi-enabled microcontroller            |
| HC-SR04 Sensor         | 1        | Ultrasonic distance sensor (pins 5, 18)  |
| L293D Motor Driver     | 1        | Controls BO DC motors                    |
| BO DC Motors           | 2        | Drives wheels (pins 21, 19, 23, 22)      |
| LEDs                   | 2        | Direction indicators (pins 14, 27)       |
| 7.4V Li-ion Battery    | 1        | Power supply for the car                 |
| LM2596 Voltage Regulator | 1      | Stable power supply                      |
| Chassis and Wheels     | 1        | Car body and wheels                      |
| Jumper Wires           | ~20      | Circuit connections                      |
| Breadboard/PCB         | 1        | Circuit assembly                         |

See [Bill of Materials](hardware/bill_of_materials.md) for details and purchase links.

## Software Requirements
- **Arduino IDE**: For uploading the ESP32 code (requires ESP32 board manager).
- **Android Studio**: To build and run the Android app (includes OkHttp for HTTP requests).
- **Firebase**: For single-user authentication in the Android app.

## Installation
### Android App
1. Download the repository from GitHub.
2. Open the `android-app` folder in Android Studio.
3. Place `google-services.json` in `android-app/app/` (see [Firebase Setup](docs/firebase_setup.md)).
4. Sync the project with Gradle to download dependencies (OkHttp, Firebase).
5. Build and run on an Android device, or install `android-app/app-release.apk`.
6. See [android-app/README.md](android-app/README.md) for detailed setup.

### ESP32 Code
1. Open `esp32-code/CarControl.ino` in Arduino IDE.
2. Install the ESP32 board manager via Boards Manager.
3. Connect the ESP32, select `ESP32 Dev Module`, and upload the sketch.
4. See [esp32-code/README.md](esp32-code/README.md) for detailed setup.

### Hardware Setup
1. Assemble the circuit as shown in [circuit_diagram.png](hardware/circuit_diagram.png).
2. Refer to [mechanical_diagram.png](hardware/mechanical_diagram.png) for car assembly.
3. Connect the battery and power on the car.
4. Verify connections using [bill_of_materials.md](hardware/bill_of_materials.md).

### Firebase Setup
1. Create a Firebase project at [console.firebase.google.com](https://console.firebase.google.com/).
2. Add the Android app and download `google-services.json`.
3. See [docs/firebase_setup.md](docs/firebase_setup.md) for details.

## Usage
1. Power on the car; the ESP32 creates a Wi-Fi AP (SSID: `Savana Node 01`, Password: `9038790118`).
2. Connect your Android device to the ESP32’s Wi-Fi network (IP: `192.168.4.1`).
3. Open the Android app, log in via Firebase authentication, and use buttons to control the car (forward, backward, left, right, stop).
4. The car automatically reverses and stops if an obstacle is detected within 60 cm.
5. LEDs indicate movement: both on for backward, left LED for left turn, right LED for right turn.

## Documentation
- [Introduction](docs/introduction.md): Project overview and objectives.
- [Types of Obstacles](docs/types_of_obstacles.md): Obstacle categories addressed.
- [Solutions](docs/solutions.md): Approaches to obstacle avoidance.
- [Working Methodology](docs/working_methodology.md): Detailed system operation.
- [Sensor Detection](docs/sensor_detection.md): HC-SR04 sensor functionality.
- [Movement Controls](docs/movement_controls.md): Motor and LED control logic.
- [Key Features](docs/key_features.md): Core functionalities.
- [Applications](docs/applications.md): Potential use cases.
- [Advantages and Disadvantages](docs/advantages_disadvantages.md): Benefits and limitations.
- [Conclusion](docs/conclusion.md): Project outcomes and learnings.
- [Challenges](docs/challenges.md): Development challenges and solutions.
- [Future Scope](docs/future_scope.md): Potential enhancements.
- [Firebase Setup](docs/firebase_setup.md): Firebase integration guide.
- [References](docs/references.md): Resources used.

## Project Presentation
View the complete project presentation: [Project-ppt.pdf](presentation/Project-ppt.pdf)

## Contributing
Contributions are welcome! Please review [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines on reporting issues, submitting pull requests, and collaborating.

## Code of Conduct
We are committed to fostering an inclusive community. Please adhere to our [Code of Conduct](CODE_OF_CONDUCT.md).

## License
This project is licensed under the MIT License - see [LICENSE](LICENSE).

## Acknowledgements
- **Team**: Soumen Mishra, Nabanita Maity, Triyasa Dey, Pousali Pal, Anirudha Roy.
- **Guides**: Shri Partha Sarathi Banerjee, Shri Achintya Gopal Mondal.
- **Institution**: Dasnagar Government Polytechnic, Howrah, West Bengal.
- **Head of Department**: Shri Aniruddha Bhaduri.
- **Principal**: Dr. Manas Kumar Saha.
- **Affiliation**: West Bengal State Council of Technical & Vocational Education and Skill Development.

## Contact
Connect with us:
- **LinkedIn**: [Your LinkedIn URL]
- **Instagram**: [Your Instagram URL]
- **Facebook**: [Your Facebook URL]
- **Email**: your.email@example.com

For inquiries or feedback, reach out via email or social media. Follow our journey as we continue to innovate in IoT and robotics!

![License](https://img.shields.io/badge/license-MIT-green)
![Issues](https://img.shields.io/github/issues/yourusername/Object-Detection-Car-ESP32-Android)
![Stars](https://img.shields.io/github/stars/yourusername/Object-Detection-Car-ESP32-Android)

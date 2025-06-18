# Conclusion

The Object Detection Car Controlled by Android Application successfully achieved its objectives of developing a Wi-Fi-enabled robotic vehicle with real-time obstacle detection and remote control capabilities. Developed as a final-year project (Session: 2022–2025) by Soumen Mishra, Nabanita Maity, Triyasa Dey, Pousali Pal, and Anirudha Roy at Dasnagar Government Polytechnic, this project demonstrates a practical application of IoT, embedded systems, and Android development.

## Key Achievements
- **Obstacle Avoidance**:
  - The HC-SR04 sensor reliably detects obstacles within 60 cm, triggering automatic avoidance actions (reversing for 500 ms and stopping).
  - Ensures collision-free navigation in static and dynamic environments.

- **Wi-Fi Control**:
  - The ESP32 establishes a stable Wi-Fi Access Point (SSID: `Savana Node 01`, IP: `192.168.4.1`).
  - The Android app sends HTTP GET requests for seamless movement control.

- **User Interface**:
  - The Android app provides intuitive controls and Firebase authentication for secure single-user access.
  - LED indicators (pins 14, 27) enhance user feedback.

- **Cost-Effectiveness**:
  - Built with affordable components (~INR 1,960), making it accessible for educational and hobbyist projects.

- **System Integration**:
  - Combines ESP32, L293D motor driver, HC-SR04 sensor, and Firebase for a cohesive system.
  - Demonstrates effective hardware-software synergy.

## Learnings
- **Sensor Integration**: Configuring and calibrating the HC-SR04 for reliable distance measurement.
- **Wi-Fi Networking**: Implementing HTTP communication between ESP32 and Android app.
- **Android Development**: Building a user-friendly app with OkHttp and Firebase integration.
- **Motor Control**: Using L293D to achieve differential movement.
- **Team Collaboration**: Coordinating hardware, software, and documentation tasks among team members.

## Challenges Overcome
- Stabilizing Wi-Fi connections in crowded environments.
- Calibrating sensor to avoid false positives from reflective surfaces.
- Managing battery life for extended operation.
- Ensuring seamless HTTP request processing.
- Integrating Firebase without impacting app performance.

## Impact
The project provides a cost-effective, scalable solution for robotic navigation, suitable for applications like automated delivery, warehouse transport, and security patrolling. It serves as an educational tool for learning IoT, robotics, and Android development, and lays the foundation for advanced enhancements (see [future_scope.md](future_scope.md)).

The team’s efforts, guided by Shri Partha Sarathi Banerjee and Shri Achintya Gopal Mondal, and supported by Shri Aniruddha Bhaduri (Head of Department) and Dr. Manas Kumar Saha (Principal), highlight the potential of student-led innovation at Dasnagar Government Polytechnic.
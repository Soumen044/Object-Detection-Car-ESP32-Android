# Introduction

The Object Detection Car Controlled by Android Application is an innovative, Wi-Fi-enabled robotic vehicle designed to provide collision-free navigation through real-time obstacle detection and remote control. This project integrates an ESP32 microcontroller, HC-SR04 ultrasonic sensor, L293D motor driver, and an Android application to create a cost-effective solution for safe robotic movement. The car establishes a Wi-Fi Access Point (AP) via the ESP32, allowing a smartphone app to send HTTP GET requests for movement control (forward, backward, left, right, stop). The HC-SR04 sensor detects obstacles within 60 cm, triggering automatic avoidance actions (reversing and stopping). Firebase integration ensures secure single-user control, preventing unauthorized access.

Developed as a final-year project (Session: 2022–2025) by Soumen Mishra, Nabanita Maity, Triyasa Dey, Pousali Pal, and Anirudha Roy at Dasnagar Government Polytechnic, Howrah, West Bengal, this project demonstrates advanced skills in IoT, embedded systems, Android development, and sensor integration. Under the guidance of Shri Partha Sarathi Banerjee and Shri Achintya Gopal Mondal, and supported by Head of Department Shri Aniruddha Bhaduri and Principal Dr. Manas Kumar Saha, the project aligns with the curriculum of the West Bengal State Council of Technical & Vocational Education and Skill Development.

**Objective**: To design a smart vehicle that:
- Navigates safely by detecting and avoiding obstacles.
- Allows remote control via a user-friendly Android app.
- Operates over Wi-Fi for real-time communication.
- Integrates Firebase for secure, single-user access.
- Serves as a scalable platform for robotic applications like automated delivery or security patrolling.

This project addresses the growing need for cost-effective, reliable robotic systems in industries such as logistics, surveillance, and home automation, offering a practical solution with potential for further enhancements.
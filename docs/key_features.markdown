# Key Features

The Object Detection Car Controlled by Android Application offers a robust set of functionalities, making it a versatile platform for robotic navigation. The following features, as outlined in the project documentation, highlight its capabilities:

- **Obstacle Detection**:
  - Utilizes the HC-SR04 ultrasonic sensor to detect objects within 60 cm.
  - Ensures safe navigation in complex environments.

- **Automatic Collision Avoidance**:
  - When an obstacle is detected (< 60 cm), the car reverses for 500 ms and stops.
  - Overrides manual commands to prioritize safety.

- **Wi-Fi Remote Control**:
  - Controlled via an Android app connected to the ESP32’s Wi-Fi Access Point (SSID: `Savana Node 01`, IP: `192.168.4.1`).
  - Supports real-time command execution with minimal latency.

- **Real-Time Response**:
  - HTTP GET requests (e.g., `http://192.168.4.1/forward`) are processed instantly.
  - Sensor readings are updated every second for timely avoidance.

- **User-Friendly Interface**:
  - Android app features intuitive buttons for movement (forward, backward, left, right, stop).
  - Firebase authentication ensures secure, single-user access.
  - Screenshots: [media/screenshots/](../../media/screenshots/).

- **Cost-Effective Design**:
  - Built with affordable components (total cost ~INR 1,960).
  - Scalable for educational and commercial applications.

- **LED Feedback**:
  - Two LEDs (pins 14, 27) indicate movement:
    - Both on: Backward (obstacle avoidance).
    - Left LED: Left turn.
    - Right LED: Right turn.
    - Off: Forward or stop.

- **Firebase Integration**:
  - Single-user authentication prevents conflicting controls.
  - Enhances security in shared environments.

- **Robust Hardware**:
  - ESP32 microcontroller for reliable Wi-Fi and processing.
  - L293D motor driver for precise motor control.
  - 7.4V Li-ion battery with LM2596 regulator for stable power.

These features make the car suitable for applications like automated delivery, warehouse navigation, and security patrolling, with potential for further enhancements (see [future_scope.md](future_scope.md)).
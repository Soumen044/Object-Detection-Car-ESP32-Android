# Solutions

To ensure effective obstacle avoidance, the Object Detection Car implements a robust set of solutions leveraging the HC-SR04 ultrasonic sensor, ESP32 microcontroller, and Android app integration. The following solutions address the challenges posed by various obstacle types:

- **Real-Time Distance Measurement**:
  - The HC-SR04 sensor continuously measures distance to obstacles using ultrasonic waves.
  - Distance is calculated as: `distance = (duration * 0.0343) / 2`, where duration is the time for the echo to return.
  - Measurements occur every second, ensuring timely detection.

- **Automatic Avoidance Mechanism**:
  - If an obstacle is detected within 60 cm, the car:
    1. Moves backward for 500 milliseconds.
    2. Stops to prevent collision.
  - This overrides manual commands from the Android app, prioritizing safety.

- **Wi-Fi-Based Remote Control**:
  - The ESP32 creates a Wi-Fi Access Point (SSID: `Savana Node 01`, Password: `iloveindia`).
  - The Android app sends HTTP GET requests (e.g., `http://192.168.4.1/forward`) to control movement.
  - Allows users to navigate around obstacles manually when needed.

- **Sensor Calibration**:
  - The HC-SR04 is calibrated to minimize false positives from reflective or soft surfaces.
  - Trigger pulse duration (10 µs) and echo timeout are optimized for reliable readings.
  - Environmental factors (temperature, humidity) are accounted for in distance calculations.

- **LED Feedback**:
  - Two LEDs (pins 14, 27) provide visual cues:
    - Both on: Backward movement (obstacle avoidance).
    - Left LED: Left turn.
    - Right LED: Right turn.
    - Off: Forward or stopped.
  - Helps users understand the car’s response to obstacles.

- **Firebase Security**:
  - Firebase authentication ensures only one user controls the car, preventing conflicting commands.
  - Enhances reliability in multi-user environments.

**Implementation Details**:
- **Hardware**: HC-SR04 connected to ESP32 (Trigger: pin 5, Echo: pin 18).
- **Software**: Arduino code processes sensor data and HTTP requests (see [CarControl.ino](../esp32-code/CarControl.ino)).
- **Testing**: Validated in environments with static, dynamic, and low-height obstacles.

These solutions enable the car to navigate complex environments safely, making it suitable for applications like automated delivery or surveillance. For sensor specifics, see [Sensor Detection](sensor_detection.markdown).

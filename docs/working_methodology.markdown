# Working Methodology

The Object Detection Car Controlled by Android Application operates through a seamless integration of hardware, software, and communication protocols, enabling remote control and obstacle avoidance. The following details the system’s workflow, as described in the project documentation:

1. **Wi-Fi Setup**:
   - The ESP32 microcontroller establishes a Wi-Fi Access Point (AP).
   - **SSID**: `Savana Node 01`
   - **Password**: `9038790118`
   - **IP Address**: `192.168.4.1` (default for clients).
   - The ESP32 runs a WiFiServer on port 80 to handle HTTP requests.

2. **Android App Connection**:
   - The Android app connects to the ESP32’s Wi-Fi AP.
   - Users log in via Firebase authentication, ensuring single-user control.
   - The app sends HTTP GET requests to control the car, e.g.:
     - `http://192.168.4.1/forward`
     - `http://192.168.4.1/stop`
   - OkHttp library handles HTTP communication.

3. **Command Processing**:
   - The ESP32 receives HTTP GET requests via its WiFiServer.
   - It parses the request to extract commands (e.g., `forward`, `left`).
   - Corresponding motor control functions are executed (see [movement_controls.md](movement_controls.md)).
   - Commands are logged to Serial Monitor (115200 baud) for debugging.

4. **Obstacle Detection**:
   - The HC-SR04 ultrasonic sensor measures distance every second.
   - **Pin Configuration**: Trigger (pin 5), Echo (pin 18).
   - **Distance Calculation**: `distance = (duration * 0.0343) / 2`, where duration is the echo return time in microseconds.
   - If distance < 60 cm and > 0 cm:
     1. The car moves backward for 500 ms.
     2. The car stops.
     3. Manual commands are ignored during avoidance.

5. **Motor Control**:
   - The L293D motor driver controls two BO DC motors for differential movement.
   - **Pin Configuration**:
     - Motor 1: M1_A (pin 21), M1_B (pin 19).
     - Motor 2: M2_A (pin 23), M2_B (pin 22).
   - Movements:
     - **Forward**: Both motors forward.
     - **Backward**: Both motors reverse.
     - **Left**: Left motor stops, right motor forward.
     - **Right**: Right motor stops, left motor forward.
     - **Stop**: Both motors off.

6. **LED Indicators**:
   - Two LEDs provide visual feedback:
     - **Left LED** (pin 14): On for left turn.
     - **Right LED** (pin 27): On for right turn.
     - **Both LEDs**: On for backward (obstacle avoidance).
     - **Off**: For forward or stop.
   - Enhances user understanding of car actions.

7. **HTTP Response**:
   - After executing a command, the ESP32 sends an HTTP response to the app, e.g.:
     ```
     HTTP/1.1 200 OK
     Content-type:text/html
     The Car is moving: forward
     ```
   - Confirms command execution to the user.

8. **Power Management**:
   - A 7.4V Li-ion battery powers the system.
   - LM2596 voltage regulator ensures stable 5V/3.3V supply for ESP32 and sensors.

**System Architecture**:
![Block Diagram](../hardware/block_diagram.png)
![Circuit Diagram](../hardware/circuit_diagram.png)
![Mechanical Diagram](../hardware/mechanical_diagram.png)

**Workflow Summary**:
- The ESP32 initializes the Wi-Fi AP and sensor/actuator pins.
- The Android app connects, authenticates via Firebase, and sends commands.
- The ESP32 processes commands, checks for obstacles, and controls motors/LEDs.
- Obstacle detection overrides manual control for safety.
- Responses are sent back to the app, and LEDs provide visual feedback.

For sensor details, see [sensor_detection.md](sensor_detection.md). For motor control, see [movement_controls.md](movement_controls.md).
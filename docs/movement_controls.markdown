# Movement Controls

The Object Detection Car uses two BO DC motors controlled by an L293D motor driver and two LEDs for visual feedback, enabling precise movement and direction indication. This section details the control logic, as implemented in the Arduino code and described in the project documentation.

## Motor Control
- **Hardware**:
  - **Motors**: Two BO DC motors drive the car’s wheels.
  - **Driver**: L293D motor driver IC interfaces with the ESP32.
  - **Pins**:
    - Motor 1: M1_A (pin 21, forward), M1_B (pin 19, backward).
    - Motor 2: M2_A (pin 23, forward), M2_B (pin 22, backward).
- **Movements**:
  - **Forward**: Both motors rotate forward (M1_A HIGH, M1_B LOW, M2_A HIGH, M2_B LOW).
  - **Backward**: Both motors rotate backward (M1_A LOW, M1_B HIGH, M2_A LOW, M2_B HIGH).
  - **Left**: Left motor stops, right motor forward (M1_A LOW, M1_B LOW, M2_A HIGH, M2_B LOW).
  - **Right**: Right motor stops, left motor forward (M1_A HIGH, M1_B LOW, M2_A LOW, M2_B LOW).
  - **Stop**: Both motors off (all pins LOW).

## LED Indicators
- **Hardware**:
  - **LEDs**: Two LEDs for direction feedback.
  - **Pins**: Left LED (pin 14), Right LED (pin 27).
- **Behavior**:
  - **Forward**: Both LEDs off.
  - **Backward**: Both LEDs on (indicates obstacle avoidance).
  - **Left**: Left LED on, right LED off.
  - **Right**: Right LED on, left LED off.
  - **Stop**: Both LEDs off.

## Code Implementation
The following functions in `CarControl.ino` handle movement and LED control:
```cpp
void moveforward() {
  resetLEDs();
  digitalWrite(M1_A, HIGH);
  digitalWrite(M1_B, LOW);
  digitalWrite(M2_A, HIGH);
  digitalWrite(M2_B, LOW);
}

void movebackward() {
  resetLEDs();
  digitalWrite(LED_PIN1, HIGH);
  digitalWrite(LED_PIN2, HIGH);
  digitalWrite(M1_A, LOW);
  digitalWrite(M1_B, HIGH);
  digitalWrite(M2_A, LOW);
  digitalWrite(M2_B, HIGH);
}

void moveleft() {
  resetLEDs();
  digitalWrite(LED_PIN1, HIGH);
  digitalWrite(M1_A, HIGH);
  digitalWrite(M1_B, LOW);
  digitalWrite(M2_A, LOW);
  digitalWrite(M2_B, HIGH);
}

void moveright() {
  resetLEDs();
  digitalWrite(LED_PIN2, HIGH);
  digitalWrite(M1_A, LOW);
  digitalWrite(M1_B, HIGH);
  digitalWrite(M2_A, HIGH);
  digitalWrite(M2_B, LOW);
}

void movestop() {
  resetLEDs();
  digitalWrite(M1_A, LOW);
  digitalWrite(M1_B, LOW);
  digitalWrite(M2_A, LOW);
  digitalWrite(M2_B, LOW);
}

void resetLEDs() {
  digitalWrite(LED_PIN1, LOW);
  digitalWrite(LED_PIN2, LOW);
}
```
- **Logic**: Each function sets motor pins for the desired movement and updates LEDs accordingly.
- **Reset LEDs**: Ensures LEDs reflect the current movement state.

## Command Processing
- **HTTP Requests**: The Android app sends commands (e.g., `/forward`, `/stop`) via HTTP GET requests to `http://192.168.4.1/`.
- **Parsing**: The ESP32 extracts the command from the request URL.
- **Execution**: The corresponding movement function is called.
- **Obstacle Override**: If an obstacle is detected (< 60 cm), `movebackward()` and `movestop()` are triggered, ignoring manual commands.

## Integration
- **Wi-Fi**: Commands are received via the ESP32’s Wi-Fi AP (see [working_methodology.md](working_methodology.md)).
- **Sensor**: Obstacle detection influences movement (see [sensor_detection.md](sensor_detection.md)).
- **Feedback**: LEDs and HTTP responses (`The Car is moving: [command]`) inform the user.

For complete code, see [esp32-code/CarControl.ino](../esp32-code/CarControl.ino).
# Sensor Detection

The HC-SR04 ultrasonic sensor is the core component for obstacle detection in the Object Detection Car, enabling real-time distance measurement to ensure collision-free navigation. This section details the sensor’s operation, as described in the project documentation.

## Sensor Overview
- **Type**: Ultrasonic distance sensor.
- **Pins**:
  - **Trigger**: Pin 5 (output, sends ultrasonic pulse).
  - **Echo**: Pin 18 (input, receives reflected pulse).
- **Range**: 2 cm to 400 cm (effective up to 60 cm for this project).
- **Accuracy**: ±3 mm.
- **Operation**: Emits 40 kHz ultrasonic waves and measures the time for the echo to return.

## Detection Process
1. **Pulse Transmission**:
   - The ESP32 sends a 10 µs HIGH pulse to the Trigger pin.
   - This triggers the HC-SR04 to emit eight 40 kHz ultrasonic waves.

2. **Echo Reception**:
   - The sensor waits for the waves to reflect off an obstacle and return.
   - The Echo pin goes HIGH for the duration of the round-trip.

3. **Distance Calculation**:
   - The ESP32 measures the Echo pulse duration using `pulseIn(ECHO_PIN, HIGH)`.
   - Distance is calculated as:
     ```
     distance = (duration * 0.0343) / 2
     ```
     - `0.0343 cm/µs`: Speed of sound (343 m/s) converted to cm/µs.
     - `/ 2`: Accounts for round-trip travel.
   - Result is in centimeters.

4. **Obstacle Avoidance**:
   - Measurements are taken every second.
   - If distance < 60 cm and > 0 cm:
     - The car moves backward for 500 ms.
     - The car stops.
     - Manual commands from the Android app are ignored.
   - A distance of 0 cm indicates an invalid reading (e.g., no echo).

## Code Implementation
The following function in `CarControl.ino` handles sensor detection:
```cpp
float getDistance() {
  digitalWrite(TRIG_PIN, LOW);
  delayMicroseconds(2);
  digitalWrite(TRIG_PIN, HIGH);
  delayMicroseconds(10);
  digitalWrite(TRIG_PIN, LOW);
  long duration = pulseIn(ECHO_PIN, HIGH);
  return (duration * 0.0343) / 2;
}
```
- **Logic**: Ensures accurate pulse timing and converts duration to distance.
- **Usage**: Called in the main `loop()` to check for obstacles.

## Calibration
- **Environmental Factors**: Temperature and humidity affect sound speed. The constant `0.0343` assumes 20°C; adjustments may be needed for extreme conditions.
- **Noise Reduction**: Short delays (2 µs LOW, 10 µs HIGH) minimize interference.
- **False Positives**: Invalid readings (e.g., 0 cm) are filtered to prevent erroneous avoidance actions.

## Challenges
- **Reflective Surfaces**: Glass or mirrors may cause weak echoes.
- **Soft Materials**: Fabrics absorb waves, reducing detection reliability.
- **Narrow Objects**: Thin obstacles may be missed due to the sensor’s beam angle.
- **Mitigation**: Calibration and positioning optimize performance (see [Solutions](solutions.markdown)).

## Integration
- **Hardware**: Connected to ESP32 pins 5 (Trigger) and 18 (Echo).
- **Software**: Integrated with motor control to trigger avoidance (see [Movement Controls](movement_controls.markdown)).
- **Feedback**: Distance readings are printed to Serial Monitor for debugging.

For complete code, see [CarControl.ino](../esp32-code/CarControl.ino).

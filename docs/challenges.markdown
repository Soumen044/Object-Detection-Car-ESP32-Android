# Challenges

The development of the Object Detection Car Controlled by Android Application presented several technical and logistical challenges, as documented in the project. The team addressed these through research, experimentation, and collaboration, resulting in a robust system.

## Challenges Faced
1. **Wi-Fi Stability**:
   - **Issue**: Maintaining a stable ESP32 Wi-Fi Access Point in environments with multiple networks.
   - **Impact**: Dropped connections disrupted app control.
   - **Solution**:
     - Selected an optimal Wi-Fi channel (e.g., channel 1) to reduce interference.
     - Increased ESP32 transmit power in code.
     - Tested in varied environments to ensure reliability.

2. **Sensor Accuracy**:
   - **Issue**: HC-SR04 sensor produced false positives/negatives due to reflective (e.g., glass) or soft (e.g., fabric) surfaces.
   - **Impact**: Incorrect avoidance actions affected navigation.
   - **Solution**:
     - Calibrated trigger pulse (10 µs) and echo timeout.
     - Filtered invalid readings (e.g., 0 cm).
     - Positioned sensor to optimize beam angle.

3. **Battery Management**:
   - **Issue**: The 7.4V Li-ion battery drained quickly due to ESP32, motors, and sensor power demands.
   - **Impact**: Limited operational time.
   - **Solution**:
     - Used LM2596 regulator for efficient power distribution.
     - Optimized code to minimize idle power consumption.
     - Recommended high-capacity batteries for extended use.

4. **Firebase Integration**:
   - **Issue**: Configuring single-user authentication without slowing app performance.
   - **Impact**: Initial login delays affected user experience.
   - **Solution**:
     - Streamlined Firebase setup with Email/Password authentication.
     - Cached credentials for faster subsequent logins.
     - Tested with multiple devices to ensure exclusivity.

5. **Code Synchronization**:
   - **Issue**: Ensuring seamless communication between Arduino code and Android app.
   - **Impact**: Mismatched commands caused delays or errors.
   - **Solution**:
     - Standardized HTTP GET request format (e.g., `/forward`).
     - Added Serial Monitor logging for debugging.
     - Synchronized sensor checks with command processing.

6. **Hardware Integration**:
   - **Issue**: Aligning ESP32, L293D, HC-SR04, and LEDs in a compact chassis.
   - **Impact**: Loose connections caused intermittent failures.
   - **Solution**:
     - Used a PCB for stable connections.
     - Followed [Mechanical Diagram](../hardware/diagram/Mechanical_Diagram.jpg) for chassis assembly.
     - Tested extensively to verify robustness.

## Lessons Learned
- Importance of iterative testing in real-world conditions.
- Need for robust error handling in IoT systems.
- Value of clear documentation for team coordination.
- Strategies for optimizing power and network performance.

These challenges strengthened the team’s problem-solving skills and resulted in a reliable, user-friendly system. For future improvements, see [Future Scope](future_scope.markdown).

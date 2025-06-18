# Types of Obstacles

The Object Detection Car is designed to detect and avoid various types of obstacles encountered in its environment, ensuring safe navigation. The following obstacle categories, as outlined in the project documentation, are addressed by the HC-SR04 ultrasonic sensor:

- **Static Obstacles**:
  - Fixed objects such as walls, furniture, or pillars.
  - Characteristics: Immovable, predictable positions.
  - Detection: HC-SR04 measures distance to trigger avoidance within 60 cm.

- **Dynamic Obstacles**:
  - Moving objects like humans, animals, or other vehicles.
  - Characteristics: Unpredictable motion, variable speed.
  - Detection: Sensor continuously scans to detect approaching objects.

- **Low-Height Obstacles**:
  - Small objects like toys, cables, or curbs.
  - Characteristics: May be missed by sensors with limited vertical range.
  - Detection: HC-SR04 positioned to cover ground-level obstacles.

- **Reflective Surfaces**:
  - Glass, mirrors, or polished metal that may interfere with ultrasonic waves.
  - Characteristics: Can cause inaccurate distance readings.
  - Detection: Calibrated sensor settings minimize false positives.

- **Soft Obstacles**:
  - Curtains, fabrics, or foam objects that absorb ultrasonic waves.
  - Characteristics: Reduced reflection may affect detection.
  - Detection: Sensor tuned for consistent performance across materials.

**Challenges**:
- Differentiating between obstacle types in real-time.
- Ensuring accurate detection in noisy environments (e.g., multiple sound sources).
- Maintaining sensor reliability across varying temperatures and humidity.

The car’s obstacle detection system, powered by the HC-SR04 sensor, addresses these categories by measuring distances every second and triggering avoidance actions (reversing for 500 ms and stopping) when obstacles are within 60 cm. For detailed sensor operation, see [Sensor Detection](sensor_detection.markdown).

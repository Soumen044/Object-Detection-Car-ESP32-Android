// Object Detection Car - ESP32 Arduino Code
// Controls a Wi-Fi-enabled car with obstacle detection using HC-SR04 sensor
// Developed by Soumen Mishra, Nabanita Maity, Triyasa Dey, Pousali Pal, Anirudha Roy
// Dasnagar Government Polytechnic, Howrah, West Bengal (Session: 2022-2025)

#include <WiFi.h> // Library for ESP32 Wi-Fi functionality

// Sensor and Actuator Pins
#define TRIG_PIN 5     // HC-SR04 Trigger Pin (sends ultrasonic pulse)
#define ECHO_PIN 18    // HC-SR04 Echo Pin (receives reflected pulse)
#define LED_PIN1 14    // Left LED (indicates left turn)
#define LED_PIN2 27    // Right LED (indicates right turn)

// Motor Pins (connected to L293D motor driver)
#define M1_A 21        // Motor 1 forward
#define M1_B 19        // Motor 1 backward
#define M2_A 23        // Motor 2 forward
#define M2_B 22        // Motor 2 backward

// WiFi Configuration (change these for your network)
const char* ssid = "Savana Node 01";    // Wi-Fi SSID for Access Point
const char* password = "9038790118";    // Wi-Fi Password (update for security)
WiFiServer server(80);                  // HTTP server on port 80

void setup() {
  Serial.begin(115200); // Initialize serial communication for debugging

  // Set up WiFi as an Access Point
  WiFi.softAP(ssid, password);
  server.begin();
  Serial.println("Access Point started");
  Serial.print("IP address: ");
  Serial.println(WiFi.softAPIP()); // Print IP (default: 192.168.4.1)

  // Initialize pins
  pinMode(TRIG_PIN, OUTPUT);
  pinMode(ECHO_PIN, INPUT);
  pinMode(LED_PIN1, OUTPUT);
  pinMode(LED_PIN2, OUTPUT);
  pinMode(M1_A, OUTPUT);
  pinMode(M1_B, OUTPUT);
  pinMode(M2_A, OUTPUT);
  pinMode(M2_B, OUTPUT);

  // Ensure motors and LEDs are off initially
  movestop();
}

void loop() {
  // Measure distance to nearest obstacle
  float distance = getDistance();
  Serial.print("Distance: ");
  Serial.print(distance);
  Serial.println(" cm");

  // Obstacle detection: If < 60 cm, move back and stop
  if (distance < 60 && distance > 0) {
    movebackward();
    delay(500); // Move back for 500 ms
    movestop();
    return; // Skip other commands to prioritize avoidance
  }

  // Check for HTTP client requests
  WiFiClient client = server.available();
  if (client) {
    String request = "";
    while (client.connected()) {
      if (client.available()) {
        char c = client.read();
        request += c;
        if (c == '\r') {
          Serial.println(request);

          // Extract command from HTTP GET request
          int start = request.indexOf("GET /") + 5;
          int end = request.indexOf("HTTP/");
          String command = request.substring(start, end);
          command.trim(); // Remove spaces/newlines

          Serial.println("Received Command: " + command);
          
          // Execute movement based on command
          if (command == "forward") moveforward();
          else if (command == "left") moveleft();
          else if (command == "stop") movestop();
          else if (command == "right") moveright();
          else if (command == "backward") movebackward();

          // Send HTTP response to client
          client.println("HTTP/1.1 200 OK");
          client.println("Content-type:text/html");
          client.println();
          client.println("The Car is moving: " + command + "");
          break;
        }
      }
    }
    client.stop(); // Close client connection
  }
}

// Measure distance using HC-SR04 sensor
float getDistance() {
  digitalWrite(TRIG_PIN, LOW);
  delayMicroseconds(2);
  digitalWrite(TRIG_PIN, HIGH);
  delayMicroseconds(10);
  digitalWrite(TRIG_PIN, LOW);
  long duration = pulseIn(ECHO_PIN, HIGH);
  return (duration * 0.0343) / 2; // Distance in cm
}

// Motor control functions
void moveforward() {
  resetLEDs();
  digitalWrite(M1_A, HIGH);
  digitalWrite(M1_B, LOW);
  digitalWrite(M2_A, HIGH);
  digitalWrite(M2_B, LOW);
}

void movebackward() {
  resetLEDs();
  digitalWrite(LED_PIN1, HIGH); // Both LEDs on
  digitalWrite(LED_PIN2, HIGH);
  digitalWrite(M1_A, LOW);
  digitalWrite(M1_B, HIGH);
  digitalWrite(M2_A, LOW);
  digitalWrite(M2_B, HIGH);
}

void moveleft() {
  resetLEDs();
  digitalWrite(LED_PIN1, HIGH); // Left LED on
  digitalWrite(M1_A, HIGH);
  digitalWrite(M1_B, LOW);
  digitalWrite(M2_A, LOW);
  digitalWrite(M2_B, HIGH);
}

void moveright() {
  resetLEDs();
  digitalWrite(LED_PIN2, HIGH); // Right LED on
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

// Reset LEDs before new movement
void resetLEDs() {
  digitalWrite(LED_PIN1, LOW);
  digitalWrite(LED_PIN2, LOW);
}

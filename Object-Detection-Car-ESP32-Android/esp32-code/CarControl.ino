// Including necessary libraries
#include <WiFi.h>

// Sensor's and Actuators Declaration
#define TRIG_PIN 5     // HC-SR04 Trigger Pin
#define ECHO_PIN 18    // HC-SR04 Echo Pin
#define LED_PIN1 14    // Left LED
#define LED_PIN2 27    // Right LED

// Motor Pins
#define M1_A 21
#define M1_B 19
#define M2_A 23
#define M2_B 22

// WiFi Configuration
const char* ssid = "Savana Node 01";
const char* password = "9038790118";
WiFiServer server(80);

void setup() {
  Serial.begin(115200);

  // Set up WiFi as an access point
  WiFi.softAP(ssid, password);
  server.begin();
  Serial.println("Access Point started");
  Serial.print("IP address: ");
  Serial.println(WiFi.softAPIP());

  // Initialize pins
  pinMode(TRIG_PIN, OUTPUT);
  pinMode(ECHO_PIN, INPUT);
  pinMode(LED_PIN1, OUTPUT);
  pinMode(LED_PIN2, OUTPUT);
  pinMode(M1_A, OUTPUT);
  pinMode(M1_B, OUTPUT);
  pinMode(M2_A, OUTPUT);
  pinMode(M2_B, OUTPUT);

  // Ensure everything is off initially
  movestop();
}

void loop() {
  float distance = getDistance();
  Serial.print("Distance: ");
  Serial.print(distance);
  Serial.println(" cm");

  // Obstacle detection logic: If < 60 cm, move back and stop
  if (distance < 60 && distance > 0) {
    movebackward();
    delay(500);
    movestop();
    return; // Prevents executing other commands while avoiding obstacle
  }

  // Check for client requests
  WiFiClient client = server.available();
  if (client) {
    String request = "";
    while (client.connected()) {
      if (client.available()) {
        char c = client.read();
        request += c;
        if (c == '\r') {
          Serial.println(request);

          // Extract command from request
          int start = request.indexOf("GET /") + 5;
          int end = request.indexOf("HTTP/");
          String command = request.substring(start, end);
          command.trim(); // Remove extra spaces and newlines

          Serial.println("Received Command: " + command);
          
          // Execute movement based on command
          if (command == "forward") moveforward();
          else if (command == "left") moveleft();
          else if (command == "stop") movestop();
          else if (command == "right") moveright();
          else if (command == "backward") movebackward();

          // Send response to client
          client.println("HTTP/1.1 200 OK");
          client.println("Content-type:text/html");
          client.println();
          client.println("<html><body>The Car is moving: " + command + "</body></html>");
          break;
        }
      }
    }
    client.stop();
  }
}

// Function to measure distance using HC-SR04
float getDistance() {
  digitalWrite(TRIG_PIN, LOW);
  delayMicroseconds(2);
  digitalWrite(TRIG_PIN, HIGH);
  delayMicroseconds(10);
  digitalWrite(TRIG_PIN, LOW);
  long duration = pulseIn(ECHO_PIN, HIGH);
  return (duration * 0.0343) / 2;
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

// Function to turn off LEDs before new commands
void resetLEDs() {
  digitalWrite(LED_PIN1, LOW);
  digitalWrite(LED_PIN2, LOW);
}
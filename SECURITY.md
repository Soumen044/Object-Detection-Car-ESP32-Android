# Security Policy

## Overview

The **Object Detection Car Controlled by Android Application** is an open-source project developed by Soumen Mishra, Nabanita Maity, Triyasa Dey, Pousali Pal, and Anirudha Roy. We are committed to ensuring the security of our project, which includes an ESP32-based robotic car, an Android application, and Firebase integration. This policy outlines how to report security vulnerabilities responsibly to protect our users and contributors.

## Supported Versions

The project is actively maintained as of June 2025. Security updates are applied to the latest version of the repository:

| Version | Supported          |
|---------|--------------------|
| Latest  | ✅ Supported       |

## Reporting a Vulnerability

If you discover a security vulnerability in our project (e.g., in the ESP32 Arduino code, Android app, or Firebase configuration), please report it privately to allow us to address it promptly. We appreciate responsible disclosure and will acknowledge your contribution.

### How to Report

1. **Contact Us Privately**:
   - Email: [soumenmishra187@gmail.com]
   - LinkedIn: [https://www.linkedin.com/in/04-soumen-mishra]
   - Please **do not** disclose the vulnerability publicly (e.g., GitHub Issues, social media) until we’ve resolved it.

2. **Provide Details**:
   - **Description**: Explain the vulnerability (e.g., Wi-Fi credential exposure, Firebase misconfiguration).
   - **Steps to Reproduce**: Include clear steps, code snippets, or screenshots.
   - **Impact**: Describe potential consequences (e.g., unauthorized car control).
   - **Suggested Fix**: (Optional) Propose a solution if you have one.
   - **Environment**: Specify the affected component (e.g., ESP32 code, Android app version, Firebase setup).

3. **Use Secure Communication**:
   - If sharing sensitive information, consider encrypting your email using our public PGP key (available upon request via [soumenmishra187@gmail.com]).

### What to Expect

- **Acknowledgement**: We’ll confirm receipt of your report within **48 hours**.
- **Response Time**: We aim to assess and address the issue within **7 days**, depending on severity.
- **Updates**: We’ll keep you informed on our progress and resolution timeline.
- **Disclosure**: After mitigation, we’ll coordinate with you for public disclosure (if applicable) and credit your contribution (unless you prefer anonymity).

## Security Best Practices

To ensure safe use of this project, we recommend:

- **Wi-Fi Credentials**: Update the default SSID (`Savana Node 01`) and password (`iloveindia`) in `esp32-code/CarControl.ino` before deployment. Avoid hardcoding sensitive data.
- **Firebase Configuration**:
  - Securely store `google-services.json` outside the repository.
  - Restrict Firebase API keys and enable only necessary permissions.
  - Regularly audit Firebase rules for public access vulnerabilities.
- **Android App**:
  - Use HTTPS for all network requests (handled by OkHttp).
  - Validate user inputs to prevent injection attacks.
- **ESP32**:
  - Keep the ESP32 firmware updated via the Arduino IDE.
  - Use a firewall or isolated network for the Wi-Fi Access Point.
- **Physical Security**: Ensure the car’s hardware (e.g., battery, sensors) is protected from tampering.

## Disclosure Policy

- **Responsible Disclosure**: We encourage reporting vulnerabilities privately to allow mitigation before public announcement.
- **Credit**: Reporters will be credited in release notes or documentation unless anonymity is requested.
- **Public Disclosure**: We’ll publish details of resolved vulnerabilities after fixes are deployed, typically within **30 days** of resolution.

## Known Security Considerations

- **Wi-Fi Range**: The ESP32’s Access Point has a limited range (~10–20 meters indoors), reducing remote attack risks but not eliminating local threats.
- **HC-SR04 Sensor**: No known vulnerabilities, but physical obstruction could impair obstacle detection.
- **Battery**: The 7.4V Li-ion battery requires proper handling to prevent safety hazards.
- **Firebase**: Misconfiguration could expose user data; follow [Firebase Setup](docs/firebase_setup.markdown) for secure setup.

## Contact

For security-related inquiries, reach out to:

- **Email**: [soumenmishra187@gmail.com]
- **LinkedIn**: [https://www.linkedin.com/in/04-soumen-mishra]
- **Team**: Soumen Mishra, Nabanita Maity, Triyasa Dey, Pousali Pal, Anirudha Roy

We value the security community’s efforts and thank you for helping keep our project safe!

## Acknowledgements

Inspired by security policies from [GitHub Security](https://github.com/security) and [OWASP](https://owasp.org/).

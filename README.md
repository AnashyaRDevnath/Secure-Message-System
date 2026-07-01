![Java CI](https://github.com/AnashyaRDevnath/Secure-Message-System/actions/workflows/maven.yml/badge.svg)
# Secure-Message-System
A secure messaging application built in Java, featuring user registration with 
strict validation rules, password complexity enforcement, and a full messaging 
system with JSON-based persistence.

## Features

**Registration & Authentication**
- Username validation (must contain an underscore, max 5 characters)
- Password complexity enforcement
- Regex-based South African cell phone number validation
- Login system with clear success/error feedback

**Messaging System**
- Menu-driven interface using `JOptionPane`
- Send, store, and manage multiple messages per session
- Auto-incrementing message numbers
- Unique Message ID and Message Hash generation via string manipulation
- Messages persisted to JSON

**Data Management & Reporting**
- Array-based storage and processing of message data
- Search messages by recipient
- Identify and display the longest message sent
- Delete messages by Message Hash
- Full message report generation

## Tech Stack
- Java
- JSON (for data persistence)
- JUnit (unit testing)

## How to Run
1. Clone the repo
2. Open in your IDE of choice (e.g. IntelliJ, Eclipse, NetBeans)
3. Run the main class

## Testing
The project includes unit tests covering core functionality such as 
registration, login, and message handling.

## What I Learned
Building this project helped me get hands-on with input validation, 
JSON file handling in Java, and structuring a multi-part console/GUI 
application around real-world data manipulation tasks like searching, 
reporting, and deletion.

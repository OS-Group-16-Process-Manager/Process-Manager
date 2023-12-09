# Process Manager Application

This Java application simulates a process manager UI displaying fake process details such as Process ID (PID), Name, CPU Usage (%), Memory Usage (bytes), and Bound Type (I/O Bound or CPU Bound).

## Prerequisites

- Java Development Kit (JDK) installed on your system

## How to Run

1. **Compile the Code:**
    - Open a terminal/command prompt.
    - Navigate to the directory containing the `Main.java` file.
    - Compile the Java file using the command: `javac Main.java`

2. **Run the Application:**
    - After successful compilation, run the application by executing the command: `java Main`
    - The UI window titled "Process Manager" will open, displaying a table with process details.
    - The table will refresh every 2 seconds with new randomly generated fake process details.

## Understanding the Application

- The application utilizes Swing, a Java GUI widget toolkit, to create the user interface.
- It generates fake process details (PID, Name, CPU Usage, Memory Usage, Bound Type) using random data.
- The table updates every 2 seconds to simulate dynamic process information.

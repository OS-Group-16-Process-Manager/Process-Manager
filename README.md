# Process-Manager
This Process Manager is a Java-based utility designed to monitor system processes, track CPU usage, memory usage, and identify I/O bound processes.

# Overview
The Process Manager offers functionalities to:

Retrieve Process Information: Obtain details about running processes, such as process name, Process ID (PID), CPU usage, and memory usage.
Identify CPU and Memory Bound Processes: Analyze processes to identify CPU-bound and memory-bound applications.
Track I/O Bound Processes: Monitor I/O utilization to identify processes with high I/O usage.
# Installation
Ensure you have Java installed on your system.
Clone this repository to your local machine.
# Usage
Running the Process Manager
Compile the Java files in the repository.
Run the Main class to launch the Process Manager UI.
# Retrieving Process Information
Click on the "Retrieve Process Details" button in the UI to fetch and display process information.
The table displayed contains:
Process Name: Name of the running process.
PID: Process ID for the corresponding process.
CPU Usage (%): Percentage of CPU utilized by the process.
Memory Usage (bytes): Amount of memory used by the process (in bytes).
I/O Bound Analysis: Provides I/O Bound analysis for process
# Identifying I/O Bound Processes
The system tracks I/O usage but may require configuration for different OS environments.
Currently, the Process Manager identifies CPU and memory-bound processes and provides real-time I/O bound analysis.

# Paper Aeroplane Simulator

A lightweight, interactive Java application, combining programming and kinematics, built to simulate and analyse flight trajectories based on custom aerodynamic and velocity inputs.

## Purpose
I designed this project to bridge the concepts learned in **AP Computer Science A** (user input streams, methods, control flow structures) with the real-world flight mechanics covered in **AP Physics C: Mechanics** (vectors, projectile kinematics, and fluid resistance/drag forces).

## How it Works
The program processes the trajectory of an aerodynamic glider over discrete time intervals (dt = 0.1s) based on real-time data entered by the user.
* **Interactive Dynamic Inputs:** Utilises the `java.util.Scanner` stream to capture custom variables (mass, launch velocity, launch angle, launch height) directly via the command line interface.
* **Aerodynamic Deceleration:** Simulates a fluid drag coefficient acting against horizontal and vertical velocity components over time (\(v_x\) and \(v_y\)), mirroring low-altitude atmospheric flight profiles.
* **Vector Analytics:** Generates a real-time console telemetry log tracking distance (X) and altitude (Y) until the landing threshold is met.

## Technologies Used
* **Language:** Java (JDK 11+)
* **Libraries:** Standard Java Utilities (`java.util.Scanner`, `java.lang.Math`)

## Sample Project Telemetry Output
```text
Paper Plane Simulator
Enter paper weight in grams (e.g., 4.5): 4.5
Enter launch speed in m/s (e.g., 8.0): 8.0
Enter launch angle in degrees (e.g., 15.0): 15.0
Enter initial launch height in meters (e.g., 1.6): 1.5

Initializing simulation parameters...
Parameters: Weight=4.5g, Speed=8.0m/s, Angle=15.0°, Height=1.5m

--- Flight Simulation Started ---
Time: 0.0s | X: 0.00m | Y: 1.50m
Time: 0.1s | X: 0.77m | Y: 1.63m
Time: 0.2s | X: 1.50m | Y: 1.72m
...
--- Flight Ended (Hit the Ground) ---
Total Distance Flown: 7.42 meters
Total Flight Time: 1.2 seconds
```


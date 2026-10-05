# Paper Aeroplane Aero-Simulator

A lightweight, interactive Java application built to simulate and analyse flight trajectories using advanced aerodynamic forces, dynamic mass profiles, and kinematic vector equations.

## Purpose
I designed this project to bridge the concepts learned in **AP Computer Science A** (user input streams, conditional logic, method architectures) with the real-world mechanics covered in **AP Physics C: Mechanics** and **AP Calculus BC** (vector resolution, fluid resistance equations, and discrete numerical integration).

## How it Works
The program calculates the trajectory of an aerodynamic glider over discrete time steps (dt = 0.1s) based on user-input physical parameters.
* **Interactive Dynamic Inputs:** Utilises `java.util.Scanner` to capture real-time command-line inputs for vehicle mass (grams), velocity (m/s), launch vector angle (degrees), and release altitude (meters).
* **Mass-Dependent Fluid Dynamics:** Implements the true fluid drag equation:
  \[F_d = \frac{1}{2} \rho v^2 C_d A\]
  By taking standard sea-level air density (ρ = 1.225 kg/m³) and A4 paper reference wing area (A = 0.025 m²), the physics engine divides drag force by mass (\(a = \frac{F}{m}\)) to calculate realistic fluid resistance deceleration.
* **Vector Integration:** Resolves forces independently across horizontal (x) and vertical (y) planes, computing instant velocity updates before executing numerical integration calculations (Δ x = v ⋅ Δ t) until hitting the ground boundary condition (y ≤ 0).

## Technologies Used
* **Language:** Java (JDK 11+)
* **Libraries:** Standard Java Utilities (`java.util.Scanner`, `java.lang.Math`)

## Sample Project Telemetry Output
```text
=== Airbus Apprentice Paper Plane Simulator ===
Enter paper weight in grams (e.g., 4.5): 4.5
Enter launch speed in m/s (e.g., 8.0): 8.0
Enter launch angle in degrees (e.g., 15.0): 15.0
Enter initial launch height in meters (e.g., 1.6): 1.5

Initializing simulation parameters...
Parameters: Weight=4.5g, Speed=8.0m/s, Angle=15.0°, Height=1.5m

--- Flight Simulation Started ---
Time: 0.0s | X: 0.00m | Y: 1.50m
Time: 0.1s | X: 0.74m | Y: 1.67m
Time: 0.2s | X: 1.44m | Y: 1.80m
Time: 0.3s | X: 2.10m | Y: 1.90m
Time: 0.4s | X: 2.73m | Y: 1.97m
...
--- Flight Ended (Hit the Ground) ---

Final Results:
Total Distance Flown: 7.21 meters
Total Flight Time: 1.3 seconds
```


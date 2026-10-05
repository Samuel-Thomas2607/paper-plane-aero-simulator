
import java.util.Scanner;

// Main driver class for the simulation application
public class AeroplaneSimulator {

    // Main method
    public static void main(String[] args) {
        // Initializing scanner
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Paper Plane Simulator ===");
        
        // Prompting and capturing custom real-time data inputs
        System.out.print("Enter paper weight in grams (e.g., 4.5): ");
        double weightGrams = scanner.nextDouble();
        
        System.out.print("Enter launch speed in m/s (e.g., 8.0): ");
        double launchVelocity = scanner.nextDouble();
        
        System.out.print("Enter launch angle in degrees (e.g., 15.0): ");
        double launchAngle = scanner.nextDouble();
        
        // Capturing the initial release height
        System.out.print("Enter initial launch height in meters (e.g., 1.6): ");
        double launchHeight = scanner.nextDouble();

        System.out.println("\nInitializing simulation parameters...");
        System.out.println("Parameters: Weight=" + weightGrams + "g, Speed=" + launchVelocity + "m/s, Angle=" + launchAngle + "°, Height=" + launchHeight + "m");

        // Execute the simulation with the user's custom values
        simulateFlight(weightGrams, launchVelocity, launchAngle, launchHeight);
        
        // Closing the scanner resource after use
        scanner.close();
    }

    // Flight simulation method
    public static void simulateFlight(double weightGrams, double launchVelocity, double launchAngle, double launchHeight) {
        // Converting degrees to radians for trigonometric functions
        double angleRad = Math.toRadians(launchAngle);
        
        // Resolving the initial velocity vector into independent 2D components (x and y)
        double vx = launchVelocity * Math.cos(angleRad); // Horizontal velocity component
        double vy = launchVelocity * Math.sin(angleRad); // Vertical velocity component
        
        // Initial coordinate conditions
        double x = 0.0;          // Starts at 0 meters horizontal distance
        double y = launchHeight; // Dynamic baseline altitude based on user input
        
        double time = 0.0; // Master clock tracking flight duration
        double dt = 0.1;   // Time step interval (calculates position updates every 0.1 seconds)
        
        double g = 9.81;   // Acceleration due to Earth's gravity (m/s^2)
        
        // Converting mass to standard international units (kg) for force equations
        double massKg = weightGrams / 1000.0;

        // Aerodynamics baseline: Fluid drag coefficient representing basic air resistance force shape
        double airDensity = 1.225; // kg/m^3 standard sea-level air density
        double wingArea = 0.025;   // m^2 reference surface area of standard folded A4 sheet
        double dragCoefficient = 0.2; // Baseline drag profile for folded paper gliders
        
        // F_drag constant grouping: 0.5 * rho * A * Cd
        double dragFactor = 0.5 * airDensity * wingArea * dragCoefficient;

        System.out.println("\n--- Flight Simulation Started ---");
        System.out.printf("Time: %.1fs | X: %.2fm | Y: %.2fm\n", time, x, y);

        // Loop runs as long as plane is above or at ground level
        while (y >= 0) {
            time += dt;
            
            // Drag Force = dragFactor * v^2. 
            // Acceleration from drag (a = F/m) updates velocity dynamically based on mass.
            double axDrag = -(dragFactor * vx * Math.abs(vx)) / massKg;
            double ayDrag = -(dragFactor * vy * Math.abs(vy)) / massKg;

            // Update velocity components by applying acceleration steps (v = u + at)
            vx += axDrag * dt;
            vy += (-g + ayDrag) * dt; 
            
            // Numerical integration updating positions (dx = v * dt)
            x += vx * dt; 
            y += vy * dt; 

            // Break the loop if the current time step caused the plane to land
            if (y <= 0) {
                break;
            }

            System.out.printf("Time: %.1fs | X: %.2fm | Y: %.2fm\n", time, x, y);
        }

        // Terminal output once the boundary condition breaks the loop
        System.out.println("--- Flight Ended (Hit the Ground) ---");
        System.out.printf("\nFinal Results:\nTotal Distance Flown: %.2f meters\nTotal Flight Time: %.1f seconds\n", x, time);
    }
}

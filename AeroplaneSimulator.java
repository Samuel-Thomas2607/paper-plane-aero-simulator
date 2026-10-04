import java.util.Scanner;

// Main driver class for the simulation application
public class AeroplaneSimulator {

    // Main method
    public static void main(String[] args) {
        // Initialize scanner
        Scanner scanner = new Scanner(System.in);

        System.out.println("Paper Plane Simulator");
        
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

    // Flight simulator method
    public static void simulateFlight(double weightGrams, double launchVelocity, double launchAngle, double launchHeight) {
        // Converting degrees to radians for trigonometric functions
        double angleRad = Math.toRadians(launchAngle);
        
        //Resolving the initial velocity vector into independent 2D components (x and y)
        double vx = launchVelocity * Math.cos(angleRad); // Horizontal velocity component
        double vy = launchVelocity * Math.sin(angleRad); // Vertical velocity component
        
        // Initial coordinate conditions
        double x = 0.0;          // Starts at 0 meters horizontal distance
        double y = launchHeight; // baseline altitude based on user input
        
        double time = 0.0; // Master clock tracking flight duration
        double dt = 0.1;   // Time step interval (calculates position updates every 0.1 seconds)
        
        double g = 9.81;   // Acceleration due to Earth's gravity (m/s^2)
        
        // Aerodynamics baseline: Simplified fluid drag coefficient representing air resistance acting on paper
        double dragCoefficient = 0.05; 

        System.out.println("\n--- Flight Simulation Started ---");
        // Using printf for clean, formatted numerical telemetry strings
        System.out.printf("Time: %.1fs | X: %.2fm | Y: %.2fm\n", time, x, y);

        // While loop continues executing until the plane hits the ground (y <= 0)
        while (y > 0) {
            // Advance the simulation clock by the set time interval
            time += dt;
            
            // Non-uniform acceleration due to fluid dynamics.
            // Drag force acts in the opposite direction of motion, slowing the horizontal velocity over time.
            vx -= dragCoefficient * vx * dt;
            
            // Net forces acting along the vertical axis.
            // Gravitational acceleration (g) pulling downward combined with vertical air resistance.
            vy -= (g + (dragCoefficient * vy)) * dt; 
            
            // Numerical Integration. 
            // Updating displacement over time step dt based on instantaneous velocity (dx = v * dt).
            x += vx * dt; // New horizontal position
            y += vy * dt; // New vertical position/altitude

            // Conditional statement ensuring we only print telemetry data if the plane is still airborne
            if (y > 0) {
                System.out.printf("Time: %.1fs | X: %.2fm | Y: %.2fm\n", time, x, y);
            }
        }

        // Terminal output once the boundary condition (y <= 0) breaks the while loop
        System.out.println("--- Flight Ended (Hit the Ground) ---");
        System.out.printf("\nFinal Results:\nTotal Distance Flown: %.2f meters\nTotal Flight Time: %.1f seconds\n", x, time);
    }
}

import java.util.Arrays;
import java.util.Scanner;

public class FareSplitter {
    private String tripId;
    private double totalFare;
    private int passengerCount;

    // Primary constructor containing validation logic
    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0) {
            throw new IllegalArgumentException("Fare cannot be negative.");
        }
        if (passengerCount <= 0) {
            throw new IllegalArgumentException("Passenger count must be positive.");
        }

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    // Constructor chaining: totalFare only (defaults passengerCount to 1)
    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 1);
    }

    // Constructor chaining: tripId only (provisional split, defaults totalFare to 0.0, passengerCount to 2)
    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    // Generates fair breakdown ensuring the total sum equals the original totalFare exactly
    public double[] fareBreakdown() {
        double[] breakdown = new double[passengerCount];

        // Convert total fare into cents/paisa to prevent floating-point rounding errors
        long totalCents = Math.round(totalFare * 100.0);
        long baseShareCents = totalCents / passengerCount;
        long remainderCents = totalCents % passengerCount;

        // Base share assignment rounded to 2 decimal places
        double baseShare = baseShareCents / 100.0;
        for (int i = 0; i < passengerCount; i++) {
            breakdown[i] = baseShare;
        }

        // Add leftover cents/paisa to the last share to ensure exact sum matches
        if (remainderCents > 0) {
            breakdown[passengerCount - 1] = Math.round((breakdown[passengerCount - 1] + (remainderCents / 100.0)) * 100.0) / 100.0;
        }

        return breakdown;
    }

    // Returns true if confirmed count is less than expected count
    public boolean isConfirmationOverdue(int confirmed, int expected) {
        if (expected <= 0 || confirmed < 0) {
            return true;
        }
        return confirmed < expected;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input 1: Full parameters (e.g., TRIP001 100000 3)
        System.out.print("Enter Trip ID, Total Fare, Passenger Count: ");
        String tripId1 = scanner.next();
        double fare1 = scanner.nextDouble();
        int count1 = scanner.nextInt();

        FareSplitter fs1 = new FareSplitter(tripId1, fare1, count1);
        System.out.println("Output 1: " + Arrays.toString(fs1.fareBreakdown()));

        // Input 2: Provisional split with Trip ID only (e.g., TRIP003)
        System.out.print("Enter Trip ID for provisional split: ");
        String tripId2 = scanner.next();

        FareSplitter fs2 = new FareSplitter(tripId2);
        System.out.println("Output 2: " + Arrays.toString(fs2.fareBreakdown()));

        scanner.close();
    }
}
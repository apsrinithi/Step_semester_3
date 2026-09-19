import java.util.Scanner;

public final class SurgeFeeCalculator {

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        if (minimumSurgePercent < 0) {
            throw new IllegalArgumentException("Minimum surge percent cannot be negative.");
        }
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Order value and delay minutes must be non-negative.");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        int tier1Minutes = Math.min(delayMinutes, 5);
        int tier2Minutes = Math.max(0, Math.min(delayMinutes - 5, 10));
        int tier3Minutes = Math.max(0, delayMinutes - 15);

        double tier1Fee = tier1Minutes * (0.005 * orderValue);
        double tier2Fee = tier2Minutes * (0.010 * orderValue);
        double tier3Fee = tier3Minutes * (0.020 * orderValue);

        double tieredCalculatedFee = tier1Fee + tier2Fee + tier3Fee;
        double minimumSurgeFloor = (minimumSurgePercent / 100.0) * orderValue;

        return Math.max(tieredCalculatedFee, minimumSurgeFloor);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter minimum surge percent (e.g., 1.0): ");
        double minPercent = scanner.nextDouble();

        SurgeFeeCalculator calculator = new SurgeFeeCalculator(minPercent);

        System.out.print("Enter order value: ");
        double orderValue = scanner.nextDouble();

        System.out.print("Enter delay minutes: ");
        int delayMinutes = scanner.nextInt();

        double surgeFee = calculator.calculateSurgeFee(orderValue, delayMinutes);
        System.out.println("Rs " + surgeFee);

        scanner.close();
    }
}
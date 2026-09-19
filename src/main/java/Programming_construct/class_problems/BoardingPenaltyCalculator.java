import java.util.Scanner;

public final class BoardingPenaltyCalculator {

    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        if (minimumPenaltyPercent < 0) {
            throw new IllegalArgumentException("Minimum penalty percent cannot be negative.");
        }
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {
        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException("Ticket fare and minutes late must be non-negative.");
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        int tier1Minutes = Math.min(minutesLate, 5);
        int tier2Minutes = Math.max(0, Math.min(minutesLate - 5, 10));
        int tier3Minutes = Math.max(0, minutesLate - 15);

        double tier1Penalty = tier1Minutes * (0.005 * ticketFare);
        double tier2Penalty = tier2Minutes * (0.010 * ticketFare);
        double tier3Penalty = tier3Minutes * (0.020 * ticketFare);

        double tieredCalculatedPenalty = tier1Penalty + tier2Penalty + tier3Penalty;

        double minimumPenaltyFloor = (minimumPenaltyPercent / 100.0) * ticketFare;

        return Math.max(tieredCalculatedPenalty, minimumPenaltyFloor);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter minimum penalty percent (e.g., 1.0): ");
        double minPercent = scanner.nextDouble();

        BoardingPenaltyCalculator calculator = new BoardingPenaltyCalculator(minPercent);

        System.out.print("Enter ticket fare: ");
        double fare = scanner.nextDouble();

        System.out.print("Enter minutes late: ");
        int minutes = scanner.nextInt();

        double penalty = calculator.calculatePenalty(fare, minutes);
        System.out.println("Rs " + penalty);

        scanner.close();
    }
}
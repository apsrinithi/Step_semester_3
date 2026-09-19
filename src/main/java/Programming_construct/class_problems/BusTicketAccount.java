public class BusTicketAccount {

    private String bookingId;
    private double ticketFare;

    private static double DEFAULT_FARE;

    static {
        DEFAULT_FARE = 500.0;
    }

    public BusTicketAccount(String bookingId, double ticketFare) {
        if (bookingId == null || bookingId.trim().isEmpty()) {
            throw new IllegalArgumentException("Booking ID cannot be empty.");
        }
        if (ticketFare < 0) {
            throw new IllegalArgumentException("Ticket fare cannot be negative.");
        }
        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, DEFAULT_FARE);
    }

    public String getBookingId() {
        return this.bookingId;
    }

    public double getTicketFare() {
        return this.ticketFare;
    }

    public final double calculatePenalty(int minutesLate) {
        if (minutesLate <= 0) {
            return 0.0;
        }

        int tier1Minutes = Math.min(minutesLate, 5);
        int tier2Minutes = Math.max(0, Math.min(minutesLate - 5, 10));
        int tier3Minutes = Math.max(0, minutesLate - 15);

        double tier1Penalty = tier1Minutes * (0.005 * ticketFare);
        double tier2Penalty = tier2Minutes * (0.010 * ticketFare);
        double tier3Penalty = tier3Minutes * (0.020 * ticketFare);

        return tier1Penalty + tier2Penalty + tier3Penalty;
    }

    public void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        double penalty = calculatePenalty(minutesLate);
        System.out.println("Processing account " + bookingId + " | Penalty: " + penalty);
    }

    public static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
        if (accounts == null || amounts == null || minutesLateArray == null) {
            System.out.println("Invalid input arrays.");
            return;
        }

        if (accounts.length != amounts.length || accounts.length != minutesLateArray.length) {
            throw new IllegalArgumentException("Array lengths must match for safe reconciliation.");
        }

        int processed = 0;
        int nullSkipped = 0;
        int sleeperCount = 0;
        int regularCount = 0;
        double grandTotalPenalties = 0.0;

        for (int i = 0; i < accounts.length; i++) {
            BusTicketAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (account instanceof Sleeper) {
                sleeperCount++;
            } else {
                regularCount++;
            }

            double penalty = account.calculatePenalty(minutesLateArray[i]);
            grandTotalPenalties += penalty;
        }

        System.out.println(processed + " processed | " + nullSkipped + " null skipped | "
                + sleeperCount + " sleeper | " + regularCount + " regular | grand total penalties = " + grandTotalPenalties);
    }

    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
            new Sleeper("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };
        double[] amounts = {1200, 900, 700};
        int[] minutesLateArray = {10, 5, 0};

        processBatch(accounts, amounts, minutesLateArray);
    }
}

class Sleeper extends BusTicketAccount {
    public Sleeper(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }

    public Sleeper(String bookingId) {
        super(bookingId);
    }
}
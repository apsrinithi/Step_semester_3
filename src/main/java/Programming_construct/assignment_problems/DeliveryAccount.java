import java.util.Scanner;

public class DeliveryAccount {

    private String studentId;
    private double orderValue;

    private static double DEFAULT_ORDER_VALUE;

    // Static block to setup one-time, class-level state
    static {
        DEFAULT_ORDER_VALUE = 300.0;
    }

    public DeliveryAccount(String studentId, double orderValue) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        if (orderValue < 0) {
            throw new IllegalArgumentException("Order value cannot be negative.");
        }
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    // Provisional constructor chaining to the full constructor
    public DeliveryAccount(String studentId) {
        this(studentId, DEFAULT_ORDER_VALUE);
    }

    public String getStudentId() {
        return this.studentId;
    }

    public double getOrderValue() {
        return this.orderValue;
    }

    // Final surge fee calculation (using tiered rate structure)
    public final double calculateSurgeFee(int delayMinutes) {
        if (delayMinutes <= 0) {
            return 0.0;
        }

        int tier1Minutes = Math.min(delayMinutes, 5);
        int tier2Minutes = Math.max(0, Math.min(delayMinutes - 5, 10));
        int tier3Minutes = Math.max(0, delayMinutes - 15);

        double tier1Fee = tier1Minutes * (0.005 * orderValue);
        double tier2Fee = tier2Minutes * (0.010 * orderValue);
        double tier3Fee = tier3Minutes * (0.020 * orderValue);

        return tier1Fee + tier2Fee + tier3Fee;
    }

    public void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        if (account == null) {
            return;
        }
        double surgeFee = account.calculateSurgeFee(delayMinutes);
        System.out.println("Processing account " + account.getStudentId() + " | Surge Fee: " + surgeFee);
    }

    public static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        if (accounts == null || amounts == null || delayMinutesArray == null) {
            System.out.println("Invalid input arrays.");
            return;
        }

        // Safety check: Mismatched array lengths can cause serious reconciliation errors
        if (accounts.length != amounts.length || accounts.length != delayMinutesArray.length) {
            throw new IllegalArgumentException("Array lengths must match for safe reconciliation.");
        }

        int processed = 0;
        int nullSkipped = 0;
        int premiumCount = 0;
        int regularCount = 0;
        double grandTotalSurgeFees = 0.0;

        for (int i = 0; i < accounts.length; i++) {
            DeliveryAccount account = accounts[i];

            // Safely skip null entries without crashing
            if (account == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            // Use instanceof to check account type
            if (account instanceof Premium) {
                premiumCount++;
            } else {
                regularCount++;
            }

            double surgeFee = account.calculateSurgeFee(delayMinutesArray[i]);
            grandTotalSurgeFees += surgeFee;
        }

        System.out.println(processed + " processed | " + nullSkipped + " null skipped | "
                + premiumCount + " premium | " + regularCount + " regular | grand total surge fees = " + grandTotalSurgeFees);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of batch entries: ");
        int n = Integer.parseInt(scanner.nextLine().trim());

        DeliveryAccount[] accounts = new DeliveryAccount[n];
        double[] amounts = new double[n];
        int[] delayMinutesArray = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Entry " + (i + 1) + " ---");
            System.out.print("Is this entry null? (yes/no): ");
            String isNull = scanner.nextLine().trim();

            if (isNull.equalsIgnoreCase("yes")) {
                accounts[i] = null;
                amounts[i] = 0;
                delayMinutesArray[i] = 0;
                continue;
            }

            System.out.print("Enter Account Type (1 for Regular, 2 for Premium): ");
            int type = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter Student ID: ");
            String id = scanner.nextLine().trim();

            System.out.print("Enter Order Value: ");
            double orderVal = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Enter Settled Amount: ");
            amounts[i] = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Enter Delay Minutes: ");
            delayMinutesArray[i] = Integer.parseInt(scanner.nextLine().trim());

            if (type == 2) {
                accounts[i] = new Premium(id, orderVal);
            } else {
                accounts[i] = new DeliveryAccount(id, orderVal);
            }
        }

        System.out.println("\n--- Running Batch Processing ---");
        processBatch(accounts, amounts, delayMinutesArray);

        scanner.close();
    }
}

class Premium extends DeliveryAccount {
    public Premium(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    public Premium(String studentId) {
        super(studentId);
    }
}
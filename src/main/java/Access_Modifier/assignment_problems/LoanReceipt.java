import java.util.Arrays;
import java.util.Scanner;

public class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    static {
        // One-time static initialization
    }

    public LoanReceipt(String memberId, String[] bookIds) {
        if (memberId == null || bookIds == null) {
            throw new IllegalArgumentException("construction rejected");
        }

        for (String id : bookIds) {
            if (id == null || !id.matches("BK-\\d{3}")) {
                throw new IllegalArgumentException("construction rejected");
            }
        }

        this.memberId = memberId;
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= bookIds.length) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        String[] updatedIds = getBookIds();
        updatedIds[index] = newId;
        return new LoanReceipt(this.memberId, updatedIds);
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        if (receipts == null) {
            return "0 processed | 0 null skipped | 0 reference-only | 0 regular";
        }

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnlyCount = 0;
        int regularCount = 0;

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnlyCount++;
            } else {
                regularCount++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | "
                + referenceOnlyCount + " reference-only | " + regularCount + " regular";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Immutable Loan Receipt Ledger ===");
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine().trim();

        System.out.print("Enter Book IDs (separated by space, e.g., BK-100 BK-101): ");
        String rawBookIds = scanner.nextLine().trim();
        String[] bookIds = rawBookIds.isEmpty() ? new String[0] : rawBookIds.split("\\s+");

        try {
            LoanReceipt receipt = new LoanReceipt(memberId, bookIds);
            System.out.println("\nReceipt successfully created for Member: " + receipt.getMemberId());
            System.out.println("Book IDs: " + Arrays.toString(receipt.getBookIds()));

            // Demonstrating defensive copying on getter
            String[] leakedIds = receipt.getBookIds();
            if (leakedIds.length > 0) {
                leakedIds[0] = "HACKED";
            }
            System.out.println("After attempting external tamper, internal Book IDs: " + Arrays.toString(receipt.getBookIds()));

            // Demonstrating batch processing
            LoanReceipt[] batch = new LoanReceipt[] {
                new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
                null,
                new LoanReceipt("LIB-002", new String[]{"BK-201"})
            };

            System.out.println("\n--- Batch Processing Output ---");
            System.out.println(processNightlyCirculation(batch));

        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage());
        }

        scanner.close();
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}
import java.util.Scanner;

public class PatientRecord {
    // 1. Choose appropriate access modifiers for fields
    private String patientId;
    String wardCode;             // default (package-private) access
    protected double vitalsScore;
    public String facilityName;

    // Parameterized constructor validating fields
    public PatientRecord(String patientId, String wardCode, double vitalsScore, String facilityName) {
        if (patientId == null || patientId.trim().isEmpty() || patientId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.patientId = patientId.trim();
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }

    public String getPatientId() {
        return patientId;
    }

    // Method to classify access permission according to Java visibility rules
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        switch (fieldModifier) {
            case "public":
                return "ALLOWED";

            case "protected":
                if ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "default":
                if ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "private":
                if ("SAME_CLASS".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";

            default:
                return "DENIED";
        }
    }

    // Method to process and summarize a batch of access attempts
    public static String summarizeBatch(String[][] attempts) {
        int allowedCount = 0;
        int deniedCount = 0;

        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt != null && attempt.length >= 2) {
                    String result = classifyAccess(attempt[0], attempt[1]);
                    if ("ALLOWED".equals(result)) {
                        allowedCount++;
                    } else {
                        deniedCount++;
                    }
                }
            }
        }

        return "Allowed: " + allowedCount + " | Denied: " + deniedCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- Interactive Patient Record Creation Test ---
        System.out.println("--- Patient Record Test ---");
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();

        System.out.print("Enter Ward Code: ");
        String wardCode = scanner.nextLine();

        System.out.print("Enter Vitals Score: ");
        double vitalsScore = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Enter Facility Name: ");
        String facilityName = scanner.nextLine();

        try {
            PatientRecord record = new PatientRecord(patientId, wardCode, vitalsScore, facilityName);
            System.out.println("Successfully created record for ID: " + record.getPatientId());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // --- Interactive Access Engine Batch Test ---
        System.out.println("\n--- Access Rule Engine Test ---");
        System.out.print("Enter number of access attempts to test: ");
        int n = Integer.parseInt(scanner.nextLine().trim());

        String[][] attempts = new String[n][2];
        for (int i = 0; i < n; i++) {
            System.out.println("\nAttempt " + (i + 1) + ":");
            System.out.print("Enter Field Modifier (private/default/protected/public): ");
            attempts[i][0] = scanner.nextLine().trim();

            System.out.print("Enter Access Context (SAME_CLASS/SAME_PACKAGE/DIFFERENT_PACKAGE): ");
            attempts[i][1] = scanner.nextLine().trim();
        }

        String summary = summarizeBatch(attempts);
        System.out.println("\nBatch Summary: " + summary);

        scanner.close();
    }
}
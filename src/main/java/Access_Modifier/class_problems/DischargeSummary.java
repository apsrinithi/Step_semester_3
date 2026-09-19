import java.util.Arrays;

public class DischargeSummary {
    private final String patientId;
    private final String[] medicationCodes;

    // Static block to set up one-time shared state if needed
    static {
        // Initialization of shared state/resources
    }

    public DischargeSummary(String patientId, String[] medicationCodes) {
        if (patientId == null || medicationCodes == null) {
            throw new IllegalArgumentException("construction rejected");
        }

        // Validate each medication code format: "MED-" followed by exactly one uppercase letter [A-Z]
        for (String code : medicationCodes) {
            if (code == null || !code.matches("MED-[A-Z]")) {
                throw new IllegalArgumentException("construction rejected");
            }
        }

        this.patientId = patientId;
        // Defensive copy on the way in
        this.medicationCodes = Arrays.copyOf(medicationCodes, medicationCodes.length);
    }

    public String getPatientId() {
        return patientId;
    }

    // Defensive copy on the way out
    public String[] getMedicationCodes() {
        return Arrays.copyOf(medicationCodes, medicationCodes.length);
    }

    // Wither-style method: returns a brand-new object with the updated code
    public DischargeSummary withCorrectedMedication(int index, String newCode) {
        if (index < 0 || index >= medicationCodes.length) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        String[] updatedCodes = getMedicationCodes();
        updatedCodes[index] = newCode;
        return new DischargeSummary(this.patientId, updatedCodes);
    }

    public static String processNightlyBatch(DischargeSummary[] summaries) {
        if (summaries == null) {
            return "0 processed | 0 null skipped | 0 critical-care | 0 routine";
        }

        int processed = 0;
        int nullSkipped = 0;
        int criticalCareCount = 0;
        int routineCount = 0;

        for (DischargeSummary summary : summaries) {
            if (summary == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (summary instanceof CriticalCareDischargeSummary) {
                criticalCareCount++;
            } else {
                routineCount++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " 
                + criticalCareCount + " critical-care | " + routineCount + " routine";
    }

    public static void main(String[] args) {
        // Test 1: Invalid code format rejection
        try {
            new DischargeSummary("MT2026-0142", new String[]{"MED-A", "bad"});
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // Output: construction rejected
        }

        // Test 2: Immutability / Defensive Copying
        DischargeSummary d = new DischargeSummary("MT2026-0142", new String[]{"MED-A", "MED-B"});
        String[] codes = d.getMedicationCodes();
        codes[0] = "TAMPERED";
        System.out.println(d.getMedicationCodes()[0]); // Output: MED-A

        // Test 3: Wither pattern
        DischargeSummary corrected = d.withCorrectedMedication(1, "MED-C");
        System.out.println(corrected.getMedicationCodes()[1]); // Output: MED-C

        // Test 4: Batch processing
        DischargeSummary[] batch = new DischargeSummary[]{
            new CriticalCareDischargeSummary("MT001", new String[]{"MED-X"}, 4),
            null,
            new DischargeSummary("MT002", new String[]{"MED-Y"})
        };
        System.out.println(processNightlyBatch(batch));
        // Output: "2 processed | 1 null skipped | 1 critical-care | 1 routine"
    }
}

// Subclass for critical care discharges
class CriticalCareDischargeSummary extends DischargeSummary {
    private final int icuDays;

    public CriticalCareDischargeSummary(String patientId, String[] medicationCodes, int icuDays) {
        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }

    public int getIcuDays() {
        return icuDays;
    }
}
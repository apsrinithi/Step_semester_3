import java.io.Serializable;
import java.util.Scanner;

public class PatientProfile implements Serializable {
    private String patientId;
    private String name;
    private boolean discharged;
    private String lockerPin;

    // Flag to ensure patientId is write-once
    private boolean patientIdSet = false;

    // 1. Full Parameterized Constructor (Primary initialization path)
    public PatientProfile(String patientId, String name) {
        if (patientId != null) {
            setPatientId(patientId);
        }
        this.name = name;
        this.discharged = false;
    }

    // 2. Single-Argument Constructor (Chained using this(...))
    public PatientProfile(String name) {
        this(null, name);
    }

    // 3. No-Argument Constructor (JavaBean compliance, Chained using this(...))
    public PatientProfile() {
        this(null, null);
    }

    // JavaBean Getters and Setters

    // Write-once setter for patientId
    public void setPatientId(String patientId) {
        if (!this.patientIdSet && patientId != null) {
            this.patientId = patientId;
            this.patientIdSet = true;
        }
    }

    public String getPatientId() {
        return this.patientId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    // Boolean getter according to JavaBean naming conventions (isX)
    public boolean isDischarged() {
        return this.discharged;
    }

    // Write-only property for locker PIN (No matching getter exists anywhere)
    public void setLockerPin(String lockerPin) {
        if (lockerPin != null && lockerPin.matches("\\d{4,6}")) {
            // Store hashed/transformed value for security
            this.lockerPin = String.valueOf(lockerPin.hashCode());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- JavaBean & Constructor Chaining Test ---");

        // Test 1: Name-only constructor
        PatientProfile p1 = new PatientProfile("Arjun Iyer");
        System.out.println("p1.getPatientId(): " + p1.getPatientId()); // Output: null
        System.out.println("p1.getName(): " + p1.getName());           // Output: Arjun Iyer

        // Test 2: Full constructor
        PatientProfile p2 = new PatientProfile("MT2026-0142", "Arjun Iyer");
        System.out.println("p2.getPatientId(): " + p2.getPatientId()); // Output: MT2026-0142

        // Test 3: Write-once patientId enforcement
        PatientProfile p3 = new PatientProfile();
        p3.setPatientId("MT2026-0142");
        System.out.println("p3.getPatientId() after 1st set: " + p3.getPatientId()); // Output: MT2026-0142
        
        p3.setPatientId("HACKED-0000"); // Second set is silently ignored
        System.out.println("p3.getPatientId() after 2nd set: " + p3.getPatientId()); // Output: MT2026-0142

        // Test 4: Locker PIN write-only test
        System.out.print("\nEnter a 4-6 digit locker PIN: ");
        String pin = scanner.nextLine().trim();
        p3.setLockerPin(pin);
        System.out.println("Locker PIN set successfully (write-only property, no getter available).");

        scanner.close();
    }
}
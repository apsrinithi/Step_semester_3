import java.util.Arrays;
import java.util.Scanner;

public class PatientVitals {
    private double[] readings;
    private int count;

    public PatientVitals(double[] initialReadings) {
        this.readings = new double[500];
        this.count = 0;

        if (initialReadings != null) {
            for (double reading : initialReadings) {
                recordReading(reading);
            }
        }
    }

    public void recordReading(double reading) {
        if (reading <= 0 || reading > 45.0) {
            return;
        }
        if (count < readings.length) {
            readings[count++] = reading;
        }
    }

    public double getAverage() {
        if (count == 0) {
            return 0.0;
        }
        double sum = 0;
        for (int i = 0; i < count; i++) {
            sum += readings[i];
        }
        return sum / count;
    }

    public double[] getAllReadings() {
        return Arrays.copyOf(readings, count);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter initial readings separated by space: ");
        String line = scanner.nextLine().trim();

        double[] initial;
        if (line.isEmpty()) {
            initial = new double[0];
        } else {
            String[] parts = line.split("\\s+");
            initial = new double[parts.length];
            for (int i = 0; i < parts.length; i++) {
                initial[i] = Double.parseDouble(parts[i]);
            }
        }

        PatientVitals vitals = new PatientVitals(initial);

        System.out.println("Valid Initial Readings: " + Arrays.toString(vitals.getAllReadings()));
        System.out.println("Average Reading: " + vitals.getAverage());

        // Demonstrate defensive copy
        double[] copy = vitals.getAllReadings();
        if (copy.length > 0) {
            copy[0] = 999.0;
            System.out.println("First reading after modifying copy: " + vitals.getAllReadings()[0]);
        }

        scanner.close();
    }
}
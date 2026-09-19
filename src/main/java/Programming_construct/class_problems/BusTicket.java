import java.util.Scanner;

public class BusTicket {
    private String passengerName;
    private String destination;
    private boolean isCheckedIn;
    public BusTicket(String passengerName, String destination) {
        if (!isValidName(passengerName)) {
            throw new IllegalArgumentException("Invalid passenger name.");
        }
        if (!isValidDestination(destination)) {
            throw new IllegalArgumentException("Invalid destination.");
        }

        this.passengerName = passengerName;
        this.destination = destination;
        this.isCheckedIn = false;
    }

    private static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        return name.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$");
    }

    private static boolean isValidDestination(String destination) {
        if (destination == null || destination.trim().isEmpty()) {
            return false;
        }
        return destination.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$");
    }
    public void markCheckedIn() {
        if (this.isCheckedIn) {
            throw new IllegalStateException("Ticket is already checked in.");
        }
        this.isCheckedIn = true;
    }

    public static void processBatch(String[][] rawBookings) {
        int validCount = 0;
        int rejectedCount = 0;
        int duplicateCount = 0;

        if (rawBookings != null) {
            int total = rawBookings.length;
            String[] acceptedNames = new String[total];
            String[] acceptedDestinations = new String[total];
            int acceptedSize = 0;

            for (String[] booking : rawBookings) {
                if (booking == null || booking.length < 2) {
                    rejectedCount++;
                    continue;
                }

                String name = booking[0];
                String dest = booking[1];

                try {
                    BusTicket ticket = new BusTicket(name, dest);
                    boolean isDuplicate = false;
                    for (int i = 0; i < acceptedSize; i++) {
                        if (acceptedNames[i].equals(name) && acceptedDestinations[i].equals(dest)) {
                            isDuplicate = true;
                            break;
                        }
                    }

                    if (isDuplicate) {
                        duplicateCount++;
                    } else {
                        acceptedNames[acceptedSize] = name;
                        acceptedDestinations[acceptedSize] = dest;
                        acceptedSize++;
                        validCount++;
                    }

                } catch (IllegalArgumentException e) {
                    rejectedCount++;
                }
            }
        }

        System.out.println("Valid: " + validCount + " | Rejected: " + rejectedCount + " | Duplicates skipped: " + duplicateCount);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of bookings: ");
        int n = Integer.parseInt(scanner.nextLine().trim());

        String[][] rawBookings = new String[n][2];

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Booking " + (i + 1) + " ---");
            System.out.print("Enter Passenger Name: ");
            rawBookings[i][0] = scanner.nextLine();

            System.out.print("Enter Destination: ");
            rawBookings[i][1] = scanner.nextLine();
        }

        processBatch(rawBookings);

        scanner.close();
    }
}
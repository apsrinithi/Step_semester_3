import java.util.Scanner;

public class DeliverySlot {
    private String orderId;
    private String timeSlot;

    private static final String DEFAULT_SLOT = "ASAP";

    public DeliverySlot(String orderId, String timeSlot) {
        this.orderId = orderId;
        this.timeSlot = timeSlot;
    }

    public DeliverySlot(String orderId) {
        this(orderId, DEFAULT_SLOT);
    }

    public boolean isPeakHour() {
        return "12:00-13:00".equals(timeSlot) ||
               "13:00-14:00".equals(timeSlot) ||
               "19:00-20:00".equals(timeSlot) ||
               "20:00-21:00".equals(timeSlot);
    }

    public String getOrderId() {
        return orderId;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Order ID: ");
        String orderId = scanner.nextLine().trim();

        System.out.print("Enter Time Slot (or press Enter for default ASAP): ");
        String timeSlot = scanner.nextLine().trim();

        DeliverySlot slot;
        if (timeSlot.isEmpty()) {
            slot = new DeliverySlot(orderId);
        } else {
            slot = new DeliverySlot(orderId, timeSlot);
        }

        System.out.println("Order ID: " + slot.getOrderId());
        System.out.println("Time Slot: " + slot.getTimeSlot());
        System.out.println("Is Peak Hour: " + slot.isPeakHour());

        scanner.close();
    }
}
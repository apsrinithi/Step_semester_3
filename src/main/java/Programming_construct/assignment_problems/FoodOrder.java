public class FoodOrder {
    private String studentName;
    private String dishName;
    private boolean isDelivered;

    public FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid student name.");
        }
        if (dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid dish name.");
        }

        this.studentName = studentName;
        this.dishName = dishName;
        this.isDelivered = false;
    }

    public void markDelivered() {
        if (this.isDelivered) {
            System.out.println("Warning: Order for " + this.studentName + " was already delivered! Double-serve detected.");
        } else {
            this.isDelivered = true;
            System.out.println("Order for " + this.studentName + " marked as delivered.");
        }
    }

    public static void processBatch(String[][] rawOrders) {
        int validCount = 0;
        int rejectedCount = 0;

        if (rawOrders != null) {
            for (String[] order : rawOrders) {
                if (order == null || order.length < 2) {
                    rejectedCount++;
                    continue;
                }

                try {
                    FoodOrder foodOrder = new FoodOrder(order[0], order[1]);
                    validCount++;
                } catch (IllegalArgumentException e) {
                    rejectedCount++;
                }
            }
        }

        System.out.println("Valid: " + validCount + " | Rejected: " + rejectedCount);
    }

    public static void main(String[] args) {
        String[][] rawOrders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };

        processBatch(rawOrders);
    }
}
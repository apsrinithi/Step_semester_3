import java.util.Scanner;

public class BookInventory {
    private int copiesTotal;
    private int copiesAvailable;

    public BookInventory(int copiesTotal) {
        if (copiesTotal <= 0) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    public void checkOut() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    public void checkIn() {
        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Book Copy Circulation Guard ===");
        System.out.print("Enter total copies: ");
        
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input");
            scanner.close();
            return;
        }

        int total = scanner.nextInt();

        try {
            BookInventory inventory = new BookInventory(total);
            System.out.println("Inventory created. Available copies: " + inventory.getCopiesAvailable());

            System.out.print("Enter number of check-outs to perform: ");
            int checkOuts = scanner.nextInt();
            for (int i = 0; i < checkOuts; i++) {
                inventory.checkOut();
            }
            System.out.println("Available copies after check-outs: " + inventory.getCopiesAvailable());

            System.out.print("Enter number of check-ins to perform: ");
            int checkIns = scanner.nextInt();
            for (int i = 0; i < checkIns; i++) {
                inventory.checkIn();
            }
            System.out.println("Available copies after check-ins: " + inventory.getCopiesAvailable());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
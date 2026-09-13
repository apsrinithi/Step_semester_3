import java.util.Scanner;

public class ProductInventoryCsvParser {

    public static void parseInventoryRecord(String csvLine) {
        String[] fields = csvLine.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
        } else {
            String productName = fields[0].trim();
            String sku = fields[1].trim();
            String quantity = fields[2].trim();

            System.out.printf("Product: %s | SKU: %s | Qty: %s\n", productName, sku, quantity);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter CSV line: ");
        String csvLine = sc.nextLine();

        parseInventoryRecord(csvLine);

        sc.close();
    }
}
import java.util.Scanner;

public class ReverseCustomerName {

    public static String reverseCustomerName(String customerName) {
        char[] originalChars = customerName.toCharArray();
        char[] reversedChars = new char[originalChars.length];

        for (int i = 0; i < originalChars.length; i++) {
            reversedChars[i] = originalChars[originalChars.length - 1 - i];
        }

        return new String(reversedChars);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        String customerName = sc.nextLine();

        String reversedName = reverseCustomerName(customerName);

        System.out.println("Original Name: " + customerName);
        System.out.println("Reversed Name: " + reversedName);

        sc.close();
    }
}
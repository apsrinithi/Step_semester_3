import java.util.Scanner;

public class PalindromeChecker {

    // Approach 1: Iterative Comparison (two pointers moving toward center)
    public static boolean isPalindromeIterative(String text) {
        int start = 0;
        int end = text.length() - 1;

        while (start < end) {
            if (text.charAt(start) != text.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    // Approach 2: Recursion
    public static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) {
            return true;
        }

        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }

        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    // Approach 3: Array Reversal
    public static boolean isPalindromeArrayReversal(String text) {
        char[] originalArray = text.toCharArray();
        char[] reversedArray = new char[originalArray.length];

        for (int i = 0; i < originalArray.length; i++) {
            reversedArray[i] = originalArray[originalArray.length - 1 - i];
        }

        String reversedText = new String(reversedArray);
        return text.equals(reversedText);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        boolean iterativeResult = isPalindromeIterative(text);
        boolean recursiveResult = isPalindromeRecursive(text);
        boolean reversalResult = isPalindromeArrayReversal(text);

        String iterativeStr = iterativeResult ? "Palindrome" : "Not Palindrome";
        String recursiveStr = recursiveResult ? "Palindrome" : "Not Palindrome";
        String reversalStr = reversalResult ? "Palindrome" : "Not Palindrome";

        System.out.printf("Iterative: %s | Recursive: %s | Array Reversal: %s\n",
                iterativeStr, recursiveStr, reversalStr);

        sc.close();
    }
}
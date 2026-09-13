import java.util.Scanner;

public class TypingSpeedAccuracyChecker {

    public static void checkTypingAccuracy(String original, String typed) {
        int matched = 0;
        int total = original.length();
        int firstMismatchPos = -1;
        char origChar = ' ';
        char typedChar = ' ';

        for (int i = 0; i < total; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else {
                if (firstMismatchPos == -1) {
                    firstMismatchPos = i + 1;
                    origChar = original.charAt(i);
                    typedChar = typed.charAt(i);
                }
            }
        }

        double accuracy = ((double) matched / total) * 100;

        if (firstMismatchPos != -1) {
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | First Mismatch at position %d ('%c' vs '%c')\n",
                    matched, total, accuracy, firstMismatchPos, origChar, typedChar);
        } else {
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | No Mismatches\n",
                    matched, total, accuracy);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter original text: ");
        String original = sc.nextLine();

        System.out.print("Enter typed text: ");
        String typed = sc.nextLine();

        if (original.length() != typed.length()) {
            System.out.println("Error: Both strings must be of equal length.");
        } else {
            checkTypingAccuracy(original, typed);
        }

        sc.close();
    }
}
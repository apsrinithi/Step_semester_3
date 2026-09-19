import java.util.Arrays;
import java.util.Scanner;

public class Canteen {
    private String canteenCode;
    private String canteenName;
    private int trustScore;

    private static final int DEFAULT_TRUST_SCORE = 3;

    public Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, DEFAULT_TRUST_SCORE);
    }

    public String getCanteenCode() {
        return this.canteenCode;
    }

    public String getCanteenName() {
        return this.canteenName;
    }

    public int getTrustScore() {
        return this.trustScore;
    }

    public int compareTo(Canteen other) {
        if (this.trustScore != other.trustScore) {
            return Integer.compare(other.trustScore, this.trustScore);
        }

        int codeIgnoreCaseCompare = this.canteenCode.compareToIgnoreCase(other.canteenCode);
        if (codeIgnoreCaseCompare != 0) {
            return codeIgnoreCaseCompare;
        }

        int codeExactCompare = this.canteenCode.compareTo(other.canteenCode);
        if (codeExactCompare != 0) {
            return codeExactCompare;
        }

        return Integer.compare(this.canteenName.length(), other.canteenName.length());
    }

    public static Canteen[] rankCanteens(Canteen[] canteens) {
        if (canteens == null || canteens.length <= 1) {
            return canteens;
        }

        Canteen[] sorted = canteens.clone();
        int n = sorted.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sorted[j].compareTo(sorted[j + 1]) > 0) {
                    Canteen temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }

        return sorted;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of canteens: ");
        int n = Integer.parseInt(scanner.nextLine().trim());

        Canteen[] canteens = new Canteen[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Canteen " + (i + 1) + " ---");
            System.out.print("Enter Canteen Code: ");
            String code = scanner.nextLine().trim();

            System.out.print("Enter Canteen Name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter Trust Score (or press Enter to use default 3): ");
            String scoreInput = scanner.nextLine().trim();

            if (scoreInput.isEmpty()) {
                canteens[i] = new Canteen(code, name);
            } else {
                int score = Integer.parseInt(scoreInput);
                canteens[i] = new Canteen(code, name, score);
            }
        }

        Canteen[] ranked = rankCanteens(canteens);

        String[] codes = new String[ranked.length];
        for (int i = 0; i < ranked.length; i++) {
            codes[i] = ranked[i].getCanteenCode();
        }

        System.out.println("\nRanked Canteen Codes: " + Arrays.toString(codes));

        scanner.close();
    }
}
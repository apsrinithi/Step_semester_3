import java.util.Scanner;

public class LibraryIsbnValidator {

    public static String normalizeCode(String raw) {
        if (raw == null) {
            return "";
        }
        
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed;
        }

        String publisherCode = trimmed.substring(0, 3).toUpperCase();
        String rest = trimmed.substring(3);

        return publisherCode + rest;
    }

    public static String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: Wrong length";
        }

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: Non-letter publisher code";
            }
        }

        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: Non-digit body";
            }
        }

        String pubCode = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(pubCode).append("] YEAR: ")
          .append(year).append(" | CATALOG: ")
          .append(catalog);

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter raw code: ");
        String rawInput = sc.nextLine();

        String normalized = normalizeCode(rawInput);
        String result = validateAndFormat(normalized);

        System.out.println(result);

        sc.close();
    }
}
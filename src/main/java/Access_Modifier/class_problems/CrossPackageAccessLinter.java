import java.util.Scanner;

public class CrossPackageAccessLinter {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        switch (fieldModifier) {
            case "public":
                return "ALLOWED";

            case "protected":
                if ("SAME_CLASS".equals(accessorContext) ||
                    "SAME_PACKAGE".equals(accessorContext) ||
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                // In Java, a subclass in a different package CANNOT access protected members 
                // through a reference of the parent type.
                return "DENIED";

            case "default":
                if ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "private":
                if ("SAME_CLASS".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";

            default:
                return "DENIED";
        }
    }

    /**
     * Converts an underscore-separated context code into a Title-Cased string.
     * Example: "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE" -> "Subclass Different Package Parent Type"
     */
    public static String describeContext(String accessorContext) {
        if (accessorContext == null || accessorContext.trim().isEmpty()) {
            return "";
        }

        String[] parts = accessorContext.trim().split("_");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < parts.length; i++) {
            String word = parts[i];
            if (!word.isEmpty()) {
                String capitalized = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                result.append(capitalized);
                if (i < parts.length - 1) {
                    result.append(" ");
                }
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Cross-Package Access Linter Test ---");
        System.out.print("Enter Field Modifier (public/protected/default/private): ");
        String modifier = scanner.nextLine().trim();

        System.out.print("Enter Access Context: ");
        String context = scanner.nextLine().trim();

        String accessResult = classifyAccess(modifier, context);
        String contextDescription = describeContext(context);

        System.out.println("\nAccess Result: " + accessResult);
        System.out.println("Described Context: \"" + contextDescription + "\"");

        scanner.close();
    }
}
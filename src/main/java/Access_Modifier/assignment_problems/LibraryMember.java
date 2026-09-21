import java.util.Scanner;

public class LibraryMember {
    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    private LibraryMember() {}

    public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
        if (membershipId == null || membershipId.trim().isEmpty() || membershipId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.membershipId = membershipId.trim();
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        switch (fieldModifier) {
            case "public":
                return "ALLOWED";

            case "protected":
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

    public static String summarizeByModifier(String[][] attempts) {
        int privateAllowed = 0, privateDenied = 0;
        int defaultAllowed = 0, defaultDenied = 0;
        int protectedAllowed = 0, protectedDenied = 0;
        int publicAllowed = 0, publicDenied = 0;

        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt != null && attempt.length >= 2) {
                    String modifier = attempt[0];
                    String context = attempt[1];
                    boolean allowed = "ALLOWED".equals(classifyAccess(modifier, context));

                    switch (modifier) {
                        case "private":
                            if (allowed) privateAllowed++; else privateDenied++;
                            break;
                        case "default":
                            if (allowed) defaultAllowed++; else defaultDenied++;
                            break;
                        case "protected":
                            if (allowed) protectedAllowed++; else protectedDenied++;
                            break;
                        case "public":
                            if (allowed) publicAllowed++; else publicDenied++;
                            break;
                    }
                }
            }
        }

        return String.format(
            "private: %d allowed / %d denied | default: %d allowed / %d denied | protected: %d allowed / %d denied | public: %d allowed / %d denied",
            privateAllowed, privateDenied,
            defaultAllowed, defaultDenied,
            protectedAllowed, protectedDenied,
            publicAllowed, publicDenied
        );
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String membershipId = scanner.nextLine();
        String branchCode = scanner.nextLine();
        double finesOwed = Double.parseDouble(scanner.nextLine().trim());
        String displayName = scanner.nextLine();

        try {
            LibraryMember member = new LibraryMember(membershipId, branchCode, finesOwed, displayName);
            System.out.println(member.getMembershipId());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
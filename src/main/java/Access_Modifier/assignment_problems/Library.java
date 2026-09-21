import java.util.Scanner;

public class Library {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    public Library() {
        this(null, null);
    }

    public Library(String name) {
        this(null, name);
    }

    public Library(String membershipId, String name) {
        this.membershipId = membershipId;
        this.name = name;
        this.premiumMember = false;
        this.securityAnswer = null;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        if (this.membershipId == null) {
            this.membershipId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {
        this.securityAnswer = answer;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== LibraryMember JavaBean Test ===");
        System.out.print("Enter Membership ID (or press Enter to skip): ");
        String idInput = scanner.nextLine().trim();
        if (idInput.isEmpty()) {
            idInput = null;
        }

        System.out.print("Enter Name (or press Enter to skip): ");
        String nameInput = scanner.nextLine().trim();
        if (nameInput.isEmpty()) {
            nameInput = null;
        }

        Library member = new Library(idInput, nameInput);

        System.out.println("\nInitial State:");
        System.out.println("Membership ID: " + member.getMembershipId());
        System.out.println("Name: " + member.getName());
        System.out.println("Is Premium Member: " + member.isPremiumMember());

        System.out.print("\nAttempt to set/update Membership ID: ");
        String newId = scanner.nextLine().trim();
        member.setMembershipId(newId);
        System.out.println("Membership ID after set: " + member.getMembershipId());

        System.out.print("Set Security Answer (write-only property): ");
        String secAnswer = scanner.nextLine().trim();
        member.setSecurityAnswer(secAnswer);
        System.out.println("Security answer recorded (no getter exists to read it back).");

        scanner.close();
    }
}
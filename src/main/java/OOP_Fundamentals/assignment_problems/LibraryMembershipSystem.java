import java.util.Scanner;

// --- Broken Version (Reproducing the Bug) ---
class BrokenLibraryMember {
    static String name;
    static String memberId;
    static int booksIssued;

    public BrokenLibraryMember(String name, String memberId, int booksIssued) {
        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }
}

// --- Fixed Version ---
class LibraryMember {
    /*
     * Explanation:
     * - 'name', 'memberId', and 'booksIssued' must be instance fields because each library member 
     *   has their own unique personal details and book borrowing counts. Marking them static causes 
     *   a single variable copy to be shared across all instances, so creating a new member overwrites 
     *   the state of all previously created members.
     * - 'libraryName' and 'memberCount' are static fields because 'libraryName' is common to all 
     *   members belonging to the institution, and 'memberCount' maintains the global count of total 
     *   registered members across all instances to generate unique member IDs.
     */

    private String name;
    private String memberId;
    private int booksIssued;

    public static String libraryName = "Central Library";
    public static int memberCount = 0;

    public LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
        memberCount++;
        this.memberId = "LM-" + (1000 + memberCount);
    }

    public void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    public static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }
}

public class LibraryMembershipSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter name for Member 1: ");
        String name1 = sc.nextLine().trim();

        System.out.print("Enter books issued for Member 1: ");
        int books1 = sc.nextInt();
        sc.nextLine(); // consume remaining newline

        System.out.print("Enter name for Member 2: ");
        String name2 = sc.nextLine().trim();

        System.out.print("Enter books issued for Member 2: ");
        int books2 = sc.nextInt();
        sc.nextLine(); // consume remaining newline

        System.out.println("\n--- Broken Version ---");
        BrokenLibraryMember b1 = new BrokenLibraryMember(name1, "LM-1001", books1);
        BrokenLibraryMember b2 = new BrokenLibraryMember(name2, "LM-1002", books2);

        System.out.println(BrokenLibraryMember.name);
        System.out.println(BrokenLibraryMember.name);
        System.out.println("(" + name1 + "'s data was overwritten - both members now show \"" + name2 + "\")");

        System.out.println("\n--- Fixed Version ---");
        LibraryMember m1 = new LibraryMember(name1, books1);
        LibraryMember m2 = new LibraryMember(name2, books2);

        m1.printMemberCard();
        m2.printMemberCard();
        LibraryMember.printTotalMembers();

        sc.close();
    }
}
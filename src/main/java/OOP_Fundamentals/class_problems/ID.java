import java.util.Scanner;

class BrokenSrmStudent {
    static String name;
    static String regNo;

    public BrokenSrmStudent(String name, String regNo) {
        BrokenSrmStudent.name = name;
        BrokenSrmStudent.regNo = regNo;
    }
}

class SrmStudent {
    /*
     * Explanation:
     * - 'name' and 'regNo' are instance fields because each student has a unique name 
     *   and registration number. Making them static creates a single shared copy in memory,
     *   causing any new student instance to overwrite the values of previously created students.
     * - 'university' and 'admissionCount' are static fields because 'university' is a constant
     *   shared across all students, and 'admissionCount' maintains the global count of total
     *   admissions across all instances.
     */

    private String name;
    private String regNo;

    public static String university = "SRM University";
    public static int admissionCount = 0;

    public SrmStudent(String name) {
        this.name = name;
        admissionCount++;
        this.regNo = "RA23110030101" + admissionCount;
    }

    public void printIdCard() {
        System.out.println(name + " | " + regNo);
    }

    public static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + admissionCount);
    }
}

public class ID {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter name for Student 1: ");
        String name1 = sc.nextLine().trim();

        System.out.print("Enter name for Student 2: ");
        String name2 = sc.nextLine().trim();

        System.out.println("\n--- Broken Version ---");
        BrokenSrmStudent b1 = new BrokenSrmStudent(name1, "RA231100301011");
        BrokenSrmStudent b2 = new BrokenSrmStudent(name2, "RA231100301012");

        System.out.println(BrokenSrmStudent.name);
        System.out.println(BrokenSrmStudent.name);
        System.out.println("(" + name1 + "'s data was overwritten \u2013 both students now show \"" + name2 + "\")");

        System.out.println("\n--- Fixed Version ---");
        SrmStudent s1 = new SrmStudent(name1);
        SrmStudent s2 = new SrmStudent(name2);

        s1.printIdCard();
        s2.printIdCard();
        SrmStudent.printTotalAdmissions();

        sc.close();
    }
}
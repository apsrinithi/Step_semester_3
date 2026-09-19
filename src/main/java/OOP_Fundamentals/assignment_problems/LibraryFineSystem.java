import java.util.Scanner;

class BookIssue {
    String title;
    String borrowerName;
    int daysOverdue;

    public BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    public double fineAmount() {
        if (daysOverdue > 0) {
            return daysOverdue * 5.0;
        }
        return 0.0;
    }

    public boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    public static double totalFineCollected(BookIssue[] issues) {
        if (issues == null) {
            return 0.0;
        }

        double totalFine = 0.0;
        for (int i = 0; i < issues.length; i++) {
            if (issues[i] != null) {
                totalFine += issues[i].fineAmount();
            }
        }
        return totalFine;
    }
}

public class LibraryFineSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BookIssue[] issues = new BookIssue[5];

        for (int i = 0; i < 5; i++) {
            System.out.print("Enter title for book " + (i + 1) + ": ");
            String title = sc.nextLine().trim();

            System.out.print("Enter borrower name for book " + (i + 1) + ": ");
            String borrowerName = sc.nextLine().trim();

            System.out.print("Enter days overdue for book " + (i + 1) + ": ");
            int daysOverdue = sc.nextInt();
            sc.nextLine(); // consume remaining newline

            issues[i] = new BookIssue(title, borrowerName, daysOverdue);
        }

        System.out.println();
        for (int i = 0; i < issues.length; i++) {
            String status = issues[i].isSeverelyOverdue() ? "Severely overdue" : "OK";
            System.out.println(issues[i].title + " - " + issues[i].daysOverdue + " days - " + status);
        }

        double totalFine = BookIssue.totalFineCollected(issues);
        System.out.println("Total fine collected: Rs " + totalFine);

        sc.close();
    }
}
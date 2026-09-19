import java.util.Scanner;

class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    public FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = 0.0;
    }

    public void pay(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid payment amount.");
            return;
        }
        this.amountPaid += amount;
    }

    public double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount {

    public HostelFeeAccount(String regNo, double totalFee) {
        super(regNo, totalFee);
    }

    public void payInTwoInstallments(double amount) {
        pay(amount);
    }
}

class ScholarshipFeeAccount extends FeeAccount {
    private double scholarshipPercent;

    public ScholarshipFeeAccount(String regNo, double totalFee, double scholarshipPercent) {
        super(regNo, totalFee);
        this.scholarshipPercent = scholarshipPercent;
    }

    public double effectiveDue() {
        double due = getDue();
        return due - (due * (scholarshipPercent / 100.0));
    }
}

public class FeeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Plain FeeAccount
        System.out.print("Enter Plain Account regNo and totalFee: ");
        String reg1 = sc.next();
        double fee1 = sc.nextDouble();
        FeeAccount plainAccount = new FeeAccount(reg1, fee1);

        System.out.print("Enter payment for Plain Account: ");
        double pay1 = sc.nextDouble();
        plainAccount.pay(pay1);

        // HostelFeeAccount
        System.out.print("Enter Hostel Account regNo and totalFee: ");
        String reg2 = sc.next();
        double fee2 = sc.nextDouble();
        HostelFeeAccount hostelAccount = new HostelFeeAccount(reg2, fee2);

        System.out.print("Enter payment for Hostel Account: ");
        double pay2 = sc.nextDouble();
        hostelAccount.payInTwoInstallments(pay2);

        // ScholarshipFeeAccount
        System.out.print("Enter Scholarship Account regNo, totalFee, and scholarship percentage: ");
        String reg3 = sc.next();
        double fee3 = sc.nextDouble();
        double scholarship = sc.nextDouble();
        ScholarshipFeeAccount scholarshipAccount = new ScholarshipFeeAccount(reg3, fee3, scholarship);

        System.out.print("Enter payment for Scholarship Account: ");
        double pay3 = sc.nextDouble();
        scholarshipAccount.pay(pay3);

        System.out.println();

        // Process accounts array using instanceof dispatch
        FeeAccount[] accounts = {plainAccount, hostelAccount, scholarshipAccount};

        for (FeeAccount acc : accounts) {
            if (acc instanceof ScholarshipFeeAccount) {
                ScholarshipFeeAccount sAcc = (ScholarshipFeeAccount) acc;
                System.out.println("Scholarship account effective due: Rs " + sAcc.effectiveDue());
            } else if (acc instanceof HostelFeeAccount) {
                System.out.println("Hostel account due: Rs " + acc.getDue());
            } else if (acc instanceof FeeAccount) {
                System.out.println("Plain account due: Rs " + acc.getDue());
            }
        }

        sc.close();
    }
}
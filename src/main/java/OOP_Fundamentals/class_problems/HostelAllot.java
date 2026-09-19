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
            System.out.println("Payment rejected: Invalid amount (" + amount + ")");
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
}

class HostelRoom {
    String roomNo;
    int beds;
    int occupied;

    public HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    public boolean allot() {
        if (occupied < beds) {
            occupied++;
            return true;
        }
        return false;
    }

    public static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        if (rooms == null) {
            return null;
        }
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] != null && rooms[i].occupied < rooms[i].beds) {
                return rooms[i];
            }
        }
        return null;
    }

    public static HostelRoom safeAllot(HostelRoom[] rooms) {
        HostelRoom room = findAvailableRoom(rooms);
        if (room != null) {
            room.allot();
            return room;
        }
        return null;
    }
}

class SrmStudent {
    String name;
    String regNo;
    HostelFeeAccount feeAccount;
    HostelRoom room;

    public static int totalStudents = 0;

    public SrmStudent(String name, String regNo, double totalFee) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = new HostelFeeAccount(regNo, totalFee);
        this.room = null;
        totalStudents++;
    }

    public void setRoom(HostelRoom room) {
        this.room = room;
    }

    public String fullStatus() {
        String roomStr = (room != null) ? room.roomNo : "unallotted";
        return name + " | Due: Rs " + feeAccount.getDue() + " | Room: " + roomStr;
    }
}

public class HostelAllot {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        HostelRoom[] rooms = {
            new HostelRoom("C-214", 3, 2),
            new HostelRoom("C-507", 2, 1)
        };

        System.out.print("Enter Student 1 name, regNo, totalFee: ");
        String name1 = sc.next();
        String reg1 = sc.next();
        double fee1 = sc.nextDouble();

        System.out.print("Enter Student 2 name, regNo, totalFee: ");
        String name2 = sc.next();
        String reg2 = sc.next();
        double fee2 = sc.nextDouble();

        System.out.print("Enter Student 3 name, regNo, totalFee: ");
        String name3 = sc.next();
        String reg3 = sc.next();
        double fee3 = sc.nextDouble();

        SrmStudent s1 = new SrmStudent(name1, reg1, fee1);
        SrmStudent s2 = new SrmStudent(name2, reg2, fee2);
        SrmStudent s3 = new SrmStudent(name3, reg3, fee3);

        s1.setRoom(HostelRoom.safeAllot(rooms));
        s2.setRoom(HostelRoom.safeAllot(rooms));

        System.out.print("Enter payment amount for " + name1 + ": ");
        double pay1 = sc.nextDouble();
        s1.feeAccount.pay(pay1);

        System.out.print("Enter payment amount for " + name2 + ": ");
        double pay2 = sc.nextDouble();
        s2.feeAccount.pay(pay2);

        System.out.print("Enter payment amount for " + name3 + " (negative/zero to test rejection): ");
        double pay3 = sc.nextDouble();
        s3.feeAccount.pay(pay3);

        System.out.println();
        System.out.println(s1.fullStatus());
        System.out.println(s2.fullStatus());
        System.out.println(s3.fullStatus());
        System.out.println("Total students: " + SrmStudent.totalStudents);

        sc.close();
    }
}
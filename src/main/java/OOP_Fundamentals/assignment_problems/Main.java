import java.util.Scanner;

class Employee {
    private String empId;
    private String empName;
    private double salary;

    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    public ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    public double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class ParkingLot {
    String slotNo;
    int capacity;
    int occupiedCount;

    public ParkingLot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    public boolean allot() {
        if (occupiedCount < capacity) {
            occupiedCount++;
            return true;
        }
        return false;
    }

    public static ParkingLot findAvailableSlot(ParkingLot[] slots) {
        if (slots == null) {
            return null;
        }
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null && slots[i].occupiedCount < slots[i].capacity) {
                return slots[i];
            }
        }
        return null;
    }

    public static ParkingLot safeAllot(ParkingLot[] slots) {
        ParkingLot slot = findAvailableSlot(slots);
        if (slot != null) {
            slot.allot();
            return slot;
        }
        return null;
    }
}

class CompanyEmployeeRecord {
    String name;
    String empId;
    Employee employee;
    ParkingLot slot;

    public static int totalRecords = 0;

    public CompanyEmployeeRecord(String name, String empId, Employee employee) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = null;
        totalRecords++;
    }

    public void setSlot(ParkingLot slot) {
        this.slot = slot;
    }

    public String fullProfile() {
        double effectivePay;
        if (employee instanceof ManagerEmployee) {
            effectivePay = ((ManagerEmployee) employee).effectiveSalary();
        } else {
            effectivePay = employee.getSalary();
        }

        String slotStr = (slot != null) ? slot.slotNo : "no parking assigned";
        return name + " | Pay: Rs " + effectivePay + " | Slot: " + slotStr;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ParkingLot[] slots = {
            new ParkingLot("A1", 4, 3),
            new ParkingLot("A2", 5, 4)
        };

        // Input Record 1 (Manager)
        System.out.print("Enter Record 1 name, empId, salary, teamBonus: ");
        String name1 = sc.next();
        String id1 = sc.next();
        double sal1 = sc.nextDouble();
        double bonus1 = sc.nextDouble();
        Employee emp1 = new ManagerEmployee(id1, name1, sal1, bonus1);

        // Input Record 2 (Plain Employee)
        System.out.print("Enter Record 2 name, empId, salary: ");
        String name2 = sc.next();
        String id2 = sc.next();
        double sal2 = sc.nextDouble();
        Employee emp2 = new Employee(id2, name2, sal2);

        // Input Record 3 (Plain Employee)
        System.out.print("Enter Record 3 name, empId, salary: ");
        String name3 = sc.next();
        String id3 = sc.next();
        double sal3 = sc.nextDouble();
        Employee emp3 = new Employee(id3, name3, sal3);

        CompanyEmployeeRecord r1 = new CompanyEmployeeRecord(name1, id1, emp1);
        CompanyEmployeeRecord r2 = new CompanyEmployeeRecord(name2, id2, emp2);
        CompanyEmployeeRecord r3 = new CompanyEmployeeRecord(name3, id3, emp3);

        // Allot parking to only two records (r3 stays unallotted)
        r1.setSlot(ParkingLot.safeAllot(slots));
        r2.setSlot(ParkingLot.safeAllot(slots));

        System.out.println();
        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);

        sc.close();
    }
}
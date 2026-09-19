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

class InternEmployee extends Employee {
    private double stipendCap;

    public InternEmployee(String empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    public double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

public class EmployeeSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Plain Employee
        System.out.print("Enter Plain Employee empId, empName, and salary: ");
        String id1 = sc.next();
        String name1 = sc.next();
        double salary1 = sc.nextDouble();
        Employee plainEmp = new Employee(id1, name1, salary1);

        // ManagerEmployee
        System.out.print("Enter Manager empId, empName, salary, and teamBonus: ");
        String id2 = sc.next();
        String name2 = sc.next();
        double salary2 = sc.nextDouble();
        double bonus = sc.nextDouble();
        ManagerEmployee managerEmp = new ManagerEmployee(id2, name2, salary2, bonus);

        // InternEmployee
        System.out.print("Enter Intern empId, empName, salary, and stipendCap: ");
        String id3 = sc.next();
        String name3 = sc.next();
        double salary3 = sc.nextDouble();
        double cap = sc.nextDouble();
        InternEmployee internEmp = new InternEmployee(id3, name3, salary3, cap);

        System.out.println();

        // Process array using instanceof dispatch
        Employee[] employees = {plainEmp, managerEmp, internEmp};

        for (Employee emp : employees) {
            if (emp instanceof ManagerEmployee) {
                ManagerEmployee m = (ManagerEmployee) emp;
                System.out.println("Manager effective pay: Rs " + m.effectiveSalary());
            } else if (emp instanceof InternEmployee) {
                InternEmployee i = (InternEmployee) emp;
                System.out.println("Intern effective pay: Rs " + i.effectiveSalary());
            } else if (emp instanceof Employee) {
                System.out.println("Plain employee pay: Rs " + emp.getSalary());
            }
        }

        sc.close();
    }
}
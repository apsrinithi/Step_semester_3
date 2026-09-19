import java.util.Scanner;

class SrmStudent {
    String name;
    String regNo;
    int attendance;

    public SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    public void addAttendanceUpdate(int newAttendance) {
        this.attendance = newAttendance;
    }

    public boolean isEligible() {
        return this.attendance >= 75;
    }
    
    public static double classAverage(SrmStudent[] students) {
        if (students == null || students.length == 0) {
            return 0.0;
        }

        int total = 0;
        for (int i = 0; i < students.length; i++) {
            total += students[i].attendance;
        }

        return (double) total / students.length;
    }
}

public class AttendanceSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        SrmStudent[] students = new SrmStudent[5];

        for (int i = 0; i < 5; i++) {
            System.out.print("Enter name for student " + (i + 1) + ": ");
            String name = sc.next();

            System.out.print("Enter regNo for student " + (i + 1) + ": ");
            String regNo = sc.next();

            System.out.print("Enter attendance (%) for student " + (i + 1) + ": ");
            int attendance = sc.nextInt();

            students[i] = new SrmStudent(name, regNo, attendance);
        }

        System.out.println();
        for (int i = 0; i < students.length; i++) {
            String status = students[i].isEligible() ? "Eligible" : "Detained";
            System.out.println(students[i].name + " - " + students[i].attendance + "% - " + status);
        }

        double avg = SrmStudent.classAverage(students);
        System.out.printf("Class average: %.1f%%\n", avg);

        sc.close();
    }
}
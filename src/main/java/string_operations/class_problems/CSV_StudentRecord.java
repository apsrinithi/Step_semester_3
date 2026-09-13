import java.util.*;
public class CSV_StudentRecord {
    public static void parseStudentRecord(String csvLine)
    {
        String data[]= csvLine.split(",");
        if(data != null && data.length == 3)
        {
           System.out.print("Name:" + data[0] + '|' + "Roll No:" + data[1] + "|" + "Dept:" + data[2]);

        }

    }
    public static void main(String[] args)
    {
        Scanner sc= new Scanner(System.in);
        String details= sc.nextLine();
        parseStudentRecord(details);
        sc.close();

    }
    
}

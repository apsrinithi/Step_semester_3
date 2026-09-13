import java.util.*;
public class File_Ext_Valid {
    public static String validFileExtension(String filename)
    {
        int lastindex= filename.lastIndexOf('.');
        if(lastindex == -1)
        {
            System.out.println("Invalid file");
        }
        String x= filename.substring(lastindex+1);
        if(x.equalsIgnoreCase("pdf")||x.equalsIgnoreCase("docx")||x.equalsIgnoreCase("zip"))
        {
            return "Accepted";
        }
        else 
            return "Rejected-invalid file type";
        
    }
    public static void main(String[] args)
    {
        Scanner sc= new Scanner(System.in);
        String name= sc.next();
        System.out.print(validFileExtension(name));
        sc.close();
    }
    
}

import java.util.*;
public class Mask_Phone_Number {
    public static void maskPhoneNumber(String phone)
    {
        if (phone.length() != 10 || !phone.matches("\\d{10}"))
        {
            System.out.println("Error: Input must be exactly 10 digits.");
            return;
        }
        for(int i= 0;i<phone.length()+1;i++)
        {
            if ( i<5)
                {
                    System.out.print("X");
                }
                else if( i==6)
                {
                    System.out.print("-");
                }
                else if( i>6)
                {
                    System.out.print(phone.charAt(i-1));
                }
            }

    }
    public static void main(String[]args)
    {
        Scanner sc= new Scanner(System.in);
        String number= sc.next();
        maskPhoneNumber(number);
        sc.close();
    }
}

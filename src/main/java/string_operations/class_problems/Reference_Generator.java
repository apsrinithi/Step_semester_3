import java.util.*;
public class Reference_Generator {
    public static String normalizeReference(String raw)
    {
        if (raw == null)
        {
            return "";
        }
        String trimmed= raw.trim();
        if(trimmed.length()<3)
        {
            return trimmed.toUpperCase();
        }
        String bankCode = trimmed.substring(0,3).toUpperCase();
        String rest = trimmed.substring(3);
        return bankCode+rest;
    }

    public static String validateAndFormat(String reference)
    {
        if(reference.length()!=14)
        {
            return "Invalid:wrong length";
        }

        for(int i=0; i<3;i++)
        {
            if (!Character.isLetter(reference.charAt(i)))
            {
                return "Invalid: bank code must be 3 letters";
            }

        }
        for(int i=3;i<14;i++)
        {
            if(!Character.isDigit(reference.charAt(i)))
            {
                return "Invalid: non-digit body";
            }
        }
        String bank = reference.substring(0, 3);
        String day = reference.substring(3, 5);
        String month = reference.substring(5, 7);
        String year = reference.substring(7, 9);
        String seq = reference.substring(9, 14);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(bank).append("] DATE: ")
          .append(day).append("/").append(month).append("/").append(year)
          .append(" | SEQ: ").append(seq);

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String input1 = sc.next();
        String norm1 = normalizeReference(input1);
        System.out.println(validateAndFormat(norm1));

        String input2 = sc.next();
        String norm2 = normalizeReference(input2);
        System.out.println(validateAndFormat(norm2));
    }
    
    
}

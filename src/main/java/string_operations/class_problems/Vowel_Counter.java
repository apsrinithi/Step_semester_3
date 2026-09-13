import java.util.*;
public class Vowel_Counter
{
    public static void countVowelsAndConsonents(String text)
{
    int vowel=0;
    int consonent=0;
    for(int i=0;i<text.length();i++)   
    {
        if(text.charAt(i)=='a'||text.charAt(i)=='A'||text.charAt(i)=='E'||text.charAt(i)=='e'||text.charAt(i)=='I'||text.charAt(i)=='i'||text.charAt(i)=='o'||text.charAt(i)=='O'||text.charAt(i)=='u'||text.charAt(i)=='U')
        {
            vowel++;
            
        }
        else 
            consonent++;
    }
    System.out.println("Vowels: " + vowel);
    System.out.println("Consonants: " + consonent);
}
    public static void main(String[] args)
    {
        Scanner sc= new Scanner(System.in);
        String input = sc.nextLine();
        countVowelsAndConsonents(input);
        sc.close();
    }
}
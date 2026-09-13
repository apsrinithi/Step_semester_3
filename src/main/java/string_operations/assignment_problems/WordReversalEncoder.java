import java.util.Scanner;

public class WordReversalEncoder {

    public static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            StringBuilder wordBuilder = new StringBuilder();
            
            for (int j = words[i].length() - 1; j >= 0; j--) {
                wordBuilder.append(words[i].charAt(j));
            }

            result.append(wordBuilder);

            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter sentence: ");
        String sentence = sc.nextLine();

        String encodedSentence = reverseEachWord(sentence);
        System.out.println(encodedSentence);

        sc.close();
    }
}
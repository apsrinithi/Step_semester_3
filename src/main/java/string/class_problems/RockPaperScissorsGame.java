import java.util.Random;
import java.util.Scanner;

public class RockPaperScissorsGame {

    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }

        if (playerMove.equalsIgnoreCase("Rock")) {
            return computerMove.equalsIgnoreCase("Scissors") ? "Player Wins" : "Computer Wins";
        } else if (playerMove.equalsIgnoreCase("Paper")) {
            return computerMove.equalsIgnoreCase("Rock") ? "Player Wins" : "Computer Wins";
        } else if (playerMove.equalsIgnoreCase("Scissors")) {
            return computerMove.equalsIgnoreCase("Paper") ? "Player Wins" : "Computer Wins";
        }

        return "Invalid Move";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        String[] moves = {"Rock", "Paper", "Scissors"};
        int totalRounds = 5;

        String[] playerMoves = new String[totalRounds];
        String[] computerMoves = new String[totalRounds];
        String[] results = new String[totalRounds];

        int wins = 0;
        int losses = 0;
        int draws = 0;

        for (int i = 0; i < totalRounds; i++) {
            System.out.print("Enter move for Round " + (i + 1) + " (Rock, Paper, Scissors): ");
            String playerMove = sc.nextLine().trim();

            String computerMove = moves[random.nextInt(3)];
            String result = playRound(playerMove, computerMove);

            playerMoves[i] = playerMove;
            computerMoves[i] = computerMove;
            results[i] = result;

            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else if (result.equals("Draw")) {
                draws++;
            }

            System.out.println("Round " + (i + 1) + " - Player: " + playerMove + ", Computer: " + computerMove);
            System.out.println("Result: " + result + "\n");
        }

        double winPercentage = ((double) wins / totalRounds) * 100;

        System.out.println("\n--- Summary Table ---");
        System.out.printf("%-8s | %-12s | %-13s | %-12s\n", "Round", "Player Move", "Computer Move", "Result");
        System.out.println("---------------------------------------------------------");

        for (int i = 0; i < totalRounds; i++) {
            System.out.printf("Round %-2d | %-12s | %-13s | %-12s\n", (i + 1), playerMoves[i], computerMoves[i], results[i]);
        }

        System.out.printf("\nWins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%\n", wins, losses, draws, winPercentage);

        sc.close();
    }
}
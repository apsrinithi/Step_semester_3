import java.util.Scanner;

public class BmiCalculatorTeam {

    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            return "Normal";
        } else if (bmi >= 25.0 && bmi <= 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static void printWellnessReport(double[] heights, double[] weights) {
        System.out.printf("%-8s | %-10s | %-11s | %-6s | %-11s\n", 
                "Person", "Height (m)", "Weight (kg)", "BMI", "Status");
        System.out.println("---------------------------------------------------------");

        for (int i = 0; i < heights.length; i++) {
            double height = heights[i];
            double weight = weights[i];
            double bmi = weight / (height * height);
            String status = getBmiStatus(bmi);

            System.out.printf("Person %-2d | %-10.2f | %-11.2f | %-6.2f | %-11s\n", 
                    (i + 1), height, weight, bmi, status);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of team members: ");
        int n = sc.nextInt();

        double[] heights = new double[n];
        double[] weights = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter height (m) for Person " + (i + 1) + ": ");
            heights[i] = sc.nextDouble();

            System.out.print("Enter weight (kg) for Person " + (i + 1) + ": ");
            weights[i] = sc.nextDouble();
        }

        System.out.println();
        printWellnessReport(heights, weights);

        sc.close();
    }
}
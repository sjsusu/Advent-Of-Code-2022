package Day1;

import java.io.File;
import java.util.Scanner;

public class Calories {
    public static void main(String[] args) {
        String filename = "input.txt";

        try {
            File file = new File(filename);
            Scanner in = new Scanner(file);

            int mostCalories = 0;
            int secondMostCalories = 0;
            int thirdMostCalories = 0;
            int sum = 0;

            /*
             * While loop reads each line of the input.txt file.
             * The number of calories carred by each elf is added to a sum.
             * If the sum is greater than any of the top 3 sums, the sum is stored in the
             * appropriate variable.
             * If the sum is less than the top 3 sums, the sum is discarded.
             * Process repeats until the end of the file is reached.
             */
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.equals("")) {
                    if (sum > mostCalories) {
                        thirdMostCalories = secondMostCalories;
                        secondMostCalories = mostCalories;
                        mostCalories = sum;

                    } else if (sum > secondMostCalories) {
                        thirdMostCalories = secondMostCalories;
                        secondMostCalories = sum;

                    } else if (sum > thirdMostCalories) {
                        thirdMostCalories = sum;
                    }

                    sum = 0;

                } else {
                    sum += Integer.parseInt(line);

                }

            }
             
            in.close();

            // Part 1 Solution: Prints out the most calories an elf is carrying
            System.out.println(mostCalories);
            // Part 2 Solution: Prints out the sum of the three elves with the most calories
            System.out.println(mostCalories + secondMostCalories + thirdMostCalories);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
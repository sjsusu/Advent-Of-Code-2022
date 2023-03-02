package Day2;

import java.util.Scanner;
import java.io.File;

public class RockPaperScissors {
    public static void main(String[] args) {
        String filename = "input.txt";

        /*
         * Key of all possible outcomes of scores based on part 1 rules
         * Rows represent A B C respectively
         * Columns represent X Y Z respectively
         */
        int[][] key = {
                { 4, 8, 3 },
                { 1, 5, 9 },
                { 7, 2, 6 }
        };

        /*
         * Key of all possible outcomes of scores based on part 2 rules
         * Rows represent A B C respectively
         * Columns represent X Y Z respectively
         */
        int[][] keyTwo = {
                { 3, 4, 8 },
                { 1, 5, 9 },
                { 2, 6, 7 }
        };

        boolean done = false;
        int total = 0;
        int totalTwo = 0;

        try {
            File file = new File(filename);
            Scanner in = new Scanner(file);

            /*
             * While loop reads each line of the input.txt file.
             * The current play is converted to the corresponding index location in key.
             * The score of the current play is calculated using the key.
             * The score is added to the total score.
             * Process repeats until the end of the file is reached.
             */
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] play = line.split(" ");
                int[] playNumber = convertPlay(play);

                total += calculate(playNumber, key);
                totalTwo += calculate(playNumber, keyTwo);

            }
            
            in.close();
            System.out.println(total);
            System.out.println(totalTwo);
            

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    /*
     * Converts the current play of each payer to the corresponding index location
     * in key
     * Rock Paper Scissors
     * A = 0, B = 1, C = 2
     * X = 0, Y = 1, Z = 2
     */
    public static int[] convertPlay(String[] play) {
        int[] result = new int[2];

        switch (play[0].charAt(0)) {
            case 'A':
                result[0] = 0;
                break;
            case 'B':
                result[0] = 1;
                break;
            case 'C':
                result[0] = 2;
                break;
        }

        switch (play[1].charAt(0)) {
            case 'X':
                result[1] = 0;
                break;
            case 'Y':
                result[1] = 1;
                break;
            case 'Z':
                result[1] = 2;
                break;
        }

        return result;
    }

    // Calculates the score of the current play using the key
    public static int calculate(int[] play, int[][] key) {
        int result = 0;

        result = key[play[0]][play[1]];

        return result;
    }
}

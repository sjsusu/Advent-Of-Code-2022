package Day3;

import java.util.Scanner;
import java.io.File;

public class Rucksacks {
    public static void main(String[] args) {
        String filename = "input.txt";

        try {
            File file = new File(filename);
            Scanner in = new Scanner(file);

            int total = 0;

            /*
             * Part 1: Finds the character that is repeated in both compartments of the
             * rucksack and calculates the priority of the rucksack based on its ASCII
             * value.
             * This values is added to a running total until the end of the file is reached.
             */
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] compartments = splitCompartments(line);
                char repeat = findRepeat(compartments);
                int priority = calculatePriority(repeat);

                total += priority;

            } 

            in.close();
            System.out.println(total);
                    

            Scanner in2 = new Scanner(file);
            int total2 = 0;

            /*
             * Part 2: Finds the character that is repeated in sets of 3 rucksacks.
             * Clculates the priority of the rucksack based on its ASCII value.
             * These values is added to a running total until the end of the file is
             * reached.
             */
            while (in2.hasNextLine()) {
                String line1 = in2.nextLine();
                String line2 = in2.nextLine();
                String line3 = in2.nextLine();

                char repeat = findRepeat(line1, line2, line3);
                int priority = calculatePriority(repeat);

                total2 += priority;

            } 
            
            in2.close();
            System.out.println(total2);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // Takes each line of input and splits it into two strings (the compartments in
    // the rucksack)
    public static String[] splitCompartments(String line) {
        int length = line.length();

        String word1 = line.substring(0, length / 2);
        String word2 = line.substring(length / 2, length);

        String[] compartments = { word1, word2 };

        return compartments;
    }

    // Finds the character that is repeated in both compartments for part 1
    public static char findRepeat(String[] compartments) {
        char repeat = ' ';
        char[] compartment1 = compartments[0].toCharArray();
        char[] compartment2 = compartments[1].toCharArray();

        outerloop: for (char c : compartment1) {
            for (char d : compartment2) {
                if (c == d) {
                    repeat = c;
                    break outerloop;
                }
            }
        }

        return repeat;
    }

    // Finds the character that is repeated in all three lines (rucksacks) for part
    // 2
    public static char findRepeat(String word1, String word2, String word3) {
        char repeat = ' ';
        char[] compartment1 = word1.toCharArray();
        char[] compartment2 = word2.toCharArray();
        char[] compartment3 = word3.toCharArray();

        outerloop: for (char c : compartment1) {
            for (char d : compartment2) {
                for (char e : compartment3) {
                    if (c == d && c == e) {
                        repeat = c;
                        break outerloop;
                    }
                }
            }
        }

        return repeat;
    }

    // Calculates the priority of the rucksack based on the character that is
    // repeated using its ASCII value
    public static int calculatePriority(char repeat) {
        int ascii = (int) repeat;

        // for captial letters
        if (ascii < 91) {
            return ascii - 38;

        } else { // for lower case letters
            return ascii - 96;

        }
    }

}

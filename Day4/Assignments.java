package Day4;

import java.io.File;
import java.util.Scanner;

public class Assignments {
    public static void main(String[] args) {
        String filename = "input.txt";

        try {
            File file = new File(filename);
            Scanner in = new Scanner(file);
            int total = 0;
            int total2 = 0;

            /*
             * Loops through each available line and parses line into integer array of the
             * range values.
             * Checks if the two ranges are fully overlapping or just overlapping and adds
             * to two separate running totals.
             * Process repeats until end of file.
             */
            while (in.hasNextLine()) {
                String line = in.nextLine();
                int[] parsedLine = parseLine(line);
                total += isFullyOverlapping(parsedLine);
                total2 += isOverlapping(parsedLine);

            }

            in.close();
            System.out.println(total);
            System.out.println(total2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Parses each line into an array of 4 integers
    public static int[] parseLine(String line) {
        String[] splitLine = line.split(",");
        int[] parsedLine = new int[4];

        for (int i = 0; i <= 2; i += 2) {
            String[] split = splitLine[i / 2].split("-");
            parsedLine[i] = Integer.parseInt(split[0]);
            parsedLine[i + 1] = Integer.parseInt(split[1]);
        }

        return parsedLine;
    }

    // Checks if the two ranges are fully overlapping returns 1 for true and 0 for
    // false
    public static int isFullyOverlapping(int[] parsedLine) {
        if (parsedLine[0] >= parsedLine[2] && parsedLine[1] <= parsedLine[3]) {
            return 1;

        } else if (parsedLine[2] >= parsedLine[0] && parsedLine[3] <= parsedLine[1]) {
            return 1;

        } else {
            return 0;

        }
    }

    // Checks if the two ranges are overlapping returns 1 for true and 0 for false
    public static int isOverlapping(int[] parsedLine) {
        if (parsedLine[0] <= parsedLine[2] && parsedLine[2] <= parsedLine[1]) {
            return 1;

        } else if (parsedLine[0] <= parsedLine[3] && parsedLine[3] <= parsedLine[1]) {
            return 1;

        } else if (parsedLine[2] <= parsedLine[0] && parsedLine[0] <= parsedLine[3]) {
            return 1;

        } else if (parsedLine[2] <= parsedLine[1] && parsedLine[1] <= parsedLine[3]) {
            return 1;

        } else {
            return 0;
        }
    }

}

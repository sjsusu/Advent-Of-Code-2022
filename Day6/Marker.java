package Day6;

import java.io.File;
import java.util.Scanner;

public class Marker {
    public static void main(String[] args) {
        String filename = "input.txt";

        try {

            File file = new File(filename);
            Scanner in = new Scanner(file);

            String line = in.nextLine();

            int marker = findMarker(line, 4);
            System.out.println("Marker 1: " + marker);

            marker = findMarker(line, 14);
            System.out.println("Marker 2: " + marker);

            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Finds the first marker in a line of text that contains a specified number of
     * unique characters.
     * Creates a substring of the specified length and checks if it contains the
     * specified number of unique characters.
     * If it does, the marker is set to the index of the last character in the
     * substring and returned.
     * If there is no substring with the specified number of unique characters,
     * -1 is returned.
     */
    public static int findMarker(String line, int uniqueChars) {
        int marker = -1;

        for (int i = 0; i < line.length() - uniqueChars + 1; i++) {
            String subString = line.substring(i, i + uniqueChars);

            if (subString.chars().distinct().count() == uniqueChars) {
                marker = i + uniqueChars;
                break;
            }
        }

        return marker;

    }
}

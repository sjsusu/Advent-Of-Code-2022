package Day8;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;

public class Trees {
    public static void main(String[] args) {
        String filename = "input.txt";
        int[][] trees = readTrees(filename);

        int[] answers = calculateAnswers(trees);

        System.out.println(answers[0]); // Part 1 answer
        System.out.println(answers[1]); // Part 2 answer

    }

    // Reads file and returns a 2D integer array of tree heights
    public static int[][] readTrees(String filename) {
        ArrayList<String> lines = new ArrayList<String>();

        // Reads file and adds each line to an ArrayList
        try {
            File file = new File(filename);
            Scanner in = new Scanner(file);

            while (in.hasNextLine()) {
                String line = in.nextLine();
                lines.add(line);
            }

            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        int columns = lines.get(0).length();
        int rows = lines.size();

        int[][] trees = new int[rows][columns];

        // Converts each character in the ArrayList to an integer and adds it to the 2D
        // array
        for (int i = 0; i < rows; i++) {
            char[] treeRow = lines.get(i).toCharArray();
            for (int j = 0; j < columns; j++) {
                trees[i][j] = (int) treeRow[j] - 48;

            }
        }

        return trees;

    }

    /*
     * Calculates the number of trees visible from the border of the forest int[0]
     * and the maximum scenic score int[1]
     */
    public static int[] calculateAnswers(int[][] trees) {
        int rows = trees.length;
        int columns = trees[0].length;

        // Counts trees visible on border of forest
        int visible = 2 * (rows + columns) - 4;

        // Stores the scenic score for each tree
        ArrayList<Integer> scores = new ArrayList<Integer>();

        // Checks each tree for visibility
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < columns - 1; j++) {
                int value = trees[i][j];

                // Stores whether tree is visible from each direction [up, down, left, right]
                boolean[] isVisible = { true, true, true, true };

                // Checks up
                int up = 0; // Counts how many trees are visible upwards from the current tree
                for (int k = i - 1; k >= 0; k--) {
                    up++;
                    if (trees[k][j] >= value) {
                        isVisible[0] = false;
                        break;
                    }
                }

                // Checks down
                int down = 0; // Counts how many trees are visible downwards from the current tree
                for (int k = i + 1; k < rows; k++) {
                    down++;
                    if (trees[k][j] >= value) {
                        isVisible[1] = false;
                        break;
                    }
                }

                // Check left
                int left = 0; // Counts how many trees are visible leftwards from the current tree
                for (int k = j - 1; k >= 0; k--) {
                    left++;
                    if (trees[i][k] >= value) {
                        isVisible[2] = false;
                        break;
                    }
                }

                // Check right
                int right = 0; // Counts how many trees are visible rightwards from the current tree
                for (int k = j + 1; k < columns; k++) {
                    right++;
                    if (trees[i][k] >= value) {
                        isVisible[3] = false;
                        break;
                    }
                }

                // Checks if the current tree is visible from any direction, if it is then adds
                // to current total of visible trees
                for (boolean b : isVisible) {
                    if (b) {
                        visible++;
                        break;
                    }
                }

                // Adds the scenic score of the current tree to the ArrayList
                scores.add(up * down * left * right);

            }
        }

        // Stores the number of visible trees and the maximum scenic score in an array
        int[] answers = { visible, Collections.max(scores) };

        return answers;

    }

}

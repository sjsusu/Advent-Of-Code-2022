package Day5;

import java.util.Stack;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Cargo {
    public static void main(String[] args) {
        String filename = "input.txt";

        try {
            File file = new File(filename);
            Scanner in = new Scanner(file);

            String[] cargoLines = new String[9];

            // Reads in the cargo lines
            for (int i = 0; i < 9; i++) {
                cargoLines[i] = in.nextLine();

            }

            in.nextLine(); // Skips blank line on line 10

            ArrayList<Stack<Character>> cargo1 = makeCargo(cargoLines);
            ArrayList<Stack<Character>> cargo2 = makeCargo(cargoLines);

            /*
             * Reads in the moves and calls the moveCargo methods
             * for each line until the end of the file.
             */
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] moves = line.split(" ");

                cargo1 = moveCargo1(cargo1, moves);
                cargo2 = moveCargo2(cargo2, moves);

            }

            System.out.println(peekTops(cargo1));
            System.out.println(peekTops(cargo2));

            in.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // Creates a cargo arraylist of stacks of characters
    public static ArrayList<Stack<Character>> makeCargo(String[] cargoLines) {
        ArrayList<Stack<Character>> cargo = new ArrayList<Stack<Character>>(9);
        ArrayList<Stack<Character>> temp = new ArrayList<Stack<Character>>(9);

        // Initializes the cargo and temp arraylists
        for (int i = 0; i < 9; i++) {
            cargo.add(new Stack<Character>());
            temp.add(new Stack<Character>());
        }

        // Adds the cargo to the temp arraylist that is in reverse order
        for (int i = 0; i < 9; i++) {
            int count = 0;
            for (int j = 1; j < cargoLines[i].length(); j += 4) {
                if (cargoLines[i].charAt(j) != ' ') {
                    temp.get(count).push(cargoLines[i].charAt(j));
                }

                count++;
            }
        }

        // Adds the cargo to the cargo arraylist in correct order
        for (int i = 0; i < 9; i++) {
            while (!temp.get(i).isEmpty()) {
                cargo.get(i).push(temp.get(i).pop());
            }
        }

        return cargo;
    }

    // Part 1: Moves cargo from one stack to another in LIFO order
    public static ArrayList<Stack<Character>> moveCargo1(ArrayList<Stack<Character>> cargo, String[] moves) {
        int howMany = Integer.parseInt(moves[1]);
        int moveFrom = Integer.parseInt(moves[3]) - 1;
        int moveTo = Integer.parseInt(moves[5]) - 1;

        for (int i = 0; i < howMany; i++) {
            cargo.get(moveTo).push(cargo.get(moveFrom).pop());
        }

        return cargo;
    }

    // Part 2: Moves cargo from one stack to another in FIFO order
    public static ArrayList<Stack<Character>> moveCargo2(ArrayList<Stack<Character>> cargo, String[] moves) {
        int howMany = Integer.parseInt(moves[1]);
        int moveFrom = Integer.parseInt(moves[3]) - 1;
        int moveTo = Integer.parseInt(moves[5]) - 1;

        Stack<Character> temp = new Stack<Character>();

        for (int i = 0; i < howMany; i++) {
            temp.push(cargo.get(moveFrom).pop());
        }

        for (int i = 0; i < howMany; i++) {
            cargo.get(moveTo).push(temp.pop());
        }

        return cargo;
    }

    // Returns the top of each stack in a string
    public static String peekTops(ArrayList<Stack<Character>> cargo) {
        String tops = "";

        for (int i = 0; i < 9; i++) {
            tops += cargo.get(i).peek();
        }

        return tops;
    }

}

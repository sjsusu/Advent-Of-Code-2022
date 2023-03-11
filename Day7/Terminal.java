package Day7;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.io.File;

public class Terminal {
    //Solution Credit: https://www.youtube.com/watch?v=FXQWIWHaFBE
    public static void main(String[] args) {
        String filename = "input.txt";
        List<String> commands = readFile(filename);

        // Keeps track of the current directory
        String path = "/home";
        // Keeps track of the size of each directory
        Hashtable<String, Integer> directories = new Hashtable<String, Integer>();
        // Initialize the home directory
        directories.put(path, 0);

        // Process commands
        for (String str : commands) {
            String[] elements = str.split(" ");

            // Check for a $ command
            if (elements[0].equals("$")) {
                if (elements[1] == "ls") {
                    // Do nothing for ls
                } else if (elements[1].equals("cd")) { // Check for a cd command
                    // Manage the path changes
                    if (elements[2].equals("/")) {
                        //Go to root
                        path = "/home";

                    } else if (elements[2].equals("..")) {
                        //Go up a directory
                        path = path.substring(0, path.lastIndexOf("/"));

                    } else {
                        //Go to a subdirectory
                        path = path + "/" + elements[2];
                        directories.put(path, 0);

                    }
                } 

            } else if (elements[0].equals("dir")) {
                // Do nothing for dir
            } else {
                // Add the file size to the current directory and change directories where it was found
                int size = Integer.parseInt(elements[0]);
                String directory = path;

                //https://www.baeldung.com/java-count-chars
                for (int i = 0; i < path.chars().filter(ch->ch =='/').count(); i++) {
                    // Add the size to the current directory
                    directories.put(directory, directories.get(directory) + size);
                    // Go up a directory
                    directory = directory.substring(0, directory.lastIndexOf("/"));
                    
                }
            }

        }

        int total = 0;

        // Find how much space is needed for the update based on unused space
        int limit = 30000000 - (70000000 - directories.get("/home"));
        ArrayList <Integer> values = new ArrayList<Integer>();

        for (String key : directories.keySet()) {
            int value = directories.get(key);

            // Find the total size of all directories less than 100000
            if (value <= 100000){
                total += value;
            }

            // Find all directories greater than limit
            if (limit <= directories.get(key)) {
                values.add(directories.get(key));
            }
        }

        // Find the smallest directory greater than limit
        int smallest = Collections.min(values);

        System.out.println(total);
        System.out.println(smallest);
        
    }

    // Reads the file and returns a list of strings from each line
    public static List<String> readFile(String filename) {
        List<String> lines = new ArrayList<String>();

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

        return lines;
    }

}

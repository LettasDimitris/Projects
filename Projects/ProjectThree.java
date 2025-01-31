package gr.aueb.cf.Projects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ProjectThree {

    public static void main(String[] args) {
        String inputFile = "src/input.txt"; // Input file path

        int[][] charFrequency = new int[128][2];

        Map<Character, Integer> charPositionMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            int ch;
            while ((ch = reader.read()) != -1) {
                char character = (char) ch;
                if (!Character.isWhitespace(character) && character < 128) {
                    if (charPositionMap.containsKey(character)) {
                        // Increment frequency if character exists in the map
                        int position = charPositionMap.get(character);
                        charFrequency[position][1]++;
                    } else {
                        // Add character to the array and map
                        int newPosition = charPositionMap.size();
                        charFrequency[newPosition][0] = character;
                        charFrequency[newPosition][1] = 1;
                        charPositionMap.put(character, newPosition);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        // Filter and sort by character (only entries with non-zero frequency)
        int[][] filteredByCharacter = Arrays.stream(charFrequency)
                .filter(entry -> entry[1] > 0)
                .toArray(int[][]::new);
        Arrays.sort(filteredByCharacter, Comparator.comparingInt(o -> o[0]));

        // Filter and sort by frequency (only entries with non-zero frequency)
        int[][] filteredByFrequency = Arrays.copyOf(filteredByCharacter, filteredByCharacter.length);
        Arrays.sort(filteredByFrequency, (o1, o2) -> Integer.compare(o2[1], o1[1]));

        // Display statistics
        System.out.println("Character Frequencies (Sorted by Character):");
        for (int[] entry : filteredByCharacter) {
            System.out.printf("%c: %d%n", entry[0], entry[1]);
        }

        System.out.println("\nCharacter Frequencies (Sorted by Frequency):");
        for (int[] entry : filteredByFrequency) {
            System.out.printf("%c: %d%n", entry[0], entry[1]);
        }
    }

}

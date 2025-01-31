package gr.aueb.cf.Projects;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProjectOne {

    private final static String inputFile = "src/numbers.txt";
    private final static String outputFile = "output_combinations.txt";
    private final static int combinationLength = 6;
    private final static int minInputFile = 1;
    private final static int maxInputFile = 49;
    private final static int minInputFileNumbers = 6;
    private final static int maxInputFileNumbers = 49;

    public static void main(String[] args) {

        try {
            // Read numbers from the file
            List<Integer> numbers = readNumbersFromFile();

            if (numbers.size() <= minInputFileNumbers || numbers.size() > maxInputFileNumbers) {
                System.out.println("The file must contain between 7 and 49 numbers.");
                return;
            }

            // Sort numbers
            Collections.sort(numbers);

            // Generate and filter combinations
            List<int[]> allCombinations = new ArrayList<>();
            generateCombinations(numbers, new int[combinationLength], 0, 0, allCombinations);

            if(allCombinations.size() != calculateTotalCombinations(numbers.size(), combinationLength)) {
                System.out.println("WRONG COMBINATION GENERATION");
            }

            List<int[]> validCombinations = allCombinations.stream().filter(ProjectOne::isValidCombination).toList();

            // Write valid combinations to output file
            writeCombinationsToFile(validCombinations);

            System.out.println("Valid combinations written to " + outputFile);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static List<Integer> readNumbersFromFile() throws IOException {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ProjectOne.inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String part : line.split(",")) {
                    int num = Integer.parseInt(part.trim());
                    if (num < minInputFile || num > maxInputFile) {
                        throw new IllegalArgumentException("Numbers must be between 1 and 49.");
                    }
                    numbers.add(num);
                }
            }
        }
        return numbers;
    }

    private static void generateCombinations(List<Integer> numbers, int[] combination, int start, int depth, List<int[]> combinations) {
        if (depth == combinationLength) {
            combinations.add(combination.clone());
            return;
        }

        for (int i = start; i < numbers.size(); i++) {
            combination[depth] = numbers.get(i);
            generateCombinations(numbers, combination, i + 1, depth + 1, combinations);
        }
    }

    private static boolean isValidCombination(int[] combination) {
        return hasValidEven(combination) &&
                hasValidOdd(combination) &&
                hasValidContiguous(combination) &&
                hasValidEndingDigits(combination) &&
                hasValidTens(combination);
    }

    private static boolean hasValidEven(int[] combination) {
        int evens = 0;
        for (int num : combination) {
            if (num % 2 == 0) evens++;
        }
        return evens <= 4;
    }

    private static boolean hasValidOdd(int[] combination) {
        int odds = 0;
        for (int num : combination) {
            if (num % 2 != 0) odds++;
        }
        return odds <= 4;
    }

    private static boolean hasValidContiguous(int[] combination) {
        for (int i = 1; i < combination.length; i++) {
            if (combination[i] - combination[i - 1] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasValidEndingDigits(int[] combination) {
        int[] endings = new int[10];
        for (int num : combination) {
            endings[num % 10]++;
        }
        for (int count : endings) {
            if (count > 3) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasValidTens(int[] combination) {
        int[] tens = new int[5];
        for (int num : combination) {
            tens[num / 10]++;
        }
        for (int count : tens) {
            if (count > 3) {
                return false;
            }
        }
        return true;
    }

    private static void writeCombinationsToFile( List<int[]> combinations) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (int[] combination : combinations) {
                bw.write(Arrays.toString(combination));
                bw.newLine();
            }
        }
    }

    private static long calculateTotalCombinations(int n, int k) {
        if (k > n) return 0;
        long numerator = 1;
        for (int i = 0; i < k; i++) {
            numerator *= (n - i);
        }
        long denominator = 1;
        for (int i = 1; i <= k; i++) {
            denominator *= i;
        }
        return numerator / denominator;
    }
}

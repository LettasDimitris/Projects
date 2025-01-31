package gr.aueb.cf.Projects;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ProjectFive {
    private static final int ROWS = 30;
    private static final int COLUMNS_NUMBER = 12;
    private static final char[] COLUMNS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L'};
    private static boolean[][] seats;


    public static void main(String[] args) {
        seats = new boolean[ROWS][COLUMNS_NUMBER];
        Arrays.stream(seats).forEach(row -> Arrays.fill(row, false));
        displaySeating();

        book('C', 1);
        displaySeating();

        book('C', 2);
        displaySeating();

        book('C', 2);
        displaySeating();

        cancel('C', 2);
        displaySeating();

        cancel('C', 2);
        displaySeating();
    }

    public static void book(char column, int row) {
        if (!isValidSeat(column, row)) {
            System.out.println("Invalid seat position.");
            return;
        }

        int colIndex = findCharIndex(column);
        int rowIndex = row - 1;

        if (seats[rowIndex][colIndex]) {
            System.out.println("Seat " + column + row + " is already booked.");
        } else {
            seats[rowIndex][colIndex] = true;
            System.out.println("Seat " + column + row + " has been successfully booked.");
        }
    }

    private static int findCharIndex(char target) {
        for (int i = 0; i < COLUMNS.length; i++) {
            if (COLUMNS[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void cancel(char column, int row) {
        if (!isValidSeat(column, row)) {
            System.out.println("Invalid seat position.");
            return;
        }

        int colIndex = findCharIndex(column);
        int rowIndex = row - 1;

        if (!seats[rowIndex][colIndex]) {
            System.out.println("Seat " + column + row + " is not booked.");
        } else {
            seats[rowIndex][colIndex] = false;
            System.out.println("Seat " + column + row + " has been successfully cancelled.");
        }
    }

    public static void displaySeating() {
        String joined = CharBuffer.wrap(COLUMNS).chars()
                .mapToObj(character -> String.valueOf((char) character))
                .collect(Collectors.joining(" "));
        System.out.println("    " + joined);
        for (int i = 0; i < ROWS; i++) {
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < COLUMNS_NUMBER; j++) {
                System.out.print(" " + (seats[i][j] ? "X" : "O"));
            }
            System.out.println();
        }
    }

    private static boolean isValidSeat(char column, int row) {
        return column >= COLUMNS[0] && column <= COLUMNS[COLUMNS.length-1] && row >= 1 && row <= ROWS;
    }
}

package gr.aueb.cf.Projects;

import java.util.Objects;
import java.util.Scanner;

public class ProjectFour {

    private static final String[][] players = {
            {"Player 1", "Player 2"},
            {"X", "O"}
    };

    public static void main(String[] args) {
        String[][] board = {
                {" ", " ", " "},
                {" ", " ", " "},
                {" ", " ", " "}
        };

        Scanner scanner = new Scanner(System.in);
        int currentPlayer = 0;
        boolean gameWon = false;
        int moves = 0;

        printBoard(board);

        while (!gameWon && moves < 9) {
            System.out.println(getPlayerName(currentPlayer) + "'s turn. Enter your move (row and column: 1-3): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;

            if (row < 0 || row > 2 || col < 0 || col > 2 || !board[row][col].equals(" ")) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            board[row][col] = getPlayerSumbol(currentPlayer);
            moves++;
            printBoard(board);

            if (checkWin(board, getPlayerSumbol(currentPlayer))) {
                System.out.println(getPlayerName(currentPlayer) + " wins!");
                gameWon = true;
            } else if (moves == 9) {
                System.out.println("The game is a tie!");
            } else {
                currentPlayer = (currentPlayer == 0) ? 1 : 0;
            }
        }

        scanner.close();
    }

    private static void printBoard(String[][] board) {
        System.out.println("  1 2 3");
        for (int i = 0; i < board.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
                if (j < board[i].length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < board.length - 1) {
                System.out.println("  -----");
            }
        }
    }

    private static boolean checkWin(String[][] board, String playerSymbol) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0].equals(playerSymbol) && board[i][1].equals(playerSymbol) && Objects.equals(board[i][2], playerSymbol)) ||
                    (Objects.equals(board[0][i], playerSymbol) && Objects.equals(board[1][i], playerSymbol) && Objects.equals(board[2][i], playerSymbol))) {
                return true;
            }
        }

        // Check diagonals
        return (board[0][0].equals(playerSymbol) && Objects.equals(board[1][1], playerSymbol) && Objects.equals(board[2][2], playerSymbol)) ||
                (Objects.equals(board[0][2], playerSymbol) && Objects.equals(board[1][1], playerSymbol) && Objects.equals(board[2][0], playerSymbol));
    }

    private static String getPlayerName(int play) {
        return players[0][play];
    }

    private static String getPlayerSumbol(int play) {
        return players[1][play];
    }
}

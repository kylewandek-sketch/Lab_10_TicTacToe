// Lab 10: Tic Tac Toe
// A two-player console Tic Tac Toe game.
import java.util.Scanner;

public class TicTacToe {

    // Board size constants and the game board
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];

    public static void main(String[] args) {
        // Pseudocode:
        // 1. Clear the board and set player to X
        // 2. Show the board and ask the current player for a row and col (1-3)
        // 3. Convert input to indices (0-2) and loop until the move is valid
        // 4. Record the move and increment the move counter
        // 5. After enough moves, check for a win, then a tie
        // 6. If the game is over, ask to play again; otherwise toggle the player
        Scanner in = new Scanner(System.in);
        boolean playAgain = true;

        System.out.println("Welcome to Tic Tac Toe!");

        while (playAgain) {
            // Start a new game
            clearBoard();
            String player = "X";
            int moveCount = 0;
            boolean gameOver = false;

            while (!gameOver) {
                display();
                System.out.println("Player " + player + "'s turn.");

                int row = 0;
                int col = 0;
                boolean validMove = false;
                while (!validMove) {
                    row = SafeInput.getRangedInt(in, "Enter row", 1, ROWS) - 1;
                    col = SafeInput.getRangedInt(in, "Enter col", 1, COLS) - 1;
                    if (isValidMove(row, col)) {
                        validMove = true;
                    } else {
                        System.out.println("That cell is already taken. Try again.");
                    }
                }

                board[row][col] = player;
                moveCount = moveCount + 1;

                if (moveCount >= 5 && isWin(player)) {
                    display();
                    System.out.println("Player " + player + " WINS!");
                    gameOver = true;
                } else if (moveCount >= 7 && isTie()) {
                    display();
                    System.out.println("It's a TIE!");
                    gameOver = true;
                } else {
                    if (player.equals("X")) {
                        player = "O";
                    } else {
                        player = "X";
                    }
                }
            }

            playAgain = SafeInput.getYNConfirm(in, "Play again?");
        }

        System.out.println("Thanks for playing!");
    }

    // Sets every cell on the board to a single space
    private static void clearBoard() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                board[r][c] = " ";
            }
        }
    }

    // Prints the current board to the console
    private static void display() {
        System.out.println();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                System.out.print(" " + board[r][c] + " ");
                if (c < COLS - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (r < ROWS - 1) {
                System.out.println("-----------");
            }
        }
        System.out.println();
    }

    // Returns true if the cell at row, col is empty and in bounds
    private static boolean isValidMove(int row, int col) {
        boolean valid = false;
        if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
            if (board[row][col].equals(" ")) {
                valid = true;
            }
        }
        return valid;
    }

    // Returns true if the given player has any winning line
    private static boolean isWin(String player) {
        if (isRowWin(player)) {
            return true;
        }
        if (isColWin(player)) {
            return true;
        }
        if (isDiagnalWin(player)) {
            return true;
        }
        return false;
    }

    // Checks every row for a win by the given player
    private static boolean isRowWin(String player) {
        for (int r = 0; r < ROWS; r++) {
            if (board[r][0].equals(player) && board[r][1].equals(player) && board[r][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    // Checks every column for a win by the given player
    private static boolean isColWin(String player) {
        for (int c = 0; c < COLS; c++) {
            if (board[0][c].equals(player) && board[1][c].equals(player) && board[2][c].equals(player)) {
                return true;
            }
        }
        return false;
    }

    // Checks both diagonals for a win by the given player
    private static boolean isDiagnalWin(String player) {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;
        }
        return false;
    }

    // Returns true if the board is full or every win line is blocked
    private static boolean isTie() {
        boolean full = true;
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c].equals(" ")) {
                    full = false;
                }
            }
        }
        if (full) {
            return true;
        }

        for (int r = 0; r < ROWS; r++) {
            if (!vectorBlocked(board[r][0], board[r][1], board[r][2])) {
                return false;
            }
        }
        for (int c = 0; c < COLS; c++) {
            if (!vectorBlocked(board[0][c], board[1][c], board[2][c])) {
                return false;
            }
        }
        if (!vectorBlocked(board[0][0], board[1][1], board[2][2])) {
            return false;
        }
        if (!vectorBlocked(board[0][2], board[1][1], board[2][0])) {
            return false;
        }
        return true;
    }

    // Returns true if the three cells contain at least one X and at least one O
    private static boolean vectorBlocked(String a, String b, String c) {
        boolean hasX = false;
        boolean hasO = false;
        if (a.equals("X") || b.equals("X") || c.equals("X")) {
            hasX = true;
        }
        if (a.equals("O") || b.equals("O") || c.equals("O")) {
            hasO = true;
        }
        return hasX && hasO;
    }
}

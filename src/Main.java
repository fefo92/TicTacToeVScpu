import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        char[][] board = {{' ', ' ', ' '},
                          {' ', ' ', ' '},
                          {' ', ' ', ' '}};

        while (true) {
            printBoard(board);
            playerTurn(board, scanner);
            if (isGameFinished(board)){
                break;
            }
            printBoard(board);
            cpuTurn(board);
        }
        scanner.close();
    }

    private static boolean isGameFinished(char[][] board) {
        if (hasContestantWon(board, 'X')){
            printBoard(board);
            System.out.println("PLAYER WINS!");
            return true;
        }

        if (hasContestantWon(board, 'O')){
            printBoard(board);
            System.out.println("CPU WINS!");
            return true;
        }

        for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == ' ') {
                        return false;
                    }
                }
            }

        printBoard(board);
        System.out.println("TIE!");
        return true;
    }

    private static boolean hasContestantWon(char[][] board, char symbol) {
        return (board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol) ||
                (board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol) ||
                (board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol) ||

                (board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol) ||
                (board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol) ||
                (board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol) ||

                (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
    }

    private static void cpuTurn(char[][] board) {
        Random rand = new Random();
        int cpuMove;
        while (true) {
            cpuMove = rand.nextInt(9) + 1;
            if (isValidMove(board, cpuMove)) {
                break;
            }
        }
        System.out.println("CPU move: " + cpuMove);
        placeMove(board, Integer.toString(cpuMove), 'O');
    }

    private static boolean isValidMove(char[][] board, int position) {
        return switch (position) {
            case 1 -> (board[0][0] == ' ');
            case 2 -> (board[0][1] == ' ');
            case 3 -> (board[0][2] == ' ');
            case 4 -> (board[1][0] == ' ');
            case 5 -> (board[1][1] == ' ');
            case 6 -> (board[1][2] == ' ');
            case 7 -> (board[2][0] == ' ');
            case 8 -> (board[2][1] == ' ');
            case 9 -> (board[2][2] == ' ');
            default -> false;
        };
    }

    private static void playerTurn(char[][] board, Scanner scanner) {
        String userInput;
        while (true) {
            System.out.println("Enter position (1-9)");
            userInput = scanner.nextLine();
            if (isValidMove(board, Integer.parseInt(userInput))) {
                break;
            } else {
                System.out.println(userInput + " is not a valid move");
            }
        }
        placeMove(board, userInput, 'X');
    }

    private static void placeMove(char[][] board, String position, char symbol) {
        switch (position) {
            case "1" -> board[0][0] = symbol;
            case "2" -> board[0][1] = symbol;
            case "3" -> board[0][2] = symbol;
            case "4" -> board[1][0] = symbol;
            case "5" -> board[1][1] = symbol;
            case "6" -> board[1][2] = symbol;
            case "7" -> board[2][0] = symbol;
            case "8" -> board[2][1] = symbol;
            case "9" -> board[2][2] = symbol;
            default -> System.out.println("This position does not exist. Enter position (1-9)");
        }
    }

    private static void printBoard(char[][] board) {
        System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
        System.out.println("-+-+-");
        System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
        System.out.println("-+-+-");
        System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
    }
}
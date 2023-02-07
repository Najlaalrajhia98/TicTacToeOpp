
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        StateManager stateManager = new StateManager();
        System.out.println("Do you want to resume a game (yes/no)?");
        String choice = sc.nextLine();

        Symbol[] symbols = new Symbol[]{new Symbol("X"), new Symbol("O")};
        Board board = new Board(symbols);
        Player[] players = new Player[]{new Player("Player 1", symbols[0]), new Player("Player 2", symbols[1])};
        int turn = 0;
        if (choice.equalsIgnoreCase("yes")){
            stateManager.loadGame(board,players,turn);
            board.printBoard(board.getSlots());
        }
            System.out.println(" Player 1 Enter your symbol (X or O):");
            String playerSymbol = sc.nextLine();
            while (!playerSymbol.equalsIgnoreCase("X") && !playerSymbol.equalsIgnoreCase("O")) {
                System.out.println("Invalid symbol. Enter X or O:");
                playerSymbol = sc.nextLine();
            }

            if (playerSymbol.equalsIgnoreCase("X")) {
                players[0] = new Player("Player 1", symbols[0]);
                players[1] = new Player("Player 2", symbols[1]);
            } else {
                players[0] = new Player("Player 2", symbols[0]);
                players[1] = new Player("Player 1", symbols[1]);
            }

            playGame(board, sc, stateManager, symbols, players, turn);
        }

    private static void playGame (Board board,Scanner sc,StateManager stateManager,Symbol[] symbols,Player[] players,int turn){


        while (!board.isFull() && !board.isWinner(players[turn % 2].getSymbol())) {
            System.out.println("Current board:");
            board.printBoard(board.getSlots());

            boolean index = false;
            int row = 0;
            int col = 0;
            do {
                System.out.println(players[turn % 2].getName() + " turn. Enter row and column (0-based):");
                row = sc.nextInt();
                col = sc.nextInt();
                if (row < 3 && col < 3) {
                    index = true;
                } else {
                    index = false;
                    System.out.println("Its invalid  col and row please make a choice between 0-2: ");
                }
            } while (!index);
            while (!board.setSlot(row, col, players[turn % 2].getSymbol())) {
                System.out.println("This slot is already taken. Enter another row and column:");
                do {
                    row = sc.nextInt();
                    col = sc.nextInt();
                    if (row < 3 && col < 3) {
                        index = true;
                    } else {
                        index = false;
                        System.out.println("Its invalid  col and row please make a choice between 0-2: ");
                    }
                } while (!index);
            }
            if (board.isWinner(symbols[(turn % 2)])) {
                break;
            }
            turn++;
            stateManager.saveGame(board,players,turn);
        }

        System.out.println("Current board:");
        board.printBoard(board.getSlots());
        if (board.isFull() && !board.isWinner(players[0].getSymbol()) && !board.isWinner(players[1].getSymbol())) {
            System.out.println("It's a draw!");
        } else if (board.isWinner(players[0].getSymbol())) {
            System.out.println(players[0].getName() + " wins!");
        } else if (board.isWinner(players[1].getSymbol())) {
            System.out.println(players[1].getName() + " wins!");
        }
    }
    }

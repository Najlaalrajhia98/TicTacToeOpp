import java.util.InputMismatchException;
import java.util.Scanner;

public class Board {
        public static Symbol[][] slots;
        private static Symbol[] symbols;

        public Board(Symbol[] symbols) {
            slots = new Symbol[3][3];
            this.symbols = symbols;
        }
        public static boolean isFull() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (slots[i][j] == null) {
                        return false;
                    }
                }
            }
            return true;
        }

        public static boolean isWinner(Symbol symbol) {
            for (int i = 0; i < 3; i++) {
                if (slots[i][0] == symbol && slots[i][1] == symbol && slots[i][2] == symbol) {
                    return true;
                }
                if (slots[0][i] == symbol && slots[1][i] == symbol && slots[2][i] == symbol) {
                    return true;
                }
            }
            if (slots[0][0] == symbol && slots[1][1] == symbol && slots[2][2] == symbol) {
                return true;
            }
            if (slots[0][2] == symbol && slots[1][1] == symbol && slots[2][0] == symbol) {
                return true;
            }
            return false;
        }

        public static boolean setSlot(int row, int col, Symbol symbol) {
            if (slots[row][col] == null) {
                slots[row][col] = symbol;
                return true;
            } else {
                return false;
            }
        }

        public static Symbol[][] getSlots() {
            return slots;
        }

       public static void printBoard(Symbol[][] slots) {
           for (int i = 0; i < 3; i++) {
               for (int j = 0; j < 3; j++) {
                   System.out.print(slots[i][j] == null ? "-" : slots[i][j].getSymbol());
                   System.out.print(" ");
               }
               System.out.println();
           }
       }

    public void setSlot(int boardArray) {
    }
}


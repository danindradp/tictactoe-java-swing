import java.util.Random;
import java.util.ArrayList;

public class GameLogic {
    private char[] board;
    private Random random;

    public GameLogic() {
        board = new char[9];
        random = new Random();
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < board.length; i = i + 1) {
            board[i] = ' ';
        }
    }

    public boolean makeMove(int index, char symbol) {
        if (index < 0 || index >= 9) {
            return false;
        }
        if (board[index] != ' ') {
            return false;
        }
        board[index] = symbol;
        return true;
    }

    public boolean checkWinner(char symbol) {
        int[][] pola = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };
        for (int i = 0; i < pola.length; i = i + 1) {
            int a = pola[i][0];
            int b = pola[i][1];
            int c = pola[i][2];
            if (board[a] == symbol && board[b] == symbol && board[c] == symbol) {
                return true;
            }
        }
        return false;
    }

    public boolean isDraw() {
        for (int i = 0; i < board.length; i = i + 1) {
            if (board[i] == ' ') {
                return false;
            }
        }
        return true;
    }

    public int computerMove() {
        // coba menang dulu
        for (int i = 0; i < 9; i = i + 1) {
            if (board[i] == ' ') {
                board[i] = 'O';
                if (checkWinner('O')) {
                    board[i] = ' ';
                    return i;
                }
                board[i] = ' ';
            }
        }
        // coba blokir player
        for (int i = 0; i < 9; i = i + 1) {
            if (board[i] == ' ') {
                board[i] = 'X';
                if (checkWinner('X')) {
                    board[i] = ' ';
                    return i;
                }
                board[i] = ' ';
            }
        }
        // ambil tengah kalau kosong
        if (board[4] == ' ') {
            return 4;
        }
        // ambil random cell yang kosong
        ArrayList<Integer> kosong = new ArrayList<Integer>();
        for (int i = 0; i < 9; i = i + 1) {
            if (board[i] == ' ') {
                kosong.add(i);
            }
        }
        if (kosong.size() > 0) {
            return kosong.get(random.nextInt(kosong.size()));
        }
        return -1;
    }

    public char[] getBoard() {
        return board;
    }
}

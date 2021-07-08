package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static char[][] cells = new char[3][3];
    static boolean isFinished = false;

    public static void main(String[] args) {
        createGame();
        while (!isFinished) {
            game();
        }
    }

    static void createGame() {
        for (char[] cell : cells) {
            Arrays.fill(cell, ' ');
        }
        cellsPrint();
    }

    static void cellsPrint() {
        System.out.println("---------");
        for (char[] y : cells) {
            System.out.print("| ");
            for (char x : y) {
                System.out.print(x + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    static void game() {
        var isSuccess = true;
        char c =  (checkCount('X') == checkCount('O')) ? 'X' : 'O';
        while (isSuccess) {
            System.out.println("Enter the coordinates: ");
            int y;
            int x;
            if ((y = checkException(scan.next())) == -1 || (x = checkException(scan.next())) == -1)
                continue;
            isSuccess = checkStep(y, x, c);
        }
        cellsPrint();
        check();
    }

    static int checkException (String  num) {
        int index;
        try {
            index = Integer.parseInt(num) - 1;
        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            return -1;
        }
        return index;
    }


    static boolean checkStep(int  y, int  x, char c) {
        if (y == -1 || x == -1) {
            return true;
        } else if (y < 0 || y > 2 || x < 0 || x > 2) {
            System.out.println("Coordinates should be from 1 to 3!");
            return true;
        } else if (cells[y][x] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            return true;
        } else {
            cells[y][x] = c;
            return false;
        }
    }

    static int checkCount(char c) {
        var count = 0;
        for (char[] y : cells) {
            for (char x : y) {
                count = x == c ? count + 1 : count;
            }
        }
        return count;
    }

    static boolean checkFinish(char c) {
        for (var i = 0; i < 3; i++) {
            if (c == cells[i][0] && cells[i][0] == cells[i][1] &&
                    cells[i][1] == cells[i][2]) {
                isFinished = true;
                return true;
            }
            if (c == cells[0][i] && cells[0][i] == cells[1][i] &&
                    cells[1][i] == cells[2][i]) {
                isFinished = true;
                return true;
            }
        }
        if (c == cells[1][1] && ((cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2]) ||
                (cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0]))) {
            isFinished = true;
            return true;
        }
        return false;
    }

    static void check() {
        if (checkFinish('O')) {
            System.out.println("O wins");
        } else if (checkFinish('X')) {
            System.out.println("X wins");
        } else if (checkCount(' ') == 0) {
            System.out.println("Draw");
            isFinished = true;
        }
    }
}

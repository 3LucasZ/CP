package USACO.Bronze.UsacoGuideBronze.Recursion;

import java.util.*;

public class ChessboardAndQueens {
    public static int[][] board = new int[8][8];
    public static int count = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        for (int i=0;i<8;i++) {
            String line = in.nextLine();
            for (int j=0;j<8;j++) {
                if (line.charAt(j) == '*') {
                    board[i][j] = 1;
                }
            }
        }
        permuteQueens(0, board);
        System.out.println(count);
    }

    public static void permuteQueens(int row, int[][] prevBoard) {
        if (row >= 8) {
            count ++;
        }
        else {
            for (int col=0;col<8;col++) {
                if (prevBoard[row][col] == 0) {
                    permuteQueens(row + 1, placeQueen(prevBoard, row, col));
                }
            }
        }
    }
    public static int[][] placeQueen(int[][] bd, int row, int col) {
        //copy array
        int newBd[][] = new int[8][8];
        for (int r=0;r<8;r++) {
            for (int c=0;c<8;c++) {
                newBd[r][c] = bd[r][c];
            }
        }

        //place the queen
        for (int r=0;r<8;r++) {
            for (int c=0;c<8;c++) {
                if (r == row || c == col || Math.abs(r-row) == Math.abs(c - col)) {
                    newBd[r][c] = 1;
                }
            }
        }

        //return new board
        return newBd;
    }
    public static void printBoard(int[][] b) {
        for (int r=0;r<8;r++) {
            String s = "";
            for (int c=0;c<8;c++) {
                s += b[r][c];
            }
            System.out.println(s);
        }
    }
}

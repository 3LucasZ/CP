package Misc.CopyCatSad;

import java.io.*;
import java.util.*;
/*
USACO 2021 US Open, Silver
Problem 1. Maze Tac Toe
 */
public class MazeTacToe {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static char[][][] board;
    static int b_i;
    static int b_j;
    //logik
    static HashSet<Integer> answers = new HashSet<>();
    static boolean[][][] beenthere;
    //helper
    static int[] pow3 = new int[10];
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        parseInput();
        dfs(b_i, b_j, 0);
        out.println(answers.size());
        out.close();
        f.close();
    }
    public static void dfs(int r, int c, int b) {
        if (beenthere[r][c][b]) return;
        beenthere[r][c][b] = true;
        if (board[r][c][0] == 'M' || board[r][c][0] == 'O') {
            int br = board[r][c][1]-'1';
            int bc = board[r][c][2]-'1';
            int idx = br*3 + bc;
            int current_char = (b/pow3[idx])%3;
            if (current_char==0) {
                int new_char = board[r][c][0]=='M'?1:2;
                b = (b % pow3[idx]) + new_char * pow3[idx] + (b - b%pow3[idx+1]);
                if (!beenthere[r][c][b] && test_win(b)) {answers.add(b); return;}
                beenthere[r][c][b] = true;
            }
        }
        for (int a=0;a<4;a++) {
            if (board[r+dx[a]][c+dy[a]][0] != '#') dfs(r+dx[a],c+dy[a],b);
        }
    }
    public static boolean test_win(int b) {
        //turn int into board state
        int[][] cells = new int[3][3];
        for (int i=0;i<3;i++) {
            for (int j = 0; j<3;j++) {
                cells[i][j] = b%3;
                b/=3;
            }
        }
        //horizontal match?
        for (int r=0;r<3;r++) {
            if (cells[r][0] == 1 && cells[r][1] == 2 && cells[r][2] == 2) return true;
            if (cells[r][0] == 2 && cells[r][1] == 1 && cells[r][2] == 1) return true;
        }
        //vertical match?
        for (int c=0;c<3;c++) {
            if (cells[0][c] == 1 && cells[1][1] == 2 && cells[2][2] == 2) return true;
            if (cells[0][c] == 2 && cells[1][1] == 2 && cells[2][2] == 1) return true;
        }
        //diagonals
        if (cells[0][0] == 1 && cells[1][1] == 2 && cells[2][2] == 2) return true;
        if (cells[0][0] == 2 && cells[1][1] == 2 && cells[2][2] == 1) return true;
        if (cells[2][0] == 1 && cells[1][1] == 2 && cells[0][2] == 2) return true;
        if (cells[2][0] == 2 && cells[1][1] == 2 && cells[0][2] == 1) return true;
        return false;
    }
    public static void parseInput() throws IOException {
        //parse input
        N = Integer.parseInt(f.readLine());
        board = new char[N][N][3];
        beenthere = new boolean[N][N][19683];
        pow3[0] = 1;
        for (int i=1;i<=9;i++) pow3[i] = pow3[i-1]*3;
        for (int i=0;i<N;i++) {
            String str = f.readLine();
            for (int j=0;j<N;j++) {
                for (int k=0;k<3;k++) board[i][j][k] = str.charAt(3*j+k);
                if (board[i][j][0] == 'B') {b_i = i; b_j = j;}
            }
        }
//        for (int i=0;i<N;i++) {
//            out.println(Arrays.deepToString(board[i]));
//        }
    }
}

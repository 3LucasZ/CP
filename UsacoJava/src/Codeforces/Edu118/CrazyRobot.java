package Codeforces.Edu118;

import java.io.*;
import java.util.*;

public class CrazyRobot {
    static boolean debug = false;

    static int[] dr = {0,-1,0,1};
    static int[] dc = {1,0,-1,0};

    static int R;
    static int C;
    static int TR;
    static int TC;
    static char[][] board;
    static boolean[][][] edge;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        R = io.nextInt();
        C = io.nextInt();
        board = new char[R][C];
        for (int r=0;r<R;r++){
            String str = io.nextLine();
            for (int c=0;c<C;c++){
                board[r][c]=str.charAt(c);
                if (board[r][c]=='L'){
                    TR=r;
                    TC=c;
                }
            }
        }

        //* simulate
        for (int dir=0;dir<4;dir++){
            sim(TR+dr[dir],TC+dc[dir]);
        }

        //* return
        for (int r=0;r<R;r++){
            for (int c=0;c<C;c++){
                io.print(board[r][c]);
            }
            io.println();
        }
    }
    static void sim(int r, int c){
        if (blocked(r,c)||deg(r,c)>1) return;
        board[r][c]='+';
        for (int dir=0;dir<4;dir++) sim(r+dr[dir],c+dc[dir]);
    }
    static int deg(int r, int c){
        int ret = 0;
        for (int i=0;i<4;i++){
            if (!blocked(r+dr[i],c+dc[i])) ret++;
        }
        return ret;
    }
    static boolean blocked(int r, int c){
        return  r<0||r>=R||c<0||c>=C||board[r][c]!='.';
    }



















    static IO io;
    public static void main(String[] args) throws IOException {
        io = new IO(debug);
        int T = io.nextInt();
        for (int i=1;i<=T;i++) solve(i);
        io.close();
    }
    private static class IO {
    BufferedReader br;
    StringTokenizer st;
    PrintWriter out;
    boolean debug;
    public IO(boolean dbg)  {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        debug=dbg;
    }
    public IO(String fileName, boolean dbg) throws IOException {
        br = new BufferedReader(new FileReader(fileName+".in"));
        out = new PrintWriter(new FileWriter(fileName+".out"));
        debug=dbg;
    }
    String next()
    {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }
    int nextInt() { return Integer.parseInt(next()); }
    long nextLong() { return Long.parseLong(next()); }
    double nextDouble() {return Double.parseDouble(next());}
    String nextLine() {
        String str = "";
        try {
            if(st.hasMoreTokens()){
                str = st.nextToken("\n");
            }
            else{
                str = br.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
    void println(){
        if (debug) System.out.println();
        else out.println();
    }
    void println(Object obj){
        if (debug) System.out.println(obj);
        else out.println(obj);
    }
    void print(Object obj){
        if (debug) System.out.print(obj);
        else out.print(obj);
    }
    public static void print(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + arr[r][c];
                while (str.length() < 5) str += " ";
                System.out.print(str);
            }
            System.out.println();
        }
        System.out.println();
    }
    void close(){
        out.close();
    }
};}
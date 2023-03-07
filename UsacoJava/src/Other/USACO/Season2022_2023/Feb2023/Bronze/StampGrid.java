package Other.USACO.Season2022_2023.Feb2023.Bronze;

import java.io.*;
import java.util.*;

public class StampGrid {
    static boolean debug = false;

    static int N;
    static char[][] goal;

    static int K;
    static char[][][] tool;

    static char[][] art;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N =io.nextInt();
        goal = new char[N][N];
        for (int r=0;r<N;r++){
            String str = io.nextLine();
            for (int c=0;c<N;c++){
                goal[r][c]=str.charAt(c);
            }
        }
        K = io.nextInt();
        tool = new char[4][K][K];
        for (int r=0;r<K;r++){
            String str = io.nextLine();
            for (int c=0;c<K;c++){
                tool[0][r][c]=str.charAt(c);
            }
        }
        for (int i=1;i<=3;i++){
            tool[i]=rot90(tool[i-1]);
        }

        //* brute force
        art = new char[N][N]; for (int r=0;r<N;r++) for (int c=0;c<N;c++) art[r][c]='.';
        for (int r=0;r<N;r++){
            for (int c=0;c<N;c++){
                for (int rot=0;rot<4;rot++){
                    if (canPlaceStamp(r,c,rot)) placeStamp(r,c,rot);
                }
            }
        }
        //* ret
        if (debug){
            io.println("tool:");
            io.print2d(tool[0]);
            io.println("goal:");
            io.print2d(goal);
            io.println("art:");
            io.print2d(art);
        }
        if (match(art,goal)) io.println("YES");
        else io.println("NO");
    }
    static boolean match(char[][] arr1, char[][] arr2){
        for (int r=0;r<N;r++){
            for (int c=0;c<N;c++){
                if (arr1[r][c]!=arr2[r][c]) return false;
            }
        }
        return true;
    }
    static boolean canPlaceStamp(int r, int c, int rot){
        for (int rt=r;rt<r+K;rt++){
            for (int ct=c;ct<c+K;ct++){
                if (rt>=N) return false;
                if (ct>=N) return false;
                if (goal[rt][ct]=='.' && tool[rot][rt-r][ct-c]=='*') return false;
            }
        }
        return true;
    }
    static void placeStamp(int r, int c, int rot){
        for (int rt=r;rt<r+K;rt++){
            for (int ct=c;ct<c+K;ct++){
                if (tool[rot][rt-r][ct-c]=='*') art[rt][ct]='*';
            }
        }
    }
    static char[][] rot90(char[][] tool){
        int N = tool.length;
        char[][] ret = new char[N][N];
        for (int r=0;r<N;r++){
            for (int c=0;c<N;c++){
                ret[c][N-1-r]=tool[r][c];
            }
        }
        return ret;
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
    void print2d(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + arr[r][c];
                while (str.length() < 4) str += " ";
                print(str);
            }
           println();
        }
        println();
    }
    void print2d(char[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + arr[r][c];
                while (str.length() < 4) str += " ";
                print(str);
            }
            println();
        }
        println();
    }
    void print2d(boolean[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + (arr[r][c]?"1":"0");
                while (str.length() < 4) str += " ";
                print(str);
            }
            println();
        }
        println();
    }
    void printBin(int bin,int len){
        String ret = "";
        for (int i=0;i<len;i++){
            ret+=bin%2;
            bin/=2;
        }
        println(ret);
    }
    void reverse(int[] arr){
		for (int i=0;i<arr.length/2;i++){
			int tmp = arr[i];
			arr[i]=arr[arr.length-1-i];
			arr[arr.length-1-i]=tmp;
		}
	}
    void close(){
        out.close();
    }
}}
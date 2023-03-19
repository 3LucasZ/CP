package Solutions.Codeforces.Round854;

import java.io.*;
import java.util.*;

public class CityUnion {
    static boolean debug = false;

    static int R;
    static int C;
    static char[][] board;
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
            }
        }
        //* fill
        connectCols();
        connectRows();
        connectCols();
        connectRows();
        if (debug) io.print2d(board);

        //* finesse
        ArrayList<ArrayList<Point>> comps = getComponents();
        // edge case
        if (comps.size()==1){
            if (debug) io.println("Fin early");
            io.print2d(board);
            return;
        }
        ArrayList<Point> l = comps.get(0);
        ArrayList<Point> r = comps.get(1);
        if (maxCol(r) < maxCol(l)){
            ArrayList<Point> tmp = l;
            l = r;
            r = tmp;
        }
        ArrayList<Point> t = comps.get(0);
        ArrayList<Point> b = comps.get(1);
        if (maxRow(b) < maxRow(t)){
            ArrayList<Point> tmp = t;
            t = b;
            b = tmp;
        }
        if (debug){
            io.println("l:"+l);
            io.println("r:"+r);
        }

        Point first;
        Point second;
        if (l==b){
            first = new Point(minRow(l),maxCol(l));
            second = new Point(maxRow(r),minCol(r));
        } else {
            first = new Point(maxRow(l),maxCol(l));
            second = new Point(minRow(r),minCol(r));
        }
        if (debug){
            io.println("first:"+first);
            io.println("second:"+second);
        }
        fill(first.r,first.c,second.r,first.c);
        fill(second.r,second.c,second.r,first.c);

        //* ret
        connectCols();
        connectRows();
        connectCols();
        connectRows();
        io.print2d(board);
    }

    /*
    minCol, maxCol, minRow, maxRow
     */
    static int minCol(ArrayList<Point> component){
        int ret = component.get(0).c;
        for (Point p : component) ret=Math.min(ret,p.c);
        return ret;
    }
    static int maxCol(ArrayList<Point> component){
        int ret = component.get(0).c;
        for (Point p : component) ret=Math.max(ret,p.c);
        return ret;
    }
    static int minRow(ArrayList<Point> component){
        int ret = component.get(0).r;
        for (Point p : component) ret=Math.min(ret,p.r);
        return ret;
    }
    static int maxRow(ArrayList<Point> component){
        int ret = component.get(0).r;
        for (Point p : component) ret=Math.max(ret,p.r);
        return ret;
    }

    /*
    Component stuff
     */
    static boolean[][] vis;
    static ArrayList<Point> component;
    static int[] dr = {0,0,1,-1};
    static int[] dc = {1,-1,0,0};
    static ArrayList<ArrayList<Point>> getComponents(){
        ArrayList<ArrayList<Point>> ret = new ArrayList<>();
        vis = new boolean[R][C];
        for (int r=0;r<R;r++) for (int c=0;c<C;c++){
            if (vis[r][c] || board[r][c]=='.') continue;
            component=new ArrayList<>();
            DFS(r,c);
            ret.add(component);
        }
        return ret;
    }
    static void DFS(int r, int c){
        if (r<0||r>=R||c<0||c>=C||vis[r][c]||board[r][c]=='.') return;
        vis[r][c]=true;
        component.add(new Point(r,c));
        for (int i=0;i<4;i++) DFS(r+dr[i],c+dc[i]);
    }
    private static class Point {
        int r;
        int c;
        public Point(int r, int c){
            this.r=r;
            this.c=c;
        }
        public String toString(){
            return "["+r+", "+c+"]";
        }
    }

    /*
    Fill Stuff
     */
    static int INF = Integer.MAX_VALUE/10;
    static void connectRows() {
        for (int r=0;r<R;r++){
            int c1 = INF; int c2 = -INF;
            for (int c=0;c<C;c++){
                if (board[r][c]=='#') {
                    c1=Math.min(c1,c);
                    c2=Math.max(c2,c);
                }
            }
            if (c1!=INF) fill(r,c1,r,c2);
        }
    }
    static void connectCols() {
        for (int c=0;c<C;c++){
            int r1 = INF; int r2 = -INF;
            for (int r=0;r<R;r++){
                if (board[r][c]=='#') {
                    r1=Math.min(r1,r);
                    r2=Math.max(r2,r);
                }
            }
            if (r1!=INF) fill(r1,c,r2,c);
        }
    }
    static void fill(int r1, int c1, int r2, int c2){
        for (int i=Math.min(r1,r2);i<=Math.max(r1,r2);i++){
            for (int j=Math.min(c1,c2);j<=Math.max(c1,c2);j++){
                board[i][j]='#';
            }
        }
    }





















    static IO io;
    public static void main(String[] args) throws IOException {
        io = new IO(debug);
        int T = io.nextInt();
        for (int i=1;i<=T;i++) solve(i);
        io.close();
    }
    static <T> T last(ArrayList<T> list){
    return list.get(list.size()-1);
}
static String binToStr(int bin,int len){
        String ret="";
        for(int i=0;i<len;i++){
            ret+=bin%2;
            bin/=2;
        }
        return ret;
    }
static int log2(int num){
    return (int)(Math.log(num)/Math.log(2));
}
static void reverse(int[] arr){
    for (int i=0;i<arr.length/2;i++){
        int tmp = arr[i];
        arr[i]=arr[arr.length-1-i];
        arr[arr.length-1-i]=tmp;
    }
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
                print(arr[r][c]);
            }
            println();
        }
        if (debug) io.println();
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
    void close(){
        out.close();
    }
}}
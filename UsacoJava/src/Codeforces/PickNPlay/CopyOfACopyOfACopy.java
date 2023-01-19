package Codeforces.PickNPlay;

import java.io.*;
import java.util.*;
/*
PROB: CopyOfACopyOfACopy
LANG: JAVA
*/
public class CopyOfACopyOfACopy {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int R; //ro
    static int C; //col
    static int N; //copies

    static int[][][] board; //board
    static boolean[][] graph; //can u reach v?
    static ArrayList<String>[][] transforms; //transforms to get from u->v

    //compass
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,1,-1};

    public static void solve() throws IOException {
        //* parse
        R = io.nextInt();
        C = io.nextInt();
        N = io.nextInt()+1;
        board = new int[N][R][C];
        for (int i=0;i<N;i++){
            io.nextLine();
            for (int r=0;r<R;r++){
                String str = io.nextLine();
                for (int c=0;c<C;c++){
                    board[i][r][c]=str.charAt(c);
                }
            }
        }

        //* make edges u,v
        graph = new boolean[N][N];
        transforms= new ArrayList[N][N];
        for (int u=0;u<N;u++){
            for (int v=0;v<N;v++){
                ArrayList<String> res = genTransforms(u,v);
                if (res==null) continue;
                graph[u][v]=true;
                transforms[u][v]=res;
            }
        }

        if (debug){
            io.println("graph: ");
            print2d(graph);
        }

        //* down path
        ArrayList<Integer> path = new ArrayList<>();
        ArrayList<String> ret = new ArrayList<>();
        boolean[] used = new boolean[N];
        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                if (used[j]) continue;
                boolean good = true;
                for (int k=0;k<N;k++){
                    if (used[k]) continue;
                    if (!graph[j][k]) good=false;
                }
                if(good){
                    if (i>0) {
                        ret.addAll(transforms[path.get(i-1)][j]);
                        ret.add("2 "+(j+1));
                    } else {
                        io.println((j+1));
                    }
                    used[j]=true;
                    path.add(j);
                    break;
                }
            }
        }
        if (debug){
            io.println("path: "+path);
        }

        //* ret
        io.println(ret.size());
        for (String str : ret) io.println(str);

    }
    static ArrayList<String> genTransforms(int u, int v){
        int[][] ub = cpy(board[u]);
        int[][] vb = board[v];
        ArrayList<String> transforms = new ArrayList<>();
        for (int r=0;r<R;r++){
            for (int c=0;c<C;c++){
                if (ub[r][c]!=vb[r][c]){
                    //corners can not be transformed
                    if (r==0 || c==0 || r==R-1 || c==C-1) return null;
                    //cant transform
                    for (int d=0;d<4;d++){
                        int r2 = r+dr[d];
                        int c2 = c+dc[d];
                        if (ub[r2][c2]==ub[r][c]) return null;
                    }
                    //transform
                    ub[r][c]=1-ub[r][c];
                    transforms.add("1 "+(r+1)+" "+(c+1));
                }
            }
        }
        return transforms;
    }
    static int[][] cpy(int[][] orig){
        int[][] ret = new int[orig.length][orig[0].length];
        for (int r=0;r<orig.length;r++){
            for (int c=0;c<orig[0].length;c++){
                ret[r][c]=orig[r][c];
            }
        }
        return ret;
    }

    static void print2d(boolean[][] arr){
        for (int r=0;r<arr.length;r++){
            for (int c=0;c<arr[0].length;c++){
                io.print(arr[r][c]?"1":"0");
            }
            io.println();
        }
        io.println();
    }















    public static void main(String[] args) throws IOException {
        if (fileSubmission){
            io = new IO(fileName, debug);
        } else {
            io = new IO(debug);
        }
        solve();
        io.close();
    }
    static IO io;
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
};;
}
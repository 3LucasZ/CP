package Solutions.USACO.Season2022_2023.Jan2023.Silver;

import java.io.*;
import java.util.*;
/*
PROB: FollowingDirections
LANG: JAVA
*/
public class FollowingDirections {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static char[][] dir;
    static int[][] cost;

    static int[][] cnt;
    static int ans;

    public static void solve() throws IOException{
        //* parse
        N = io.nextInt();
        dir = new char[N+1][N+1];
        cost = new int[N+1][N+1];
        for(int r=0;r<=N;r++){
            String str="";
            if(r<N) str=io.next();
            for(int c=0;c<=N;c++){
                if(r==N&&c==N) continue;
                else if(r==N){
                    cost[r][c]=io.nextInt();
                }else if(c==N){
                    cost[r][c]=io.nextInt();
                }else{
                    dir[r][c]=str.charAt(c);
                }
            }
        }
        cnt = new int[N+1][N+1];
        for (int r=0;r<N;r++){
            for (int c=0;c<N;c++){
                cnt[r][c]++;
                if (dir[r][c]=='R') cnt[r][c+1]+=cnt[r][c];
                else cnt[r+1][c]+=cnt[r][c];
            }
        }
        if (debug){
            io.print(cnt);
        }
        int Q=io.nextInt();
        ans = 0;
        for (int r=0;r<=N;r++){
            for (int c=0;c<=N;c++){
                ans+=cnt[r][c]*cost[r][c];
            }
        }
        io.println(ans);
        for(int i=0;i<Q;i++){
            int r=io.nextInt()-1;
            int c=io.nextInt()-1;
            int amt = cnt[r][c];
            if (dir[r][c]=='R'){
                changePath(r,c+1,-amt);
                changePath(r+1,c,amt);
                dir[r][c]='D';
            } else {
                changePath(r+1,c,-amt);
                changePath(r,c+1,amt);
                dir[r][c]='R';
            }
            if (debug){
                io.println("new query");
                    io.print(cnt);
            }
            io.println(ans);
        }
    }
    static void changePath(int r, int c, int amt){
        cnt[r][c]+=amt;
        if (r==N || c==N) {
            ans+=amt*cost[r][c];
            return;
        }
        if (dir[r][c]=='R'){
            changePath(r,c+1,amt);
        } else {
            changePath(r+1,c,amt);
        }
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
    void print(int[][] arr) {
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
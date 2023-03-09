package Solutions.Codeforces.Edu140;

import java.io.*;
import java.util.*;
/*
PROB: AlgebraFlash
LANG: JAVA
*/
public class AlgebraFlash {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static int M=40;
    static int MID=20;

    static boolean[][] graph;
    static int[] buy;

    static final int INF = Integer.MAX_VALUE/2;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt(); int takein = io.nextInt();
        int[] color = new int[N];
        buy= new int[M];
        for (int i=0;i<N;i++){
            color[i]=io.nextInt()-1;
        }
        graph = new boolean[M][M];
        for (int i=0;i<N-1;i++){
            graph[color[i]][color[i+1]]=true;
            graph[color[i+1]][color[i]]=true;
        }
        for (int i=0;i<takein;i++){
            buy[i]=io.nextInt();
        }
        if (debug){
            io.println("cost:"+Arrays.toString(buy));
            io.println("graph:");
            io.print2d(graph);
        }
        //* mask group 1
        int[] dp = new int[1<<MID]; Arrays.fill(dp,INF);
        for (int mask=0;mask<(1<<MID);mask++){
            if (color[0]<MID) mask = mask|(1<<color[0]); //infuse with src
            if (color[N-1]<MID) mask = mask|(1<<color[N-1]); //infuse with sink
            if (legal(mask,0,MID-1)){
                int con = 0;
                for (int i=0;i<MID;i++){
                    for (int j=MID;j<M;j++){
                        //j must be in con
                        if (graph[i][j] && (mask&(1<<i))==0){
                            con = con|(1<<(j-MID));
                        }
                    }
                }
                int cost = 0;
                for (int i=0;i<MID;i++){
                    //i in mask
                    if ((mask&(1<<i))!=0){
                        cost+=buy[i];
                    }
                }
                if (debug){
                    io.print("mask1:");io.printBin(mask,M);
                    io.print("con:");io.printBin(con,M);
                    io.println("cost:"+cost);
                }
                dp[con]=Math.min(dp[con],cost);
            }
        }
        for (int mask=0;mask<(1<<MID);mask++){
            for (int next=0;next<MID;next++){
                dp[mask|(1<<next)]=Math.min(dp[mask|(1<<next)],dp[mask]);
            }
        }
        //* mask group 2
        int ans = INF;
        for (int mask=0;mask<(1<<MID);mask++){
            if (color[0]>=MID) mask = mask|(1<<(color[0]-MID));
            if (color[N-1]>=MID) mask = mask|(1<<(color[N-1]-MID));
            if (legal(mask,MID,M-1)){
                int cost = 0;
                for (int i=MID;i<M;i++){
                    //i in mask
                    if ((mask&(1<<(i-MID)))!=0){
                        cost+=buy[i];
                    }
                }
                if (debug){
                    io.print("mask2:");io.printBin(mask,M);
                    io.println("cost:"+cost);
                }
                ans=Math.min(ans,dp[mask]+cost);
            }
        }
        //* ret
        io.println(ans);
    }
    static boolean legal(int mask, int l, int r){
        for (int i=l;i<=r;i++){
            for (int j=l;j<=r;j++){
                //edge ij but both not in mask -> failure
                if (graph[i][j] && (mask&(1<<(i-l)))==0 && (mask&(1<<(j-l)))==0) {
                    return false;
                }
            }
        }
        return true;
    }
    /*
    10 5
    5 4 1 2 5 1 4 1 4 3
    646 594 976 582 392
     */
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
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
};
}
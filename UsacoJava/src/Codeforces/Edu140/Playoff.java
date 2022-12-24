package Codeforces.Edu140;

import java.io.*;
import java.util.*;
/*
PROB: Playoff
LANG: JAVA
*/
public class Playoff {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static int[] type;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        String str = io.nextLine();
        type = new int[N];
        for (int i=0;i<N;i++){
            type[i]=str.charAt(i)=='1'?1:0;
        }
        if (debug) io.println(Arrays.toString(type));
        //* dp
        int nums = 1<<N;
        boolean[][] dp = new boolean[N+1][nums+1];
        int[][] sum = new int[N+1][nums+1];
        //base case
        dp[0][1]=true;
        sum[0][1]=1;
        //transitions
        for (int i=1;i<=N;i++){
            if (debug) io.println("Round: "+i);
            for (int num = 1;num<=(1<<i);num++){
                sum[i][num]=sum[i][num-1];
                int l = num-1;
                int r = (1<<i)-num;
                int lo,hi;
                //lower skill levels win
                if (type[i-1]==0){
                    if (r==0) continue;
                    int rlo = Math.max(1,(r-l+1)/2);
                    int rhi = (r+1)/2;
                    lo = (1<<(i-1)) - rhi + 1;
                    hi = (1<<(i-1)) - rlo + 1;
                }
                else {
                    if (l==0) continue;
                    hi = (l+1)/2;
                    lo = Math.max(1,(l-r+1)/2);
                }
                if (debug) {
                    io.println("num: " + num);
                    io.println("[" + lo + ", " + hi + "]");
                }
                //is num good?
                boolean good = sum[i-1][hi]-sum[i-1][lo-1]>0;
                if (good) {
                    dp[i][num] = true;
                    sum[i][num]++;
                }
            }
        }
        if (debug) for (int i=0;i<=N;i++){
            io.println("round: "+i+": "+Arrays.toString(dp[i]));
        }
        //* ret
        for (int i=1;i<=nums;i++){
            if (dp[N][i]) io.print(i+" ");
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
    void close(){
        out.close();
    }
};;
}
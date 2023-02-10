package Other.USACO.Season2022_2023.Jan2023;

import java.io.*;
import java.util.*;
/*
PROB: LightsOff
LANG: JAVA
*/
public class LightsOff {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int Q;
    static int n;

    static int[][] flip;
    static boolean[][] dp;

    public static void solve() throws IOException {
        //* parse
        Q = io.nextInt();
        n= io.nextInt();

        //* precomp consec flip
        flip = new int[n][3*n+1];
        for (int i=0;i<n;i++){
            for (int len=0;len<=3*n;len++){
                for (int j=0;j<len;j++){
                    int cur = (i+j)%n;
                    flip[i][len]^=(1<<cur);
                }
            }
        }

        //* if switches were all off dp
        dp = new boolean[3*n+1][1<<n];
        dp[0][0]=true;

        for (int i=0;i<3*n;i++){
            for (int mask=0;mask<(1<<n);mask++){
                if (!dp[i][mask])continue;
                for (int chg=0;chg<n;chg++){
                    dp[i+1][mask^flip[chg][i+1]]=true;
                }
            }
        }

        //* answer query
        for (int i=0;i<Q;i++){
            solveQ();
        }
    }
    static void solveQ(){
        //parse
        int light=0;
        int button=0;
        String str1 = io.next();
        for (int i=0;i<n;i++){
            light+=(1<<i)*(str1.charAt(i)-'0');
        }
        String str2 = io.next();
        for (int i=0;i<n;i++){
            button+=(1<<i)*(str2.charAt(i)-'0');
        }

        //brute force
        for (int m=0;m<=3*n;m++){
            if (debug){
                io.println("m:"+m);
                io.printBin(button,n+1);
            }
            //use cache
            if (dp[m][light]) {
                io.println(m);
                return;
            }

            //simulate
            light=light^button;
            button = (button<<1)+(button>>(n-1));
            if (button>=(1<<n)) button-=(1<<n);
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
                this.print(str);
            }
            this.println();
        }
        this.println();
    }
         void printBin(int bin, int len){
            for (int i=0;i<len;i++){
                io.print(bin%2);
                bin/=2;
            }
            io.println();
        }
    void close(){
        out.close();
    }
}
}
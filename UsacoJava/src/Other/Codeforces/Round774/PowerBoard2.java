package Other.Codeforces.Round774;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
PROB: PowerBoard
LANG: JAVA
*/
public class PowerBoard2{
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static final int MAXP = 20;
    public static void solve() throws IOException{
        //* parse
        int N=io.nextInt();
        int M=io.nextInt();

        //* precomp
        long[] dp=new long[MAXP+1];
        boolean[] vis=new boolean[MAXP*M+1];
        for (int p=1;p<=MAXP;p++){
            dp[p]=dp[p-1];
            for (int i=1;i<=M;i++){
                if (!vis[i*p]){
                    vis[i*p]=true;
                    dp[p]++;
                }
            }
        }if (debug){
            io.println("dp: "+Arrays.toString(dp));
            io.println("vis: "+Arrays.toString(vis));
        }

        //* ans
        vis = new boolean[N+1];
        long ans = 1;
        for (int i=2;i<=N;i++){
            if (vis[i]) continue;
            long j = i;
            long m = 1;
            while (j<=N){
                vis[(int)j]=true;
                j*=i;
                m++;
            }
            ans+=dp[(int)(m-1)];
        }

        //* ret
        io.println(ans);
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
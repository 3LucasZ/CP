package Solutions.Codeforces.Round804;

import java.io.*;
import java.util.*;

public class ThreeDaysGrace {
    static boolean debug = false;
    
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        int n = io.nextInt();
        int m = io.nextInt();
        int[] a = new int[n];
        boolean[] inSet = new boolean[m+1];
        for (int i=0;i<n;i++){
            int j = io.nextInt();
            inSet[j]=true;
            a[i]=j;
        }
        if(debug){
            io.println("a:"+Arrays.toString(a));
        }
        //* precomp
        int min = Integer.MAX_VALUE;
        for (int i=0;i<n;i++){
            min=Math.min(min,a[i]);
        }
        //* dp
        int ans = m;
        int max = m;
        int[] dp = new int[m+1]; for (int i=1;i<=m;i++) dp[i]=i;
        int[] freq = new int[m+1]; for (int i=1;i<=m;i++) if (inSet[i]) freq[i]++;
        for (int L=m;L>=1;L--){
            if (debug){
                io.println("min:"+L);
            }
            int old = dp[L];
            dp[L]=L;
            int next = dp[L];
            if (inSet[L]){
                freq[old]--;
                freq[next]++;
            }
            if ((long)L*L<=m){
                for(int mult=L*L;mult<=m;mult+=L){
                    old=dp[mult];
                    dp[mult]=Math.min(dp[mult],dp[mult/L]);
                    next=dp[mult];
                    if(inSet[mult]){
                        freq[old]--;
                        freq[next]++;
                    }
                }
            }
            //search new max
            while (freq[max]==0){
                if (debug){
                    io.println("max:"+max);
                }
                max--;
            }
            //update ans
            if (L<=min){
                ans=Math.min(ans,max-L);
            }
        }

        //* ret
        io.println(ans);
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
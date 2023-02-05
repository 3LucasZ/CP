package Other.Codeforces.Edu840;

import java.io.*;
import java.util.*;
/*
PROB: NodePairs
LANG: JAVA
*/
public class NodePairs {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = true;
    static final int MAX = 1000;
    static final int INF = Integer.MAX_VALUE/10;

    static int P;
    public static void solve() throws IOException {
        //* parse
        P = io.nextInt();
        int[] chs2 = new int[MAX+1];
        for (int i=1;i<=MAX;i++){
            chs2[i]=(i*(i-1))/2;
        }

        //* dp
        int[] dp = new int[P+1]; for (int i=0;i<=P;i++) dp[i]=INF;
        int[] dp2 = new int[P+1];
        dp[0]=0;
        dp2[0]=0;
        for (int i=1;i<=P;i++){
            for (int j=2;j<=MAX;j++){
                int last = i-chs2[j];
                if (last<0) break;

                if (dp[i]==dp[last]+j){
                    dp2[i]=Math.max(dp2[i],dp2[last]+dp[last]*j);
                } else if (dp[i]>dp[last]+j){
                    dp[i]=dp[last]+j;
                    dp2[i]=dp2[last]+dp[last]*j;
                }
            }
        }

        //* ret
        io.println(dp[P]+" "+dp2[P]);
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
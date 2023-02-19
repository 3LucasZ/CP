import java.io.*;
import java.util.*;
/*
PROB: VelepinAndMarketing
LANG: JAVA
*/
public class VelepinAndMarketing {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int INF = Integer.MAX_VALUE;
    public static void solve() throws IOException {
        //* parse
        int N = io.nextInt();
        Integer[] A = new Integer[N+1];
        for (int i=1;i<=N;i++){
            A[i]=io.nextInt();
        }
        A[0]=0;

        //* sort
        Arrays.sort(A);
        if (debug){
            io.println("A:"+Arrays.toString(A));
        }

        //* dp - best partitioning for prefix i
        int[] dp = new int[N+1];
        int[] dpMax = new int[N+1];
        for (int i=1;i<=N;i++){
            if (i<A[i]) dp[i]=-INF;
            else dp[i]=dpMax[i-A[i]]+1;
            dpMax[i]=Math.max(dp[i],dpMax[i-1]);
        }
        if (debug){
            io.println("dp:"+Arrays.toString(dp));
        }

        //* fix the dp
        for (int i=0;i<=N;i++){
            if (dp[i]==-INF){
                dp[i]=N-A[i]+1;
            } else {
                dp[i]+=N-i;
            }
        }
        if (debug){
            io.println("dpFix:"+Arrays.toString(dp));
        }

        //* answer queries
        int Q = io.nextInt();
        for (int i=0;i<Q;i++){
            int k = io.nextInt();
            // binary search largest prefix i where k<=dp[i]
            int lo = 0;
            int hi = N;
            while (lo<hi){
                int mid = (lo+hi+1)/2;
                if (dp[mid]>=k) lo=mid;
                else hi=mid-1;
            }
            // ret
            io.println(lo);
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
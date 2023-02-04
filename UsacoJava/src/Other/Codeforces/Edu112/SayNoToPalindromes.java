package Other.Codeforces.Edu112;

import java.io.*;
import java.util.*;
/*
PROB: SayNoToPalindromes
LANG: JAVA
*/
public class SayNoToPalindromes {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N, Q;
    static int[][] sum;
    static char[][] perm;
    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        Q = io.nextInt();
        String str = io.nextLine();
        char[] A = new char[N+1];
        for (int i=1;i<=N;i++) A[i]=str.charAt(i-1);
        if (debug){
            io.println("A:"+Arrays.toString(A));
        }

        //* create perms
        perm = new char[6][3];
        perm[0]=new char[]{'a','b','c'};
        perm[1]=new char[]{'a','c','b'};
        perm[2]=new char[]{'b','c','a'};
        perm[3]=new char[]{'b','a','c'};
        perm[4]=new char[]{'c','b','a'};
        perm[5]=new char[]{'c','a','b'};
        //* create sum
        sum = new int[6][N+1];
        for (int i=0;i<6;i++){
            for (int j=1;j<=N;j++){
                sum[i][j]=sum[i][j-1];
                if (A[j]!=perm[i][j%3]) sum[i][j]++;
            }
        }
        if (debug){
            for (int i=0;i<6;i++){
                io.println("sum "+i+":"+Arrays.toString(sum[i]));
            }
        }
        //* answer queries
        for (int i=0;i<Q;i++){
            int l = io.nextInt();
            int r = io.nextInt();
            int ans = Integer.MAX_VALUE;
            for (int j=0;j<6;j++){
                ans=Math.min(ans,sum[j][r]-sum[j][l-1]);
            }
            io.println(ans);
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
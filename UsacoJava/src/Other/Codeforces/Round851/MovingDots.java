package Other.Codeforces.Round851;

import java.io.*;
import java.util.*;
/*
PROB: MovingDots
LANG: JAVA
*/
public class MovingDots {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = true;

    static int N;
    static int[] A;
    static long MOD =(long)((1e9)+7);
    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        int[] A = new int[N+3];
        for (int i=1;i<=N;i++){
            A[i]=io.nextInt();
        }
        A[0]=-1000000010;
        A[N+1] = 2000000010;

        TreeSet<Integer> set = new TreeSet<>(Comparator.comparingInt(a->A[a]));
        for (int i=0;i<=N+1;i++) set.add(i);

        //* exploiting independence
        long ans = 0;
        for (int i=1;i<=N;i++){
            for (int j=i+1;j<=N;j++){
                int d = A[j]-A[i];
                A[N+2] = A[i]-d;
                Integer l = set.lower(N+2);
                A[N+2] = A[j]+d;
                Integer r = N+1-set.ceiling(N+2);
                ans = (ans + pow(2,l+r))%MOD;
            }
        }

        //* ret
        io.println(ans);
    }

    public static long pow(long x, int p){
        if(x==0) return 0;
        if(p==0) return 1;
        if(p%2==1)return x*pow(x,p-1)%MOD;
        else return pow(x*x%MOD,p/2);
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
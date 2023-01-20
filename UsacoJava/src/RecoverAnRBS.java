import java.io.*;
import java.util.*;

public class RecoverAnRBS {
    static boolean debug = false;

    static String str;
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        str = io.nextLine();
        int N = str.length();
        char[] A = new char[N];
        for (int i=0;i<N;i++) A[i]=str.charAt(i);

        //* cnt init open (
        int open = 0;
        for (int i=0;i<N;i++){
            if (A[i]=='(') open++;
        }

        //* edit A to soonest cfg
        int INF = Integer.MAX_VALUE;
        int a = -INF;
        int b = INF;
        for (int i=0;i<N;i++){
            if (A[i]=='?') {
                if (open < N/2){
                    a=Math.max(a,i);
                    A[i]='(';
                    open++;
                } else {
                    b=Math.min(b,i);
                    A[i]=')';
                }
            }
        }
        if (a==-INF || b==INF) {
            io.println("YES");
            return;
        }

        //* try cfg
        A[a]=')';
        A[b]='(';
        int waiting = 0;
        for (int i=0;i<N;i++){
            if (A[i]=='(') waiting++;
            else waiting --;
            if (waiting < 0){
                io.println("YES");
                return;
            }
        }
        io.println("NO");
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
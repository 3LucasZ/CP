package Other.Codeforces.PickNPlay;

import java.io.*;
import java.util.*;

public class EmptyGraph {
    static boolean debug = false;
    static int INF = (int)1e9;
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        int N = io.nextInt();
        int K = io.nextInt();
        int[] A = new int[N+1];
        for (int i=0;i<N;i++) A[i]=io.nextInt();
        A[N]=INF;
        if (debug){
            io.println("A:"+Arrays.toString(A));
        }

        //* base case
        if (N==2 && K==1){
            io.println(Math.max(A[0],A[1]));
            return;
        }
        //* rank
        Integer[] sort = new Integer[N+1];
        for (int i=0;i<=N;i++) sort[i]=i;
        Arrays.sort(sort,(a,b)->(A[a]-A[b]));
        if (debug){
            io.println("Sort:"+Arrays.toString(sort));
        }

        //* simulate
        int ans = 0;
        for (int i=0;i<N-1;i++){
            int u, v;
            u = A[i];
            v = A[i+1];
            int d;

            //stay
            ans=Math.max(ans,Math.min(Math.min(u,v),2*A[sort[K]]));
            //bring u up
            d=0;
            if (u>A[sort[K-1]]) {
                d++;
                K--;
            }
            ans=Math.max(ans,Math.min(Math.min(INF,v),2*A[sort[K]]));
            K+=d;
            //bring v up
            d=0;
            if (v>A[sort[K-1]]) {
                d++;
                K--;
            }
            ans=Math.max(ans,Math.min(Math.min(INF,u),2*A[sort[K]]));
            K+=d;
            //bring u,v up
            d=0;
            if (v>A[sort[K-1]]) {
                d++;
                K--;
            }
            if (K<=0) {
                K+=d;
                continue;
            }
            if (u>A[sort[K-1]]){
                d++;
                K--;
            }
            if (K<=0) {
                K+=d;
                continue;
            }
            ans=Math.max(ans,Math.min(INF,2*A[sort[K]]));
            K+=d;
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
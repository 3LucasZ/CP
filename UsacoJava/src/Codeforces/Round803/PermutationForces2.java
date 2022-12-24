package Codeforces.Round803;

import java.io.*;
import java.util.*;

public class PermutationForces2 {
    static boolean debug = false;

    static int N;
    static int S;
    static int[] A;
    static int[] B;

    static long MOD = 998244353;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        S = io.nextInt();
        A = new int[N];
        B = new int[N];
        for (int i=0;i<N;i++){
            A[i]=io.nextInt();
        }
        for (int i=0;i<N;i++){
            B[i]=io.nextInt();
        }
        //* check for pre impossible case
        for (int i=0;i<N;i++){
            if (B[i]!=-1&&A[i]-B[i]>S){
                io.println(0);
                return;
            }
        }
        //* gen q,t then sort q,t
        ArrayList<Integer> q = new ArrayList<>();
        ArrayList<Integer> t = new ArrayList<>();
        boolean[] has = new boolean[N+1];
        for (int i=0;i<N;i++){
            if (B[i]==-1){
                q.add(A[i]);
            }
        }
        Collections.sort(q);
        for (int i=0;i<N;i++){
            if (B[i]==-1) continue;
            has[B[i]]=true;
        }
        for (int i=1;i<=N;i++) if (!has[i]) t.add(i);
        if (debug){
            io.println("q: "+q);
            io.println("t: "+t);
        }

        //* cnt 2 pointers
        int tp=t.size();
        int sz = 0;
        long ans = 1;
        for (int qp=q.size()-1;qp>=0;qp--){
            while (tp-1>=0&&q.get(qp)-t.get(tp-1)<=S) {
                tp--;
                sz++;
            }
            ans=ans*sz%MOD;
            sz--;
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
    void close(){
        out.close();
    }
};}
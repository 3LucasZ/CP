package USACO.Season2022_2023.Bronze;

import java.io.*;
import java.util.*;

public class FeedingTheCows {
    static boolean debug = false;

    static int N;
    static int R;
    static char[] breed;
    static char[] ans;
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        R = io.nextInt();
        String str = io.nextLine();
        breed = new char[N];
        ans = new char[N]; for (int i=0;i<N;i++) ans[i]='.';
        for (int i=0;i<N;i++) breed[i]=str.charAt(i);

        //* solve
        int t = 0;
        t+=solveBreed('G');
        t+=solveBreed('H');
        io.println(t);
        for (int i=0;i<N;i++)io.print(ans[i]);
        io.println();
    }
    public static int solveBreed(char b){
        int ret = 0;
        for (int i=0;i<N;i++){
            if (breed[i]!=b) continue;
            int last = 0;
            for (int j=i;j<=Math.min(N-1,i+2*R);j++){
                if (breed[j]==b) last=j;
            }
            ret++;
            int pos = Math.min(i+R,N-1);
            if (ans[pos]!='.') pos--;
            ans[pos]=b;
            i=last;
        }
        return ret;
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
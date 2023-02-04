package Other.Codeforces.Round842;

import java.io.*;
import java.util.*;

public class LuckyPermutation {
    static boolean debug = false;

    static int N;
    static int[] next;
    static ArrayList<ArrayList<Integer>> cycles;
    static boolean[] vis;
    static int[] id;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse]
        N = io.nextInt();
        next = new int[N+1];
        for (int i=1;i<=N;i++){
            next[i]=io.nextInt();
        }
        if (debug){
            io.println(Arrays.toString(next));
        }

        //cycle maker
        cycles = new ArrayList<>();
        vis = new boolean[N+1];
        id = new int[N+1];
        int marker = 1;
        for (int i=1;i<=N;i++){
            if (!vis[i]) {
                ArrayList<Integer> cycle = cycle(i);
                cycles.add(cycle);
                if (debug) io.println("new cycle: "+cycle);
                //claim ids
                for (int node : cycle) id[node]=marker;
                marker++;
            }
        }
        if (debug){
            io.println("id: "+Arrays.toString(id));
        }
        //total answer of a completely sorted set
        int ans = 0;
        for (ArrayList<Integer> cycle : cycles){
            ans+=cycle.size()-1;
        }
        ans++;
        //if there are 2 adjacent nodes with the same id we can subtract 1 from the total answer
        for (int i=1;i<N;i++){
            if (id[i]==id[i+1]){
                ans-=2;
                break;
            }
        }
        //ret
        io.println(ans);
    }
    static ArrayList<Integer> cycle(int i){
        ArrayList<Integer> ret = new ArrayList<>();
        int cur = i;
        vis[cur]=true;
        ret.add(cur);
        while (next[cur]!=i) {
            cur=next[cur];
            vis[cur]=true;
            ret.add(cur);

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
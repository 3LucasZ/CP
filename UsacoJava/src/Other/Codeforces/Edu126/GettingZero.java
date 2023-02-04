package Other.Codeforces.Edu126;

import java.io.*;
import java.util.*;
/*
PROB: GettingZero
LANG: JAVA
*/
public class GettingZero {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static final int N = 32768;
    public static void solve() throws IOException {
        //* parse
        Queue<Integer> BFS = new LinkedList<>();
        BFS.add(0);
        int[] dist = new int[N+1];
        dist[0]=1;
        dist[N]=1;
        BFS.add(N);
        while (!BFS.isEmpty()){
            int next = BFS.poll();
            int sub = (next-1+N)%N;
            int div = (next/2);
            int div2 = (next/2+N/2);
            if (next%2==0){
                if (dist[div]==0){
                    BFS.add(div);
                    dist[div]=dist[next]+1;
                }
                if (dist[div2]==0){
                    BFS.add(div2);
                    dist[div2]=dist[next]+1;
                }
            }
            if (dist[sub]==0){
                BFS.add(sub);
                dist[sub]=dist[next]+1;
            }
        }
        if (debug){
            io.println("dist:"+Arrays.toString(dist));
        }
        int M = io.nextInt();
        for (int i=0;i<M;i++){
            io.print(dist[io.nextInt()]-1+" ");
        }
        io.println();
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
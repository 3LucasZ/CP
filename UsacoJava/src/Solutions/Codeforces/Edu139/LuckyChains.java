package Solutions.Codeforces.Edu139;

import java.io.*;
import java.util.*;

public class LuckyChains {
    static boolean debug = false;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        int x = io.nextInt(); //small
        int y = io.nextInt(); //big
        int d = y-x;

        //* cheap case
        if (y-x==1) {
            io.println(-1);
            return;
        }

        //* math
        int min = Integer.MAX_VALUE;
        for (int i:primes){
            if (i*i>d) break;
            while (d%i==0){
                if (x%i==0) min=0;
                else min=Math.min(min,i-x%i);
                d/=i;
            }
        }
        if (d>1){
            if (x%d==0) min=0;
            else min=Math.min(min,d-x%d);
        }
        io.println(min);

    }




















    static IO io;
    static ArrayList<Integer> primes = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        io = new IO(debug);

        primes = primesLEQ(10000);
        int T = io.nextInt();
        for (int i=1;i<=T;i++) solve(i);
        io.close();
    }
    public static ArrayList<Integer> primesLEQ(int n){
        boolean[] prime = new boolean[n+1]; for (int i=2;i<=n;i++) prime[i]=true;
        for (int p=2;p*p<=n;p++){
            if (prime[p]){
                for (int i=p*p;i<=n;i+=p){
                    prime[i]=false;
                }
            }
        }
        ArrayList<Integer> ret = new ArrayList<>();
        for (int p=2;p<=n;p++){
            if (prime[p]) ret.add(p);
        }
        return ret;
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
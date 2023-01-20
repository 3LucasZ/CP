import java.io.*;
import java.util.*;

public class GCDQueries {
    static boolean debug = false;
    
    public static boolean solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        int N;
        N = io.nextInt();

        //* find what p1 is
        int[] freq = new int[N];
        int[] mem = new int[N+1];
        for (int i=2;i<=N;i++){
            int gcd = gcd(1,i);
            freq[gcd]++;
            mem[i]=gcd;
        }
        int val = 0;
        for (int k=1;k<N;k++){
            if (freq[k]==(N-1)/k){
                val=k;
            }
        }
        if (debug){
            io.println("freq: "+Arrays.toString(freq));
            io.println("p1: "+val);
        }

        //* edge case: val is 0 or 1
        if (val==0){
            int res = ans(1,1);
            if (res==-1) return false;
        }

        //* find pi where it is largest k*val
        ArrayList<Integer> srch = new ArrayList<>();
        for (int i=2;i<=N;i++) if (mem[i]==val) srch.add(i);

        int a = 1;
        int b = 1;
        val = 0;
        for (int i : srch){
            int gcd = gcd(b,i);
            if (gcd>val) {
                a = b;
                b = i;
                val=gcd;
            }
        }
        int res = ans(a,b);

        return res!=-1;
    }
    static int gcd(int i, int j){
        io.println("? "+i+" "+j);
        io.out.flush();
        return io.nextInt();
    }
    static int ans(int i, int j){
        io.println("! "+i+" "+j);
        io.out.flush();
        return io.nextInt();
    }




















    static IO io;
    public static void main(String[] args) throws IOException {
        io = new IO(debug);
        int T = io.nextInt();
        for (int i=1;i<=T;i++) if (!solve(i)) break;
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
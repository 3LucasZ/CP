package Other.Codeforces.Edu139;

import java.io.*;
import java.util.*;
/*
PROB: Decomposition
LANG: JAVA
*/
public class Decomposition {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static int[] A;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        A = new int[N+1];
        for (int i=1;i<=N;i++) A[i]=io.nextInt();

        //* unit test
        if (debug){
            ArrayList<Integer> test = new ArrayList<>();
            Collections.addAll(test,0);
            io.println("test: "+test);
            int pack = pack(test);
            io.println("pack: "+pack);
            ArrayList<Integer> unpack = unpack(pack);
            io.println("unpack: "+unpack);
            int add = add(pack,2);
            io.println("new: "+unpack(add));
        }
        //* dp
        //stp
        int K = 64;
        long[][] seq = new long[N+1][K+1];
        long ans = 0;

        //transitions
        for (int i=0;i<N;i++){
            seq[i][0]++;
            for (int j=0;j<=K;j++){
                if (A[i + 1] == 0) {
                    seq[i+1][j]=seq[i][j];
                    ans+=(N-i)*seq[i][j];
                } else {
                    if (seq[i][j]==0) continue;
                    int k = add(j, A[i+1]);
                    if (debug) io.println("j: "+j+", k: "+k);
                    seq[i+1][k]+=seq[i][j];
                    ArrayList<Integer> jlist = unpack(j);
                    ArrayList<Integer> klist = unpack(k);
                    if (klist.size()>jlist.size()) ans+=(N-i)*seq[i][j];
                }
            }
        }

        //* ret
        io.println(ans);
    }
    static int add(int seq, int next){
        ArrayList<Integer> unpack = unpack(seq);
        for (int i=0;i<unpack.size();i++){
            if ((unpack.get(i)&next)!=0) {
                unpack.set(i,next);
                return pack(unpack);
            }
        }
        unpack.add(next);
        return pack(unpack);
    }
    static ArrayList<Integer> unpack(int seq){
        ArrayList<Integer> ret = new ArrayList<>();
        if (seq%4!=0) ret.add(seq%4);
        if (seq%16/4!=0)ret.add(seq%16/4);
        if (seq/16!=0) ret.add(seq/16);
        return ret;
    }
    static int pack(ArrayList<Integer> seq){
        int ret = 0;
        for (int i=0;i<seq.size();i++){
            ret+=seq.get(i)*Math.pow(4,i);
        }
        return ret;
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
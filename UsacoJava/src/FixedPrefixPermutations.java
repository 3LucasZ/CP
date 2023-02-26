import java.io.*;
import java.util.*;

public class FixedPrefixPermutations {
    static boolean debug = false;

    static long A1=9973;
    static long A2 = 9157;
    static long MOD1=(int)(2e9)+11;
    static long MOD2=(int)(1e9)+9;
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        int N = io.nextInt();
        int M = io.nextInt();
        int[][] perms = new int[N][M];
        int[][] pos = new int[N][M+1];
        for (int i=0;i<N;i++){
            for (int j=0;j<M;j++){
                int num = io.nextInt();
                perms[i][j]=num;
                pos[i][num]=j;
            }
        }
        if (debug){
            io.print2d(perms);
        }
        long[] pow1 = new long[M];
        long[] pow2 = new long[M];
        pow1[0]=1;
        pow2[0]=1;
        for (int i=1;i<M;i++) {
            pow1[i]=(pow1[i-1]*A1)%MOD1;
            pow2[i]=(pow2[i-1]*A2)%MOD2;
        }
        if (debug){
            io.println("pow:"+Arrays.toString(pow1));
        }
        //* spam Hash
        HashMap<Long,Integer> hash1 = new HashMap<>();
        HashMap<Long,Integer> hash2 = new HashMap<>();
        for (int i=0;i<N;i++){
            long hsh1 = 0;
            long hsh2 = 0;
            for (int j=0;j<M;j++){
                hsh1 =(hsh1 + perms[i][j]*pow1[j])%MOD1;
                hsh2 = (hsh2 + perms[i][j]*pow2[j])%MOD2;
                hash1.put(hsh1,0);
                hash2.put(hsh2,0);
            }
            if (debug){
                io.println("final hsh1 "+i+":"+hsh1);
            }
        }
        //* checkin
        for (int i=0;i<N;i++){
            long build1 = 0;
            long build2 = 0;
            for (int j=1;j<=M;j++){
                build1 =(build1 + (pos[i][j]+1)*pow1[j-1])%MOD1;
                build2 =(build2 + (pos[i][j]+1)*pow2[j-1])%MOD2;
                if (hash1.containsKey(build1) && hash2.containsKey(build2)){
                    hash1.put(build1,1);
                    hash2.put(build2,1);
                }
            }
        }

        //* ret
        for (int i=0;i<N;i++){
            long hsh1 = 0;
            long hsh2 = 0;
            int ans = 0;
            for (int j=0;j<M;j++){
                hsh1 = (hsh1 + perms[i][j]*pow1[j])%MOD1;
                hsh2 = (hsh2 + perms[i][j]*pow2[j])%MOD2;
                if (hash1.get(hsh1)==1 && hash2.get(hsh2)==1){
                    ans=Math.max(ans,(j+1));
                }
            }
            io.print(ans+" ");
        }
        io.println();
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
    void print2d(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + arr[r][c];
                while (str.length() < 4) str += " ";
                print(str);
            }
           println();
        }
        println();
    }
    void print2d(char[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + arr[r][c];
                while (str.length() < 4) str += " ";
                print(str);
            }
            println();
        }
        println();
    }
    void print2d(boolean[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + (arr[r][c]?"1":"0");
                while (str.length() < 4) str += " ";
                print(str);
            }
            println();
        }
        println();
    }
    void printBin(int bin,int len){
        String ret = "";
        for (int i=0;i<len;i++){
            ret+=bin%2;
            bin/=2;
        }
        println(ret);
    }
    void close(){
        out.close();
    }
}}
import java.io.*;
import java.util.*;
/*
PROB: PilingPapers2
LANG: JAVA
*/
public class PilingPapers2 {
    static boolean fileSubmission = false;
    static String fileName = "";

    static boolean debug = true;

    static int N;
    static long A;
    static long B;
    static int[] digits;

    static int Q;
    static final long MOD =(long)(1e9+7);
    public static void solve() throws IOException {
        //* parse
        N = io.nextInt(); A = io.nextLong()-1; B = io.nextLong();
        digits = new int[N+2];
        for (int i=1;i<=N;i++) digits[i]=io.nextInt();
        if (debug){
            io.println("C:"+Arrays.toString(digits));
        }
        //* dp
        int Alen = len(A);
        int Blen = len(B);
        long[][] dpA = dpGen(toArr(A),digits);
        long[][] dpB = dpGen(toArr(B),digits);
        //* query
        Q = io.nextInt();
        for (int i=0;i<Q;i++){
            int l = io.nextInt();
            int r = io.nextInt();
            long Aans = dpA[l][r];
            long Bans = dpB[l][r];
            io.println((Bans-Aans+MOD)%MOD);
        }
    }
    static long[][] dpGen(int[] limit, int[] digits){
        long[][] ret = new long[N+1][N+1];
        for (int l=1;l<=N;l++){
            long[][][] dp = new long[limit.length][limit.length][3];
            for (int r=l;r<=N;r++){
                for (int x=1;x<=limit.length;x++){
                    for (int y=x;y<=limit.length;y++){

                    }
                }
            }
        }
        return ret;
    }
    static int[] toArr(long A){
        int len = len(A);
        int[] ret = new int[len+2];
        for (int i=len;i>=1;i--){
            ret[i]=(int)(A%10);
            A/=10;
        }
        return ret;
    }
    static int len(long x){
        int ret = 0;
        while (x>0){
            x/=10;
            ret++;
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
    void reverse(int[] arr){
		for (int i=0;i<arr.length/2;i++){
			int tmp = arr[i];
			arr[i]=arr[arr.length-1-i];
			arr[arr.length-1-i]=tmp;
		}
	}
    void close(){
        out.close();
    }
};
}
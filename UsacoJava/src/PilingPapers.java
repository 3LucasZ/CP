import java.io.*;
import java.util.*;
/*
PROB: PilingPapers
LANG: JAVA
*/
public class PilingPapers {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static long A;
    static long B;
    static int[] C;

    static int Q;
    static final long MOD =(long)(1e9+7);
    public static void solve() throws IOException {
        //* parse
        N =io.nextInt(); A = io.nextLong()-1; B = io.nextLong();
        C = new int[N+2];
        for (int i=1;i<=N;i++) C[i]=io.nextInt();
        if (debug){
            io.println("C:"+Arrays.toString(C));
        }
        //* dp
        int Adig = digits(A);
        int Bdig = digits(B);
        long[][] dpA = dpGen(A);
        long[][] dpB = dpGen(B);
        //* query
        Q = io.nextInt();
        for (int i=0;i<Q;i++){
            int l = io.nextInt();
            int r = io.nextInt();
            io.println(((dpB[l][r]-dpA[l][r])%MOD+MOD)%MOD);
        }
    }
    static long[][] dpGen(long given){
        if (debug) io.println("running dpGen on:"+given);
        //comp dig[i]
        int digits = digits(given);
        int[] dig = new int[digits+2];
        for (int i=digits;i>=1;i--){
            dig[i]=(int)(given%10);
            given/=10;
        }
        if (debug) io.println("dig:"+Arrays.toString(dig));
        //dp
        long[][][][][] dp = new long[N+2][N+2][digits+2][digits+2][3];
        //base case
        for (int l=1;l<=N;l++){
            for (int r=l;r<=N;r++){
                for(int Al=1;Al<=digits;Al++){
                    dp[l][r][Al][Al][comp(C[r],dig[Al])]+=2;
                }
            }
        }
        //transitions
        for (int l=1;l<=N;l++){
            for (int r=l;r<=N;r++){
                for(int x=digits;x>=1;x--){
                    for(int y=x;y<=digits;y++){
                        for (int q=0;q<=2;q++) {
                            //resolve
                            dp[l][r][x][y][q]%=MOD;
                            //throwaway
                            for (int q=0;q<=2;q++) dp[l][r+1][x][y][q]+=dp[l][r][x][y][q];
                        }
                        //1 top
                        if (C[r+1]==dig[y+1]) dp[l][r+1][x][y+1][1]+=dp[l][r][x][y][1];
                        //1 bottom
                        if (C[r+1]==dig[x-1]) dp[l][r+1][x-1][y][1]+=dp[l][r][x][y][1];

                        //0 top
                        dp[l][r+1][x][y+1][0]+=dp[l][r][x][y][0];
                        if (C[r+1]<dig[y+1]) dp[l][r+1][x][y+1][0]+=dp[l][r][x][y][1];
                        //2 top
                        dp[l][r+1][x][y+1][2]+=dp[l][r][x][y][2];
                        if (C[r+1]>dig[y+1]) dp[l][r+1][x][y+1][2]+=dp[l][r][x][y][1];

                        //0 bottom
                        if (C[r+1]<dig[x-1]){
                            for (int q=0;q<=2;q++) dp[l][r+1][x-1][y][0]+=dp[l][r][x][y][q];
                        }
                        if (C[r+1]==dig[x-1]) dp[l][r+1][x-1][y][0]+=dp[l][r][x][y][0];
                        //2 bottom
                        if (C[r+1]>dig[x-1]){
                            for (int q=0;q<=2;q++) dp[l][r+1][x-1][y][2]+=dp[l][r][x][y][q];
                        }
                        if (C[r+1]==dig[x-1]) dp[l][r+1][x-1][y][2]+=dp[l][r][x][y][2];
                        if (debug){
                            for (int q=0;q<=2;q++){
                                io.println(l+" "+r+" "+x+" "+y+" "+q+":"+dp[l][r][x][y][q]);
                            }
                        }
                    }
                }
            }
        }
        long[][] ret = new long[N+1][N+1];
        for (int l=1;l<=N;l++){
            for (int r=l;r<=N;r++){
                for (int x=1;x<=digits;x++){
                    ret[l][r]+=dp[l][r][x][digits][0];
                    ret[l][r]+=dp[l][r][x][digits][1];
                    if (x!=1) ret[l][r]+= dp[l][r][x][digits][2];
                    ret[l][r]%=MOD;
                }
            }
        }
        if (debug){
            io.print2d(ret);
        }
        return ret;
    }
    static int comp(int a, int b){
        if (a<b) return 0;
        else if (a==b) return 1;
        else return 2;
    }
    static int digits(long x){
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
    void print2d(long[][] arr) {
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
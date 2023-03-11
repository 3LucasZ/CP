package Solutions.USACO.SeasonOld.March2014.Silver;

import java.io.*;
import java.util.*;
/*
PROB: Odometer
LANG: JAVA
*/
public class Odometer {
    static boolean fileSubmission = true;
    static String fileName = "odometer";
    
    static boolean debug = false;

    static long A;
    static long B;
    public static void solve() throws IOException {
        //* parse
        A = io.nextLong();
        B = io.nextLong();
        io.println(leq(B)-leq(A-1));
    }
    static long leq(long num){
        int[] lim = split(num);
        int digs = lim.length-1;
        if (debug){
            io.println("num:"+num);
            io.println("lim:"+Arrays.toString(lim));
        }
        long[][][][] dp = new long[10][digs+1][50][3];
        //iter
        for (int T=0;T<10;T++){
            for (int L=1;L<=digs;L++){
                for (int add=0;add<10;add++){
                    //base
                    if (add!=0){
                        if (L==1){
                            dp[T][L][25+(add==T?1:-1)][cmp(add,lim[L])]++;
                        } else {
                            dp[T][L][25+(add==T?1:-1)][0]++;
                        }
                    }
                    for (int cnt=5;cnt<=45;cnt++){
                        //trans
                        int cntq = cnt+(add==T?-1:1);
                        dp[T][L][cnt][0]+=dp[T][L-1][cntq][0];
                        dp[T][L][cnt][2]+=dp[T][L-1][cntq][2];
                        dp[T][L][cnt][cmp(add,lim[L])]+=dp[T][L-1][cntq][1];
                    }
                }
            }
        }
        if (debug){
            for (int i=0;i<10;i++){
                for (int j=1;j<=digs;j++){
                    for (int k=25;k<=25;k++){
                        for (int l=0;l<3;l++){
                            if (dp[i][j][k][l]!=0){
                                io.println(i+", "+j+", "+k+", "+l+": "+dp[i][j][k][l]);
                            }
                        }
                    }
                }
            }
            io.println();
        }
        long[][][][][] dp2;
        dp2 = new long[10][10][digs+1][50][3];
        for (int T1=0;T1<10;T1++){
            for (int T2=T1+1;T2<10;T2++){
                int[] x = {T1,T2};
                for (int L=1;L<=digs;L++){
                    for (int addId=0;addId<2;addId++){
                        int add = x[addId];
                        //base
                        if(add!=0){
                            if(L==1){
                                dp2[T1][T2][L][25+(add==T1?1:-1)][cmp(add,lim[L])]++;
                            }else{
                                dp2[T1][T2][L][25+(add==T1?1:-1)][0]++;
                            }
                        }
                        for (int cnt1=5;cnt1<=45;cnt1++){
                            //trans
                            int cntq = cnt1+(add==T1?-1:1);
                            dp2[T1][T2][L][cnt1][0]+=dp2[T1][T2][L-1][cntq][0];
                            dp2[T1][T2][L][cnt1][2]+=dp2[T1][T2][L-1][cntq][2];
                            dp2[T1][T2][L][cnt1][cmp(add,lim[L])]+=dp2[T1][T2][L-1][cntq][1];
                        }
                    }
                }
            }
        }

        long ret = 0;
        for (int T=0;T<10;T++){
            for (int cnt=25;cnt<50;cnt++){
                for (int q=0;q<=1;q++){
                    ret+=dp[T][digs][cnt][q];
                }
            }
        }
        for (int T1=0;T1<10;T1++){
            for (int T2=0;T2<10;T2++){
                    for(int q=0;q<=1;q++){
                        ret-=dp2[T1][T2][digs][25][q];
                    }
            }
        }
        return ret;
    }
    static int cmp(int a, int b){
        return Integer.signum(a-b)+1;
    }
    static int[] split(long num){
        String str = " "+num;
        int[] ret = new int[str.length()];
        for (int i=0;i<str.length();i++){
            ret[i]=str.charAt(i)-'0';
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
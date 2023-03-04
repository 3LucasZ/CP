package Other.Codeforces.Edu137;

import java.io.*;
import java.util.*;
/*
PROB: FTL
LANG: JAVA
*/
public class FTL {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int p1; static long t1;
    static int p2; static long t2;
    static int h; static int s;

    static final long INF = Long.MAX_VALUE/10;
    public static void solve() throws IOException {
        //* parse
        p1=io.nextInt();t1=io.nextLong();
        p2=io.nextInt();t2=io.nextLong();
        h=io.nextInt();s=io.nextInt();

        //* itemReset, item
        long[] item = new long[h+1]; //dp[damage]=min time, end with double shoot
        long[] itemReset = new long[h+1]; //dp[damage]=min time, all single shoot
        Arrays.fill(item,INF);
        Arrays.fill(itemReset,INF);
        for (int s1=0;s1<=h;s1++){
            for (int s2=0;s2<=h;s2++){
                long T1 = s1*t1;
                long T2 = s2*t2;
                long T12 = Math.max(T1,T2);
                long x1 = (s1+1)*t1 - T12;
                long x2 = (s2+1)*t2 - T12;
                long x12 = Math.max(x1,x2);

                int damage = Math.min(h,(p1-s)*s1 + (p2-s)*s2);
                item[damage]=Math.min(item[damage],T12);
                int damageReset = Math.min(h,damage + (p1+p2-s));
                itemReset[damageReset]=Math.min(itemReset[damageReset],T12+x12);
            }
        }
        for (int i=h-1;i>=0;i--){
            itemReset[i]=Math.min(itemReset[i],itemReset[i+1]);
            item[i]=Math.min(item[i],item[i+1]);
        }
        if (debug){
            io.println("item:"+Arrays.toString(item));
            io.println("itemReset:"+Arrays.toString(itemReset));
        }
        //* knapsack
        long[] dp = new long[h+1]; //min time for damage (using resetItem and then item last)
        Arrays.fill(dp,INF); dp[0]=0;
        for (int i=0;i<h;i++){
            for (int j=1;j<=h;j++){
                if (i+j>h) continue;
                dp[i+j]=Math.min(dp[i+j],dp[i]+itemReset[j]);
            }
            dp[h]=Math.min(dp[h],dp[i]+item[h-i]);
        }
        //* ret
        io.println(dp[h]);
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
import java.io.*;
import java.util.*;

public class PermutationRestoration {
    static boolean debug = false;

    static int N;
    static int[] B;
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        B = new int[N+1];
        for (int i=1;i<=N;i++) B[i]=io.nextInt();
        //* find l,r
        ArrayList<Range> ranges = new ArrayList<>();
        for (int i=1;i<=N;i++){
            ranges.add(new Range(i,firstEq(i,B[i]),firstEq(i,B[i]-1)-1));
        }
        if (debug){
            io.println(ranges);
        }
        Collections.sort(ranges,(a,b)->{
            if (a.l==b.l){
                return a.r-b.r;
            }
            return a.l-b.l;
        });
        //* ret
        int[] ans = new int[N+1];
        int x = 1;
        for (Range r :ranges){
            ans[r.id]=x;
            x++;
        }
        for (int i=1;i<=N;i++){
            io.print(ans[i]+" ");
        }
        io.println();
    }
    static int firstEq(int i, int b){
        int lo=1;int hi=N+1;
        while (lo<hi){
            int mid = (lo+hi)/2;
            if (i/mid<=b){
                hi=mid;
            } else {
                lo=mid+1;
            }
        }
        return hi;
    }
    private static class Range {
        int id;
        int l;
        int r;
        public Range(int id, int l, int r){
            this.id=id;
            this.l=l;
            this.r=r;
        }
        public String toString(){
            return "["+l+", "+r+"]";
        }
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
}}
package Solutions.USACO.Season2022_2023.Feb2023.Silver;

import java.io.*;
import java.util.*;
/*
PROB: CowLibi
LANG: JAVA
*/
public class CowLibi {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int G;
    static int N;

    static int INF =(int)(1e9+1);
    public static void solve() throws IOException {
        //* parse
        G = io.nextInt();
        N = io.nextInt();
        TreeSet<Garden> gardens = new TreeSet<>(Comparator.comparingLong(a->a.t));
        for (int i=0;i<G;i++){
            long x = io.nextInt();
            long y = io.nextInt();
            long t = io.nextInt();
            gardens.add(new Garden(x,y,t));
        }
        if (debug){
            io.println(gardens);
        }
        //* brute force
        int ans = N;
        for (int i=0;i<N;i++){
            long x = io.nextInt();
            long y = io.nextInt();
            long t = io.nextInt();
            Garden cow = new Garden(x,y,t);
            Garden lo = gardens.floor(cow);
            Garden hi = gardens.ceiling(cow);
            if (lo!=null){
                if (!canTravel(cow,lo)) continue;
            }
            if (hi!=null){
                if (!canTravel(cow,hi)) continue;
            }
            if (debug){
                io.println("can travel:"+cow);
            }
            ans--;
        }

        //* ret
        io.println(ans);
    }
    static boolean canTravel(Garden a, Garden b){
        long dist = Math.abs(b.x-a.x)*Math.abs(b.x-a.x)+Math.abs(b.y-a.y)*Math.abs(b.y-a.y);
        long t = Math.abs(a.t-b.t)*Math.abs(a.t-b.t);
        return t>=dist;
    }
    private static class Garden {
        long x;
        long y;
        long t;
        public Garden(long x, long y, long t){
            this.x=x;
            this.y=y;
            this.t=t;
        }
        public String toString(){
            return "["+x+", "+y+": "+t+"]";
        }
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
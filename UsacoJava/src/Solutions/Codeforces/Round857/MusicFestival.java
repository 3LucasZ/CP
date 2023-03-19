package Solutions.Codeforces.Round857;

import java.io.*;
import java.util.*;

public class MusicFestival {
    static boolean debug = false;

    static int N;
    static ArrayList<Integer>[] albums;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        albums = new ArrayList[N+1];
        albums[0] = new ArrayList<>(); albums[0].add(0);
        for (int i=1;i<=N;i++){
            albums[i] = new ArrayList<>();
            int K = io.nextInt();
            albums[i].add(io.nextInt());
            for (int j=1;j<K;j++) {
                int prev = albums[i].get(albums[i].size()-1);
                int next = io.nextInt();
                if (next>prev) albums[i].add(next);
            }
        }
        //* greedy sort based on end
        Arrays.sort(albums,Comparator.comparingInt(a->a.get(a.size()-1)));
        if (debug){
            io.println(Arrays.toString(albums));
        }
        //* dp
        int[] dp = new int[N+1];
        for (int i=1;i<=N;i++){
            dp[i] = Math.max(dp[i-1],albums[i].size());
            for (int x=0;x<albums[i].size();x++){
                int cost = albums[i].get(x);
                int j = firstLessThan(cost);
                if (j==0) continue;
                dp[i]=Math.max(dp[i],dp[j]+albums[i].size()-x);
            }
        }
        //* ret
        io.println(dp[N]);
    }
    static int firstLessThan(int x){
        int lo = 0;
        int hi = N;
        while (lo<hi){
            int mid = (lo+hi+1)/2;
            if (albums[mid].get(albums[mid].size()-1)<x){
                lo=mid;
            } else {
                hi=mid-1;
            }
        }
        return lo;
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
    String binToStr(int bin,int len){
			String ret="";
			for(int i=0;i<len;i++){
				ret+=bin%2;
				bin/=2;
			}
			return ret;
		}
	int log2(int num){
	    return (int)(Math.log(num)/Math.log(2));
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
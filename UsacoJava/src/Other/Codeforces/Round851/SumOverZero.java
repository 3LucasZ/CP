package Other.Codeforces.Round851;

import java.io.*;
import java.util.*;
/*
PROB: SumOverZero
LANG: JAVA
*/
public class SumOverZero {
	static boolean fileSubmission = false;
	static String fileName = "";

	static boolean debug = false;

	static int n;
	static int[] a;
	static int[] p;

	public static void solve() throws IOException {
		//* parse
		n = io.nextInt();
		a = new int[n+1];
		for (int i=1;i<=n;i++) a[i]=io.nextInt();
		//* precomp p
		long[] pre = new long[n+1]; pre[0] = 0;
		for (int i=1;i<=n;i++) pre[i]=pre[i-1]+a[i];
		Integer[] srt = new Integer[n+1];
		for (int i=0;i<=n;i++) srt[i]=i;
		Arrays.sort(srt,Comparator.comparingLong(a->pre[a]));
		p = new int[n+1]; p[srt[0]]=1;
		for (int i=1;i<=n;i++){
			p[srt[i]]=p[srt[i-1]];
			if (pre[srt[i]]>pre[srt[i-1]]) p[srt[i]]++;
		}
		if (debug){
			io.println("a:"+Arrays.toString(a));
			io.println("pre:"+Arrays.toString(pre));
			io.println("p:"+Arrays.toString(p));
		}
		//* dp with seg
		SegTree seg = new SegTree(n+2);
		int[] dp = new int[n+1];
		dp[0]=0;
		seg.set(p[0],0);
		for (int i=1;i<=n;i++){
			dp[i]=(int)Math.max(dp[i-1],seg.max(1,p[i])+i);
			seg.set(p[i],dp[i]-i);
		}
		//* ret
		io.println(dp[n]);
	}
	private static class SegTree {
		//1-indexed
		//range is []
		int size;
		long[] tree;
		long[] val;
		public SegTree(int n){
			init(n);
		}
		public void init(int n){
			size = 1;
			while (size < n) size *= 2;
			tree = new long[2*size+1];
			val = new long[n+1];
			Arrays.fill(tree,Integer.MIN_VALUE);
			Arrays.fill(val,Integer.MIN_VALUE);
		}
		void set(int k, long x){
			val[k]=Math.max(val[k],x);
			k+=size-1;
			tree[k]=x;
			for (k/=2;k>=1;k/=2){
				tree[k]=Math.max(tree[2*k],tree[2*k+1]);
			}
		}
		long max(int a, int b) {
			a+=size-1;
			b+=size-1;
			long ret = Integer.MIN_VALUE;
			while (a<=b){
				if (a%2==1) ret=Math.max(ret,tree[a++]);
				if (b%2==0) ret=Math.max(ret,tree[b--]);
				a/=2;
				b/=2;
			}
			return ret;
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
		void close(){
			out.close();
		}
	};
}

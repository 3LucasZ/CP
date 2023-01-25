package Codeforces.Round793;

import java.io.*;
import java.util.*;

public class CircularSpanningTree {
	static boolean debug = false;

	static int N;
	static int[] par;

	public static void solve(int tcs) throws IOException {
		if (debug) io.println("Case: "+tcs);
		//* parse
		N = io.nextInt();
		String str = io.nextLine();
		par = new int[N];
		for (int i=0;i<N;i++){
			par[i]=str.charAt(i)-'0';
		}

		//* check for suf
		int ones = 0;
		for (int i=0;i<N;i++){
			if (par[i]==1) ones++;
		}
		if (ones%2!=0 || ones<2){
			io.println("NO");
			return;
		}
		io.println("YES");

		//* construct
		//find center
		int center = 0;
		for (int i=0;i<N;i++){
			int l = (i-1+N)%N;
			if (par[l]==1) {
				center = i;
				break;
			}
		}
		if (debug){
			io.println("center: "+center);
		}

		//loop through rest of nodes
		for (int i=1;i<N;i++){
			int u = (center+i)%N;
			int l = (u-1+N)%N;
			//leaf case
			if (par[u]==1){
				//0 1
				if (par[l]==0){
					makeEdge(l,u);
				}
				//1 1
				else {
					makeEdge(center,u);
				}
			}
			//connector case
			else {
				//0 0
				if (par[l]==0){
					makeEdge(l,u);
				}
				//1 0
				else {
					makeEdge(center,u);
				}
			}
		}
	}
	static void makeEdge(int u, int v){
		io.println((u+1)+" "+(v+1));
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
	};}
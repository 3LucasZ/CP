package Helpers.Graph;

import java.util.*;
import java.io.*;

public class LCASlow{
	/*
    Goal: LCA (u,v)
    Arguments:  [1-indexed tree]
    Time Complexity: O(N+M+NQ)
    Space Complexity: O(N+M)
    CSES Data set (verified 3/23): https://cses.fi/problemset/task/1688/
     */
	private static class LCA {
		ArrayList<Integer>[] tree;
		//[node]
		int[] ancestor;
		int[] height;

		public LCA(ArrayList<Integer>[] tree){
			this.tree=tree;
			height = new int[tree.length];
			DFS_height(1, 0, 0);
			ancestor = new int[tree.length];
			DFS_ancestor(1, 0);
		}
		public int getLCA(int u, int v){
			//assume height[v]<height[u]
			if (height[v]>height[u]){
				int tmp = v;
				v=u;
				u=tmp;
			}
			//move u up until its at v
			int num = height[u]-height[v];
			for (int i=0;i<num;i++) u=ancestor[u];
			//binary lifting
			while (u!=v){
				u=ancestor[u];
				v=ancestor[v];
			}
			return u;
		}
		public void DFS_height(int node, int par, int h){
			height[node]=h;
			for (int child : tree[node]) if (child!=par) DFS_height(child,node,h+1);
		}
		public void DFS_ancestor(int node, int par){
			ancestor[node]=par;
			for (int child : tree[node]) if (child!=par) DFS_ancestor(child,node);
		}
	}


	static ArrayList<Integer>[] tree;
	static boolean debug=false;

	public static void solve() {
		int N = io.nextInt();
		int Q = io.nextInt();
		ArrayList<Integer>[] tree=new ArrayList[N+1];
		for(int i=1;i<=N;i++) tree[i]=new ArrayList<>();
		for(int u=2;u<=N;u++){
			int v = io.nextInt();
			tree[u].add(v);
			tree[v].add(u);
		}
		LCA lca=new LCA(tree);
		for(int i=0;i<Q;i++){
			int u=io.nextInt();
			int v=io.nextInt();
			int m=lca.getLCA(u,v);
			io.println(m);
		}
	}

	public static void main(String[] args){
		io = new IO(true);
		solve();
		io.close();
	}
	static IO io;
	public static long lcm(long a, long b){
		return a*b/gcd(a,b);
	}
	public static long gcd(long a, long b){
		if (b==0) return a;
		return gcd(b,a%b);
	}
	private static class Triple <T1, T2, T3> {
		T1 first;
		T2 second;
		T3 third;
		public Triple(T1 first,T2 second, T3 third){
			this.first=first;
			this.second=second;
			this.third=third;
		}
		public String toString(){
			return "["+first+", "+second+", "+third+"]";
		}
	}
	private static class Pair <T1, T2> {
		T1 first;
		T2 second;
		public Pair(T1 first,T2 second){
			this.first=first;
			this.second=second;
		}
		@Override
		public boolean equals(Object o){
			if (!(o instanceof Pair)) return false;
			Pair<T1,T2> other = (Pair<T1,T2>) o;
			return first.equals(other.first) && second.equals(other.second);
		}
		public String toString(){
			return "["+first+", "+second+"]";
		}
	}
	static <T> T last(ArrayList<T> list){
		return list.get(list.size()-1);
	}
	static String binToStr(int bin,int len){
		String ret="";
		for(int i=0;i<len;i++){
			ret+=bin%2;
			bin/=2;
		}
		return ret;
	}
	static int log2(int num){
		return (int)(Math.log(num)/Math.log(2));
	}
	static void reverse(int[] arr){
		for (int i=0;i<arr.length/2;i++){
			int tmp = arr[i];
			arr[i]=arr[arr.length-1-i];
			arr[arr.length-1-i]=tmp;
		}
	}
	static void print2d(int[][] arr){
		for(int r=0;r<arr.length;r++){
			for(int c=0;c<arr[r].length;c++){
				String str=""+arr[r][c];
				while(str.length()<4) str+=" ";
				io.print(str);
			}
			io.println();
		}
		io.println();
	}

	static void print2d(char[][] arr){
		for(int r=0;r<arr.length;r++){
			for(int c=0;c<arr[r].length;c++){
				io.print(arr[r][c]);
			}
			io.println();
		}
		io.println();
	}

	static void print2d(boolean[][] arr){
		for(int r=0;r<arr.length;r++){
			for(int c=0;c<arr[r].length;c++){
				String str=""+(arr[r][c]?"1":"0");
				while(str.length()<4) str+=" ";
				io.print(str);
			}
			io.println();
		}
		io.println();
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
		void close(){
			out.close();
		}
	}
}

import java.io.*;
import java.util.*;

public class TestsForProblemD{
	static boolean debug=true;

    static int N;
	static ArrayList<Integer>[] tree;

	public static void solve() throws IOException{
		//* parse
		N = io.nextInt();
		tree = new ArrayList[N+1]; for (int i=1;i<=N;i++){
			tree[i] = new ArrayList<>();
		}
		for (int i=0;i<N-1;i++){
			int u = io.nextInt();
			int v = io.nextInt();
			tree[u].add(v);
			tree[v].add(u);
		}
		//* DFS1
		ArrayList<Integer> ret = DFS(1,0).toList();
		String[] ans = new String[N+1]; Arrays.fill(ans,"");
		for (int i = 0;i<2*N;i++){
			ans[ret.get(i)]+=(i+1)+" ";
		}
		for (int i=1;i<=N;i++){
			io.println(ans[i]);
		}
	}

	static DoublyLinkedList<Integer> DFS(int node, int par){
		ArrayList<Integer> fin = new ArrayList<>();
		DoublyLinkedList<Integer> u = new DoublyLinkedList<>();
		for (int child : tree[node]){
			if (child==par) continue;
			DoublyLinkedList<Integer> v = DFS(child,node);
			fin.add(v.popFirst());
			u.append(v);
		}
		u.addFirst(node);
		for (int i : fin) u.addFirst(i);
		u.addFirst(node);
		return u;
	}
	public static void main(String[] args) throws IOException{
		io=new IO(debug);
		solve();
		io.close();
	}

	static IO io;

	public static long lcm(long a,long b){
		return a*b/gcd(a,b);
	}

	public static long gcd(long a,long b){
		if(b==0) return a;
		return gcd(b,a%b);
	}

	private static class DoublyLinkedList<T1> {
		private Node<T1> first;
		private Node<T1> last;
		public void addFirst(T1 value){
			addFirst(new Node<>(value));
		}
		public void addFirst(Node<T1> node){
			if (first==null){
				first=node;
				last=node;
			} else{
				first.before=node;
				node.after=first;
				first=node;
			}
		}
		public void addBack(T1 value){
			addBack(new Node<>(value));
		}
		public void addBack(Node<T1> node){
			if (last==null){
				first=node;
				last=node;
			} else{
				last.after=node;
				node.before=last;
				last=node;
			}
		}
		public void append(DoublyLinkedList<T1> other){
			if (last==null) {
				this.first=other.first;
				this.last=other.last;
			} else{
				last.after=other.first;
				other.first.before=last;
				last=other.last;
				other=null;
			}
		}
		public T1 popFirst() {
			if (first==null) return null;
			else {
				T1 ret = first.value;
				first=first.after;
				return ret;
			}
		}
		public T1 popLast() {
			if (last==null) return null;
			else {
				T1 ret = last.value;
				last=last.before;
				return ret;
			}
		}
		public ArrayList<T1> toList() {
			ArrayList<T1> ret = new ArrayList<>();
			Node<T1> cur = first;
			while (cur!=null){
				ret.add(cur.value);
				cur=cur.after;
			}
			return ret;
		}
		private static class Node <T2> {
			Node<T2> after;
			Node<T2> before;
			T2 value;
			public Node(T2 value){
				this.value=value;
			}
		}
	}
	private static class Triple<T1,T2,T3>{
		T1 first;
		T2 second;
		T3 third;

		public Triple(T1 first,T2 second,T3 third){
			this.first=first;
			this.second=second;
			this.third=third;
		}

		public String toString(){
			return "["+first+", "+second+", "+third+"]";
		}
	}

	private static class Pair<T1,T2>{
		T1 first;
		T2 second;

		public Pair(T1 first,T2 second){
			this.first=first;
			this.second=second;
		}

		@Override
		public boolean equals(Object o){
			if(!(o instanceof Pair)) return false;
			Pair<T1,T2> other=(Pair<T1,T2>)o;
			return first.equals(other.first)&&second.equals(other.second);
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
		for(int i=0;i<arr.length/2;i++){
			int tmp=arr[i];
			arr[i]=arr[arr.length-1-i];
			arr[arr.length-1-i]=tmp;
		}
	}

	private static class IO{
		BufferedReader br;
		StringTokenizer st;
		PrintWriter out;
		boolean debug;

		public IO(boolean dbg){
			br=new BufferedReader(new InputStreamReader(System.in));
			out=new PrintWriter(System.out);
			debug=dbg;
		}

		public IO(String fileName,boolean dbg) throws IOException{
			br=new BufferedReader(new FileReader(fileName+".in"));
			out=new PrintWriter(new FileWriter(fileName+".out"));
			debug=dbg;
		}

		String next(){
			while(st==null||!st.hasMoreElements()){
				try{
					st=new StringTokenizer(br.readLine());
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt(){
			return Integer.parseInt(next());
		}

		long nextLong(){
			return Long.parseLong(next());
		}

		double nextDouble(){
			return Double.parseDouble(next());
		}

		String nextLine(){
			String str="";
			try{
				if(st.hasMoreTokens()){
					str=st.nextToken("\n");
				}else{
					str=br.readLine();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
			return str;
		}

		void println(){
			if(debug) System.out.println();
			else out.println();
		}

		void println(Object obj){
			if(debug) System.out.println(obj);
			else out.println(obj);
		}

		void print(Object obj){
			if(debug) System.out.print(obj);
			else out.print(obj);
		}

		void print2d(int[][] arr){
			for(int r=0;r<arr.length;r++){
				for(int c=0;c<arr[r].length;c++){
					String str=""+arr[r][c];
					while(str.length()<4) str+=" ";
					print(str);
				}
				println();
			}
			println();
		}

		void print2d(char[][] arr){
			for(int r=0;r<arr.length;r++){
				for(int c=0;c<arr[r].length;c++){
					print(arr[r][c]);
				}
				println();
			}
			println();
		}

		void print2d(boolean[][] arr){
			for(int r=0;r<arr.length;r++){
				for(int c=0;c<arr[r].length;c++){
					String str=""+(arr[r][c]?"1":"0");
					while(str.length()<4) str+=" ";
					print(str);
				}
				println();
			}
			println();
		}

		void close(){
			out.close();
		}
	}

	;
}
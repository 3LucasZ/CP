package Solutions.Codeforces.PickNPlay;

import java.io.*;
import java.util.*;
/*
Works, but TLE. Its okay tho, I'll take it as a win! :)
 */
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
		int[] l = new int[N+1];
		int[] r = new int[N+1];
		for (int i = 0;i<2*N;i++){
			if (l[ret.get(i)]==0) l[ret.get(i)]=i+1;
			else r[ret.get(i)]=i+1;
		}
		for (int i=1;i<=N;i++){
			io.println(l[i]+" "+r[i]);
		}
	}
	static int[] l;
	static int[] r;
	static DoublyLinkedList<Integer> DFS(int node, int par){
		DoublyLinkedList<Integer> u = new DoublyLinkedList<>();
		for (int child : tree[node]){
			if (child==par) continue;
			DoublyLinkedList<Integer> v = DFS(child,node);
			u.append(v);
		}
		u.addFirst(node);
		for (int i : tree[node]) if (i!=par) u.addFirst(i);
		if (node==1) u.addFirst(node);
		return u;
	}


	public static void main(String[] args) throws IOException{
		io=new IO();
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
				T1 ret = first.value;
				first=first.after;
				return ret;
		}
		public T1 popLast() {
				T1 ret = last.value;
				last=last.before;
				return ret;
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

	static class IO{
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;
		PrintWriter out;

		public IO()
		{
			din = new DataInputStream(System.in);
			out = new PrintWriter(System.out);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public IO(String file_name) throws IOException
		{
			din = new DataInputStream(
					new FileInputStream(file_name+".in"));
			out = new PrintWriter(new FileWriter(file_name+".out"));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException
		{
			byte[] buf = new byte[64]; // IMPORTANT: read line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n') {
					if (cnt != 0) {
						break;
					}
					else {
						continue;
					}
				}
				buf[cnt++] = (byte)c;
			}
			return new String(buf, 0, cnt);
		}

		public String readLine(int len) throws IOException{
			byte[] buf=new byte[len]; // IMPORTANT: read line length
			int cnt=0, c;
			while((c=read())!=-1){
				if(c=='\n'){
					if(cnt!=0){
						break;
					}else{
						continue;
					}
				}
				buf[cnt++]=(byte)c;
			}
			return new String(buf,0,cnt);
		}

		public int nextInt() throws IOException
		{
			int ret = 0;
			byte c = read();
			while (c <= ' ') {
				c = read();
			}
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() throws IOException
		{
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() throws IOException
		{
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException
		{
			bytesRead = din.read(buffer, bufferPointer = 0,
					BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException
		{
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException
		{
			if (din == null)
				return;
			din.close();
			out.close();
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
	}
}
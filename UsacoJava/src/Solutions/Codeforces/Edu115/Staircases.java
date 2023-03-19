package Solutions.Codeforces.Edu115;

import java.io.*;
import java.util.*;

public class Staircases{
	static boolean debug=false;

	static int R;
	static int C;
	static int Q;
	static boolean[][] blocked;

	static int single;

	public static void solve() throws IOException{
		//* parse
        R = io.nextInt();
		C = io.nextInt();
		blocked = new boolean[R][C];
		single= R*C;
		//* init
		long ans = 0;
		for (int r=0;r<R-1;r++){
			Pair<Long, Integer> res =  getStairCaseResFromHead(r,0,1);
			ans+=res.first+res.second;
		}
		for (int c=0;c<C-1;c++){
			Pair<Long, Integer> res = getStairCaseResFromHead(0,c,0);
			ans+=res.first+res.second;
		}
		if (debug){
			io.println("fin init");
		}
		//* handle Q
		Q = io.nextInt();
		for (int i=0;i<Q;i++){
			int r = io.nextInt()-1;
			int c = io.nextInt()-1;
			if (debug){
				io.println();
				io.println("query:"+r+", "+c);
			}

			Triple<Integer,Integer,Integer> head1 = getHead(r,c,0);
			Triple<Integer,Integer,Integer> head2 = getHead(r,c,1);
			Pair<Long,Integer> res1;
			Pair<Long,Integer> res2;

			res1 = getStairCaseResFromHead(head1.first,head1.second,head1.third);
			ans -= res1.first+res1.second;
			res2 = getStairCaseResFromHead(head2.first,head2.second,head2.third);
			ans -= res2.first+res2.second;

			if (blocked[r][c]) single++;
			else single--;
			blocked[r][c]=!blocked[r][c];

			res1 = getStairCaseResFromHead(head1.first,head1.second,head1.third);
			ans += res1.first+res1.second;
			res2 = getStairCaseResFromHead(head2.first,head2.second,head2.third);
			ans += res2.first+res2.second;

			//ret
			if (debug){
				io.println("freeBlocks:"+single);
			}
			io.println(ans+single);
		}
	}


	//0 => R, 1 => D
	static int[] dr = {0,1};
	static int[] dc = {1,0};
	public static Triple<Integer,Integer,Integer> getHead(int r, int c, int dir){
		while(r-dr[dir]>=0&&r-dr[dir]<R&&c-dc[dir]>=0&&c-dc[dir]<C){
			r=r-dr[dir];
			c=c-dc[dir];
			dir=1-dir;
		}
		return new Triple<>(r,c,1-dir);
	}
	public static Pair<Long,Integer> getStairCaseResFromHead(int r, int c, int dir){
		if (debug){
			io.println("r:"+r+", c:"+c+", dir:"+dir);
		}
		long ret = 0;
		long cnt = 0;
		while (!(r<0||r>=R||c<0||c>=C)){
			if(blocked[r][c]) {
				if (debug) io.println("blocked");
				ret+=cnt*(cnt-1)/2;
				cnt = 0;
			}
			else cnt++;
			r = r+dr[dir];
			c = c+dc[dir];
			dir = 1-dir;
		}
		ret+=cnt*(cnt-1)/2;
		if (debug){
			io.println("ret:"+ret);
		}
		return new Pair<>(ret,0);
	}


	public static void main(String[] args) throws IOException{
		io=new IO(debug);
		solve();
		io.close();
	}

	static IO io;

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

		public String toString(){
			return "["+first+", "+second+"]";
		}
	}

	static <T> T last(ArrayList<T> list){
		return list.get(list.size()-1);
	}

	static <T> void swap(T a,T b){
		T tmp=a;
		a=b;
		b=tmp;
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
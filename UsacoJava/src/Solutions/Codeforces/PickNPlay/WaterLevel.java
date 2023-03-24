package Solutions.Codeforces.PickNPlay;

import java.io.*;
import java.util.*;

public class WaterLevel{
	static boolean debug=false;

    static long k,l,r,t,y;
    static int x;

	public static void solve() throws IOException{
		//* parse
        k = io.nextLong();
        l = io.nextLong();
        r = io.nextLong();
        t = io.nextLong();
        x = io.nextInt();
        y = io.nextLong();
        r-=l;k-=l;l-=l;
        if (debug){
            io.println("l:"+l+" k: "+k+", r:"+r);
            io.println("x:"+x+", y:"+y);
        }
        //* case1
        if (y < x) {
            //simulate day 1
            t--;
            if (k+y<=r) k+=y;
            k-=x;
            if (k<0) {
                io.println("NO");
                return;
            }
            //how many more days until lose?
            long dk = x-y;
            if ((k+1+dk-1)/dk <= t) {
                io.println("NO");
            } else {
                io.println("YES");
            }
        }
        //* case2
        else {
			//modular compaction
            boolean[] vis = new boolean[x];
            t-=k/x;
            k%=x;
            while (!vis[(int)k] && t>0){
                vis[(int)k]=true;
                k+=y;
                if (k > r) {
                    io.println("NO");
                    return;
                }
                t-=k/x;
                k%=x;
            }
            io.println("YES");
        }
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

		void close(){
			out.close();
		}
	}

	;
}
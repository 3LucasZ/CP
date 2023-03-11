package Debug;

import java.io.*;
import java.util.*;

public class Sol{
	static boolean debug=true;

	public static void solve() throws IOException{
		//* parse
		int A = io.nextInt();
		int B = io.nextInt();
		int ans = 0;
		for (int i=A;i<=B;i++){
			int[] freq = new int[10];
			int j = i;
			int len = 0;
			while (j!=0){
				freq[j%10]++;
				j/=10;
				len++;
			}
			for (int d=0;d<10;d++){
				if (freq[d]>=(len+1)/2) {
					ans++;
					break;
				}
			}
		}
		io.println(ans);
	}

















	public static void main(String[] args) throws IOException{
		io=new IO(debug);
		solve();
		io.close();
	}

	static IO io;

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
					String str=""+arr[r][c];
					while(str.length()<4) str+=" ";
					print(str);
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

		void printBin(int bin,int len){
			String ret="";
			for(int i=0;i<len;i++){
				ret+=bin%2;
				bin/=2;
			}
			println(ret);
		}

		void reverse(int[] arr){
			for(int i=0;i<arr.length/2;i++){
				int tmp=arr[i];
				arr[i]=arr[arr.length-1-i];
				arr[arr.length-1-i]=tmp;
			}
		}

		void close(){
			out.close();
		}
	}

	;
}
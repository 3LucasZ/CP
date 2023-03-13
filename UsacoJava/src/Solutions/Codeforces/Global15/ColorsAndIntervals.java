package Solutions.Codeforces.Global15;

import java.io.*;
import java.util.*;

public class ColorsAndIntervals{
	static boolean debug=false;

    static int N;
    static int K;
    static int[] timer;
    static int[][] x;
	public static void solve() throws IOException{
		//* parse
        N = io.nextInt();
        K = io.nextInt();
        timer = new int[N+1];
        x = new int[N+1][K+1];
        for (int i=1;i<=N*K;i++){
            int color = io.nextInt();
            timer[color]++;
            x[color][timer[color]]=i;
        }
        if (debug){
            io.print2d(x);
        }
        //* bulk brute forces
        boolean[] vis = new boolean[100+1];
        String[] ans = new String[N+1];
        for (int j=2;j<=K;j++){
            ArrayList<Integer> endpoints = new ArrayList<>();
            for (int i=1;i<=N;i++){
                if (!vis[i]) endpoints.add(i);
            }
            int finalJ=j;
            endpoints.sort(Comparator.comparingInt(a->x[a][finalJ]));
            for (int i=0;i<(N+K-2)/(K-1);i++){
                if (i>=endpoints.size()) break;
                vis[endpoints.get(i)]=true;
                ans[endpoints.get(i)]=(x[endpoints.get(i)][j-1]+" "+x[endpoints.get(i)][j]);
            }
        }

        //* ret
        for (int i=1;i<=N;i++) io.println(ans[i]);
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
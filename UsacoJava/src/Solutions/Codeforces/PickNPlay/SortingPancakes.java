package Solutions.Codeforces.PickNPlay;

import java.io.*;
import java.util.*;

public class SortingPancakes{
	static boolean debug=false;

    static int N; //dishes
    static int M; //pancakes
    static int[] A;
    static int[] cnt; //number of pancakes in first i dishes
    static int[] p; //sum of first i pancake positions
	public static void solve() throws IOException{
		//* parse
        N = io.nextInt();
        M = io.nextInt();
        A = new int[N+1];
        cnt = new int[N+1];
        p = new int[M+1];
        for (int i=1;i<=N;i++){
            A[i]=io.nextInt();
        }
        //* precomp
        cnt = new int[N+1];
        for (int i=1;i<=N;i++){
            cnt[i]=cnt[i-1]+A[i];
        }
        int cur = 1;
        for (int i=1;i<=N;i++){
            for (int j=0;j<A[i];j++){
                p[cur]=p[cur-1]+i;
                cur++;
            }
        }
        if (debug){
            io.println("A:"+Arrays.toString(A));
            io.println("cnt:"+Arrays.toString(cnt));
            io.println("p:"+Arrays.toString(p));
        }
        //* dp
        int[][][] dp = new int[N+1][M+1][M+1];
        for (int i=0;i<=N;i++) for (int j=0;j<=M;j++) for (int k=0;k<=M;k++) dp[i][j][k]=Integer.MAX_VALUE/5;
        //base
        for (int last=0;last<=M;last++){
			if (cnt[1]>=last) dp[1][last][last]=cnt[1]-last;
			else dp[1][last][last]=p[last]-last;
        }
		for (int last=M-1;last>=0;last--){
            for (int sum=last;sum<=M;sum++){
                dp[1][sum][last]=Math.min(dp[1][sum][last],dp[1][sum][last+1]);
            }
        }
        if (debug){
            io.println("base:");
            io.print2d(dp[1]);
        }
        // transitions
        for (int i=2;i<=N;++i){
	        for (int last=0;last<=M;++last){
                for (int sum=last;sum<=M;++sum){
                    int cost;
                    if (cnt[i]>=sum){
                        cost=cnt[i]-sum;
                    } else {
                        int lend = Math.min(last,sum-cnt[i]);
                        cost=p[sum]-p[sum-lend]-i*lend;
                    }
                    dp[i][sum][last]=dp[i-1][sum-last][last]+cost;
                }
            }
            for (int last=M-1;last>=0;last--){
                for (int sum=i*last;sum<=M;sum++){
                    dp[i][sum][last]=Math.min(dp[i][sum][last],dp[i][sum][last+1]);
                }
            }
        }
        // ret
        io.println(dp[N][M][0]);
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
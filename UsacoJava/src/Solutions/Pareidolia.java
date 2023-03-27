package Solutions;

import java.io.*;
import java.util.*;

public class Pareidolia{
	static boolean debug=false;

    static int N;
    static char[] tar = "bessie".toCharArray();
    static char[] str;
    static int[] cost;
    static int INF = Integer.MAX_VALUE/10;
	public static void solve() throws IOException{
		//* parse
        str = (" "+io.next()).toCharArray();
        N = str.length-1;
        cost = new int[N+1];
        for (int i=1;i<=N;i++) cost[i]=io.nextInt();
        //* dp
        Pair<Integer,Integer>[][] dp= new Pair[N+1][6];
        //base
        for (int i=0;i<=N;i++) for (int j=0;j<6;j++) dp[i][j]=new Pair<>(-INF,INF);
        dp[0][5] = new Pair<>(0,0);
        //trans
        for (int i=0;i<N;i++){
            for (int j=0;j<6;j++){
                //char completes
                int nextJ = (j+1)%6;
                if (str[i+1]==tar[nextJ]) dp[i+1][nextJ]=best(dp[i+1][nextJ],new Pair<>(dp[i][j].first+(nextJ==5?1:0),dp[i][j].second));
                //delete char
                dp[i+1][j]=best(dp[i+1][j],new Pair<>(dp[i][j].first,dp[i][j].second+cost[i+1]));
                //restart
                dp[i+1][5]=best(dp[i+1][5],dp[i][j]);
                //b restart
                if (str[i+1]==tar[0]){
                    dp[i+1][0]=best(dp[i+1][0],dp[i][j]);
                }
            }
        }
        //* ret
        Pair<Integer,Integer> ans = new Pair<>(-INF,INF);
        for (int i=0;i<=N;i++){
            for (int j=0;j<6;j++){
                if (debug){
                    if (dp[i][j].first>=0 && dp[i][j].second<=100000) io.println("i:"+i+", j:"+j+" = "+dp[i][j]);
                }
                ans=best(ans,dp[i][j]);
            }
        }
        io.println(ans.first);
        io.println(ans.second);
	}
    static Pair<Integer,Integer> best(Pair<Integer,Integer> p1, Pair<Integer,Integer> p2){
        if (p1.first>p2.first) return p1;
        else if (p1.first<p2.first) return p2;
        else {
            if (p1.second>p2.second) return p2;
            else return p1;
        }
    }


	public static void main(String[] args) throws IOException{
		io=new IO(debug);
		solve();
		io.close();
	}

	static IO io;

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
/*
bessie
1 1 1 1 1 1
 */
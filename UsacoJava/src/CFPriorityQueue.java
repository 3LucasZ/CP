import java.io.*;
import java.util.*;

public class CFPriorityQueue{
	static boolean debug=true;

    static int N;
    static boolean[] minus;
    static long[] A;
    static long MOD = 998244353;
	public static void solve() throws IOException{
		//* parse
        N = io.nextInt();
        A = new long[N+1];
        minus = new boolean[N+1];
        for (int i=1;i<=N;i++) {
            char sgn = io.next().charAt(0);
            if (sgn=='+'){
                A[i]=io.nextInt();
            }
            else {
                minus[i]=true;
            }
        }
        //* brute force
        long ret = 0;
        for (int i=1;i<=N;i++){
            if (minus[i]) continue;
            ret=(ret+A[i]*solve(i))%MOD;
        }
        //* ret
        io.println(ret);
	}
    static long solve(int fix){
        //dp solve
        long[][] dp = new long[N+2][N+2];
        dp[0][0]=1;
        for (int i=0;i<N;i++){
            for (int x=0;x<=N;x++){
				//relax
				dp[i][x]%=MOD;
                dp[i+1][x]+=dp[i][x]; // skip op
                if (i+1==fix); // at fix
                
                else if (minus[i+1]){ // - op
                    if (x-1>=0 || i+1<fix){
                        dp[i+1][Math.max(0,x-1)]+=dp[i][x];
                    }
                }
                else if (A[i+1]<A[fix] || (A[i+1]==A[fix] && i+1>fix)){ // + less OR =
                    dp[i+1][x+1]+=dp[i][x];
                } else { // + greater
                    dp[i+1][x]+=dp[i][x];
                }
            }
        }
        long ret = 0;
        for (int i=0;i<=N;i++) ret+=dp[N][i];
        return ret%MOD;
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
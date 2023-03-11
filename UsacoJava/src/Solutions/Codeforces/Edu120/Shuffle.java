package Solutions.Codeforces.Edu120;

import java.io.*;
import java.util.*;

public class Shuffle{
	static boolean debug=false;

    static int N;
    static int K;
    static int[] A;

    static long MOD = 998244353;
	public static void solve() throws IOException{
		//* parse
        N = io.nextInt();
        K = io.nextInt();
        A = new int[N+1];
        String str = io.next();
        for (int i=1;i<=N;i++){
            A[i]=str.charAt(i-1)-'0';
        }
		if (debug){
			io.println("A:"+Arrays.toString(A));
		}
		//* precomp
        int cnt[][] = new int[2][N+1];
        for (int i=1;i<=N;i++){
			cnt[0][i]=cnt[0][i-1];
			cnt[1][i]=cnt[1][i-1];
			cnt[A[i]][i]++;
        }
		if (debug){
			io.println("cnt0:"+Arrays.toString(cnt[0]));
			io.println("cnt1:"+Arrays.toString(cnt[1]));
		}
		NT nt = new NT(N,MOD);
		if (cnt[1][N]<K) {
			io.println(1);
			return;
		}
		//* bash
		long ret = 1;
		for (int l=1;l<=N;l++){
			for (int r=l+1;r<=N;r++){
				if (cnt[1][r]-cnt[1][l-1]>K) continue;
				int cnt0 = cnt[0][r]-cnt[0][l-1];
				int cnt1 = cnt[1][r]-cnt[1][l-1];

				if (A[l]==0) cnt1--;
				else cnt0--;

				if (A[r]==0) cnt1--;
				else cnt0--;
				if (cnt0<0 || cnt1<0) continue;
				long ans = nt.choose(cnt0+cnt1,cnt0);

				if (debug){
					io.println("l:"+l+", r:"+r+", ans:"+ans);
				}
				ret=(ret+ans)%MOD;
			}
		}
		//* ret
		io.println(ret);
	}




    private static class NT {
        //* pow, inv
        long MOD;
        public long inv(long x) {
            return pow(x,MOD-2);
        }
        public long pow(long x, long p) {
            if (x==0) return 0;
            if (p == 0) return 1;
            if (p % 2 == 1) return (x * pow(x, p - 1)) % MOD;
            else return pow((x * x) % MOD, p / 2);
        }
        public NT(long MOD) {
            this.MOD=MOD;
        }
        //* choose, factorials, factorial inverses
        long[] f;
        long[] i;
        int MAXF;
        public NT(int MAXF, long MOD) {
            //gen factorials (1...N)!
            this.MAXF=MAXF;
            this.MOD=MOD;
            f = new long[MAXF + 1];
            f[0] = 1;
            for (int i = 1; i <= MAXF; i++) f[i] = (f[i - 1] * i) % MOD;
            //gen inverses (1...N)!^-1
            i = new long[MAXF + 1];
            i[MAXF]=inv(f[MAXF]);
            for (int A = MAXF; A > 0; A--) {
                i[A-1]=i[A]*A%MOD;
            }
        }
        public long choose(int n, int k) {
            if (k == n || k == 0) return 1;
            return ((f[n] * i[k] % MOD) * i[n - k]) % MOD;
        }
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
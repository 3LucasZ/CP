package Solutions.Codeforces.PickNPlay;

import java.io.*;
import java.util.*;

public class FancyStack {
    static boolean debug = false;

    static int N;
    static int[] freq;
    static int[] tot;
    static long MOD = 998244353;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        freq= new int[N+5];
        tot= new int[N+5];
        for (int i=N;i>=1;i--){
            freq[N+1-io.nextInt()]++;
        }
        for (int i=1;i<=N;i++){
            tot[i]=tot[i-1]+freq[i];
        }
        if (debug){
            io.println("A:"+Arrays.toString(freq));
            io.println("Asum:"+Arrays.toString(tot));
        }
        NT nt = new NT(100*N,MOD);
        //* dp
        long[][] dp = new long[N+2][N+2];
        dp[0][0]=1;
        //push i+1 on top of i
        for (int i=0;i<=N;i++){
            for (int j=0;j<=N;j++){
                if (debug) if (dp[i][j]!=0) io.println("i:"+i+" j:"+j+" = "+dp[i][j]);
                if (freq[i+1]==0) {
                    dp[i+1][j]+=dp[i][j];
                    continue;
                }
                int free = Math.max(j-1,0)-(tot[i]-j)+e(j==N/2);
                //1 big, rest small
                dp[i+1][j+1]=(dp[i+1][j+1]+dp[i][j]*nt.choose(free,freq[i+1]-1))%MOD;
                //all small
                dp[i+1][j]=(dp[i+1][j]+dp[i][j]*nt.choose(free,freq[i+1]))%MOD;
            }
        }
        //* ret
        io.println(dp[N][N/2]);
    }
    static int e(boolean b){
        return b?1:0;
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
            if (k>n) return 0;
            if (k == n || k == 0) return 1;
            return ((f[n] * i[k] % MOD) * i[n - k]) % MOD;
        }
    }




















    static IO io;
    public static void main(String[] args) throws IOException {
        io = new IO(debug);
        int T = io.nextInt();
        for (int i=1;i<=T;i++) solve(i);
        io.close();
    }
        public static long lcm(long a, long b){
        return a*b/gcd(a,b);
    }
    public static long gcd(long a, long b){
        if (b==0) return a;
        return gcd(b,a%b);
    }
    private static class Triple <T1, T2, T3> {
        T1 first;
        T2 second;
        T3 third;
        public Triple(T1 first,T2 second, T3 third){
            this.first=first;
            this.second=second;
            this.third=third;
        }
        public String toString(){
            return "["+first+", "+second+", "+third+"]";
        }
    } 
   private static class Pair <T1, T2> {
        T1 first;
        T2 second;
        public Pair(T1 first,T2 second){
            this.first=first;
            this.second=second;
        }
        @Override
        public boolean equals(Object o){
            if (!(o instanceof Pair)) return false;
            Pair<T1,T2> other = (Pair<T1,T2>) o;
            return first.equals(other.first) && second.equals(other.second);
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
        for (int i=0;i<arr.length/2;i++){
            int tmp = arr[i];
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
    private static class IO {
        BufferedReader br;
        StringTokenizer st;
        PrintWriter out;
        boolean debug;
        public IO(boolean dbg)  {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
            debug=dbg;
        }
        public IO(String fileName, boolean dbg) throws IOException {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new FileWriter(fileName+".out"));
            debug=dbg;
        }
        String next()
        {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() {return Double.parseDouble(next());}
        String nextLine() {
            String str = "";
            try {
                if(st.hasMoreTokens()){
                    str = st.nextToken("\n");
                }
                else{
                    str = br.readLine();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
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
        void close(){
            out.close();
        }
    }
}
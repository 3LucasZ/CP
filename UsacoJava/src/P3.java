import java.io.*;
import java.util.*;

public class P3{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter out = new PrintWriter(System.out);
	boolean debug = true;

	static final long MOD =(long)(1e9+7);
	static final int MAXF = (int)(1e6);
	public static void main(String[] args) throws IOException{
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		if (N==1){
			out.println(1);
		} else{
			//choose
			NT nt=new NT(2*MAXF,MOD);
			int common = Math.min(A,B)/2;
			int extra = Math.max(A,B)/2-common;
			out.println(nt.choose(common+extra-1,extra));
		}
		out.close();
	}

	private static class NT {
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
		long[] f;
		long[] i;
		int MAXF;
		public NT(int MAXF, long MOD) {
			this.MAXF=MAXF;
			this.MOD=MOD;
			f = new long[MAXF + 1];
			f[0] = 1;
			for (int i = 1; i <= MAXF; i++) f[i] = (f[i - 1] * i) % MOD;
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
}

package Other.Codeforces.Round797;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ArrayDecrements {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            out.println(solve()?"YES":"NO");
        }
        out.close();
    }
    public static boolean solve() throws IOException {
        int ops = 0;
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i=0;i<N;i++) A[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] B = new int[N];
        for (int i=0;i<N;i++) {
            B[i] = Integer.parseInt(st.nextToken());
            ops = Math.max(ops,A[i]-B[i]);
        }

        for (int i=0;i<N;i++) A[i]=Math.max(0,A[i]-ops);
        for (int i=0;i<N;i++) if(A[i]!=B[i]) return false;
        return true;
    }
}

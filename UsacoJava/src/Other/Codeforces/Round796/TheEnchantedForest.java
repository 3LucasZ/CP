package Other.Codeforces.Round796;

import java.io.*;
import java.util.StringTokenizer;

public class TheEnchantedForest {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        setup("");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            out.println(solve());
        }
        out.close();
    }
    public static long solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long[] A = new long[N+1];
        long[] presum = new long[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            A[i] = Long.parseLong(st.nextToken());
            presum[i]=presum[i-1]+A[i];
        }

        if (K <= N){
            long max = 0;
            for (int i=1;i+K-1<=N;i++){
                max = Math.max(max, presum[i+K-1]-presum[i-1]);
            }
            for (int i=1;i<=K-1;i++) max+=i;
            return max;
        }
        else {
            long sum = presum[N];
            for (int i=1;i<=N;i++) sum += i+K-N-1;
            return sum;
        }
    }




















    static BufferedReader br;
    static PrintWriter out;
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}

package Other.EC.SilverPS;

import java.io.*;
import java.util.StringTokenizer;

public class NumberOfZerosInRange {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int[] arr;
    static int[] preSum;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("zeros.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("zeros.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //logic
        preSum = new int[N+1];
        for (int i=1;i<=N;i++) {
            preSum[i]=preSum[i-1];
            if (arr[i]==0)preSum[i]++;
        }
        int K = Integer.parseInt(br.readLine());
        for (int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            out.print(preSum[v]-preSum[u-1] + " ");
        }
        out.close();
    }
}

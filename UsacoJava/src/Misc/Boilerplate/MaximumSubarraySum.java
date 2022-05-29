package Misc.Boilerplate;

import java.io.*;
import java.util.StringTokenizer;

public class MaximumSubarraySum {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        setup("");
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        long sum = 0;
        long ans = Long.MIN_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            A[i] = Integer.parseInt(st.nextToken());
            sum = Math.max(A[i],sum+A[i]);
            ans = Math.max(ans,sum);
        }
        out.println(ans);
        out.close();
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

package Codeforces.Edu130;

import java.io.*;
import java.util.*;

public class Promo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] price = new int[N+1];
        for (int i=1;i<=N;i++) price[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(price);
        long[] presum = new long[N+1];
        for (int i=1;i<=N;i++) presum[i]=presum[i-1]+price[i];
        for (int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            out.println(presum[N-x+y]-presum[N-x]);
        }
        out.close();
    }
}

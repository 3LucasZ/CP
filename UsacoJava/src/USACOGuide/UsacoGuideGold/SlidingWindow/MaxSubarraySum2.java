package USACOGuide.UsacoGuideGold.SlidingWindow;

import java.io.*;
import java.util.*;

public class MaxSubarraySum2 {
    static boolean submission = false;
    static boolean debug = true;

    static int[] num;
    static long[] presum;
    static int N;
    static int A;
    static int B;

    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        num = new int[N+1];
        presum = new long[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++)num[i]=Integer.parseInt(st.nextToken());
        for (int i=1;i<=N;i++)presum[i]=presum[i-1]+num[i];

        //maxqueue sliding window complete search
        long ans = Long.MIN_VALUE;
        PriorityQueue<Long> window = new PriorityQueue<>(Comparator.comparingLong(a->-a));
        for (int i=A;i<=B;i++){
            window.add(presum[i]);
        }
        for (int i=1;i<=N;i++){
            if (window.size()>0) ans=Math.max(ans,window.peek()-presum[i-1]);
            if (i+A-1 <= N) window.remove(presum[i+A-1]);
            if (i+B <= N) window.add(presum[i+B]);
        }

        //ret
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

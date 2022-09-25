package USACOGuide.UsacoGuideGold.DP;

import java.io.*;
import java.util.*;
/*
USACO 2015 January Contest, Gold
Problem 2. Moovie Mooving
USACO Gold Guide - Subset DP - Easy
Conventional subset dp!
 */
public class MoovieMooving {
    static boolean submission = true;
    static boolean debug = true;

    static int L;
    static int N;

    static int duration[];
    static TreeSet<Integer>[] showtimes;
    public static void main(String[] args) throws IOException {
        setup("movie");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        duration = new int[N];
        showtimes = new TreeSet[N]; for (int i=0;i<N;i++) showtimes[i] = new TreeSet<>();
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            duration[i] = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j=0;j<cnt;j++){
                showtimes[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        //subset dp
        int ans = Integer.MAX_VALUE;
        int subsets = 1 << N;
        int dp[] = new int[subsets];
        for (int s=1;s<subsets;s++){
            int watched = 0;
            for (int next=0;next<N;next++){
                int bit = 1<<next;
                if ((s&bit)!=0){
                    watched ++;
                    Integer start = showtimes[next].floor(dp[s^bit]);
                    if (start!=null && start+duration[next] >= dp[s^bit]){
                        dp[s]=Math.max(start+duration[next], dp[s]);
                    }
                }
            }
            if (dp[s] >= L) ans = Math.min(ans,watched);
        }

        //ret
        if (ans == Integer.MAX_VALUE)  out.println(-1);
        else out.println(ans);
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

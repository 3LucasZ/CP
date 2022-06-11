package Gold.UsacoGuideGold.DP;

import java.io.*;
import java.util.*;
/*
USACO 2014 December Contest, Gold
Problem 1. Guard Mark
USACO Gold Guide - Bitmask DP
Thoughts:
conventional subset dp
 */
public class GuardMark {
    static boolean submission = true;
    static boolean debug = true;

    //param
    static int N;
    static int H;

    static int[] height;
    static int[] weight;
    static int[] strength;
    public static void main(String[] args) throws IOException {
        setup("guard");
        //parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        height = new int[N];
        weight = new int[N];
        strength = new int[N];
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            height[i] = Integer.parseInt(st.nextToken());
            weight[i] = Integer.parseInt(st.nextToken());
            strength[i] = Integer.parseInt(st.nextToken());
        }

        //subset dp
        //setup
        //dp[set]=min strength left
        int sets = 1<<N;
        int[] dp = new int[sets];
        dp[0] = Integer.MAX_VALUE;

        //dp
        int ans = -1;
        for (int s=1;s<sets;s++){
            int best = -1;
            int tot = 0;
            for (int next=0;next<N;next++){
                int bit = 1<<next;
                if ((s&bit)!=0){
                    tot += height[next];
                    if (dp[s^bit] >= weight[next]){
                        best=Math.max(best,Math.min(dp[s^bit]-weight[next],strength[next]));
                    }
                }
            }
            dp[s]=best;
            if (tot >= H){
                ans = Math.max(ans, dp[s]);
            }
        }

        //ret
        if (ans == -1) out.println("Mark is too tall");
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

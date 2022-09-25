package USACOGuide.UsacoGuideSilver.SilverGreedy;

import java.io.*;
import java.util.*;
/*
USACO 2020 January Contest, Silver
Problem 1. Berry Picking
USACO Silver Guide - Greedy + Sorting - Normal
FINALLY SOLVED AFTER LIKE 2 MONTHS
wacky greedy problem tht finall makes sense :(!
groups of berries, take baskets of them, maximize the least half of the baskets
greedy: try every "max size" basket to use to collect the berries
reasoning example: why have 3 4 5 7 8 9? 7 8 9 is harmful and we can greedily only consider 3 4 5 7 7 7
 */
public class BerryPicking {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static int K;
    static int berries[];
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("berries.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("berries.out")));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        berries = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            berries[i] = Integer.parseInt(st.nextToken());
        }
        int best = 0;
        for (int i=1;i<=1000;i++){
            best = Math.max(best, tryMaxSize(i));
        }
        out.println(best);
        out.close();
    }
    public static int tryMaxSize(int size){
        ArrayList<Integer> baskets = new ArrayList<>();
        for (int i=0;i<N;i++){
            int tmp = berries[i];
            while (tmp > 0){
                int take = Math.min(tmp,size);
                tmp-=take;
                baskets.add(take);
            }
        }
        if (baskets.size()<K) return 0;
        Collections.sort(baskets);
        int tot=0;
        for (int i= baskets.size()-K;i<= baskets.size()-1-K/2;i++) tot+=baskets.get(i);
        if (!submission) {
            System.out.println(baskets);
            System.out.println(tot);
        }
        return tot;
    }
}

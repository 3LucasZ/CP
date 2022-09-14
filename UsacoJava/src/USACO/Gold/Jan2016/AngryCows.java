package USACO.Gold.Jan2016;

import java.io.*;
import java.util.*;
/*
USACO 2016 January Contest, Gold
Problem 1. Angry Cows
Focus: DP Advanced
Commentary:
2 pointers/greedy/sliding window/DP to find the power a haybale must be hit to explode all the ones to the left/right
reversing array to do right without repeating left
2 pointers/greedy to move across 2 endpoint haybales to try to explode with a middle cow
 */
public class AngryCows {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean submission = true;
    //param
    static int N;
    static Integer loc[];
    static Integer locReverse[];
    //dp
    static int l;
    static int r;
    static Integer[] lDP;
    static Integer[] rDP;
    public static void main(String[] args) throws IOException {
        if (submission){
            br = new BufferedReader(new FileReader("angry.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
        }
        //parse
        N = Integer.parseInt(br.readLine());
        loc = new Integer[N];
        locReverse = new Integer[N];
        for (int i=0;i<N;i++) {
            int num = 2*Integer.parseInt(br.readLine());
            loc[i] = num;
            locReverse[i] = num;
        }
        //sort
        Arrays.sort(loc);
        Arrays.sort(locReverse, Comparator.reverseOrder());

        //dp left & right detonate
        lDP = new Integer[N];
        fillDP(lDP, loc);
        rDP = new Integer[N];
        fillDP(rDP, locReverse);
        Arrays.sort(rDP, Comparator.reverseOrder());
//        out.println(Arrays.toString(loc));
//        out.println(Arrays.toString(lDP));
//        out.println(Arrays.toString(locReverse));
//        out.println(Arrays.toString(rDP));
        //best crossover point
        int min = Integer.MAX_VALUE;
        l=0;r=N-1;
        while (l < r) {
            int len = loc[r]-loc[l];
            min = Math.min(min, Math.max(len/2, Math.max(lDP[l], rDP[r])+2));
            if (lDP[l+1] < rDP[r-1]){
                l++;
            }
            else {
                r--;
            }
        }

        out.println(min/2 + (min%2==0? ".0" : ".5"));
        out.close();
    }
    static void fillDP(Integer[] dp, Integer[] locs){
        l=0;
        dp[0]=0;
        for (r=1;r<N;r++) {
            if (Math.abs(locs[r]-locs[l]) > dp[l]) {
                dp[r] = Math.max(Math.abs(locs[r]-locs[r-1]), dp[l]+2);
                l=r;
            }
            else {
                dp[r]=dp[l]+2;
            }
        }
    }
}
/*
9
0
1
5
16
17
18
19
21
23
 */
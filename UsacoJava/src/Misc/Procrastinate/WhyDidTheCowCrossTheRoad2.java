package Misc.Procrastinate;

import java.io.*;
import java.util.*;
/*
USACO 2017 February Contest, Silver
Problem 2. Why Did the Cow Cross the Road II
USACO Guide Silver - Prefix Sums - Easy
*/

public class WhyDidTheCowCrossTheRoad2 {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static int k;
    static int b;
    static int[] presum;
    static boolean[] broken;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("maxcross.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        broken = new boolean[n+1];
        presum = new int[n+1];
        for (int i=0;i<b;i++) {
            broken[Integer.parseInt(f.readLine())] = true;
        }
        presum[0] = 0;
        for (int i=1;i<=n;i++) {
            presum[i] = presum[i-1];
            if (broken[i])presum[i]++;
        }
        //logic
        int min = Integer.MAX_VALUE;
        for (int i=k;i<=n;i++){
            int linear_broken = presum[i]-presum[i-k];
            min = Math.min(min, linear_broken);
        }
        //turn in answer
        out.println(min);
        out.close();
        f.close();
    }
}

package Solutions.USACOGuide.UsacoGuideSilver.PrefixSums;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Maximum Subarray Sum
USACO Silver Guide - More on Prefix Sums - Easy
 */
public class MaximumSubarraySum {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int n;
    static int[] arr;
    static long minPrefSum = 0;
    static long runningPrefSum = 0;
    static long maxSubSum;
    public static void main(String[] args) throws IOException {
        //parse input
        n = Integer.parseInt(f.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(f.readLine());
        for (int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //logic
        maxSubSum = arr[0];
        for (int i=0;i<n;i++) {
            runningPrefSum += arr[i];
            maxSubSum = Math.max(maxSubSum, runningPrefSum - minPrefSum);
            minPrefSum = Math.min(minPrefSum, runningPrefSum);
        }
        //turn in answer
        out.println(maxSubSum);
        out.close();
        f.close();
    }
}

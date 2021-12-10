package UsacoGuideSilver.BinarySearch;/*
C. Maximum Median
CodeForces
USACO Guide - Silver - Binary Search - Easy
 */

import java.io.*;
import java.util.*;

public class MaximumMedian {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int n;
    static int opsMax;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        opsMax = Integer.parseInt(st.nextToken());
        arr = new int[n];
        StringTokenizer numStr = new StringTokenizer(f.readLine());
        for (int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(numStr.nextToken());
        }
        Arrays.sort(arr);
        //logic
        out.println(lastTrue());
        out.close();
        f.close();
    }
    public static int lastTrue(){
        int lo = 0;
        int hi = (int) 2e9;
        while (lo < hi) {
            int mid = lo + (hi - lo + 1) / 2;
            if (medReachable(mid)) {
                lo = mid;
            }
            else {
                hi = mid - 1;
            }
        }
        return lo;
    }
    public static boolean medReachable(int med) {
        long opsNeeded = 0;
        for (int i=n/2;i<n;i++) {
            opsNeeded += Math.max(0, med - arr[i]);
        }
        return opsNeeded <= opsMax;
    }
}

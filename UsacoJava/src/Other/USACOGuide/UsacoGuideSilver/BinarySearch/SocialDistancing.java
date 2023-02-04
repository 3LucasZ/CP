package Other.USACOGuide.UsacoGuideSilver.BinarySearch;/*
USACO 2020 US Open Contest, Silver
Problem 1. Social Distancing
USACO Guide Silver - Binary Search - Normal
 */

import java.io.*;
import java.util.*;

public class SocialDistancing {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int cows;
    static int intervals;
    static Interval intervalArr[];

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("socdist.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("socdist.out")));
        } else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        cows = Integer.parseInt(st.nextToken());
        intervals = Integer.parseInt(st.nextToken());
        intervalArr = new Interval[intervals];
        for (int i = 0; i < intervals; i++) {
            st = new StringTokenizer(f.readLine());
            Interval toAdd = new Interval(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
            intervalArr[i] = toAdd;
        }
        Arrays.sort(intervalArr);
        //logic
        out.println(binarySearch());
        out.close();
        f.close();
    }
    public static long binarySearch() {
        long lo = 1;
        long hi = intervalArr[intervals-1].hi;
        while (lo < hi) {
            long mid = lo + (hi - lo + 1) / 2;
            if (tryDist(mid)) {
                lo = mid;
            }
            else {
                hi = mid - 1;
            }
        }
        return lo;
    }
    public static boolean tryDist(long d) {
        int intervalIndex = 0;
        //place first cow at first interval lo
        long runningIndex = intervalArr[intervalIndex].lo;
        int onCow = 1;
        while (true) {
            if (intervalIndex >= intervals || runningIndex > intervalArr[intervals-1].hi) {
                break;
            }
            while (runningIndex + d > intervalArr[intervalIndex].hi) {
                intervalIndex ++;
                if (intervalIndex >= intervals) return false;
            }
            if (intervalArr[intervalIndex].contains(runningIndex + d)) {
                runningIndex += d;
            }
            else {
                runningIndex = intervalArr[intervalIndex].lo;
            }
            onCow ++;
            if (onCow == cows) break;
        }
        return onCow >= cows;
    }
    private static class Interval implements Comparable<Interval> {
        long lo;
        long hi;
        public Interval(long l, long h) {
            lo = l;
            hi = h;
        }
        public boolean contains(long i) {
            if (i < lo || i > hi) return false;
            return true;
        }
        public String toString() {
            return "[" + lo + ", " + hi + "]";
        }
        public int compareTo(Interval other) {
            return (lo - other.lo > 0) ? 1 : -1;
        }
    }
}
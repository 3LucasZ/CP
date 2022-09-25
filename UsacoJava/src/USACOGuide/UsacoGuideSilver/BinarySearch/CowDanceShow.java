package USACOGuide.UsacoGuideSilver.BinarySearch;/*
USACO 2017 January Contest, Silver
Problem 1. Cow Dance Show
USACO Guide - Silver - Binary Search - Easy
 */
import java.io.*;
import java.util.*;
public class CowDanceShow {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int N;
    static int T;
    static int[] time;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("cowdance.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        time = new int[N];
        for (int i=0;i<N;i++) {
            time[i] = Integer.parseInt(f.readLine());
        }
        //logic
        int lo = 1, hi = N;
        while (lo < hi) {
            int mid = (lo + hi)/2;
            if (tryK(mid)) hi = mid;
            else lo = mid + 1;
        }
        out.println(lo);
        out.close();
        f.close();
    }
    public static boolean tryK(int K) {
        ArrayList<Integer> stage = new ArrayList<>();
        int p = 0;
        int min_i = 0;
        int run = 0;
        //set the stage
        for (p=0;p<K;p++) {
            stage.add(time[p]);
        }
        while (p < N) {
            for (int i=0;i<K;i++) {
                if (stage.get(i) < stage.get(min_i)) min_i = i;
            }
            int min = stage.get(min_i);
            for (int i=0;i<K;i++) {
                stage.set(i, stage.get(i)-min);
            }
            run += min;
            stage.remove(min_i);
            stage.add(time[p]);
            p++;
        }
        int max = -1;
        for (int i=0;i<K;i++) {
            max = Math.max(max, stage.get(i));
        }
        run += max;
        return run <= T;
    }
}

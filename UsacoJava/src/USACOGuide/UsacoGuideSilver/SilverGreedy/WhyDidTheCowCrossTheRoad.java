package USACOGuide.UsacoGuideSilver.SilverGreedy;

import java.io.*;
import java.util.*;
/*
USACO 2017 February Contest, Silver
Problem 1. Why Did the Cow Cross the Road
USACO Guide - Greedy + Sorting - Normal
Concepts: "Sense", flexibility
Issues: TLE on Dataset 9... idk how to fix haha
*/
public class WhyDidTheCowCrossTheRoad {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int C;
    static int N;
    static int chickens[];
    static Cow cows[];
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("helpcross.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        C = Integer.parseInt(st.nextToken());
        chickens = new int[C];
        N = Integer.parseInt(st.nextToken());
        cows = new Cow[N];
        for (int i=0;i<C;i++) {
            chickens[i] = Integer.parseInt(f.readLine());
        }
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(f.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            cows[i] = new Cow(u, v);
        }
        solution1();
        out.close();
        f.close();
    }
    private static void solution1(){
        boolean[] marked = new boolean[N];
        Arrays.sort(chickens);
        int ans = 0;
        for (int chicken_i : chickens) {
            int smallest = -1;
            for (int cow_i=0;cow_i<N;cow_i++) {
                if (!marked[cow_i] && cows[cow_i].contains(chicken_i)) {
                    if (smallest < 0 || cows[cow_i].B < cows[smallest].B) {
                        smallest = cow_i;
                    }
                }
            }
            if (smallest != -1) {
                marked[smallest] = true;
                ans++;
            }
        }
        out.println(ans);
    }
    private static class Cow {
        int A;
        int B;
        public Cow(int a, int b){
            A = a;
            B = b;
        }
        public boolean contains(int T) {
            return !(T > B || T < A);
        }
        public boolean contains(Cow o) {
            return o.A >= A && o.B <= B;
        }
        public String toString(){
            return "[" + A + ", " + B + "]";
        }
    }
}

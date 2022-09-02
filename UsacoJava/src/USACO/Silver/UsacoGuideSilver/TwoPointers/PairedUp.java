package USACO.Silver.UsacoGuideSilver.TwoPointers;

import java.io.*;
import java.util.*;
/*
USACO 2017 US Open Contest, Silver
Problem 1. Paired Up
USACO Guide Silver - 2 Pointers - Easy
 */
public class PairedUp {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int N;
    static Entry milk[];
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("pairup.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(f.readLine());
        milk = new Entry[N];
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int copies = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            milk[i] = new Entry(copies, time);
        }
        //logic
        Arrays.sort(milk, (a, b) -> a.time - b.time);
        //out.println(Arrays.toString(milk));
        int pS = 0;
        int pE = N-1;
        int max = 0;
        while (pS != pE) {
            max = Math.max(max, milk[pS].time + milk[pE].time);
            int run = Math.min(milk[pS].copies, milk[pE].copies);
            milk[pS].copies -= run;
            milk[pE].copies -= run;
            if (milk[pS].copies == 0) pS++;
            if (milk[pE].copies == 0) pE--;
        }
        max = Math.max(max, milk[pS].time + milk[pE].time);
        //turn in answer
        out.println(max);
        out.close();
        f.close();
    }
    private static class Entry {
        public int copies;
        public int time;
        public Entry(int c, int t) {
            copies = c;
            time = t;
        }
        public String toString() {
            return "["+copies + "x: " + time + "]";
        }
    }
}

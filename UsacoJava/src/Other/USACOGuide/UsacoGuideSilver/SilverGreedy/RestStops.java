package Other.USACOGuide.UsacoGuideSilver.SilverGreedy;

import java.io.*;
import java.util.*;

/*
USACO 2018 February Contest, Silver
Problem 1. Rest Stops
USACO Guide Silver - Additional Practice - Normal
Concepts: Greedy with Sorting, ERROR WITH LARGE NUMBERS: USE LONGS FOR ALL INTERMEDIATE CALCULATIONS!
 */
public class RestStops {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int L;
    static int N;
    static int rF;
    static int rB;
    static Stop stops[];
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("reststops.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        rF = Integer.parseInt(st.nextToken());
        rB = Integer.parseInt(st.nextToken());
        stops = new Stop[N];
        for (int i=0;i<N;i++){
            st = new StringTokenizer(f.readLine());
            int p = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            stops[i] = new Stop(p, t);
        }
        //logic
        Arrays.sort(stops, (a,b)->b.tastiness-a.tastiness);
        //out.println(Arrays.toString(stops));
        int p = 0;
        long ans = 0;
        for (int i=0;i<N;i++) {
            if (i!=0 && stops[i].position < p){}
            else {
                long d = stops[i].position-p;
                long tB = d*rB;
                long tF = d*rF;
                ans += (tF-tB)*(long)(stops[i].tastiness);
                p = stops[i].position;
            }
        }
        //turn in answer
        out.println(ans);
        out.close();
        f.close();
    }
    private static class Stop {
        int position;
        int tastiness;
        public Stop(int p, int t){
            position=p;
            tastiness =t;
        }
        public String toString(){
            return "["+position+": "+tastiness+"]";
        }
    }
}

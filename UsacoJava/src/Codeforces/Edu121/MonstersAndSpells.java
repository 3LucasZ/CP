package Codeforces.Edu121;

import java.io.*;
import java.util.*;
/*
Codeforces Educational round 121
C. Monsters And Spells
USACO Silver Training
Read the solution :|
Notes:
Each monster -> [x,y] interval of where you have to start gathering mana and when you have to deploy
combine these intervals from left to right until they are all disjoint
(n)(n+1)/2 all the interval sizes for the answer
 */
public class MonstersAndSpells {
    //io
    static boolean debug = false;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //glob
    static int T;
    public static void main(String[] args) throws IOException {
        //run for T testcases
        T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) {
            solve();
        }
        out.close();
    }
    public static void solve() throws IOException {
        //parse
        int N = Integer.parseInt(br.readLine());
        int[] position = new int[N];
        int[] health = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            position[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            health[i] = Integer.parseInt(st.nextToken());
        }
        //interval union technique
        Interval[] intervals = new Interval[N];
        for (int i=0;i<N;i++) {
            intervals[i] = new Interval(position[i]-health[i]+1, position[i]);
        }
        Arrays.sort(intervals, (a,b)->a.x-b.x);
        if (debug) System.out.println(Arrays.toString(intervals));
        //add one by one and union or disjoint
        ArrayList<Interval> active = new ArrayList<>();
        active.add(intervals[0]);
        for (int i=1;i<intervals.length;i++) {
            Interval last = active.get(active.size()-1);
            if (intervals[i].x<=last.y) last.y=Math.max(last.y, intervals[i].y);
            else active.add(intervals[i]);
        }
        if (debug) System.out.println(active);
        long ans = 0;
        for (int i=0;i< active.size();i++) {
            ans += (long)active.get(i).getLength()*(active.get(i).getLength()+1)/2;
        }
        out.println(ans);
        if (debug) System.out.println();
    }
    private static class Interval {
        int x;
        int y;
        public Interval(int x, int y){
            this.x=x;
            this.y=y;
        }
        public int getLength(){
            return y-x+1;
        }
        public String toString(){
            return "["+x+", "+y+"]";
        }
    }
}

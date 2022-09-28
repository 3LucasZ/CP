package USACO.Season2016_2017.Dec2016.Gold;

import java.io.*;
import java.util.*;
/*
USACO 2016 December Contest, Gold
Problem 1. Moocast
USACO Guide Silver - DFS - Normal
 */
public class MoocastGold {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static Cow[] cows;
    static int visits;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("moocast.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        n = Integer.parseInt(f.readLine());
        cows = new Cow[n];
        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            cows[i] = new Cow(x, y);
        }
        //binary search on each squaredPower
        int lo=0, hi=1250000000;
        while (lo < hi) {
            int mid = (int)((long)(lo+hi)/2);
            if (trySquaredPower(mid)) hi = mid;
            else lo = mid + 1;
        }
        //turn in answer
        out.println(lo);
        out.close();
        f.close();
    }
    public static boolean trySquaredPower(int p) {
        //reset
        for (Cow c : cows) {
            c.canReach = new ArrayList<>();
            c.visited = false;
        }
        visits = 0;
        //fill the canReach of each cow
        for (Cow curr : cows) {
            for (Cow other : cows) {
                if (other != curr && curr.reachable(other, Math.sqrt(p))) curr.canReach.add(other);
            }
        }
        //single dfs of arbitrary cow
        dfs(cows[0]);
        return visits >= n;
    }
    public static void dfs(Cow curr) {
        curr.visited = true;
        visits ++;
        for (Cow child : curr.canReach) {
            if (!child.visited) {
                dfs(child);
            }
        }
    }
    private static class Cow {
        int x;
        int y;
        ArrayList<Cow> canReach = new ArrayList<>();
        boolean visited = false;
        public Cow(int x1, int y1) {
            x = x1;
            y = y1;
        }
        public boolean reachable(Cow o, double power) {
            return distanceTo(o) <= power;
        }
        public double distanceTo(Cow o) {
            return Math.sqrt((o.x-x)*(o.x-x)+(o.y-y)*(o.y-y));
        }
    }
}

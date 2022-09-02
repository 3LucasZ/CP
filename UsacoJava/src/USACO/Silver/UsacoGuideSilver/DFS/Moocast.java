package USACO.Silver.UsacoGuideSilver.DFS;

import java.io.*;
import java.util.*;
/*
USACO 2016 December Contest, Silver
Problem 3. Moocast
USACO Guide Silver - DFS - Easy
 */
public class Moocast {
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
            int p = Integer.parseInt(st.nextToken());
            cows[i] = new Cow(x, y, p);
        }
        //logic
        //fill the canReach of each cow
        for (Cow curr : cows) {
            for (Cow other : cows) {
                if (other != curr && curr.reachable(other)) curr.canReach.add(other);
            }
        }
        int ans = 0;
        //dfs each cow to see how many each can reach
        for (Cow curr : cows) {
            //reset vars
            visits = 0;
            for (int i=0;i<n;i++) {
                cows[i].visited = false;
            }
            dfs(curr);
            ans = Math.max(ans, visits);
        }
        //turn in answer
        out.println(ans);
        out.close();
        f.close();
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
        int power;
        ArrayList<Cow> canReach = new ArrayList<>();
        boolean visited = false;
        public Cow(int x1, int y1, int p) {
            x = x1;
            y = y1;
            power = p;
        }
        public boolean reachable(Cow o) {
            return distanceTo(o) <= power;
        }
        public double distanceTo(Cow o) {
            return Math.sqrt((o.x-x)*(o.x-x)+(o.y-y)*(o.y-y));
        }
    }
}

package UsacoGuideSilver.IntroToTreeAlgor;

import java.util.*;
import java.io.*;
public class MilkVisitsINCORRECT {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int numCows;
    static int numVisits;
    static Farm[] farms;
    //temp
    static boolean happy;
    static char want;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("milkvisits.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));
        } else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        numCows = Integer.parseInt(st.nextToken());
        numVisits = Integer.parseInt(st.nextToken());
        farms = new Farm[numCows+1];
        String str = f.readLine();
        for (int i=1;i<=numCows;i++) {
            farms[i] = new Farm(i, str.charAt(i-1));
        }
        for (int i=0;i<numCows-1;i++) {
            st = new StringTokenizer(f.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            farms[u].add(farms[v]);
            farms[v].add(farms[u]);
        }
        for (int i=0;i<numVisits;i++) {
            st = new StringTokenizer(f.readLine());
            out.print(happyFarmer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)) ? 1 : 0);
        }
        out.close();
        f.close();
    }
    public static boolean happyFarmer(int u, int v, char c) {
        happy = false;
        want = c;
        farms[u].contains(farms[v], new Farm(1, 'A'));
        return happy;
    }
    private static class Farm {
        char type;
        ArrayList<Farm> adj;
        int id;
        public Farm(int i, char c) {
            id = i;
            type = c;
            adj = new ArrayList<>();
        }
        public void add(Farm o) {
            adj.add(o);
        }
        public boolean contains(Farm o, Farm p) {
            boolean good = false;
            if (this == o) {
                if (type == want) happy = true;
                return true;
            }
            for (int i=0;i<adj.size();i++) {
                if (adj.get(i) == p) continue;
                if (adj.get(i).contains(o, this)) good = true;
            }
            if (good) {
                if (type == want) happy = true;
                return true;
            }
            return false;
        }
        public String toString() {
            return "Farm " + id + ", type " + type + ", " + adj.size() + " connections.";
        }
    }
}

package UsacoGuideBronze.CompleteSearch;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//USACO 2016 February Contest, Silver
//        Problem 2. Load Balancing
//USACO Guide Bronze Complete Search Very Hard

public class LoadBalancing {
    //param
    static int n;
    static State[] cows;
    public static void main(String[] args) throws IOException {
        //default
        PrintWriter out;
        BufferedReader f;
        //setup
        boolean submission = true;
        if (submission) {
            f = new BufferedReader(new FileReader("balancing.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        n = Integer.parseInt(f.readLine());
        cows = new State[n];
        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            cows[i] = new State(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(cows, (a, b) -> a.x - b.x);
        int ret = n;
        for (int i=0;i<n;i++) {
            ArrayList<State> below = new ArrayList<State>();
            ArrayList<State> above = new ArrayList<State>();
            for (int j=0;j<n;j++) {
                if (cows[j].y <= cows[i].y) {
                    below.add(cows[j]);
                }
                else {
                    above.add(cows[j]);
                }
            }
            System.out.println(below);
            System.out.println(above);
            int belowIndex = 0;
            int aboveIndex = 0;
            while (belowIndex < below.size() || aboveIndex < above.size()) {
                int xBorder = Integer.MAX_VALUE;
                if (belowIndex < below.size()) {
                    xBorder = Math.min(xBorder, below.get(belowIndex).x);
                }
                if (aboveIndex < above.size()) {
                    xBorder = Math.min(xBorder, above.get(aboveIndex).x);
                }
                while (belowIndex < below.size() && below.get(belowIndex).x <= xBorder) {
                    belowIndex ++;
                }
                while (aboveIndex < above.size() && above.get(aboveIndex).x <= xBorder) {
                    aboveIndex ++;
                }
                ret = Math.min(ret, Math.max(Math.max(aboveIndex, above.size() - aboveIndex), Math.max(belowIndex, below.size() - belowIndex)));
            }
        }

        //turn in answer
        out.println(ret);
        out.close();
        f.close();
    }
}
class State{
    int x;
    int y;
    public State(int x2, int y2) {
        x = x2;
        y = y2;
    }
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
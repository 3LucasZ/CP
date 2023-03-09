package Solutions.USACOGuide.UsacoGuideSilver.SilverGreedy;

import java.io.*;
import java.util.*;
/*
USACO 2015 December Contest, Silver
Problem 2. High Card Wins
USACO Guide Silver - Easy Greedy + Sorting
 */
public class HighCardWins {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static boolean[] forElsie;
    static ArrayList<Integer> elsie = new ArrayList<>();
    static ArrayList<Integer> bessie = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("highcard.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        n = Integer.parseInt(f.readLine());
        forElsie = new boolean[2*n];
        for (int i=0;i<n;i++) {
            int u = Integer.parseInt(f.readLine())-1;
            forElsie[u] = true;
            elsie.add(u);
        }
        for (int i=0;i<2*n;i++) {
            if (!forElsie[i]) bessie.add(i);
        }
        //out.println(elsie);
        //out.println(bessie);
        //logic
        Collections.sort(elsie);
        Collections.sort(bessie);
        int e = 0;
        int b = 0;
        int ans = 0;
        while (e < n && b < n) {
            if (bessie.get(b) > elsie.get(e)) {
                ans++;
                b++;
                e++;
            }
            else {
                b++;
            }
        }
        //turn in answer
        out.println(ans);
        out.close();
        f.close();
    }
}

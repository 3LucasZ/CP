package USACO.Bronze.UsacoGuideBronze.IntroGreedy;

import java.io.*;
import java.util.Arrays;
/*
USACO 2018 January Contest, Bronze
Problem 3. Out of Place
Greedy - hard- bronze
 */

public class OutOfPlace {
    //default
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static int[] lineup;

    public static void main(String[] args) throws IOException {
        //setup
        if (submission) {
            f = new BufferedReader(new FileReader("outofplace.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("outofplace.out")));
            n = Integer.parseInt(f.readLine());
            lineup = new int[n];
            for (int i = 0; i < n; i++) {
                lineup[i] = Integer.parseInt(f.readLine());
            }
        } else {
            n = 10;
            lineup = new int[]{1, 1000, 3, 4, 5, 6, 7, 8, 9, 10};
        }
        //logic
        int sorted[] = lineup.clone();
        Arrays.sort(sorted);
        int swaps = -1;
        for (int i=0;i<n;i++) {
            if (lineup[i] != sorted[i]) {
                swaps ++;
            }
        }
        out.println(Math.max(0, swaps));
        out.close();
    }
}
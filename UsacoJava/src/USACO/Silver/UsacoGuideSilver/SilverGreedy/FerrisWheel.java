package USACO.Silver.UsacoGuideSilver.SilverGreedy;

import java.io.*;
import java.util.*;

/*
CSES Problem Set
Ferris Wheel
USACO Silver Guide - Greedy + Sorting - Easy
 */
public class FerrisWheel {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int X;
    static int[] weight;
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        weight = new int[N];
        for (int i=0;i<N;i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }
        //logic
        Arrays.sort(weight);
        int i=0; int j=N-1;
        int ans = 0;
        while (i < j) {
            if (weight[i]+weight[j]>X) {
                j--;
                ans++;
            }
            else {
                i++;
                j--;
                ans++;
            }
        }
        if (i==j) ans++;
        //turn in answer
        out.println(ans);
        out.close();
    }
}

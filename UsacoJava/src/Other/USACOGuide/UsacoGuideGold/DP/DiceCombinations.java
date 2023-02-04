package Other.USACOGuide.UsacoGuideGold.DP;/*
CSES Problem Set
Dice Combinations
USACO Gold Guide - DP Series
 */
import java.io.*;

public class DiceCombinations {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int[] combo;
    static int N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        combo = new int[N+7];

        combo[5] = 1;
        for (int i=6;i<=N+5;i++) {
            combo[i] = (int) (((long)combo[i-1]+combo[i-2]+combo[i-3]+combo[i-4]+combo[i-5]+combo[i-6])%(1e9+7));
        }
        //out.println(Arrays.toString(combo));
        out.println(combo[N+5]);
        out.close();
    }
}

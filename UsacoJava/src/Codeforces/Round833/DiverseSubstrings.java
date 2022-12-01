package Codeforces.Round833;

import java.io.*;
import java.util.*;

/*
PROB: DiverseSubstrings
LANG: JAVA
*/
public class DiverseSubstrings {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup("DiverseSubstrings");
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    public static void solve(int tcs) throws IOException {
        if (debug) System.out.println("Case: " + tcs);
        //* parse
        int N = Integer.parseInt(br.readLine());
        int window = 100;
        int[] arr = new int[N];
        String str = br.readLine();
        for (int i=0;i<N;i++) arr[i]=str.charAt(i)-'0';
        //* complete search every window
        int ans = 0;
        for (int i=0;i<N;i++){
            HashSet<Integer> distinct = new HashSet<>();
            int[] cnt = new int[10];
            for (int j=0;j<=window;j++){
                if (i+j>=N) continue;
                distinct.add(arr[i+j]);
                cnt[arr[i+j]]++;
                boolean good = true;
                for (int k=0;k<10;k++) {
                    if (cnt[k]>distinct.size()) good=false;
                }
                if (good) ans++;
            }
        }
        //* ret
        out.println(ans);
    }


    static BufferedReader br;
    static PrintWriter out;

    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName + ".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}
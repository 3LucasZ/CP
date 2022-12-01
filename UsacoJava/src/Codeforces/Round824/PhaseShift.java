package Codeforces.Round824;

import java.io.*;
import java.util.*;

/*
PROB: PhaseShift
LANG: JAVA
*/
public class PhaseShift {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup("PhaseShift");
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    static int N;
    static char[] A;
    static boolean[] taken;
    static char[] next;

    public static void solve(int tcs) throws IOException {
        if (debug) System.out.println("Case: " + tcs);
        //* parse
        N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        A = str.toCharArray();
        //* functional graph
        taken = new boolean[200];
        next = new char[200];
        for (int i=0;i<N;i++){
            if (next[A[i]]!=0) continue;
            for (char tryNext='a';tryNext<='z';tryNext++){
                if (taken[tryNext]) continue;
                next[A[i]]=tryNext;
                if (cycle(tryNext)) next[A[i]]=0;
                else {
                    taken[tryNext]=true;
                    break;
                }
            }
        }
        //* ret
        for (int i=0;i<N;i++) out.print(next[A[i]]);
        out.println();
    }
    public static boolean cycle(int head){ //constant time
        int cur = head;
        for (int i=0;i<25;i++){
            cur=next[cur];
            if (cur==head) return true;
        }
        return false;
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
package Codeforces.Round838;

import java.io.*;
import java.util.*;

/*
PROB: MakeArrayGood
LANG: JAVA
*/
public class MakeArrayGood {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup("MakeArrayGood");
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    public static void solve(int tcs) throws IOException {
        if (debug) System.out.println("Case: " + tcs);
        //* parse
        int N = Integer.parseInt(br.readLine());
        Element[] A = new Element[N+1]; A[0] = new Element(-100,0);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int i=1;i<=N;i++){
            A[i]=new Element(Integer.parseInt(st.nextToken()),i);
        }
        //* sort
        Arrays.sort(A, Comparator.comparingInt(a -> a.val));
        //* add a little bit more until at least the previous
        System.out.println(N-1);
        for (int i=2;i<=N;i++){
            int tar = A[i].val/A[i-1].val+1;
            tar*=A[i-1].val;
            int delta = tar-A[i].val;
            System.out.println(A[i].i+" "+delta);
            A[i].val+=delta;
        }
    }
    private static class Element {
        int val;
        int i;
        public Element(int v, int i){
            this.val=v;
            this.i=i;
        }
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
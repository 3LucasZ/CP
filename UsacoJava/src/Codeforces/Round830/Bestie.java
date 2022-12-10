package Codeforces.Round830;

import java.io.*;
import java.util.*;

/*
PROB: Bestie
LANG: JAVA
*/
public class Bestie {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup("Bestie");
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    static int N;
    static int[] A;
    public static void solve(int tcs) throws IOException {
        if (debug) System.out.println("Case: " + tcs);
        //* parse
        N = Integer.parseInt(br.readLine());
        A = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            A[i]=Integer.parseInt(st.nextToken());
        }
        //* TRY STUFF
        if (done()) {
            out.println(0);
            return;
        }
        int prev = A[N];
        A[N]=gcd(A[N],N);
        if (done()){
            out.println(1);
            return;
        }
        A[N]=prev;
        A[N-1]=gcd(A[N-1],N-1);
        if (done()){
            out.println(2);
            return;
        }
        out.println(3);
        return;
    }
    public static boolean done() {
        int g = A[1];
        for (int i=2;i<=N;i++) g=gcd(g,A[i]);
        return g==1;
    }
    public static int gcd(int a, int b){
        if (b==0) return a;
        return gcd(b,a%b);
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
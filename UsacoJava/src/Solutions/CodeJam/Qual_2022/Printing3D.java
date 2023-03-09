package Solutions.CodeJam.Qual_2022;

import java.io.*;
import java.util.*;

public class Printing3D {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup();
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    static int[][] ink;
    static int M = (int)(1e6);
    public static void solve(int tcs) throws IOException {
        System.out.print("Case #" + tcs + ": ");
        if (debug) System.out.println("");
        //* parse
        ink = new int[3][4];
        for (int p=0;p<3;p++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i=0;i<4;i++){
                ink[p][i]=Integer.parseInt(st.nextToken());
            }
        }
        //* min cmyk
        int[] minInk = new int[4];
        for (int i=0;i<4;i++){
            minInk[i]=ink[0][i];
            for (int p=0;p<3;p++){
                minInk[i]=Math.min(minInk[i],ink[p][i]);
            }
        }
        //* IMPOSSIBLE
        int sum = minInk[0]+minInk[1]+minInk[2]+minInk[3];
        if (sum<M) {
            System.out.println("IMPOSSIBLE");
            return;
        }
        //* finalize
        int run = 0;
        for (int i=0;i<4;i++){
            run+=minInk[i];
            if (run>M) {
                int overflow = run-M;
                run-=overflow;
                minInk[i]-=overflow;
            }
        }
        //* ret
        for (int i=0;i<4;i++) System.out.print(minInk[i]+" ");
        System.out.println();
    }


    static BufferedReader br;
    static PrintWriter out;

    public static void setup() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
}
package CodeJam.Qual_2022;

import java.io.*;
import java.util.*;

public class d1000000 {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup();
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    public static void solve(int tcs) throws IOException {
        System.out.print("Case #" + tcs + ": ");
        if (debug) System.out.println("");
        //* parse
        int N = Integer.parseInt(br.readLine());
        Integer[] S = new Integer[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) S[i]=Integer.parseInt(st.nextToken());
        Arrays.sort(S);
        //* running
        int ret = 1;
        for (int i=0;i<N;i++){
            if (S[i]>=ret){
                ret++;
            }
        }
        System.out.println(ret-1);
    }


    static BufferedReader br;
    static PrintWriter out;

    public static void setup() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
}
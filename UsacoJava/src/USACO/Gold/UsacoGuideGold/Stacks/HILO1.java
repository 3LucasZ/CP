package USACO.Gold.UsacoGuideGold.Stacks;

import java.io.*;
import java.util.*;

public class HILO1 {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int[] P;
    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        N = Integer.parseInt(br.readLine());
        P = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            P[i]=Integer.parseInt(st.nextToken());
        }

        //simulate
        for (int x=0;x<=N;x++){
            int minHI = N+1;
            int maxLO = -1;
            int last = -1; // {0 for LO, 1 for HI}
            int ans = 0;
            for (int i:P){
                //case: ignore
                if (i>minHI || i<maxLO) continue;
                //case: LO
                if (i <= x) {
                    if (last==1) ans++;
                    last=0;
                    maxLO=i;
                }
                //case: HI
                else {
                    last=1;
                    minHI=i;
                }
            }
            out.println(ans);
        }
        out.close();
    }




















    static BufferedReader br;
    static PrintWriter out;
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}

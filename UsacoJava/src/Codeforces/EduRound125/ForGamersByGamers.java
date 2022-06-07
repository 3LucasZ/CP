package Codeforces.EduRound125;

import java.io.*;
import java.util.*;

public class ForGamersByGamers {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    //param
    static int N;
    static int C;
    static long best[];

    static int M;

    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        best = new long[C+1];
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            long strength = Integer.parseInt(st.nextToken()) * 1L * Integer.parseInt(st.nextToken());
            best[c]=Math.max(best[c],strength);
        }
        if (debug) System.out.println(Arrays.toString(best));

        //what is the maximum power for cost c?
        for (int unit=C;unit>=1;unit--){
            for (int spend=unit;spend<=C;spend+=unit){
                best[spend]=Math.max(best[spend],best[unit]*(spend/unit));
            }
        }
        if (debug) System.out.println(Arrays.toString(best));

        for (int i=1;i<=C;i++){
            best[i]=Math.max(best[i],best[i-1]);
        }
        if (debug) System.out.println(Arrays.toString(best));

        //query monsters
        M = Integer.parseInt(br.readLine());
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            long strength = Integer.parseInt(st.nextToken()) * Long.parseLong(st.nextToken());
            out.print(cheapestWin(strength)+" ");
        }
        out.close();
    }

    public static int cheapestWin(long strength){
        if (strength >= best[C]) return -1;

        int lo=1;
        int hi=C;

        while (lo < hi){
            int mid = (lo+hi)/2;
            if (best[mid] > strength) hi=mid;
            else lo=mid+1;
        }

        return lo;
    }
}

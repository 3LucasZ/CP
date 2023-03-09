package Solutions.USACO.Season2017_2018.Dec2017.Plat;

import java.io.*;
import java.util.*;
public class GreedyGiftTakers {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int[] P;

    public static void main(String[] args) throws IOException {
        //parse
        setup("greedy");
        N = Integer.parseInt(br.readLine());
        P = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) P[i]=N-Integer.parseInt(st.nextToken());
        //binary search the first cow to be sad
        int lo = 1;
        int hi = N;
        while(lo<hi){
            int mid = (lo+hi)/2;
            if (sadCow(mid)) hi=mid;
            else lo=mid+1;
        }
        //ret
        out.println(N-lo+1);
        out.close();
    }
    public static boolean sadCow(int cow){
        //a cow is sad if there is some subset from 1..cow-1 of cows where:
        //every cow's P is leq subset size
        int[] cnt = new int[N+1];
        for (int i=1;i<cow;i++) cnt[P[i]]++;
        int run = 0;
        for (int i=1;i<cow;i++) {
            run+=cnt[i];
            if (run>=i) return true;
        }
        return false;
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
/*
10
4 3 2 4 1 2 3 4 1 2
 */
package Solutions.Codeforces.PickNPlay;

import java.io.*;
import java.util.*;
/*
PROB: ChoosingFlowers
LANG: JAVA
*/
public class ChoosingFlowers {
    static boolean submission = false;
    static boolean debug = false;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        //* TCS
        setup("ChoosingFlowers");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) {
            solve();
            br.readLine();
        }
        out.close();
    }
    public static void solve() throws IOException {
        if (debug) System.out.println("TCS: ");
        //* parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Flower[] flowers = new Flower[M+1]; flowers[0]=new Flower(INF,INF);
        for (int i=1;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            long a = Integer.parseInt(st.nextToken());
            long b = Integer.parseInt(st.nextToken());
            flowers[i]=new Flower(a,b);
        }
        //* greedy
        //sort by A descending
        Arrays.sort(flowers,Comparator.comparingLong(a->-a.a));
        if (debug) System.out.println(Arrays.toString(flowers));
        //presum A
        long[] presum = new long[M+1];
        for (int i=1;i<=M;i++) presum[i]=presum[i-1]+flowers[i].a;
        //try each B fixation
        long ans = 0;
        for (int bi=1;bi<=M;bi++){
            //binary search best Ai to stop on
            int lo = 0;
            int hi = M;
            while (lo<hi){
                int mid = (lo+hi+1)/2;
                if (flowers[mid].a>=flowers[bi].b) lo=mid;
                else hi=mid-1;
            }
            int ai = lo;
            //edge casing, summing, updating
            long sum = presum[ai];
            if (flowers[bi].a<flowers[bi].b){
                sum+=flowers[bi].a;
                ai++;
            }
            if (ai>N) continue;
            ans=Math.max(ans,sum+(N-ai)*flowers[bi].b);
            if (debug) System.out.println("["+bi+", "+ai+"]");
        }
        //wonky case
        if (N<=M){
            ans=Math.max(ans,presum[N]);
        }
        //* ret
        out.println(ans);
    }
    private static class Flower {
        long a;
        long b;
        public Flower(long a, long b){
            this.a=a;
            this.b=b;
        }
        public String toString(){
            return "["+a+", "+b+"]";
        }
    }
/*
1
19 2
587575696 945418070
762247361 719309487
*/
















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
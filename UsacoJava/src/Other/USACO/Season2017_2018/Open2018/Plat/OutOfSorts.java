package Other.USACO.Season2017_2018.Open2018.Plat;

import java.io.*;
import java.util.*;
/*
PROB: OutOfSorts
LANG: JAVA
*/
public class OutOfSorts {
    static boolean submission = true;
    static boolean debug = true;

    static int N;
    //value of i
    static Pair[] A;

    public static void main(String[] args) throws IOException {
        //parse
        setup("sort");
        N = Integer.parseInt(br.readLine());
        A = new Pair[N];
        for (int i=0;i<N;i++)A[i]=new Pair(i,Integer.parseInt(br.readLine()));
        //sort A
        Arrays.sort(A,(a,b)->{
            //second order: i least to greatest
            if (a.val==b.val) return a.i-b.i;
            //first order: val least to greatest
            return a.val-b.val;
        });
        //find when each partition line appears where partition line i divides segment 0..i-1 from i..N-1
        int[] partition = new int[N+1];
        int runMax = 0;
        for (int i=1;i<N;i++){
            runMax=Math.max(runMax,A[i-1].i);
            partition[i]=Math.max(partition[i],runMax-i+1);
        }
        //for each element find when its l, r partitions are activated. Activation time will be the complexity contribution.
        long ans = 0;
        for (int i=0;i<N;i++){
            int complexity = Math.max(partition[i],partition[i+1]);
            //weird case: each element has at least complexity 1
            if (complexity==0) complexity++;
            ans+=complexity;
        }
        //ret
        out.println(ans);
        out.close();
    }
    private static class Pair {
        int i;
        int val;
        public Pair(int i, int val){
            this.i=i;
            this.val=val;
        }
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
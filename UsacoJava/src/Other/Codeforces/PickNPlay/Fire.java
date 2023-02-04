package Other.Codeforces.PickNPlay;

import java.io.*;
import java.util.*;

public class Fire {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static Item[] items;

    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        N = Integer.parseInt(br.readLine());
        items = new Item[N+1]; items[0] = new Item(0,0,0,0);
        for (int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            items[i] = new Item(i,Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        //sort
        Arrays.sort(items, (a,b)->a.d-b.d);
        if (debug) System.out.println(Arrays.toString(items));

        //dp[on item i][total time]
        Triple[][] dp = new Triple[N+1][20*N+1];
        for (int i=0;i<=N;i++) for (int t=0;t<=20*N;t++) dp[i][t] = new Triple(0,0, false);
        for (int i=0;i<N;i++){
            for (int t=0;t<=20*N;t++){
                int newT = t+items[i+1].t;
                if (newT<=20*N && items[i+1].d>newT && dp[i][t].value+items[i+1].p > dp[i+1][newT].value) {
                    dp[i+1][newT]=new Triple(dp[i][t].value+items[i+1].p,t,true);
                }
                if (dp[i][t].value>dp[i+1][t].value){
                    dp[i+1][t]=new Triple(dp[i][t].value,t,false);
                }
            }
        }

        //get best pair i,t w/ debug
        int best = 0;
        int bestI = 0;
        int bestT = 0;
        for (int i=1;i<=N;i++) {
            if (debug) System.out.print(i+": ");
            for (int t=0;t<=20*N;t++) {
                if (debug) System.out.print(dp[i][t]+" ");
                if (dp[i][t].value > best){
                    bestI=i;
                    bestT=t;
                    best=dp[i][t].value;
                }
            }
            if (debug) System.out.println();
        }
        if (debug){
            System.out.println("bestI: "+bestI+", bestT: "+bestT+", best: "+best);
        }

        //stack traceback to find which items to buy
        ArrayList<Integer> buy = new ArrayList<>();
        for (;bestI>0;bestI--){
            if (dp[bestI][bestT].tookI) buy.add(items[bestI].id);
            bestT = dp[bestI][bestT].lastT;
        }
        Collections.reverse(buy);

        //ret
        out.println(best);
        out.println(buy.size());
        for (int i : buy) out.print(i+" ");
        out.close();
    }
    private static class Item {
        int id;
        int t;
        int d;
        int p;
        public Item(int i, int t, int d, int p){
            this.id=i;
            this.t=t;
            this.d=d;
            this.p=p;
        }
        public String toString(){
            return "["+t+", "+d+", "+p+"]";
        }
    }
    private static class Triple {
        int value;
        int lastT;
        boolean tookI;
        public Triple(int v, int l, boolean t){
            value=v;
            lastT=l;
            tookI=t;
        }
        public String toString(){
            return "["+value+", "+lastT+", "+tookI+"]";
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

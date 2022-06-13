package Platinum.EC.DAY1;

import java.io.*;
/*
USACO 2010 Gold January P1
Taking Turns
concept: dp on game, optimization, "look into future", bot player, turn state, either or
commentary:
beautiful example of dp -> video game optimization
compute the future to compute the present. propagate and transition until get answered state.
basis:
take[i] + dp[i+1][1]
noTake[i] + dp[i+1][0]
at first started with a prototype which failed miserably
debugging PAIN :
READ instruction: a cow will choose to eat the FIRST bale of hay if 2 different moves are optimal
Rolling arrays to save time and memory: dp[1]=dp[0] reset dp[0]
 */
public class TakingTurns {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int[] weight;

    public static void main(String[] args) throws IOException {
        setup("");
        N = Integer.parseInt(br.readLine());
        weight = new int[N+1];
        for (int i=1;i<=N;i++) weight[i]=Integer.parseInt(br.readLine());

        //dp setup: dp["rolling" bale][turn: 0=B,1=D]
        Pair[][] dp = new Pair[2][2];
        //init
        dp[1][0] = new Pair(weight[N],0);
        dp[1][1] = new Pair(0,weight[N]);

        //transitions
        for (int i=N-1;i>=1;i--){
            for (int p=0;p<2;p++){
                Pair take = dp[1][1-p].clone();take.arr[p]+=weight[i];
                Pair noTake = dp[1][p].clone();
                if (take.arr[p] >= noTake.arr[p]) dp[0][p]=take;
                else dp[0][p]=noTake;
            }
            dp[1][0]=dp[0][0].clone();
            dp[1][1]=dp[0][1].clone();
        }

        //ans
        out.println(dp[1][0]);
        out.close();
    }
    private static class Pair {
        long[] arr = new long[2];
        public Pair(long B, long D){
            arr[0]=B;
            arr[1]=D;
        }
        public Pair clone(){
            return new Pair(arr[0],arr[1]);
        }
        public String toString(){
            return arr[0]+" "+arr[1];
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

package Platinum.EC.WARM1;

import java.io.*;

public class TakingTurnsPROTOTYPE {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int[] weight;

    public static void main(String[] args) throws IOException {
        setup("");
        N = Integer.parseInt(br.readLine());
        weight = new int[N];
        for (int i=0;i<N;i++) weight[i]=Integer.parseInt(br.readLine());

        //dp setup: dp[bale][turn: 0=B,1=D]
        Pair[][] dp = new Pair[N][2];
        //init
        dp[0][0] = new Pair(0,0);
        dp[0][1] = new Pair(weight[0],0);
        //transitions
        for (int i=0;i<N-1;i++){
            Pair bb = dp[i][0].clone();
            Pair db = dp[i][1].clone(); db.D+=weight[i+1];
            if (bb.B < db.B || (bb.B == db.B && bb.D < db.D)){
                dp[i+1][0] = db;
            } else {
                dp[i+1][0] = bb;
            }

            Pair dd = dp[i][1].clone();
            Pair bd = dp[i][0].clone(); bd.B+=weight[i+1];
            if (dd.D < bd.D || (dd.D == bd.D && dd.B < db.B)){
                dp[i+1][1] = bd;
            } else {
                dp[i+1][1] = dd;
            }
        }
        if (debug){
            out.println("dp: B   |   D");
            for (int i=0;i<N;i++){
                out.println(i+": "+dp[i][0]+" | "+dp[i][1]);
            }
        }
        if (dp[N-1][0].B > dp[N-1][0].B){
            out.println(dp[N-1][0]);
        } else {
            out.println(dp[N-1][1]);
        }
        out.close();
    }
    private static class Pair {
        int B;
        int D;
        public Pair(int B, int D){
            this.B=B;
            this.D=D;
        }
        public Pair clone(){
            return new Pair(B,D);
        }
        public String toString(){
            return B+" "+D;
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

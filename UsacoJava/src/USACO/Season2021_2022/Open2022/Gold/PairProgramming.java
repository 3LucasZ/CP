package USACO.Season2021_2022.Open2022.Gold;

import java.io.*;
import java.util.ArrayList;

public class PairProgramming {
    static boolean submission = false;
    static boolean debug = false;

    static int T;
    static int N;
    static long MOD = (long)1e9+7;

    public static void main(String[] args) throws IOException {
        //handle TCS
        setup("");
        T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }
    public static void solve() throws IOException{
        //parse
        N = Integer.parseInt(br.readLine());
        ArrayList<Boolean> B = new ArrayList<>(); //true => x num, false => + var
        ArrayList<Boolean> E = new ArrayList<>();
        String str;

        str = br.readLine();
        for (int i=0;i<N;i++){
            if (str.charAt(i)=='0') {B = new ArrayList<>();B.add(true);}
            else if (str.charAt(i)=='1');
            else B.add(str.charAt(i)=='+'?false:true);
        }
        int P = B.size();

        str = br.readLine();
        for (int i=0;i<N;i++){
            if (str.charAt(i)=='0') {E = new ArrayList<>();E.add(true);}
            else if (str.charAt(i)=='1');
            else E.add(str.charAt(i)=='+'?false:true);
        }
        int Q = E.size();

        //check
        if (debug){
            System.out.println();
            System.out.println("Bessie: "+B);
            System.out.println("Elsie: "+E);
        }

        //tiny case :l
        if (P==0||Q==0) {
            out.println(1);
            return;
        }

        //grid DP
        //dp[Bi][Ei][last: B=0/E=1]
        long[][][] dp = new long[P+1][Q+1][2];
        dp[1][0][0]=1;
        dp[0][1][1]=1;
        for (int i=0;i<=P;i++){
            for (int j=0;j<=Q;j++){
                //src ij0
                if (i+1<=P)dp[i+1][j][0]=(dp[i+1][j][0]+dp[i][j][0])%MOD;
                if (j+1<=Q)dp[i][j+1][1]=(dp[i][j+1][1]+dp[i][j][0])%MOD;
                //src ij1
                if (i+1<=P && j>0 && E.get(j-1)!=B.get(i)) dp[i+1][j][0]=(dp[i+1][j][0]+dp[i][j][1])%MOD;
                if (j+1<=Q) dp[i][j+1][1]=(dp[i][j+1][1]+dp[i][j][1])%MOD;
            }
        }

        //check n ret
        if (debug){
            for (int i=0;i<=P;i++){
                for (int j=0;j<=Q;j++){
                    System.out.print("["+dp[i][j][0]+", "+dp[i][j][1]+"]");
                }
                System.out.println();
            }
            System.out.println();
        }
        out.println((dp[P][Q][0]+dp[P][Q][1])%MOD);
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

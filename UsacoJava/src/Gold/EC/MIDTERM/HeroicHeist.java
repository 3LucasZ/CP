package Gold.EC.MIDTERM;

import java.io.*;
import java.util.*;

public class HeroicHeist {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;
    //param
    static int N;
    static Starship[] starships;
    //dp
    //last ship, on ship
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(br.readLine());
        starships = new Starship[N];
        //dummy ship for 1-indexing
        //starships[0]=new Starship(-1, 0);
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            starships[i] = new Starship(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        //dp left to right:
        //sort starships
        Arrays.sort(starships,(a,b)->a.loc-b.loc);
        if (debug) System.out.println(Arrays.toString(starships));
        //setup
        dp = new int[N][N];

        for (int l=0;l<N;l++){
            //base casing
            dp[l][l]=starships[l].money;

            int bp=l;
            int best=0;
            for (int r=l+1;r<N;r++){
                while (bp >= 0 && starships[r].loc-starships[l].loc >= starships[l].loc - starships[bp].loc){
                    best=Math.max(best,dp[bp][l]);
                    bp--;
                }
                dp[l][r]=best+starships[r].money;
            }
        }
        //get left to right answer
        int ansL=0;
        for (int l=0;l<N;l++){
            for (int r=l;r<N;r++){
                ansL=Math.max(ansL, dp[l][r]);
            }
        }

        //left to right debug
        if (debug) for (int l=0;l<N;l++){
            System.out.println(Arrays.toString(dp[l]));
        }
        if (debug) System.out.println(ansL);







        //dp right to left DESPARATION
        //sort starships
        Arrays.sort(starships,(a,b)->b.loc-a.loc);
        if (debug) System.out.println(Arrays.toString(starships));
        //setup
        dp = new int[N][N];

        for (int l=0;l<N;l++){
            //base casing
            dp[l][l]=starships[l].money;

            int bp=l;
            int best=0;
            for (int r=l+1;r<N;r++){
                while (bp >= 0 && starships[l].loc-starships[r].loc >= starships[bp].loc - starships[l].loc){
                    best=Math.max(best,dp[bp][l]);
                    bp--;
                }
                dp[l][r]=best+starships[r].money;
            }
        }
        //get answer
        int ansR=0;
        for (int l=0;l<N;l++){
            for (int r=l;r<N;r++){
                ansR=Math.max(ansR, dp[l][r]);
            }
        }

        //debug
        if (debug) for (int l=0;l<N;l++){
            System.out.println(Arrays.toString(dp[l]));
        }
        if (debug) System.out.println(ansR);

        //answering
        out.println(Math.max(ansL,ansR));
        out.close();
    }
    private static class Starship{
        int loc;
        int money;
        public Starship(int loc, int money){
            this.loc=loc;
            this.money=money;
        }
        public String toString(){
            return "["+loc+", "+money+"]";
        }
    }
}

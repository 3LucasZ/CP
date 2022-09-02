package USACO.Gold.EC.MIX9;

import java.io.*;
import java.util.*;
/*
Moortal Combat
Gold Advanced B - MIX
Thoughts:
Everything easy except the dp treasure:
dp[letter][position]=Math.min(last K are letter, letter+lastK are letter)
 */
public class MoortalCombat {
    //io
    static boolean submission = false;
    static boolean debug = false;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static int M;
    static int K;
    static int[] orig;
    static int[][] dist;

    //preprocess
    static int[][] cost2;

    //const
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        orig = new int[N+1];
        String str = br.readLine();
        for (int i=1;i<=N;i++) orig[i]=str.charAt(i-1)-'a';
        if (debug) System.out.println(Arrays.toString(orig));
        dist = new int[M][M];
        for (int r=0;r<M;r++){
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<M;c++){
                int cost = Integer.parseInt(st.nextToken());
                dist[r][c]=cost;
            }
        }

        //Floyd dist
        for (int mid=0;mid<M;mid++){
            for (int u=0;u<M;u++){
                for (int v=0;v<M;v++){
                    //relax
                    dist[u][v]=Math.min(dist[u][v], dist[u][mid]+dist[mid][v]);
                }
            }
        }
        if (debug && false) {
            for (int u=0;u<M;u++){
                for (int v=0;v<M;v++){
                    System.out.print(dist[u][v]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
        //cost2 presum preprocess
        cost2 = new int[M][N+1];
        for (int l=0;l<M;l++){
            for (int p=1;p<=N;p++){
                cost2[l][p] = cost2[l][p-1]+dist[orig[p]][l];
            }
        }
        if (debug && false) {
            for (int l=0;l<M;l++){
                for (int p=1;p<=N;p++){
                    System.out.print(cost2[l][p]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }

        //init
        int[][] dp;
        int[] best;
        dp = new int[M][N+1];
        best = new int[N+1];
        Arrays.fill(best, INF);
        for (int p=K;p<=Math.min(N,2*K-1);p++) for (int l=0;l<M;l++) {
            dp[l][p] = cost2[l][p];
            best[p]=Math.min(best[p], dp[l][p]);
        }

        //dp treasure
        for (int p=2*K;p<=N;p++){
            for (int l=0;l<M;l++){
                dp[l][p] = Math.min(best[p-K]+get(p-K+1,p,l), dp[l][p-1]+get(p,p,l));
                best[p]=Math.min(best[p], dp[l][p]);
            }
        }

        if (debug) System.out.println(Arrays.toString(best));
        //turn in answer
        out.println(best[N]);
        out.close();
    }

    public static int get(int i, int j, int l) {return cost2[l][j]-cost2[l][i-1];}
}

package USACO.Gold.UsacoGuideGold.DP;

import java.io.*;
import java.util.*;
/*
USACO 2017 January Contest, Gold
Problem 2. Hoof, Paper, Scissors
USACO Gold Guide - Dynamic Programming Intro - Easy
 */

public class HoofPaperScissors {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int K;
    static int win[];
    static int pointsDP[][][];
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("hps.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        win = new int[N+1];
        win[0] = -1000;
        for (int i=1;i<=N;i++) {
            int fj = br.readLine().charAt(0);
            if (fj=='P') {
                win[i]=0;
            } else if (fj=='S'){
                win[i]=1;
            } else {
                win[i]=2;
            }
        }
        //out.println(Arrays.toString(win));
        pointsDP = new int[N+1][K+1][3];
        //logic
        pointsDP[1][0][0] = (win[1] == 0) ? 1 : 0;
        pointsDP[1][0][1] = (win[1] == 1) ? 1 : 0;
        pointsDP[1][0][2] = (win[1] == 2) ? 1 : 0;
        for (int round=2;round<=N;round++) {
            for (int change=0;change<=K;change++) {
                for (int move=0;move<3;move++) {
                    pointsDP[round][change][move] = Math.max(pointsDP[round][change][move], pointsDP[round-1][change][move] + (move == win[round] ? 1 : 0));
                }
                if (change != K) {
                    int bestPrev = 0;
                    for (int i=0;i<3;i++) {
                        bestPrev = Math.max(bestPrev, pointsDP[round-1][change][i]);
                    }
                    for (int i=0;i<3;i++) {
                        pointsDP[round][change + 1][i] = Math.max(pointsDP[round][change + 1][i], bestPrev + (i == win[round] ? 1 : 0));
                    }
                }
            }
        }
        //turn in answer
        //printDP();
        out.println(Math.max(pointsDP[N][K][0],Math.max(pointsDP[N][K][1], pointsDP[N][K][2])));
        out.close();
    }
    public static void printDP(){
        for (int i=1;i<=N;i++) {
            out.println(i+":");
            for (int j=0;j<=K;j++) {
                out.print(j + " [");
                for (int k=0;k<3;k++) {
                    out.print(k + ":" + pointsDP[i][j][k] + "   ");
                }
                out.print("]");
                out.println();
            }
            out.println();
        }
    }
}

package USACOGuide.UsacoGuideGold.DP;

import java.io.*;
import java.util.*;
/*
USACO 2013 US Open, Silver
Problem 1. What's Up With Gravity
USACO Silver/Gold Training

Thoughts: Super fun grid dp problem
queue of states
destroy useless states
drop the state
if during drop it encounters "better" grid or falls off map, destroy state
add 3 new states: l, r, g+1
Beautiful dp :)
 */
public class WhatsUpWithGravity {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean submission = true;
    //param
    static int R;
    static int C;
    static Point captain;
    static Point doctor;
    static boolean[][] blocked;

    //dp
    static int[][][] dp;
    static Queue<Point> states = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("gravity.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("gravity.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        blocked = new boolean[R][C];
        for (int r=0;r<R;r++){
            String str = br.readLine();
            for (int c=0;c<C;c++){
                if (str.charAt(c)=='#'){
                    blocked[r][c]=true;
                }
                else if (str.charAt(c)=='C'){
                    captain = new Point(r,c,0);
                }
                else if (str.charAt(c)=='D'){
                    doctor = new Point(r,c,0);
                }
            }
        }
        if (!submission) printInput();

        //logic: dp
        //dp[r][c][up/down gravity]=gravity swtiches
        dp = new int[R][C][2];
        for (int r=0;r<R;r++) for (int c=0;c<C;c++) for (int i=0;i<2;i++) dp[r][c][i] = Integer.MAX_VALUE;
        states.add(captain);

        while (!states.isEmpty()){
            Point next = states.poll();

            //illegal
            if (!isLegalPoint(next.r, next.c) || blocked[next.r][next.c]) continue;

            //visited with less gravity
            if (dp[next.r][next.c][next.g%2]<=next.g) continue;

            dp[next.r][next.c][next.g%2]=next.g;
            if (FallPoint(next)) {
                //add to queue
                states.add(new Point(next.r,next.c+1,next.g));
                states.add(new Point(next.r, next.c-1,next.g));
                states.add(new Point(next.r, next.c, next.g+1));
            }
        }

        //turn in answer
        if (!submission) printDP();
        if (dp[doctor.r][doctor.c][0]==Integer.MAX_VALUE&&dp[doctor.r][doctor.c][1]==Integer.MAX_VALUE) out.println(-1);
        else out.println(Math.min(dp[doctor.r][doctor.c][0],dp[doctor.r][doctor.c][1]));
        out.close();
    }

    //0gravity is regular 1gravity is irregular
    static int[] dg={1,-1};
    public static boolean FallPoint(Point p){
        while (isLegalPoint(p.r+dg[p.g%2],p.c) && !blocked[p.r+dg[p.g%2]][p.c]) {
            p.r+=dg[p.g%2];
            if (dp[p.r][p.c][p.g%2]<=p.g) return false;
            dp[p.r][p.c][p.g%2]=p.g;
        }
        if (!isLegalPoint(p.r+dg[p.g%2],p.c)) return false;
        return true;
    }
    public static boolean isLegalPoint(int r, int c){
        if (r<0||r>=R||c<0||c>=C) return false;
        return true;
    }
    private static class Point {
        int r;
        int c;
        int g;
        public Point(int r, int c, int g){
            this.r=r;
            this.c=c;
            this.g=g;
        }
        public String toString(){
            return "["+r+", "+c+"]";
        }
    }
    public static void printInput(){
        for (int r=0;r<R;r++){
            for (int c=0;c<C;c++){
                if (r==doctor.r&&c==doctor.c) System.out.print("D");
                else if (r==captain.r&&c==captain.c) System.out.print("C");
                else System.out.print(blocked[r][c]?"#":".");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void printDP(){
        for (int r=0;r<R;r++){
            for (int c=0;c<C;c++){
                System.out.print(Math.min(dp[r][c][0],dp[r][c][1])+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

package Other.USACOGuide.UsacoGuideSilver.FloodFill;
/*
USACO 2016 February Contest, Silver
Problem 3. Milk Pails
USACO Silver Guide - Normal - Floodfill
Concepts: Similar to Snow boots problem... Abstract floodfill with "visits" concept
 */
import java.io.*;
import java.util.*;

public class MilkPails {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int X;
    static int Y;
    static int K;
    static int M;
    static int visited[][];
    static int ans = 100000;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("pails.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new int[X+1][Y+1];
        //logic
        floodfill(0,0, 0);
        //turn in answer
        out.println(ans);
        out.close();
    }
    public static void floodfill(int bucket1, int bucket2, int round){
        if (visited[bucket1][bucket2] != 0 && round >= visited[bucket1][bucket2] || round > K) return;
        visited[bucket1][bucket2] = round;
        ans = Math.min(ans, Math.abs(bucket2+bucket1-M));
        //out.println("["+bucket1+", "+bucket2+"]");
        //fill bucket
        floodfill(bucket1, Y, round+1);
        floodfill(X, bucket2, round+1);
        //empty bucket
        floodfill(0, bucket2, round+1);
        floodfill(bucket1, 0, round+1);
        //pour from bucket to other
        int toPour = Math.min(Y-bucket2, bucket1);
        floodfill(bucket1-toPour,bucket2+toPour,round+1);
        toPour = Math.min(X-bucket1, bucket2);
        floodfill(bucket1+toPour,bucket2-toPour,round+1);
    }
}

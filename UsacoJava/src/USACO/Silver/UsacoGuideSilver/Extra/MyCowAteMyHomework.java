package USACO.Silver.UsacoGuideSilver.Extra;

import java.io.*;
import java.util.*;

/*
USACO 2017 December Contest, Silver
Problem 1. My Cow Ate My Homework
USACO Silver Practice
Thoughts: Sweep, simple dynamic programming, tracking
 */
public class MyCowAteMyHomework {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int[] scores;
    //intermediary
    static int[] runMin;
    static int[] runSum;
    static float[] avg;
    static float bestAvg = 0;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("homework.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        scores = new int[N];
        runSum = new int[N];
        runMin = new int[N];
        avg = new float[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }
        //logic
        runMin[N-1] = scores[N-1];
        for (int i=N-2;i>=0;i--) {
            runMin[i] =  Math.min(runMin[i+1], scores[i]);
        }
        runSum[N-1]=scores[N-1];
        for (int i=N-2;i>=0;i--) {
            runSum[i] = runSum[i+1] + scores[i];
        }

        for (int i=0;i<=N-3;i++) {
            avg[i] = (float)(runSum[i+1]-runMin[i+1])/(N-2-i);
            bestAvg = Math.max(bestAvg, avg[i]);
        }
//        out.println(Arrays.toString(scores));
//        out.println(Arrays.toString(runMin));
//        out.println(Arrays.toString(runSum));
//        out.println(Arrays.toString(avg));
        for (int i=0;i<=N-3;i++) {
            if (Math.abs(avg[i]-bestAvg)< 0.00000001) {
                out.println(i+1);
            }
        }
        out.close();
    }
}

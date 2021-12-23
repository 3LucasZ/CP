package Silver.Usaco2021_2022_Silver.Silver2021_1;

import java.io.*;
import java.util.*;

public class ConvolutedIntervals {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int M;
    static int[][] duplicates;
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        duplicates = new int[M+1][M+1];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            duplicates[u][v]++;
        }
        //work
        int prev;
//        for (int u=0;u<M;u++) {
//            for (int v=0;v<M;v++) {
//                if (duplicates[u][v])
//            }
//        }
        //turn in answer
        out.println();
        out.close();
    }
//    private static class Interval implements Comparable<Interval> {
//        int start;
//        int end;
//        public Interval(int s, int e){
//            start = s;
//            end = e;
//        }
//
//        @Override
//        public int compareTo(Interval o) {
//            if (start == o.start) return end - o.end;
//            return start-o.start;
//        }
//    }
}

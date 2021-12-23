package Silver.Usaco2021_2022_Silver.Silver2021_1;/*
USACO 2021 December Contest, Silver
Problem 3. Convoluted Intervals
In-Contest
Desperate alternative to file1 to get first 5 test cases
 */
import java.io.*;
import java.util.*;

public class ConvolutedIntervals2 {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int M;
    static Interval[] intervals;
    static int preSumAns[];
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        intervals = new Interval[N];
        preSumAns = new int[2*M+2];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            intervals[i] = new Interval(u, v);
        }
        //out.println(Arrays.toString(intervals));
        //logic
        for (int a=0;a<N;a++) {
            for (int b=0;b<N;b++) {
                int start = intervals[a].start+intervals[b].start;
                int end = intervals[a].end+intervals[b].end;
                preSumAns[start]++;
                preSumAns[end+1]--;
            }
        }
        //turn in answer
        //out.println(Arrays.toString(preSumAns));
        int runSum = 0;
        for (int i=0;i<=2*M;i++) {
            runSum += preSumAns[i];
            out.println(runSum);
        }
        out.close();
    }
    private static class Interval {
        int start;
        int end;
        public Interval(int s, int e){
            start = s;
            end = e;
        }
        public String toString(){
            return "["+start+", "+end+"]";
        }
    }
}

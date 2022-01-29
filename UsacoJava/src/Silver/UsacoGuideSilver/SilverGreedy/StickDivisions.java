package Silver.UsacoGuideSilver.SilverGreedy;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Stick Divisions
USACO Silver Guide - Greedy with Sorting - Hard
Notes:
Looked at solution, implementation is super easy, why it works I do not know!!!
Look: Huffman coding?
take 2 lowest sticks, combine, repeat N-1 times.
 */
public class StickDivisions {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int X;
    static int N;
    static PriorityQueue<Integer> lengths = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        //parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            lengths.add(Integer.parseInt(st.nextToken()));
        }
        //super greedy adding cheat without proof
        long ans = 0;
        for (int i=0;i<N-1;i++) {
            Integer a = lengths.poll();
            Integer b = lengths.poll();
            lengths.add(a+b);
            ans += a + b;
        }
        out.println(ans);
        out.close();
    }
}

package CSES.Misc;

import java.io.*;
import java.util.*;

public class StickDivisions {
    static boolean submission = false;
    static boolean debug = true;
    static StringTokenizer st;

    static int X;
    static int N;
    static PriorityQueue<Integer> pq = new PriorityQueue();

    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            pq.add(Integer.parseInt(st.nextToken()));
        }

        //greedy: combine lo1 with lo2
        long ans = 0;
        for (int i=0;i<N-1;i++){
            int a = pq.poll();
            int b = pq.poll();
            pq.add(a+b);
            ans+=a+b;
        }

        //ret
        out.println(ans);
        out.close();
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

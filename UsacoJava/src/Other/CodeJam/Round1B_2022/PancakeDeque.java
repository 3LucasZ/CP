package Other.CodeJam.Round1B_2022;

import java.io.*;
import java.util.*;

public class PancakeDeque {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup();
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    public static void solve(int tcs) throws IOException {
        System.out.print("Case #" + tcs + ": ");
        if (debug) System.out.println();
        //* parse
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Deque<Integer> deque = new LinkedList<>();
        for (int i=0;i<N;i++) deque.add(Integer.parseInt(st.nextToken()));
        //* SUPER DUPER DUPER GREEDY
        int mx = Integer.MIN_VALUE;
        int ans = 0;
        for (int i=0;i<N;i++){
            int l = deque.getFirst();
            int r = deque.getLast();
            if (l<r){
                deque.pollFirst();
                if (l>=mx) ans++;
                mx = Math.max(mx,l);
            } else {
                deque.pollLast();
                if (r>=mx) ans++;
                mx = Math.max(mx,r);
            }
        }
        //* ret
        System.out.println(ans);
    }


    static BufferedReader br;
    static PrintWriter out;

    public static void setup() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
}
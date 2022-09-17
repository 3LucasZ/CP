package Codeforces.PickNPlay;

import java.io.*;
import java.util.*;

public class GoodPairs {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }

    public static void solve() throws IOException {
        int N = Integer.parseInt(br.readLine());
        Pair[] A = new Pair[N+1];
        A[0] = new Pair(-1000, -1000);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++)A[i]=new Pair(Integer.parseInt(st.nextToken()),i);
        Arrays.sort(A,(a,b)->a.value-b.value);
        out.println(A[1].index+" "+A[N].index);
    }

    private static class Pair {
        int value;
        int index;
        public Pair(int v, int i){
            value=v;
            index=i;
        }
    }
}

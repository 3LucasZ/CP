package Codeforces.Round773;

import java.io.*;
import java.util.*;

public class GreatSequence {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }

    public static void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long X = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Multiset ms = new Multiset();
        for (int i=0;i<N;i++) ms.add(Integer.parseInt(st.nextToken()));
        int ans = 0;
        while (!ms.ms.isEmpty()) {
            long first = ms.ms.firstKey();
            ms.remove(first);
            long second = first*X;
            if (ms.ms.containsKey(second)){
                ms.remove(second);
            }
            else {
                ans++;
            }
        }
        out.println(ans);
    }

    private static class Multiset {
        TreeMap<Long, Integer> ms = new TreeMap<>();
        public void add(long num){
            if (!ms.containsKey(num)) ms.put(num, 0);
            ms.put(num, ms.get(num)+1);
        }
        public void remove(long num){
            ms.put(num, ms.get(num)-1);
            if (ms.get(num)==0) ms.remove(num);
        }
    }
}

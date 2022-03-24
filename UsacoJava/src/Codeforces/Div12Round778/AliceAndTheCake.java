package Codeforces.Div12Round778;

import java.io.*;
import java.util.*;
public class AliceAndTheCake {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) out.println(solve());
        out.close();
    }

    static boolean lose;
    static Multiset ms;

    public static String solve() throws IOException {
        //parse
        int N = Integer.parseInt(br.readLine());
        long S = 0;
        ms = new Multiset();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            int num = Integer.parseInt(st.nextToken());
            ms.add(num);
            S+=num;
        }

        //binning
        lose = false;
        bin(S);
        return lose ? "NO":"YES";
    }

    public static void bin(long num){
        if (lose) return;
        if (ms.contains(num)) {
            ms.remove(num);
            return;
        }

        if (num==1) {
            lose=true;
            return;
        }

        bin(num/2);
        bin((num+1)/2);
    }

    private static class Multiset {
        HashMap<Long, Integer> freqs = new HashMap<>();

        boolean contains(long num) {
            return freqs.containsKey(num);
        }

        void add(long num){
            if (!contains(num)) freqs.put(num,0);
            freqs.put(num,freqs.get(num)+1);
        }

        void remove(long num){
            freqs.put(num,freqs.get(num)-1);
            if (freqs.get(num)==0) freqs.remove(num);
        }
    }
}
package Contests.Codeforces.Round797;

import java.io.*;
import java.util.*;

public class PriceMaximization {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            solve();
        }
        out.close();
    }
    public static void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long ret = 0;
        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> mod = new ArrayList<>();
        for (int i=0;i<N;i++) {
            int next = Integer.parseInt(st.nextToken());
            mod.add(next%K);
            ret+=next/K;
        }

        Collections.sort(mod);
        int l=0;
        int r=N-1;
        for (int i=0;i<mod.size()/2;i++){
            if (mod.get(l)+mod.get(r)>=K){
                ret++;
                l++;r--;
            } else {
                l+=2;
            }
        }

        out.println(ret);
    }
}

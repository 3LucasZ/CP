package Other.Codeforces.Round797;

import java.io.*;
import java.util.*;

public class BlackAndWhiteStripes {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }
    public static void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] white = new int[N+1];
        String str = br.readLine();
        for (int i=0;i<N;i++){
            white[i+1]=white[i];
            if(str.charAt(i)=='W')white[i+1]++;
        }
        if (debug) System.out.println(Arrays.toString(white));

        int ans = Integer.MAX_VALUE;
        for (int i=0;i+K<=N;i++){
            ans = Math.min(ans,white[i+K]-white[i]);
        }
        out.println(ans);
    }
}

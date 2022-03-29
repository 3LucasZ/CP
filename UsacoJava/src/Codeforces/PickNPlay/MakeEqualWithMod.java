package Codeforces.PickNPlay;

import java.io.*;
import java.util.*;

public class MakeEqualWithMod {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) out.println(solve());
        out.close();
    }

    public static String solve() throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean has0=false;
        boolean has1=false;
        for (int i=0;i<N;i++) {
            A[i]=Integer.parseInt(st.nextToken());
            if (A[i]==0) has0=true;
            if (A[i]==1) has1=true;
        }
        if (!has1) return "YES";

        Arrays.sort(A);
        for (int i=0;i<N-1;i++) if (A[i+1]-A[i]==1) return "NO";
        return "YES";
    }
}

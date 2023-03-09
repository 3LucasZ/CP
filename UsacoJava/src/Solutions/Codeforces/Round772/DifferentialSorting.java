package Solutions.Codeforces.Round772;

import java.io.*;
import java.util.*;

public class DifferentialSorting {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }

    public static void solve() throws IOException {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //0 ops case
        int i;
        for (i=0;i<N-1;i++){
            if (arr[i+1]<arr[i]) {
                break;
            }
        }
        if (i==N-1) {out.println(0); return;}

        //bad ending case
        if (arr[N-1]<arr[N-2] || arr[N-1]<0) {out.println(-1); return;}

        //reg
        out.println(N-2);
        for (i=N-3;i>=0;i--){
            out.println((i+1)+" "+(i+2)+" "+(N));
        }
    }
}

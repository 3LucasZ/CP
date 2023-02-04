package Other.Codeforces.Round824;

import java.io.*;
import java.util.*;

/*
PROB: TeaWithTangerines
LANG: JAVA
*/
public class TeaWithTangerines {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup("TeaWithTangerines");
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    public static void solve(int tcs) throws IOException {
        if (debug) System.out.println("Case: " + tcs);
        //* parse
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) A[i]=Integer.parseInt(st.nextToken());
        //* sort (convenient)
        Arrays.sort(A);
        if (debug) System.out.println(Arrays.toString(A));
        //* greedy
        int ans = 0;
        for (int i=1;i<N;i++){
            int lo=1; int hi=A[i];
            while (lo<hi){
                int mid = (lo+hi)/2;
                if ((A[i]+mid-1)/mid<2*A[0])hi=mid;
                else lo=mid+1;
            }
            ans+=lo-1;
        }
        out.println(ans);
    }


    static BufferedReader br;
    static PrintWriter out;

    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName + ".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}
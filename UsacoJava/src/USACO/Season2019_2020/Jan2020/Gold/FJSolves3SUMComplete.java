package USACO.Season2019_2020.Jan2020.Gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FJSolves3SUMComplete {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int Q;

    static int[] A;
    static int[][] triples;
    static long[][] ans;

    static int[] cnt;
    static int MID = (int)1e6;

    public static void main(String[] args) throws IOException {
        setup("threesum");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        A = new int[N];
        for (int i=0;i<N;i++) A[i]=Integer.parseInt(st.nextToken());

        //preprocess
        triples = new int[N][N];
        ans = new long[N][N];
        cnt = new int[MID*2+1];

        //gen triples
        for (int l=0;l<N;l++){
            //updates
            for (int r=l+2;r<N;r++){
                cnt[MID+A[r-1]]++;
                if (Math.abs(A[l]+A[r])<=MID) triples[l][r]=cnt[MID-A[l]-A[r]];
            }
            //clean up
            for (int r=l+2;r<N;r++){
                cnt[MID+A[r-1]]--;
            }
        }
        if (debug){
            for (int l=0;l<N;l++) System.out.println(Arrays.toString(triples[l]));
        }

        //gen ans
        for (int l=N-1;l>=0;l--){
            for (int r=l+2;r<N;r++){
                if (r-l+1==3) ans[l][r]=triples[l][r];
                else {
                    ans[l][r]=triples[l][r]+ans[l+1][r]+ans[l][r-1]-ans[l+1][r-1];
                }
            }
        }

        for (int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            out.println(ans[l-1][r-1]);
        }
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

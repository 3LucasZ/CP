package USACOGuide.UsacoGuideGold.DP;

import java.io.*;
import java.util.*;

public class FJSolves3SUMSubtask {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int Q;

    static int[] A;
//    static int[][] pairSumCnt;
//
//    static int LEAST = -(int)1e6;
//    static int MAX = (int)1e6;
//    static int MIDPOINT = (int)1e6;
    static HashMap<Integer, HashMap<Integer, Integer>> pairSumCnt;
    public static void main(String[] args) throws IOException {
        setup("threesum");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        A = new int[N];
        for (int i=0;i<N;i++) A[i]=Integer.parseInt(st.nextToken());

        //preprocess O(N^3)
        //ans[left_i][right_i]
        long[][] ans = new long[N][N];
        //pairSumCnt[sum][leftmost_i]
        //pairSumCnt = new int[MIDPOINT*2+1][N];
        pairSumCnt = new HashMap<>();
        for (int r=1;r<N;r++){
            //gen ans for [l][r]
            long runAns = 0;
            int matchPairSum = -A[r];
            for (int l=r-2;l>=0;l--){
                if (pairSumCnt.containsKey(matchPairSum)&&pairSumCnt.get(matchPairSum).containsKey(l))
                    runAns += pairSumCnt.get(matchPairSum).get(l);
                ans[l][r]=ans[l][r-1]+runAns;
            }
            //gen new pairs with l,r
            for (int l=0;l<r;l++){
                int newPairSum = A[l]+A[r];
                if (!pairSumCnt.containsKey(newPairSum)) pairSumCnt.put(newPairSum, new HashMap<>());
                if (!pairSumCnt.get(newPairSum).containsKey(l)) pairSumCnt.get(newPairSum).put(l,0);
                pairSumCnt.get(newPairSum).put(l,pairSumCnt.get(newPairSum).get(l)+1);
            }
        }
        if (debug){
            for (int l=0;l<N;l++) System.out.println(l+": "+Arrays.toString(ans[l]));
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

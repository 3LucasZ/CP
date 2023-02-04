package Other.USACO.Season2018_2019.Open2019.Gold;

import java.io.*;
import java.util.StringTokenizer;
/*
USACO 2019 US Open Contest, Gold
Problem 3. Balancing Inversions
Thoughts:
EXTREMELY EXTREMELY DIFFICULT!
14/14 TCS after reading editorial 10x times, few weeks, 2 hours of debug
let delta = inversions1-inversions2
non central swaps contribute +1 or -1 to delta
central swaps contribute oneCount - N to delta if (1,0) -> (0,1)
lets say we are moving 1s from left to right migration
let t be the number of central swaps used
central swaps used == 1s moved from left to right
bash out all ts
t is capped at #1s so O(N) suffices using 2 pointers
 */
public class BalancingInversions {
    static boolean submission = true;
    static boolean debug = false;

    //setup - good
    static int N;
    static int[] A1;
    static int[] A2;
    static int oneCount = 0;

    static long ans = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        //parse - good
        setup("balance");
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A1 = new int[N];
        A2 = new int[N];
        for (int i=0;i<N;i++) {
            A1[i] = Integer.parseInt(st.nextToken());
            if (A1[i]==1) oneCount++;
        }
        for (int i=0;i<N;i++) {
            A2[i] = Integer.parseInt(st.nextToken());
            if (A2[i]==1) oneCount++;
        }

        solve();
        solve2();

        //print - good
        out.println(ans);
        out.close();
    }
    //get inversions - good
    public static long getInversions(int[] arr){
        long ret = 0;
        long ones = 0;
        for (int i=0;i<N;i++){
            if (arr[i]==0){
                ret += ones;
            }
            else {
                ones++;
            }
        }
        return ret;
    }
    public static void solve(){
        //setup - good

        long inversions1 = getInversions(A1);
        long inversions2 = getInversions(A2);
        long delta = inversions1-inversions2;
        long moves = 0;

        //t==0 - good
        ans = Math.min(ans, Math.abs(delta));

        int rightMostOne = N-1;
        int leftMostZero = 0;
        int centralSwap = oneCount-N;

        while (true){
            //move 2 points to prepare for next t - good
            while (rightMostOne>=0&&A1[rightMostOne]==0) rightMostOne--;
            if (rightMostOne<0) break;
            while (leftMostZero<=N-1&&A2[leftMostZero]==1) leftMostZero++;
            if (leftMostZero>N-1) break;

            int leftSteps = N-1-rightMostOne;
            int rightSteps = leftMostZero;

            delta = delta - leftSteps + rightSteps + centralSwap;
            moves = moves + leftSteps + rightSteps + 1;
            ans = Math.min(ans,moves+Math.abs(delta));
            rightMostOne--;
            leftMostZero++;
        }
    }
    public static void solve2(){
        long inversions1 = getInversions(A1);
        long inversions2 = getInversions(A2);
        long delta = inversions1-inversions2;
        long moves = 0;
        //t==0
        ans = Math.min(ans, Math.abs(delta));

        if (debug){
            System.out.println("["+inversions1+", "+inversions2+"]");
        }

        int rightMostZero = N-1;
        int leftMostOne = 0;
        int centralSwap = oneCount-N;

        while (true){
            while (rightMostZero>=0&&A1[rightMostZero]==1) rightMostZero--;
            if (rightMostZero<0) break;
            while (leftMostOne<=N-1&&A2[leftMostOne]==0) leftMostOne++;
            if (leftMostOne>N-1) break;

            int leftSteps = N-1-rightMostZero;
            int rightSteps = leftMostOne;

            delta = delta + leftSteps - rightSteps - centralSwap;
            moves = moves + leftSteps + rightSteps + 1;
            ans = Math.min(ans,moves + Math.abs(delta));
            rightMostZero--;
            leftMostOne++;
        }
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

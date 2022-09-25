package Training.SilverTraining;

import java.io.*;
import java.util.*;
/*
USACO 2014 March Contest, Silver
Problem 3. Mooo Moo
USACO Silver Training
Concepts: Trivial Knapsack DP
 */
public class MoooMoo {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int B;
    static int[] breedNoise;
    static int[] minCowsDP;
    //const
    static final int maxNoise = 100000;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("mooomoo.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("mooomoo.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        breedNoise = new int[N];
        for (int i=0;i<B;i++) {
            breedNoise[i] = Integer.parseInt(br.readLine());
        }
        //logic
        //DP: calculate min cows for each noise between 0 and 100K
        minCowsDP = new int[maxNoise+1];
        Arrays.fill(minCowsDP, -1);
        minCowsDP[0] = 0;
        for (int i=1;i<=maxNoise;i++) {
            int cows = Integer.MAX_VALUE;
            for (int j=0;j<B;j++) {
                int search = i-breedNoise[j];
                if (search >= 0 && minCowsDP[search] != -1) cows = Math.min(minCowsDP[search], cows);
            }
            if (cows != Integer.MAX_VALUE) minCowsDP[i] = cows+1;
        }
        if (!submission) out.println(Arrays.toString(minCowsDP));
        //handle queries
        int tot = 0;
        int prev = 0;
        for (int i=0;i<N;i++) {
            int curr = Integer.parseInt(br.readLine());
            int change = curr-(Math.max(0,prev-1));
            if (!submission) out.println("delta: "+change);
            tot += minCowsDP[change];
            prev=curr;
        }
        out.println(tot);
        out.close();
    }
}

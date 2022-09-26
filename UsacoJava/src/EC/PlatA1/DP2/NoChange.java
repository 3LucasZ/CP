package EC.PlatA1.DP2;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
USACO 2013 November Contest, Gold
Problem 3. No Change
EC 2: Advanced DP - Sliding Window
Thoughts:
sliding window find end[coin][last payment finished]
set dp to find best permutation(coins) -> dp[set]=Math.max(end[coin][set-coin])
 */
public class NoChange {
    static boolean submission = true;
    static boolean debug = false;

    static int K;
    static int[] coin;

    static int N;
    static int[] price;

    public static void main(String[] args) throws IOException {
        //parse
        setup("nochange");
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        coin = new int[K];
        price = new int[N+1];
        for (int i=0;i<K;i++) coin[i]=Integer.parseInt(br.readLine());
        for (int i=1;i<=N;i++) price[i]=Integer.parseInt(br.readLine());

        //sliding window: O(NK)
        int[][] end = new int[K][N+1];
        for (int c=0;c<K;c++){
            int runPrice = 0;
            int pointer = 0;
            for (int p=0;p<=N;p++){
                runPrice-=price[p];
                while (pointer<N && runPrice+price[pointer+1]<=coin[c]){
                    pointer++;
                    runPrice+=price[pointer];
                }
                end[c][p]=pointer;
            }
        }
        if (debug){
            for (int i=0;i<K;i++) System.out.println(Arrays.toString(end[i]));
        }

        int ans = -1;
        //set dp: O(N*2^N)
        int sets = 1<<K;
        int[] dp = new int[sets];
        dp[0]=0;
        for (int s=0;s<sets;s++){
            for (int add=0;add<K;add++){
                int bit = 1<<add;
                if ((s&bit)!=0){
                    dp[s]=Math.max(dp[s],end[add][dp[s^bit]]);
                }
            }

            //update ans
            if (dp[s]==N){
                int leftover = 0;
                for (int c=0;c<K;c++){
                    int bit = 1<<c;
                    if ((s&bit)==0) leftover+=coin[c];
                }
                ans=Math.max(ans,leftover);
            }
        }
        if (debug){
            System.out.println(Arrays.toString(dp));
        }

        //ret
        out.println(ans);
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

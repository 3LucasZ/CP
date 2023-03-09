package Solutions.EC.GoldB2.DP2;
/*
Talent Show
Gold Advanced B 2
DP - Knapsack DP
Inspiration from building sets (Dice Combo 2 style)
Notes:
took a small guess (dreamy about knapsack)
dp on talent, for each talent keep track of the min weight for the talent
loop thru dp and find best ratio
Question: why does this produce the best ratio? Counter example thought:
t: 2, 2
w: 1, 1000
broken with a bit of logic
 */
import java.io.*;
import java.util.*;
public class TalentShow {
    static boolean submission = true;
    static boolean debug = true;

    //input
    static int N;
    static int W;
    static int weight[];
    static int talent[];
    //dp
    static int talsum = 0;
    //talent -> weight
    static int[] dp;
    public static void main(String[] args) throws IOException {
        //parse input
        setup("talent");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        weight = new int[N];
        talent = new int[N];
        W = Integer.parseInt(st.nextToken());
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            talent[i] = Integer.parseInt(st.nextToken());
            talsum += talent[i];
        }
        //setup dp
        dp = new int[talsum+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        //dp
        for (int t=0;t<N;t++){
            for (int x=talsum;x>=0;x--) {
                if (dp[x]!=-1) {
                    if (dp[x+talent[t]]==-1) dp[x+talent[t]] = dp[x]+weight[t];
                    else dp[x+talent[t]] = Math.min(dp[x+talent[t]],dp[x]+weight[t]);
                }
            }
        }
        if (!submission) System.out.println(Arrays.toString(dp));
        //print answer
        double answer = 0;
        for (int i=1;i<=talsum;i++) {
            if (dp[i]>=W) answer = Math.max(answer, (double)i/dp[i]);
        }
        out.println((int)(answer * 1000));
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

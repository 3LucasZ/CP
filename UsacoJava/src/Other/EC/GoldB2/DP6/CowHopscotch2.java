package Other.EC.GoldB2.DP6;

import java.io.*;
import java.util.*;
/*
Cow Hopscotch 2
Gold Advanced B 6
DP - counting
Strategies: presum, pigeonhole optimizer, hashmap, update AFTER finished row, 1-index, implementation, complementary... etc
Thoughts:
Very nice problem, although listened and looked at huge hints
2dpresum to keep track of sum[r-1][c-1]
dp[col] arr of hashmaps to subtract dp[1..c-1].get(color)
update dp[col] after row finished
:)
 */
public class CowHopscotch2 {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    static boolean debug = false;

    //param
    static int R;
    static int C;
    static int K;
    static int[][] color;
    static long[][] presum;

    //const
    static final long MOD = (long)1e9+7;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        color = new int[R+1][C+1];
        for (int r=1;r<=R;r++){
            st = new StringTokenizer(br.readLine());
            for (int c=1;c<=C;c++){
                color[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        //logic: dp
        //init
        HashMap<Integer, Long>[] dp = new HashMap[C+1];
        presum = new long[R+1][C+1];
        for (int c=0;c<=C;c++) dp[c] = new HashMap<>();

        presum[1][1]=1;

        //dp
        int[] newKey = new int[C+1];
        long[] newValue = new long[C+1];
        for (int r=1;r<=R;r++){
            for (int c=1;c<=C;c++){
                if (r==1 && c==1) continue;
                long tot = presum[r-1][c-1];
                for (int c2=1;c2<=c-1;c2++){
                    if (dp[c2].containsKey(color[r][c])) {
                        tot = (tot - dp[c2].get(color[r][c]))%MOD;
                    }
                }
                tot = (tot+MOD)%MOD;
                presum[r][c]=(presum[r-1][c]+presum[r][c-1]-presum[r-1][c-1]+tot)%MOD;

                newKey[c] = color[r][c];
                if (!dp[c].containsKey(color[r][c])) dp[c].put(color[r][c], 0L);
                newValue[c] = (dp[c].get(color[r][c])+tot)%MOD;
            }
            if (r==1) {
                newKey[1]=color[1][1];
                newValue[1]=1;
            }
            for (int c=1;c<=C;c++){
                dp[c].put(newKey[c], newValue[c]);
            }
        }
        if (debug){
            for (int r=0;r<=R;r++){
                for (int c=0;c<=C;c++){
                    out.print(presum[r][c]);
                }
                out.println();
            }
            out.println();

            for (int r=0;r<=R;r++){
                for (int c=0;c<=C;c++){
                    out.print(dp[c]);
                }
                out.println();
            }
            out.println();
        }
        //turn in answer
        out.println((presum[R][C]-presum[R-1][C]-presum[R][C-1]+presum[R-1][C-1]+MOD)%MOD);
        out.close();
    }
}

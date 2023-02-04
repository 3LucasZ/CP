package Other.EC.GoldB2.DP4;

import java.io.*;
import java.util.*;
/*
Grazing 2
Gold Advanced B 4
DP - "regular move + special move with limited special moves" type
Observations:
Each cow must be placed (S-1)/(N-1) or (S-1)/(N-1)+1 from the last
The amount of (S-1)/(N-1)+1 cows you can have is equal to (S-1)%(N-1), call these deviations
from state dp[on cow][devs] you can get dp[on cow+1][devs] and dp[on cow+1][devs+1]
we do this incremental propagation until dp is filled
the answer will always be at dp[N][DD]
elegant wordy problem -> move and special move -> incremental dp
 */
public class Grazing2 {
    //io
    static boolean submission = false;
    static boolean debug = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int S;
    static int loc[];
    //dp
    static int D;
    static int DD;
    //dp[cow][deviations]
    static int dp[][];
    //const
    static int INF = Integer.MAX_VALUE;
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
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        loc = new int[N];
        for (int i=0;i<N;i++) {
            loc[i] = Integer.parseInt(br.readLine());
        }
        //sort loc
        Arrays.sort(loc);
        //find D and DD
        D = (S-1)/(N-1);
        DD = (S-1)%(N-1);
        //logic (dp)
        dp = new int[N][DD+2];
        for (int r=0;r<N;r++){
            for (int c=0;c<=DD+1;c++){
                dp[r][c] = INF;
            }
        }
        if (debug){
            System.out.println(Arrays.toString(loc));
            System.out.println(D);
            System.out.println(DD);
            printDP();
        }
        //start with base case dp[0][0]
        dp[0][0] = loc[0]-1;
        //given dp[a][b] we can easily find dp[a+1][b] and dp[a+1][b+1];
        for (int cow=0;cow<N-1;cow++){
            for (int deviations=0;deviations<=DD;deviations++){
                int locV;
                locV = deviations*(D+1)+(cow+1-deviations)*D+1;
                if (dp[cow][deviations]!=INF) dp[cow+1][deviations] = Math.min(dp[cow+1][deviations], dp[cow][deviations] + Math.abs(locV-loc[cow+1]));
                locV = (deviations+1)*(D+1)+(cow-deviations)*D+1;
                if (dp[cow][deviations]!=INF) dp[cow+1][deviations+1] = Math.min(dp[cow+1][deviations+1], dp[cow][deviations] + Math.abs(locV-loc[cow+1]));
            }
        }
        //turn in answer
        if (debug) printDP();
        out.println(dp[N-1][DD]);
        out.close();
    }
    public static void printDP(){
        for (int i=0;i<N;i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
    }
}

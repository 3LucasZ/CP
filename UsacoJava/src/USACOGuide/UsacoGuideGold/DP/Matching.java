package USACOGuide.UsacoGuideGold.DP;

import java.io.*;
import java.util.*;

public class Matching {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    //pair[r=m][c=w]
    static boolean[][] pair;
    static ArrayList<Integer>[] dig;

    static long MOD = (long) (1e9)+7;
    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        N = Integer.parseInt(br.readLine());
        pair = new boolean[N][N];
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++){
                pair[i][j]=Integer.parseInt(st.nextToken())==1;
            }
        }

        //create dig precomp
        dig = new ArrayList[N+1];for (int i=0;i<=N;i++) dig[i] = new ArrayList<Integer>();
        for (int set=0;set<(1<<N);set++){
            int cnt = 0;
            for (int i=0;i<N;i++) if ((set&(1<<i))!=0) cnt++;
            dig[cnt].add(set);
        }
        //if (debug) for (int i=0;i<=N;i++) System.out.println(dig[i]);

        //bitset dp
        int sets = 1<<N;
        //dp[1...m][taken w set]
        long[] dp = new long[sets];
        dp[0]=1;

        for (int i=0;i<N;i++){
            for (int set : dig[i+1]){
                for (int j=0;j<N;j++){
                    int bit = 1<<j;
                    if (pair[i][j] && (set&bit)!=0){
                        dp[set]=(dp[set]+dp[set^bit])%MOD;
                    }
                }
            }
        }

        //ret
        out.println(dp[sets-1]);
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

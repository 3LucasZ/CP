package TrainingGateway.Chapter4;

import java.io.*;
import java.math.BigInteger;
import java.util.*;
/*
PROB: buylow
LANG: JAVA
*/
public class buylow {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int[] S;

    public static void main(String[] args) throws IOException {
        //parse
        setup("buylow");
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = new int[N+2]; S[0]=Integer.MAX_VALUE; S[N+1]=Integer.MIN_VALUE;
        for (int i=1;i<=N;i++){
            if (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            S[i]=Integer.parseInt(st.nextToken());
        }
        //LDS dp
        int[] dp = new int[N+2];
        for (int i=1;i<=N+1;i++) {
            for (int j = 0; j < i; j++) {
                if (S[j] > S[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        //find seqs
        BigInteger[] seqs = new BigInteger[N+2]; seqs[0]=BigInteger.valueOf(1); for (int i=1;i<=N+1;i++) seqs[i]=BigInteger.valueOf(0);
        for (int i=1;i<=N+1;i++){
            HashSet<Integer> visited = new HashSet<>();
            for (int j=i-1;j>=0;j--){
                if (dp[j]==dp[i]-1&&S[j]>S[i]&&!visited.contains(S[j])) {
                    seqs[i]=seqs[i].add(seqs[j]);
                    visited.add(S[j]);
                }
            }
        }

        //ret
        out.println(dp[N+1]-1+" "+seqs[N+1]);
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
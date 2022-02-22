package Codeforces.Div2Round772;

import java.io.*;
import java.util.*;
/*
Codeforces Round #772 (Div. 2)
D. Infinite Set
Thoughts:
Looked at solution
2 step problem

1. useful number storage
can number j be generated from number i using 2i+1 and 4i?
if so, it is useless
store useful numbers in treeset
O(NlogAlogN)

2. dp
dp[i]=dp[i-1]+dp[i-2]+usefulWithLen[i]
O(P)
 */
public class InfiniteSet {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;
    //param
    static TreeSet<Integer> useful = new TreeSet<>();
    static int[] all;
    static int N;
    static int P;
    //const
    static final long MOD=(long)1e9+7;
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        all = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            all[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(all);
        if (debug) System.out.println(Arrays.toString(all));

        //logic
        //create useful treeSet
        for (int i=0;i<N;i++){
            if (isUseful(all[i])) useful.add(all[i]);
        }
        if (debug) System.out.println(useful);

        //fill this
        int[] usefulWithLen = new int[Math.max(40,P+1)];
        for (int u : useful){
            usefulWithLen[size(u)]++;
        }

        if (debug) System.out.println(Arrays.toString(usefulWithLen));

        //do dp: dp[i]=dp[i-1]+dp[i-2]+usefulWithLen[i]
        long[] dp = new long[P+1];
        dp[1]=usefulWithLen[1];
        if (P>1) dp[2]=usefulWithLen[2]+dp[1];
        for (int i=3;i<=P;i++){
            dp[i]=(dp[i-1]+dp[i-2]+usefulWithLen[i])%MOD;
        }

        //turn in answer
        if (debug) System.out.println(Arrays.toString(dp));
        long ans=0;
        for (int i=1;i<=P;i++)ans=(ans+dp[i])%MOD;
        out.println(ans);
        out.close();
    }
    static boolean isUseful(int num){
        while (num > 0){
            if (num%2==1){
                num>>=1;
            }
            else if (num%4==0){
                num>>=2;
            }
            else {
                break;
            }
            if (useful.contains(num)) return false;
        }
        return true;
    }
    static int size(int num){
        return (int)(Math.log(num)/Math.log(2)+1);
    }
}

package Codeforces.Round833;

import java.io.*;
import java.util.*;

/*
PROB: ZeroSumPrefixes
LANG: JAVA
*/
public class ZeroSumPrefixes {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup("ZeroSumPrefixes");
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    static int N;
    static Integer[] A;
    static Long[] presum;

    public static void solve(int tcs) throws IOException {
        if (debug) System.out.println("Case: " + tcs);
        //* parse
        N = Integer.parseInt(br.readLine());
        A = new Integer[N];
        presum = new Long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) A[i]=Integer.parseInt(st.nextToken());
        presum[0]= Long.valueOf(A[0]);
        for (int i=1;i<N;i++) presum[i]=presum[i-1]+A[i];
        Collections.reverse(Arrays.asList(A));
        Collections.reverse(Arrays.asList(presum));
        if (debug) {
            System.out.println("A:"+Arrays.toString(A));
            System.out.println("presum:"+Arrays.toString(presum));
        }
        //* cnt same per seg
        HashMap<Long, Integer> freq = new HashMap<>();
        int ans =0;
        int mostFreq = 0;
        for (int i=0;i<N;i++){
            //add new element non0
            if (!freq.containsKey(presum[i]))freq.put(presum[i],0);
            freq.put(presum[i],freq.get(presum[i])+1);
            mostFreq=Math.max(mostFreq,freq.get(presum[i]));
            //process 0
            if (A[i]==0){
                ans+=mostFreq;
                mostFreq=0;
                freq = new HashMap<>();
            }
        }
        //* cnt prefix with 0
        for (int i=N-1;i>=0;i--){
            if (A[i]==0) break;
            if (presum[i]==0) ans++;
        }
        //* ret
        out.println(ans);
    }


    static BufferedReader br;
    static PrintWriter out;

    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName + ".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}
package EC.SilverPS;

import java.io.*;
import java.util.*;

public class MaximumGCD {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static long[] arr;
    static long[] preSumF;
    static long[] preSumB;
    static boolean submission = false;
    public static void main(String[] args) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader("maxgcd.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("maxgcd.out")));
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        arr = new long[N];
        preSumF = new long[N];
        preSumB = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        //logic
        for (int i=1;i<N;i++){
            preSumF[i] = GCD(preSumF[i-1],arr[i-1]);
        }
        for (int i=N-2;i>=0;i--) {
            preSumB[i]=GCD(arr[i+1],preSumB[i+1]);
        }
//        out.println(Arrays.toString(arr));
//        out.println(Arrays.toString(preSumF));
//        out.println(Arrays.toString(preSumB));
        long ans = 0;
        for (int i=0;i<N;i++) {
            ans = Math.max(GCD(preSumF[i],preSumB[i]),ans);
        }
        //turn in answer
        out.println(ans);
        out.close();
    }
    public static long GCD(long a, long b){
        while (a > 0 && b > 0) {
            if (a > b) {
                a = a % b;
            } else {
                b = b % a;
            }
        }
        return Math.max(a,b);
    }
}

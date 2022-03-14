import java.io.*;
import java.util.*;

public class Odometer {
    //io
    static boolean submission = false;
    static boolean debug = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int A;
    static int[] X;
    static int B;
    static int[] Y;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str1 = st.nextToken();
        A = str1.length();
        String str2 = st.nextToken();
        B = str2.length();
        X = new int[A];
        Y = new int[B];
        for (int i=0;i<A;i++){
            X[i] = str1.charAt(i)-48;
        }
        for (int i=0;i<B;i++){
            Y[i] = str2.charAt(i)-48;
        }
        if (debug) {
            System.out.println(Arrays.toString(X));
            System.out.println(Arrays.toString(Y));
        }
    }
    public static long specialLeq(int[] arr){
        long ans = 0;
        for (int tar=0;tar<10;tar++){
            ans += G1(arr, tar);
        }

        for (int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                if (i==j) continue;
                ans -= G2(arr, i, j);
            }
        }
        return 0;
    }

    public static long G1(int[] arr, int tar){
        int N = arr.length;
        //dp[dig][freq][leq][fir]
        long dp[][][][] = new long[N+1][50][2][2];
        dp[0][25][0][1] = 1;
        for (int dig=0;dig<N;dig++){
            for (int freq=0;freq<=49;freq++){
                for (int add=0;add<10;add++){
                    if (add==tar){

                    }
                    //freq+1, leq, fir
                    dp[dig+1][freq+1][1][1] += dp[dig][freq][1][0];
                   // dp[dig+1][freq+1][1][1] += dp[dig][freq+1][]
                }
            }
        }
        return 0;
    }

    public static long G2(int[] arr, int i, int j){
        return 0;
    }
}

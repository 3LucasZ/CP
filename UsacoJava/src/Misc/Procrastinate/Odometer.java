package Misc.Procrastinate;

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
        //dp[dig][und][freq][is0]
        int N = arr.length;
        long dp[][][][] = new long[N+1][2][50][2];
        dp[0][25][0][1] = 1;
        for (int dig=0;dig<N;dig++){
            for (int freq=0;freq<=49;freq++){
                for (int nxt=0;nxt<10;nxt++){
                    for (int und=0;und<2;und++){
                        for (int is0=0;is0<2;is0++){
                            //not under, next > cap
                            if (und==0 && nxt > arr[dig+1]) continue;

                            //adding zero to already zero
                            if (is0==1 && nxt == 0) {
                                dp[dig+1][und][freq][is0]+=dp[dig][und][freq][is0];
                                continue;
                            }

                            //not under, next < cap
                            if (und==0 && nxt<arr[dig]) {
                                //tar add
                                if (nxt==tar) dp[dig+1][1][freq+1][is0]+=dp[dig][0][freq][is0];
                                else dp[dig+1][1][freq][is0]+=dp[dig][0][freq][is0];
                            }

                            // under
                            if (und==1){
                                //tar add
                                //if (nxt==tar) dp[dig+1][1][freq+1][is0]+=dp[dig][1]
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    public static long G2(int[] arr, int i, int j){
        return 0;
    }
}

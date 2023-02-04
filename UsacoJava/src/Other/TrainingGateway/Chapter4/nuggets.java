package Other.TrainingGateway.Chapter4;

import java.io.*;
/*
PROB: nuggets
LANG: JAVA
 */
public class nuggets {
    static boolean submission = true;
    static boolean debug = true;

    static int N;
    static int[] A;
    static boolean[] dp;
    static int row = 0;

    static int MAX = 256;

    public static void main(String[] args) throws IOException {
        //parse
        setup("nuggets");
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        int gcd = 0;
        for (int i=0;i<N;i++){
            A[i]=Integer.parseInt(br.readLine());
            if (gcd==0) gcd=A[i];
            else gcd=gcd(gcd,A[i]);
        }

        //if all of A[i] are collectively not relatively prime, then the answer is infinity
        if (gcd!=1) {
            out.println(0);
        } else {
            //dp
            int last = 0;
            dp = new boolean[MAX*MAX+1]; dp[0]=true;
            for (int i=0;i<=MAX*MAX;i++){
                if (dp[i]) {
                    for (int num : A){
                        int amt = i+num;
                        if (amt<dp.length)dp[amt]=true;
                    }
                } else last=i;
            }
            out.println(last);
        }

        out.close();
    }
    public static int gcd(int a, int b){
        if (b==0) return a;
        return gcd(b,a%b);
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

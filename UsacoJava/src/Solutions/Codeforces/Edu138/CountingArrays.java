package Solutions.Codeforces.Edu138;

import java.io.*;
import java.util.*;
/*
PROB: CountingArrays
LANG: JAVA
*/
public class CountingArrays {
    static boolean submission = false;
    static boolean debug = false;

    static int N; //array size 1..N
    static long M; //pick numbers 1..M
    static long MOD = 998244353;
    static int MAXPRIME = 1000;
    public static void main(String[] args) throws IOException {
        //*parse
        setup("CountingArrays");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        //*find tot = M^1+M^2+...+M^N
        long tot = 0;
        long run = 1;
        for (int i=0;i<N;i++) {
            run=(run*(M%MOD))%MOD;
            tot=(tot+run)%MOD;
        }
        if (debug) System.out.println("tot: "+tot);
        //*gen list of primes (~10K)
        ArrayList<Integer> primes = new ArrayList<>(); primes.add(2);
        for (int i=3;i<=Math.min(MAXPRIME,N);i++){
            boolean isPrime = true;
            for (int prime : primes) if (i%prime==0) isPrime=false;
            if (isPrime) primes.add(i);
        }
        if (debug) System.out.println("Primes: "+primes);
        //*find for each i in 2..N how many numbers in 1..M are divisible by all primes in range 2..i
        long[] div = new long[N+1]; Arrays.fill(div,1);
        for (int prime : primes){
            div[prime]*=prime;
        }
        for (int i=2;i<=N;i++) {
            div[i]=(div[i]*div[i-1]);
            //after this threshold, all div[i] will be big enough that M/div[i] is just 0
            if (div[i]>M) {
                div[i]=0;
                break;
            }
        }
        if (debug) System.out.println("Div: "+Arrays.toString(div));
        //find complement of tot
        long cmp = 0;
        run = 1;
        for (int i=1;i<=N;i++){
            if (div[i]==0) break;
            run=(((M/div[i])%MOD)*run)%MOD;
            cmp=(cmp+run)%MOD;
        }
        //ret
        long ans = (tot-cmp+MOD)%MOD;
        out.println(ans);
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
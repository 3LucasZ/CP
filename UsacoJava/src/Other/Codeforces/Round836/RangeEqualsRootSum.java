package Other.Codeforces.Round836;

import java.io.*;

/*
PROB: RangeEqualsRootSum
LANG: JAVA
*/
public class RangeEqualsRootSum {
    static boolean submission = false;
    static boolean debug = false;

    static long MAXRANGE = 17320510;
    static long MAXA = (long)1e9;
    public static void main(String[] args) throws IOException {
        //* TCS
        setup("RangeEqualsRootSum");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve(i);
        out.close();
    }

    static int N;
    public static void solve(int t) throws IOException {
        if (debug) System.out.println("Test case: "+t);
        //* parse
        N = Integer.parseInt(br.readLine());
        //brute force ranges (b-a)
        for (int range=N-1;range<=MAXRANGE;range++){
            //bin srch first a where (1 to N-2)SUM(a+i) <= range^2 - (range+2a)
            long lo=1;long hi=MAXA;
            while (lo<hi){
                long aTry = (lo+hi+1)/2;
                long bTry = range+aTry;
                if (minK(aTry)<=getK(aTry,bTry)) lo=aTry;
                else hi=aTry-1;
            }
            //eval a,b,k
            long a = lo;
            long b = range+a;
            long k = getK(a,b);
            //good a,b,k is found
            if (minK(a)<=k && k<=maxK(b)) {
                long[] ans = new long[N];
                ans[0]=a; ans[N-1]=b; for (int i=1;i<=N-2;i++) ans[i]=a+i;
                long diff = k-minK(a);
                if (diff!=0) {
                    for (int i = 1; i <= N - 2; i++) ans[i] += diff / (N - 2);
                    for (int i = N - 2; i >= N - 2 - diff % (N - 2)+1; i--) ans[i]++;
                }
                for (int i=0;i<N;i++) out.print(ans[i]+" ");
                out.println();
                return;
            }
        }
    }
    public static long getK (long a, long b){
        return (b-a)*(b-a)-(a+b);
    }
    public static long minK (long a){
        return (2*a+N-1)*(N-2)/2;
    }
    public static long maxK (long b){
        return (2*b-N+1)*(N-2)/2;
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
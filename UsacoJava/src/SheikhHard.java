import java.io.*;
import java.util.*;

/*
PROB: SheikhHard
LANG: JAVA
*/
public class SheikhHard {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup("SheikhHard");
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    static int N;
    static int L,R;
    static int Q;
    static int[] A;
    static long[] presum;
    static long[] prexor;

    public static void solve(int tcs) throws IOException {
        if (debug) System.out.println("Case: " + tcs);
        //* parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        A = new int[N+1];
        for (int i=1;i<=N;i++) A[i]=Integer.parseInt(st.nextToken());
        //* precomp
        presum = new long[N+1]; for (int i=1;i<=N;i++) presum[i]=presum[i-1]+A[i];
        prexor = new long[N+1]; for (int i=1;i<=N;i++) prexor[i]=prexor[i-1]^A[i];
        //* solve queries online
        for (int i=1;i<=Q;i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            long tar = eval(l,r);
            //find lNew, rNew
        }
        long tar=0;
        //* find the interval with minimum length using bin search
        int lo=0;int hi=R-L;
        ansL=0; ansR=0;
        while (lo<hi){
            int mid = (lo+hi)/2;
            if (trySize(mid,tar)) hi=mid;
            else lo=mid+1;
        }
        //* ret
        trySize(lo,tar);
        out.println(ansL+" "+ansR);
    }
    static int ansL = 0;
    static int ansR = 0;
    public static boolean trySize(int sz, long tar){
        for (int l=L;l+sz<=R;l++){
            if (eval(l,l+sz)==tar) {
                ansL=l;
                ansR=l+sz;
                return true;
            }
        }
        return false;
    }
    public static long eval(int l, int r){
        return (presum[r]-presum[l-1])-(prexor[r]^prexor[l-1]);
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
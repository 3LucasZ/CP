package Other.Codeforces.Round816;

import java.io.*;
import java.util.*;
/*
PROB: Monoblock
LANG: JAVA
*/
public class Monoblock {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int M;
    static long[] AA;
    static long[] A;

    public static void main(String[] args) throws IOException {
        //* parse
        setup("Monoblock");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        AA = new long[N+1];
        for (int i=1;i<=N;i++){
            AA[i] = Integer.parseInt(st.nextToken());
        }
        //* find init ans
        long ans = (long) N *(N+1)/2;
        A = new long[N+1]; for (int i=1;i<=N;i++) A[i]=1;
        for (int i=1;i<=N;i++){
            ans+=query(i,AA[i]);
        }
        if (debug) System.out.println("init: "+ans);
        //* handle queries
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            long x = Integer.parseInt(st.nextToken());
            ans+=query(p,x);
            out.println(ans);
        }
        //cleanup
        out.close();
    }
    public static long query(int p, long x){
        long ret = 0;
        //p-1 and p now match
        if (p-1>=1&&A[p]!=A[p-1]&&x==A[p-1])ret-=f(p);
        //p and p+1 now match
        if (p+1<=N&&A[p]!=A[p+1]&&x==A[p+1])ret-=f(p+1);
        //p-1 and p now DONT match
        if (p-1>=1&&A[p]==A[p-1]&&x!=A[p-1])ret+=f(p);
        //p and p+1 now DONT match
        if (p+1<=N&&A[p]==A[p+1]&&x!=A[p+1])ret+=f(p+1);
        //change
        A[p]=x;
        return ret;
    }
    public static long f(int p){
        return (long) (N - p + 1) *(p-1);
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
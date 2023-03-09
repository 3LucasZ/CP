package Solutions.Codeforces.Edu135;

import java.io.*;
import java.util.*;
/*
PROB: RedBlackPepper
LANG: JAVA
*/
public class RedBlackPepper {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N; //dishes
    static long[] A; //red pepper
    static long[] B; //black pepper
    static long redSum;
    static Long[] C; //we start with red peppering everything, C is how much taste we get switching red to black and is B-A
    static Long[] CSum;
    static int Q; //shop queries
    static int tar; //the point where the best taste is gotten :)

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        A = new long[N];
        B = new long[N];
        for (int i=0;i<N;i++){
            int a = io.nextInt();
            int b = io.nextInt();
            A[i]=a;
            B[i]=b;
        }
        redSum = 0; for (int i=0;i<N;i++) redSum+=A[i];
        //how much we get by taking black instead of red pepper, sorted from most to least
        //in our queries we'll take a contiguous array from the beginning to maximize the sum
        C = new Long[N];
        for (int i=0;i<N;i++) C[i]=B[i]-A[i];
        Arrays.sort(C,Comparator.comparingLong(a->-a));
        for (int i=0;i<N;i++){
            if (C[i]<0) break;
            tar=i;
        }
        CSum = new Long[N];
        CSum[0]=C[0]; for (int i=1;i<N;i++) CSum[i]=CSum[i-1]+C[i];
        if (debug){
            io.println("redSum: "+redSum);
            io.println("C: "+Arrays.toString(C));
            io.println("CSum: "+Arrays.toString(CSum));
            io.println("tar: "+tar);
        }
        //* handle queries
        Q = io.nextInt();
        for (int i=0;i<Q;i++){
            int a = io.nextInt();
            int b = io.nextInt();
            io.println(solve(a,b));
        }
    }
    public static long solve(int a, int b){
        EGCD egcd = new EGCD(a,b);
        if (N%egcd.gcd!=0) return -1;
        //initial solution to ax+by=N
        long by = b*egcd.y*N/egcd.gcd;
        //incr
        long byi = egcd.yi*b;
        if(debug){
            System.out.println(a+"x"+egcd.x+" + "+b+"x"+egcd.y+" = "+egcd.gcd);
            System.out.println("generator: "+egcd.xi+", "+egcd.yi);
        }
        //binary search until by+byi is really close to the left of tar (l)
        //other possible solution would be r = l+byi
        int lo=-N;
        int hi=N;
        while (lo<hi){
            int mid = (lo+hi+1)/2;
            if (by+mid*byi<=tar){
                lo=mid;
            } else {
                hi=mid-1;
            }
        }
        long l = by+lo*byi-1;
        long r = l+byi;
        if (debug){
            io.println("l: "+l+", r: "+r);
        }
        long ans = -1;
        if (l==-1) ans=redSum;
        if (l>=0 && l<N) ans=Math.max(redSum+CSum[(int)l],ans);
        if (r>=0 && r<N) ans=Math.max(redSum+CSum[(int)r],ans);
        return ans;
    }
    private static class EGCD {
        /*
        Find gcd(a,b) -> gcd
        Find initial solution to ax+by=gcd(a,b) -> x,y
        Find the incrementer (x,y) + m(b/gcd(a,b), a/gcd(a,b))
        */
        long x;
        long y;
        long xi;
        long yi;
        long gcd;
        public EGCD(long aa, long bb){
            long a = aa;
            long b = bb;
            x=0;
            y=1;
            long u=1;
            long v=0;
            while (a!=0){
                //std euclidean
                long q=b/a;
                long r=b%a;
                b=a;
                a=r;

                //extended
                long m=x-u*q;
                long n=y-v*q;
                x=u;
                y=v;
                u=m;
                v=n;
            }
            gcd=b;
            xi=bb/gcd;
            yi=aa/gcd;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) throws IOException {
        if (fileSubmission){
            io = new IO(fileName, debug);
        } else {
            io = new IO(debug);
        }
        solve();
        io.close();
    }
    static IO io;
    private static class IO {
    BufferedReader br;
    StringTokenizer st;
    PrintWriter out;
    boolean debug;
    public IO(boolean dbg)  {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        debug=dbg;
    }
    public IO(String fileName, boolean dbg) throws IOException {
        br = new BufferedReader(new FileReader(fileName+".in"));
        out = new PrintWriter(new FileWriter(fileName+".out"));
        debug=dbg;
    }
    String next()
    {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }
    int nextInt() { return Integer.parseInt(next()); }
    long nextLong() { return Long.parseLong(next()); }
    double nextDouble() {return Double.parseDouble(next());}
    String nextLine() {
        String str = "";
        try {
            if(st.hasMoreTokens()){
                str = st.nextToken("\n");
            }
            else{
                str = br.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
    void println(){
        if (debug) System.out.println();
        else out.println();
    }
    void println(Object obj){
        if (debug) System.out.println(obj);
        else out.println(obj);
    }
    void print(Object obj){
        if (debug) System.out.print(obj);
        else out.print(obj);
    }
    public static void print(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + arr[r][c];
                while (str.length() < 5) str += " ";
                System.out.print(str);
            }
            System.out.println();
        }
        System.out.println();
    }
    void close(){
        out.close();
    }
};;
}
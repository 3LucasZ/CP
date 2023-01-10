package Codeforces.Edu133;

import java.io.*;
import java.util.*;
/*
PROB: SwapAndMaximumBlock
LANG: JAVA
*/
public class SwapAndMaximumBlock {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static int segs;

    static long[][] sum;
    static long[][] pref;
    static long[][] suf;
    static long[][] ans;

    public static void solve() throws IOException {
        //* parse + setup
        N = io.nextInt();
        segs= (1<<(N+1))-1;
        sum = new long[segs+1][1];
        pref = new long[segs+1][1];
        suf = new long[segs+1][1];
        ans=new long[segs+1][1];
        for (int i=segs/2+1;i<=segs;i++){
            long next = io.nextInt();
            sum[i][0]=next;
            pref[i][0]=Math.max(0,next);
            suf[i][0]=Math.max(0,next);
            ans[i][0]=Math.max(0,next);
        }
        if (debug){
            io.println("N: "+N+", segs: "+segs);
            io.println(height(4));
        }

        //* divide and conquer DP
        for (int i=segs/2;i>=1;i--){
            int height = height(i);
            int segSz = 1<<height;
            sum[i]=new long[segSz];
            pref[i]=new long[segSz];
            suf[i]=new long[segSz];
            ans[i]=new long[segSz];
            for (int x=0;x<segSz/2;x++){
                int l = i*2;
                int r = i*2+1;
                comb(i,x,l,r,x);
                int x2 = x+segSz/2;
                comb(i,x2,r,l,x);
            }
        }
        if (debug){
            for (int i=1;i<=segs;i++) io.println(Arrays.toString(ans[i]));
        }

        //* handle queries
        int q = io.nextInt();
        int query = 0;
        for (int i=0;i<q;i++){
            int bit = io.nextInt();
            int x = 1<<bit;
            query^=x;
            io.println(ans[1][query]);
        }
    }
    static void comb(int i, int x, int l, int r, int x2){
        sum[i][x]=sum[l][x2]+sum[r][x2];
        pref[i][x]=Math.max(pref[l][x2],sum[l][x2]+pref[r][x2]);
        suf[i][x]=Math.max(suf[r][x2],sum[r][x2]+suf[l][x2]);
        ans[i][x]=Math.max(ans[l][x2],ans[r][x2]);
        ans[i][x]=Math.max(ans[i][x],suf[l][x2]+pref[r][x2]);
    }
    static int height(int x){
        int ret = 0;
        while(x*2<segs){
            ret++;
            x*=2;
        }
        return ret;
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
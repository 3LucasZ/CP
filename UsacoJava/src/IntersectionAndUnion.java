import java.io.*;
import java.util.*;
/*
PROB: IntersectionAndUnion
LANG: JAVA
*/
public class IntersectionAndUnion {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = true;

    static int[] l;
    static int[] r;
    static int N;
    static int MX= 300000;
    static long MOD = 998244353;

    static long[][] off = {{3,1},{0,2}};
    static long[][] on = {{1,1},{2,2}};
    static long[][] pass ={{1,0},{0,1}};
    static long[][] initOn = {{0},{1}};
    static long[][] initOff = {{1},{0}};

    static boolean firstCovering = false;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        l = new int[N]; r = new int[N];
        for (int i=0;i<N;i++){
            l[i]=io.nextInt();
            r[i]=io.nextInt();
        }
        //* precomp
        ArrayList<Integer>[] add = new ArrayList[MX+1]; for (int i=0;i<=MX;i++) add[i] = new ArrayList<>();
        ArrayList<Integer>[] rem = new ArrayList[MX+1]; for (int i=0;i<=MX;i++) rem[i] = new ArrayList<>();
        for (int i=0;i<N;i++){
            add[l[i]].add(i);
            rem[r[i]].add(i);
        }
        //* insane bulk dp...
        SegTree seg = new SegTree(N-1);
        long ans = 0;
        for (int i=0;i<=MX;i++){
            for (int v : add[i]){
                if (v==0) firstCovering=true;
                else seg.set(v,on);
            }
            long[][] init = firstCovering?initOn:initOff;
            ans = (ans + multMatrix(seg.sum(),init)[1][0])%MOD;
            for (int v : rem[i]){
                if (v==0) firstCovering=false;
                else seg.set(v,off);
            }
        }
        io.println(ans);
    }
    //change to int if necessary
    static long[][] multMatrix(long[][] A,long[][] B){
        //ret = AB = B(A)
        int rA = A.length;
        int cA = A[0].length;
        int rB = B.length;
        int cB = B[0].length;
        if (rB!=cA){
            throw new RuntimeException("ILLEGAL MATRIX MULT rA:"+rA+", cA:"+cA+", rB:"+rB+", cB:"+cB);
        }
        long[][] ret = new long[rA][cB];
        for (int i=0;i<rA;i++){
            for (int j=0;j<cB;j++){
                for (int k=0;k<rB;k++){
                    //insert mod when necessary
                    ret[i][j] += A[i][k]*B[k][j];
                }
            }
        }
        return ret;
    }

    private static class SegTree {
        int size;
        long[][][] tree;
        public SegTree(int n){
            init(n);
        }
        public void init(int n){
            size = 1;
            while (size < n) size *= 2;
            tree = new long[2*size+1][0][0];
            for (int i=1;i<=n;i++){
                tree[i+size-1]=off;
            }
            for (int i=n+1;i<=size;i++){
                tree[i+size-1]=pass;
            }
            for (int i=size-1;i>=1;i--){
                tree[i]=multMatrix(tree[2*i+1],tree[2*i]);
            }
        }
        void set(int k, long[][] x){
            k+=size-1;
            tree[k]=x;
            for (k/=2;k>=1;k/=2) tree[k]=multMatrix(tree[2*k+1],tree[2*k]);
        }
        long[][] sum() {
            return tree[1];
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
    void print2d(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + arr[r][c];
                while (str.length() < 4) str += " ";
                print(str);
            }
           println();
        }
        println();
    }
    void print2d(char[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + arr[r][c];
                while (str.length() < 4) str += " ";
                print(str);
            }
            println();
        }
        println();
    }
    void print2d(boolean[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + (arr[r][c]?"1":"0");
                while (str.length() < 4) str += " ";
                print(str);
            }
            println();
        }
        println();
    }
    void printBin(int bin,int len){
        String ret = "";
        for (int i=0;i<len;i++){
            ret+=bin%2;
            bin/=2;
        }
        println(ret);
    }
    void reverse(int[] arr){
		for (int i=0;i<arr.length/2;i++){
			int tmp = arr[i];
			arr[i]=arr[arr.length-1-i];
			arr[arr.length-1-i]=tmp;
		}
	}
    void close(){
        out.close();
    }
};
}
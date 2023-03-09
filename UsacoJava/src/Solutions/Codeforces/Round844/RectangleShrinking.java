package Solutions.Codeforces.Round844;

import java.io.*;
import java.util.*;

public class RectangleShrinking{
    static boolean debug = false;

    static int N;
    static R[] A;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        A = new R[N];
        R[] B = new R[N];
        for (int i=0;i<N;i++){
            int u = io.nextInt();
            int l = io.nextInt();
            int d = io.nextInt();
            int r = io.nextInt();
            R rect = new R(i+1,u,l,d,r);
            A[i]=rect;
            B[i]=rect;
        }
        //* sort to processing order (smallest l first)
        Arrays.sort(A,Comparator.comparingInt(a->a.l));
        //* construction
        //trackers
        PriorityQueue<R> pq = new PriorityQueue<>(Comparator.comparingInt(a->a.r));
        int p1 = 0;
        int p2 = 0;
        for (int i=0;i<N;i++){
            if (debug) io.println("processing:"+A[i].id);
            //flatten doubles to singles
            if (A[i].u!=A[i].d){
                if (A[i].r<=p1) A[i].u=A[i].d;
                if (A[i].r<=p2) A[i].d=A[i].u;
            }
            //handle still doubles
            if (A[i].u!=A[i].d){

                while (true){
                    R top = pq.peek();
                    if (top==null) break;
                    if (top.r>=A[i].l) top.r=A[i].l-1;
                    pq.remove(top);
                }
                p1=A[i].r;
                p2=A[i].r;
            }
            //handle singles
            else if(A[i].u==1){
                A[i].l=Math.max(A[i].l,p1+1);
                p1=Math.max(p1,A[i].r);
            } else {
                A[i].l=Math.max(A[i].l,p2+1);
                p2=Math.max(p2,A[i].r);
            }
            //add to pq
            pq.add(A[i]);
        }

        //* ret
        int ans = 0;
        for (int i=0;i<N;i++){
            ans+=A[i].area();
        }
        io.println(ans);

        for (int i=0;i<N;i++){
            io.println(B[i].toString());
        }
    }
    private static class R{
        int id;
        int u;
        int d;
        int l;
        int r;
        public R(int id, int u,int l,int d,int r){
            this.id=id;
            this.u=u;
            this.l=l;
            this.d=d;
            this.r=r;
        }
        public boolean bad(){
            return l>r;
        }
        public int area() {
            if (bad()) return 0;
            return (d-u+1)*(r-l+1);
        }
        public String toString(){
            if (bad()){
                return "0 0 0 0";
            } else {
                return u+" "+l+" "+d+" "+r;
            }
        }
    }




















    static IO io;
    public static void main(String[] args) throws IOException {
        io = new IO(debug);
        int T = io.nextInt();
        for (int i=1;i<=T;i++) solve(i);
        io.close();
    }
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
    void close(){
        out.close();
    }
}}
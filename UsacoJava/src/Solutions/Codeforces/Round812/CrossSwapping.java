package Solutions.Codeforces.Round812;

import java.io.*;
import java.util.*;

public class CrossSwapping {
    static boolean debug = false;

    static int N;
    static int[][] A;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        A = new int[N+1][N+1];
        for (int i=1;i<=N;i++){
            for (int j=1;j<=N;j++){
                A[i][j]=io.nextInt();
            }
        }

        //* DSU
        DSU dsu = new DSU(N);
        for (int r=1;r<=N;r++){
            for (int c=1;c<=N;c++){
                if (r>=c) continue;
                if (A[r][c]<A[c][r]) dsu.union(r,c);
                else if (A[r][c]>A[c][r]) dsu.union(r,-c);
            }
        }

        //* Construct
        for (int i=1;i<=N;i++){
            if (dsu.get(i)>0){
                //perform operation
                for (int j=1;j<=N;j++){
                    int tmp = A[i][j];
                    A[i][j]=A[j][i];
                    A[j][i]=tmp;
                }
            }
        }

        //* ret
        for (int i=1;i<=N;i++){
            for (int j=1;j<=N;j++){
                io.print(A[i][j]+" ");
            }
            io.println();
        }
    }
    private static class DSU {
        /*
        union and get operations
        sz is the size of the component
         */
        int[] parent;

        public DSU(int num){
            parent = new int[num+1];
            Arrays.fill(parent, -1);
        }

        //return parent
        public int get(int v){
            if (v<0) return -get(-v);
            if (parent[v] == -1)  return v;
            return parent[v] = get(parent[v]);
        }

        //add edge
        public void union(int u, int v){
            int U = get(u);
            int V = get(v);
            //same component, do nothing
            if (Math.abs(U) == Math.abs(V)) return;
            //op
            if (U>0) parent[U]=V;
            else parent[-U] = -V;
        }
        //check CC
        public boolean connected(int u, int v){
            return get(u)==get(v);
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
}}
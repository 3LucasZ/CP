import java.io.*;
import java.util.*;

public class MakeItConnected {
    static boolean debug = false;

    static int N;
    static boolean[][] graph;
    static boolean[] vis;
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        graph = new boolean[N][N];
        for (int i=0;i<N;i++){
            String str =io.nextLine();
            for (int j=0;j<N;j++){
                graph[i][j]=str.charAt(j)=='1';
            }
        }
        //* find # components, agglom head, each full component
        vis = new boolean[N];
        int components = 0;
        int agglomHead = -1;
        ArrayList<ArrayList<Integer>> full = new ArrayList<>();
        for (int i=0;i<N;i++){
            if (!vis[i]){
                comp = new ArrayList<>();
                edges = 0;
                components++;
                DFS(i);
                if (comp.size()*(comp.size()-1)==edges){
                    full.add(comp);
                } else {
                    for (int j:comp){
                        int e = 0;
                        for (int k=0;k<N;k++){
                            if (graph[j][k]) e++;
                        }
                        if (e==comp.size()-1) continue;
                        agglomHead = j;
                    }
                }
            }
        }
        //* case: connected
        if (components==1) {
            io.println(0);
            return;
        }
        //* case: agglom
        if (agglomHead!=-1){
            io.println(1);
            io.println(agglomHead+1);
            return;
        }
        //* case: 2 dense
        if (full.size()==2){
            ArrayList<Integer> print = full.get(0).size()<full.get(1).size()?full.get(0):full.get(1);
            io.println(print.size());
            for (int i : print) io.print(i+1+" ");
            io.println();
            return;
        }
        //* case: a bunch of disjoint full graphs (more than 2 ofc)
        Collections.sort(full,Comparator.comparingInt(ArrayList::size));
        ArrayList<Integer> smallest = full.get(0);
        if (smallest.size()==1){
            io.println(1); io.println(smallest.get(0));
        } else{
            io.println();
            io.print(full.get(0).get(0)+full.get(1).get(0));
            io.println();
        }
    }
    static ArrayList<Integer> comp;
    static int edges = 0;
    static void DFS(int node){
        if (vis[node]) return;
        vis[node]=true;
        comp.add(node);
        for (int i=0;i<N;i++) {
            if (graph[node][i]){
                edges++;
                DFS(i);
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
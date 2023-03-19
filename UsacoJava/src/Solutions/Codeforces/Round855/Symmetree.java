package Solutions.Codeforces.Round855;

import java.io.*;
import java.util.*;

public class Symmetree {
    static boolean debug = false;

    static int N;
    static ArrayList<Integer>[] tree;

    static HashMap<ArrayList<Integer>, Integer> hsh;
    static boolean[] sym;
    static int last;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }
        //* bottom up dp
        hsh = new HashMap<>();
        sym = new boolean[N+1];
        last = 0;
        int finalHsh = DFS(1,0);
        //* ret
        io.println(sym[finalHsh]?"YES":"NO");
    }
    static int DFS(int node, int par){
        //child first
        HashMap<Integer,Integer> idCnt = new HashMap<>();
        for (int child : tree[node]){
            if (child==par)continue;
            int childHsh = DFS(child,node);
            if (!idCnt.containsKey(childHsh)) idCnt.put(childHsh,0);
            idCnt.put(childHsh,idCnt.get(childHsh)+1);
        }

        //create preSym, preHsh
        int odds = 0;
        int oddSyms = 0;
        ArrayList<Integer> preHsh = new ArrayList<>();
        for (Integer id : idCnt.keySet()){
            int freq = idCnt.get(id);
            for (int i=0;i<freq;i++) preHsh.add(id);
            if (freq%2==1){
                odds++;
                if (sym[id]) oddSyms++;
            }
        }

        //create Hsh and Sym
        Collections.sort(preHsh);
        if (!hsh.containsKey(preHsh)){
            hsh.put(preHsh,last);
            last++;
        }
        int nodeHsh = hsh.get(preHsh);
        if (odds==0 || (odds==1 && oddSyms==1)) sym[nodeHsh]=true;
        return nodeHsh;
    }




















    static IO io;
    public static void main(String[] args) throws IOException {
        io = new IO(debug);
        int T = io.nextInt();
        for (int i=1;i<=T;i++) solve(i);
        io.close();
    }
    static <T> T last(ArrayList<T> list){
    return list.get(list.size()-1);
}
static String binToStr(int bin,int len){
        String ret="";
        for(int i=0;i<len;i++){
            ret+=bin%2;
            bin/=2;
        }
        return ret;
    }
static int log2(int num){
    return (int)(Math.log(num)/Math.log(2));
}
static void reverse(int[] arr){
    for (int i=0;i<arr.length/2;i++){
        int tmp = arr[i];
        arr[i]=arr[arr.length-1-i];
        arr[arr.length-1-i]=tmp;
    }
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
    void close(){
        out.close();
    }
}}
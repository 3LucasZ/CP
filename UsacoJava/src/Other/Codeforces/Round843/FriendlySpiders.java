package Other.Codeforces.Round843;

import java.io.*;
import java.util.*;
/*
PROB: FriendlySpiders
LANG: JAVA
*/
public class FriendlySpiders {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;


    static int MAX = 300000;
    public static void solve() throws IOException {
        //* parse
        int N = io.nextInt();
        int[] A = new int[N+1];
        for (int i=1;i<=N;i++) A[i]=io.nextInt();
        int S = io.nextInt();
        int T = io.nextInt();

        //* precomp primes
        ArrayList<Integer> primes = primesLEQ(MAX);

        //* precomp for each i, all the prime d who divides Ai
        //* precomp for each prime all i whose Ai is div by that prime
        ArrayList<Integer>[] d = new ArrayList[N+1];
        for (int i=1;i<=N;i++) d[i] = new ArrayList<>();
        ArrayList<Integer>[] set = new ArrayList[MAX+1];
        for (int i=0;i<=MAX;i++) set[i] = new ArrayList<>();
        for (int i=1;i<=N;i++){
            int next = A[i];
            for (int j=0;(long)primes.get(j)*primes.get(j)<=next;j++){
                int p = primes.get(j);
                if (next%p==0) {
                    d[i].add(p);
                    set[p].add(i);
                }
                while (next%p==0)next/=primes.get(j);
            }
            if (next>1) {
                d[i].add(next);
                set[next].add(i);
            }
        }
        if (debug) {
            io.println("A: "+Arrays.toString(A));
            io.println("primes: "+primes);
            //io.println("pf: "+Arrays.toString(d));
            //io.println("set: "+Arrays.toString(set));
        }

        //* BFS with traceback
        boolean[] pvis = new boolean[MAX+1]; //visited prime set
        int[] before = new int[N+1];
        int[] dist = new int[N+1];

        Queue<Integer> BFS = new LinkedList<>();
        BFS.add(S);
        dist[T]=-1;
        dist[S]=1;
        while (!BFS.isEmpty()){
            int i = BFS.poll();
            if (debug){
                io.println("i: "+i);
            }
            for (int p : d[i]){
                if(!pvis[p]){
                    pvis[p]=true;
                    for(int k: set[p]){
                        if(dist[k]<1){
                            dist[k]=dist[i]+1;
                            before[k]=i;
                            BFS.add(k);
                        }
                    }
                }
            }
        }
        if (debug){
            io.println("dist: "+Arrays.toString(dist));
            io.println("bef: "+Arrays.toString(before));
        }

        int finalDist = dist[T];
        io.println(finalDist);
        ArrayList<Integer> sb = new ArrayList<>();
        int l = T;
        while (finalDist>0){
            finalDist--;
            sb.add(l);
            l=before[l];
        }
        Collections.reverse(sb);
        for (int i : sb) io.print(i+" ");
        io.println();
    }
    public static ArrayList<Integer> primesLEQ(int n){
        //is prime boolean array
        boolean[] prime = new boolean[n+1]; for (int i=2;i<=n;i++) prime[i]=true;
        for (int p=2;p*p<=n;p++){
            if (prime[p]){
                for (int i=p*p;i<=n;i+=p){
                    prime[i]=false;
                }
            }
        }
        //convert array to list
        ArrayList<Integer> ret = new ArrayList<>();
        for (int p=2;p<=n;p++){
            if (prime[p]) ret.add(p);
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
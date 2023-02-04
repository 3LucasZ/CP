package Other.Codeforces.Edu135;

import java.io.*;
import java.util.*;

public class DigitalLogarithm {
    static boolean debug = false;

    static int N;
    static Multiset A;
    static Multiset B;
    static int ops;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
         N = io.nextInt();
         A = new Multiset();
         B = new Multiset();
        for (int i=0;i<N;i++){
            A.add(io.nextInt());
        }
        for (int i=0;i<N;i++){
            B.add(io.nextInt());
        }

        //* match and reduce and match and finalize
        ops = 0;
        if (debug){
            io.println("init");
            io.println("A: "+A);
            io.println("B: "+B);
        }
        match();
        if (debug){
            io.println("match");
            io.println("A: "+A);
            io.println("B: "+B);
        }
        A = transform(A);
        B = transform(B);
        if (debug){
            io.println("transform");
            io.println("A: "+A);
            io.println("B: "+B);
        }
        match();
        if (debug){
            io.println("match");
            io.println("A: "+A);
            io.println("B: "+B);
        }
        for (int key : A.keySet()){
            if (key!=1) ops+=A.get(key);
        }
        for (int key : B.keySet()){
            if (key!=1) ops+=B.get(key);
        }
        io.println(ops);

    }
    static void match(){
        for (int key : A.ms.keySet())  {
            if (B.ms.containsKey(key)){
                int min = Math.min(B.ms.get(key),A.ms.get(key));
                A.ms.replace(key,A.ms.get(key)-min);
                B.ms.replace(key,B.ms.get(key)-min);
            }
        }
        Iterator<Integer> it = A.iterator();
        while (it.hasNext()){
            int next = it.next();
            if (A.get(next)==0){
                it.remove();
            }
            if (B.contains(next)&&B.get(next)==0){
                B.removeKey(next);
            }
        }
    }
    static Multiset transform(Multiset ms){
        Multiset ret = new Multiset();
        for (int key : ms.ms.keySet()){
            int newKey = key;
            if (key>9){
                newKey = len(key);
                ops+=ms.get(key);
            }
            ret.add(newKey,ms.get(key));
        }
        return ret;
    }
    static int len(int num){
        int ret = 0;
        while (num>0){
            ret++;
            num/=10;
        }
        return ret;
    }
    private static class Multiset {
        private TreeMap<Integer, Integer> ms = new TreeMap<>();
        private int sz = 0;
        public boolean contains(int x){
            return ms.containsKey(x);
        }
        public void add(int x){add(x,1);}
        public void add(int x, int freq){
            if (!ms.containsKey(x))ms.put(x,0);
            ms.put(x,ms.get(x)+freq);
            sz+=freq;
        }
        public void remove(int x){
            remove(x,1);}
        public void remove(int x, int freq){
            ms.put(x,ms.get(x)-freq);
            if (ms.get(x)<=0) ms.remove(x);
            sz-=freq;
        }
        public void removeKey(int x){
            ms.remove(x);
        }
        public int get(int x){
            return ms.get(x);
        }
        public Iterator<Integer> iterator(){
            return ms.keySet().iterator();
        }
        public int size(){
            return sz;
        }
        public Set<Integer> keySet(){
            return ms.keySet();
        }
        public String toString(){
            return ms.toString();
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
};}
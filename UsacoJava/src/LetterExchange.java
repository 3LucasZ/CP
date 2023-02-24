import java.io.*;
import java.util.*;

public class LetterExchange {
    static boolean debug = true;
    //w = 0, i = 1, n = 2 and for our pairs of wanted,needed assume wanted is smaller and needed x3
    static int N;
    static Stack[][] stack;
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        stack = new Stack[3][3]; for (int i=0;i<3;i++) for (int j=0;j<3;j++) stack[i][j] = new Stack();
        //* parse
        for (int i=0;i<N;i++){
            String str = io.next();
            Multiset ms = new Multiset();
            for (int j=0;j<3;j++){
                char s = str.charAt(j);
                if (s=='w') s=0;
                else if (s=='i') s=1;
                else s=2;
                ms.add(s);
            }
        }
        //* brute force
        
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){

            }
        }
    }

    private static class Multiset {
        /*
        Overloaded TreeMap functioning as Multiset
        TreeMap private
         */
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
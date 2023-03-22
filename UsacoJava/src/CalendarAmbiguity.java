import java.io.*;
import java.util.*;

public class CalendarAmbiguity {
    static boolean debug = false;

    static long M;
    static long D;
    static long W;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        M = io.nextInt();
        D = io.nextInt();
        W = io.nextInt();
        long min=Math.min(D,M);
        long W2 = W/gcd(W,D-1);
        long R = min/W2;
        io.println(R*min-R*(R+1)/2*W2);
    }
    public static long lcm(long a, long b){
        return a*b/gcd(a,b);
    }
    public static long gcd(long a, long b){
        if (b==0) return a;
        return gcd(b,a%b);
    }




















    static IO io;
    public static void main(String[] args) throws IOException {
        io = new IO(debug);
        int T = io.nextInt();
        for (int i=1;i<=T;i++) solve(i);
        io.close();
    }
        
    private static class Triple <T1, T2, T3> {
        T1 first;
        T2 second;
        T3 third;
        public Triple(T1 first,T2 second, T3 third){
            this.first=first;
            this.second=second;
            this.third=third;
        }
        public String toString(){
            return "["+first+", "+second+", "+third+"]";
        }
    } 
   private static class Pair <T1, T2> {
        T1 first;
        T2 second;
        public Pair(T1 first,T2 second){
            this.first=first;
            this.second=second;
        }
        @Override
        public boolean equals(Object o){
            if (!(o instanceof Pair)) return false;
            Pair<T1,T2> other = (Pair<T1,T2>) o;
            return first.equals(other.first) && second.equals(other.second);
        }
        public String toString(){
            return "["+first+", "+second+"]";
        }
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
                    print(arr[r][c]);
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
    }
}
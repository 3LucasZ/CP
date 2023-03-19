package Solutions.Codeforces.Round845;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class ComfortablyNumb {
    static boolean debug = false;

    static int N;
    static int[] A;
    static int[] PXOR;
    static int[] lm;
    static int[] rm;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        A = new int[N+1];
        PXOR = new int[N+1];
        for (int i=1;i<=N;i++) {
            A[i]=io.nextInt();
            PXOR[i]=PXOR[i-1]^A[i];
        }
        if (debug){
            io.println("A:"+Arrays.toString(A));
            io.println("PXOR:"+Arrays.toString(PXOR));
        }
        //* precomp lm, rm
        lm = new int[N+1]; //closest larger to the left
        rm = new int[N+1]; //closest larger to the right
        Stack<Integer> s = new Stack<>();
        for (int i=1;i<=N;i++){
            while (!s.isEmpty()&&A[i]>=A[s.peek()]){
                s.pop();
            }
            if (s.isEmpty()) lm[i]=0;
            else lm[i]=s.peek();
            s.push(i);
        }
        s = new Stack<>();
        for (int i=N;i>=1;i--){
            while (!s.isEmpty()&&A[i]>A[s.peek()]){
                s.pop();
            }
            if (s.isEmpty()) rm[i]=N+1;
            else rm[i]=s.peek();
            s.push(i);
        }
        if (debug){
            io.println("lm:"+Arrays.toString(lm));
            io.println("rm:"+Arrays.toString(rm));
        }
        //* solve
        int ans = 0;
        Trie[] tries = new Trie[N+2];
        for (int i=1;i<=N+1;i++) {
            tries[i] = new Trie();
            tries[i].insert(PXOR[i-1]);
        }
        //order to solve the ranges
        Integer[] asc = new Integer[N+1];
        for (int i=1;i<=N;i++) asc[i]=i;
        Arrays.sort(asc, 1, N+1, Comparator.comparingInt(a->A[a]));
        if (debug) io.println("asc:"+Arrays.toString(asc));
        //iterative strategy
        for (int i=1;i<=N;i++){
            int mid = asc[i];
            int l = lm[mid]+1;
            int r = rm[mid]-1;
            if (debug){
                io.println("l:"+l+", r:"+r+", mid:"+mid);
            }
            if (mid-l<r-mid){
                for (int k=l-1;k<mid;k++){
                    ans=Math.max(ans,tries[mid+1].complement(PXOR[k]^A[mid]));
                }
                tries[l] = tries[mid+1];
                for (int k=l-1;k<mid;k++){
                    tries[l].insert(PXOR[k]);
                }
            }
            else {
                for (int k=mid;k<=r;k++){
                    ans=Math.max(ans,tries[l].complement(PXOR[k]^A[mid]));
                }
                for (int k=mid;k<=r;k++){
                    tries[l].insert(PXOR[k]);
                }
            }
        }

        //* ret
        if (debug) io.println("ans:");
        io.println(ans);
    }

    private static class Trie {
        Node head = new Node();
        public void insert(int x){
            Node cur = head;
            for (int i=30;i>=0;i--){
                int bit = x&(1<<i);
                if (bit==0){
                    if (cur.zeroChild==null) cur.zeroChild = new Node();
                    cur = cur.zeroChild;
                } else {
                    if (cur.oneChild==null) cur.oneChild = new Node();
                    cur = cur.oneChild;
                }
            }
        }
        public int complement(int x){
            Node cur = head;
            for (int i=30;i>=0;i--){
                int bit = x&(1<<i);
                if (bit==0){
                    //bit 0, srch 1
                    if (cur.oneChild!=null){
                        cur=cur.oneChild;
                        x^=(1<<i);
                    }
                    //bit 0, srch 0
                    else {
                        cur = cur.zeroChild;
                    }
                }
                else {
                    //bit 1, srch 0
                    if (cur.zeroChild!=null) {
                        cur = cur.zeroChild;
                    }
                    //bit 1, srch 1
                    else {
                        cur = cur.oneChild;
                        x^=(1<<i);
                    }
                }
            }
            return x;
        }
    }
    private static class Node {
        Node zeroChild = null;
        Node oneChild = null;
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
        public String toString(){
            return "["+first+", "+second+"]";
        }
    }
    static <T> T last(ArrayList<T> list){
        return list.get(list.size()-1);
    }
    static <T> void swap(T a, T b){
        T tmp = a;
        a = b;
        b = tmp;
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
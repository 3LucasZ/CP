package Helpers.Graph;

import java.io.*;
import java.util.*;

public class LCAEulerTour{
    /*
    Goal: LCA (u,v)
    Arguments: [1-indexed tree]
    Time Complexity: O(NlgN+Q)
    Space Complexity: O(NlgN)
    CSES Data set (verified): https://cses.fi/problemset/task/1688/
    */

    private static class LCA{
        int[] depth;
        int timer=-1;
        int[] start;
        int[] order;
        ArrayList<Integer>[] tree;
        RMQ rmq;

        public LCA(ArrayList<Integer>[] tree){
            this.tree=tree;
            start=new int[tree.length];
            depth=new int[tree.length];
            order=new int[2*tree.length];
            depth[0]=-1;
            DFS_depth(1,0);
            DFS_tin(1,0);
            rmq=new RMQ(order);
        }

        public void DFS_depth(int node,int par){
            depth[node]=depth[par]+1;
            for(int child: tree[node]){
                if(child!=par) DFS_depth(child,node);
            }
        }

        public void DFS_tin(int node,int par){
            order[++timer]=node;
            start[node]=timer;
            for(int child: tree[node]){
                if(child!=par){
                    DFS_tin(child,node);
                    order[++timer]=node;
                }
            }
        }

        public int getLCA(int u,int v){
            int l=Math.min(start[u],start[v]);
            int r=Math.max(start[u],start[v]);
            return rmq.query(l,r);
        }

        /*
        Goal: Range queries
        Arguments: [any array]
        Time Complexity: O(NlgN+Q)
        Space Complexity: O(NlgN)
         */
        private static class RMQ{
            int lgSz;
            int[] arr;
            int[][] rangeMin;
            public RMQ(int[] arr){
                this.arr=arr;
                this.lgSz=log2(arr.length)+1;
                rangeMin=new int[arr.length][lgSz+1];
                for(int i=0;i<arr.length;i++) rangeMin[i][0]=arr[i];
                int range=1;
                for(int bin=1;bin<=lgSz;bin++){
                    for(int i=0;i<arr.length;i++){
                        int j=i+range;
                        if(i+2*range>arr.length) break;
                        rangeMin[i][bin]=Math.min(rangeMin[i][bin-1],rangeMin[j][bin-1]);
                    }
                    range*=2;
                }
            }
            public int query(int l,int r){
                int len=r-l+1;
                int bits=log2(len);
                int subLen=1<<bits;
                return Math.min(rangeMin[l][bits],rangeMin[r-subLen+1][bits]);
            }
        }
    }

    static ArrayList<Integer>[] tree;
    static boolean debug=false;

    public static void solve() {
        int N = io.nextInt();
        int Q = io.nextInt();
        ArrayList<Integer>[] tree=new ArrayList[N+1];
        for(int i=1;i<=N;i++) tree[i]=new ArrayList<>();
        for(int u=2;u<=N;u++){
            int v = io.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }
        LCA lca=new LCA(tree);
        for(int i=0;i<Q;i++){
            int u=io.nextInt();
            int v=io.nextInt();
            int m=lca.getLCA(u,v);
            io.println(m);
        }
    }

    public static void main(String[] args){
        io = new IO(true);
        solve();
        io.close();
    }
    static IO io;
    public static long lcm(long a, long b){
        return a*b/gcd(a,b);
    }
    public static long gcd(long a, long b){
        if (b==0) return a;
        return gcd(b,a%b);
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
    static void print2d(int[][] arr){
        for(int r=0;r<arr.length;r++){
            for(int c=0;c<arr[r].length;c++){
                String str=""+arr[r][c];
                while(str.length()<4) str+=" ";
                io.print(str);
            }
            io.println();
        }
        io.println();
    }

    static void print2d(char[][] arr){
        for(int r=0;r<arr.length;r++){
            for(int c=0;c<arr[r].length;c++){
                io.print(arr[r][c]);
            }
            io.println();
        }
        io.println();
    }

    static void print2d(boolean[][] arr){
        for(int r=0;r<arr.length;r++){
            for(int c=0;c<arr[r].length;c++){
                String str=""+(arr[r][c]?"1":"0");
                while(str.length()<4) str+=" ";
                io.print(str);
            }
            io.println();
        }
        io.println();
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
        void close(){
            out.close();
        }
    }
}

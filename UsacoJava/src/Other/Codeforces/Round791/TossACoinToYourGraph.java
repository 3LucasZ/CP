package Other.Codeforces.Round791;

import java.io.*;
import java.util.*;
/*
Solution right, but TLE on CF... Im upset about this but its okay we got the logic/impl down FIRST try
technically... screw TLE on untested java!!
 */
public class TossACoinToYourGraph {
    static boolean debug = false;

    static int N,M;
    static int[] A;
    static long K;
    static ArrayList<Integer>[] orig;
    static int[] depth;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        M = io.nextInt();
        K = io.nextLong();
        A = new int[N+1];
        for (int i=1;i<=N;i++) A[i]=io.nextInt();
        orig = new ArrayList[N+1];
        for (int i=1;i<=N;i++) orig[i] = new ArrayList<>();
        for (int i=0;i<M;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            orig[u].add(v);
        }

        //* binary search min max element
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i=1;i<=N;i++){
            min=Math.min(min,A[i]);
            max=Math.max(max,A[i]);
        }
        depth = new int[N+1];
        int lo=min;
        int hi=max+1;
        while (lo<hi){
            int mid = (lo+hi)/2;
            if (tryMax(mid)){
                hi=mid;
            }
            else {
                lo=mid+1;
            }
        }
        //* ret
        if (lo==max+1) io.println(-1);
        else io.println(lo);
    }


    static boolean tryMax(int max){
        //* top sort
        sort = new ArrayList<>();
        vis = new boolean[N+1];
        for (int i=1;i<=N;i++) dfs(i,max);

        //* check for cycles
        int M = sort.size();
        for (int i=0;i<M;i++){
            depth[sort.get(i)]=M-i;
        }
        for (int u = 1;u<=N;u++){
            if (A[u]>max) continue;
            for (int v : orig[u]){
                if (A[v]>max) continue;
                if (depth[u]>depth[v]){
                    return true;
                }
            }
        }

        //* DAG DP
        int[] dp = new int[N+1];
        int ret = 0;
        for(int i=M-1;i>=0;i--){
            int u = sort.get(i);
            ret=Math.max(dp[u],ret);
            for(int v: orig[u]){
                if(A[v]>max) continue;
                dp[v]=Math.max(dp[v],dp[u]+1);
            }
        }

        //* Ret
        return ret>=K-1;
    }
    static ArrayList<Integer> sort;
    static boolean[] vis;
    public static void dfs(int node, int max){
        if (A[node]>max || vis[node]) return;
        vis[node]=true;
        for (int child : orig[node]){
            dfs(child,max);
        }
        sort.add(node);
    }


















    public static void main(String[] args) throws IOException {
        io = new Reader();
        solve();
        io.close();
    }
    static Reader io;
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
        PrintWriter out;

        public Reader()
        {
            din = new DataInputStream(System.in);
            out = new PrintWriter(System.out);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(
                    new FileInputStream(file_name+".in"));
            out = new PrintWriter(new FileWriter(file_name+".out"));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // IMPORTANT: read line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    }
                    else {
                        continue;
                    }
                }
                buf[cnt++] = (byte)c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
            out.close();
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
    }
}
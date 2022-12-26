package USACO.Season2022_2023.Gold;

import java.io.*;
import java.util.*;

/*
PROB: StrongestFriendshipGroupSubtask
LANG: JAVA
*/
public class StrongestFriendshipGroup {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static int M;
    static ArrayList<Integer>[] graph;
    static boolean[] vis;
    static int[] sz;
    static PriorityQueue<Integer> pq;
    static ArrayList<Edge>[] add;
    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        M = io.nextInt();
        graph = new ArrayList[N+1]; for (int i=1;i<=N;i++) graph[i] = new ArrayList<>();
        for (int i=0;i<M;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }

        //* setup pq and add
        sz = new int[N+1]; for (int i=1;i<=N;i++) sz[i] = graph[i].size();
        pq = new PriorityQueue<>((a,b)->{
            if (sz[a]==sz[b]) return a-b;
            return sz[a]-sz[b];
        });
        for (int i=1;i<=N;i++) pq.add(i);
        add = new ArrayList[M+1];
        for (int i=0;i<=M;i++) add[i] = new ArrayList<>();

        //* for each minDeg, use pq to find edges to remove and add the edges removed to add
        vis = new boolean[N+1];
        for (int minDeg=0;minDeg<=M;minDeg++){
            trim(minDeg);
        }

        //* iterate through add, using DSU to calculate largest component
        int maxComponent = 0;
        DSU dsu = new DSU(N);
        int ans = 0;
        for (int minDeg=M;minDeg>=0;minDeg--){
            for (Edge e : add[minDeg]){
                int u = e.u;
                int v = e.v;
                dsu.union(u,v);
                maxComponent=Math.max(maxComponent,dsu.sz[dsu.get(u)]);
            }
            ans=Math.max(ans,maxComponent*(minDeg-1));
        }
        io.println(ans);
    }
    public static void trim(int deg){
        while (true){
            Integer lo = pq.peek();
            if (lo==null || sz[lo]>=deg) break;
            vis[lo]=true;
            pq.poll();
            Iterator<Integer> it = graph[lo].listIterator();
            while (it.hasNext()){
                Integer child = it.next();
                //child visited b4
                if (vis[child]) continue;
                //reg child
                pq.remove(child);
                sz[child]--;
                if (sz[child]!=0) pq.add(child);
                add[deg].add(new Edge(lo,child));
            }
        }
    }
    private static class Edge {
        int u;
        int v;
        public Edge(int u, int v){
            this.u=u;
            this.v=v;
        }
        public String toString(){
            return "["+u+", "+v+"]";
        }
    }
    private static class DSU {
        /*
        union and get operations
        sz is the size of the component
         */
        int[] parent;
        int[] sz;

        public DSU(int num){
            sz = new int[num+1];
            parent = new int[num+1];
            Arrays.fill(sz, 1);
            Arrays.fill(parent, -1);
        }

        //return parent
        public int get(int v){
            if (parent[v] == -1) {
                return v;
            }
            parent[v] = get(parent[v]);
            return parent[v];
        }

        //add edge
        public void union(int u, int v){
            int u_parent = get(u);
            int v_parent = get(v);
            //same component, do nothing
            if (u_parent == v_parent) return;
            if (sz[u_parent] < sz[v_parent]){
                parent[u_parent] = v_parent;
                sz[v_parent] += sz[u_parent];
            }
            else {
                parent[v_parent] = u_parent;
                sz[u_parent] += sz[v_parent];
            }
        }
        //check fo connected components
        public boolean connected(int u, int v){
            return get(u)==get(v);
        }
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
        PrintWriter out = new PrintWriter(System.out);

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(
                    new FileInputStream(file_name));
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
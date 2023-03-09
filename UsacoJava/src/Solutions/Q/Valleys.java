package Solutions.Q;

import java.io.*;
import java.util.*;
/*
PROB: Valleys
LANG: JAVA
*/
public class Valleys {
    static boolean fileSubmission = true;
    static String fileName = "valleys";
    
    static boolean debug = false;

    static int N;
    static int[][] h;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        ArrayList<Pair> cells = new ArrayList<>();
        h = new int[N][N];
        for (int x=0;x<N;x++){
            for (int y=0;y<N;y++){
                h[x][y]=io.nextInt();
                cells.add(new Pair(x,y));
            }
        }

        //* sort cells by increasing height
        Collections.sort(cells, Comparator.comparingInt(a -> h[a.x][a.y]));
        if (debug){
            io.println("Cells: "+cells);
        }
        //* create holes timeline
        ArrayList<Integer> holes = new ArrayList<>(); holes.add(0);
        DSU A = new DSU(N*N);
        for (int i=N*N-1;i>=0;i--){
            int x = cells.get(i).x;
            int y = cells.get(i).y;
            A.components++;
            //union all surrounding pointwise shorter
            for (int dx=-1;dx<=1;dx++){
                for (int dy=-1;dy<=1;dy++){
                    if (!in(x+dx,y+dy)) A.union(pack(x,y),pack(N,0));
                    if (in(x+dx,y+dy) && h[x+dx][y+dy]>h[x][y]) {
                        A.union(pack(x + dx, y + dy), pack(x, y));
                    }
                }
            }
            //upd timeline
            holes.add(A.components);
        }
        Collections.reverse(holes);
        if (debug) io.println("holes: "+holes);

        //* find components and sizes going forward. keep track of holes using timeline.
        DSU B = new DSU(N*N);
        long ans = 0;
        for (int i=0;i<N*N;i++){
            int x = cells.get(i).x;
            int y = cells.get(i).y;
            for (int d=0;d<4;d++){
                if (in(x+dx[d],y+dy[d]) && h[x+dx[d]][y+dy[d]]<h[x][y]){
                    B.union(pack(x+dx[d],y+dy[d]),pack(x,y));
                }
            }
            if (i>0)B.holes[B.get(pack(x,y))]+=holes.get(i+1)-holes.get(i);
            if (B.holes[B.get(pack(x,y))]==0) ans+=B.sz[B.get(pack(x,y))];
            if (debug) {
                io.println("x: "+x+", y: "+y);
                io.println("valley sz: " + B.sz[B.get(pack(x, y))]);
                io.println("valley holes: "+B.holes[B.get(pack(x,y))]);
            }
        }

        //* ret
        io.println(ans);
    }
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public static boolean in(int x, int y){
        return x>=0&&x<N&&y>=0&&y<N;
    }
    private static class Pair {
        int x;
        int y;
        public Pair(int x, int y){
            this.x=x;
            this.y=y;
        }
        public String toString(){
            return "["+x+", "+y+"]";
        }
    }
    static int pack(Pair pair){
        return pair.x*N+pair.y;
    }
    static int pack(int x,int y){
        return x*N+y;
    }
    static Pair unpack(int k){
        return new Pair(k/N,k%N);
    }
    private static class DSU {
        /*
        union and get operations
        sz is the size of the component
         */
        int[] parent;
        int[] sz;
        int[] holes;
        int components;

        public DSU(int N){
            sz = new int[N+1];
            parent = new int[N+1];
            holes = new int[N+1];
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
            int U = get(u);
            int V = get(v);
            //same component, do nothing
            if (U == V) return;
            //enforce sz[V]<sz[U]
            if (sz[U]<sz[V]){
                int tmp=U;
                U=V;
                V=tmp;
            }
            //op
            parent[V] = U;
            sz[U] += sz[V];
            holes[U] += holes[V];
            components--;
        }
        //check CC
        public boolean connected(int u, int v){
            return get(u)==get(v);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    static Reader io;
    public static void main(String[] args) throws IOException {
        if (fileSubmission) {
            io = new Reader(fileName);
        }
        else {
            io = new Reader();
        }
        solve();
        io.close();
    }
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
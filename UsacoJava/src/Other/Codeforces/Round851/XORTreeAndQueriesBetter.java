package Other.Codeforces.Round851;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/*
PROB: XORTreeAndQueries
LANG: JAVA
*/
public class XORTreeAndQueriesBetter{
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N, Q;
    static ArrayList<Edge>[] tree; //init tree
    static ArrayList<Edge>[] graph; //graph of constraints

    static ArrayList<Integer> component; //current DFS component
    static ArrayList<Integer> specialComponent; //some special current DFS component
    static int constant;
    static boolean[] vis; //node vis yet in flood fill?

    static int[] p; //p[node] = 0111001010
    static int[] ans; //ans[edge] = p[i]^p[j]
    static boolean working = true;
    static int special;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        Q = io.nextInt();
        tree= new ArrayList[N+1];
        for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            Edge uv = new Edge(u,v);
            uv.id=i;
            Edge vu = new Edge(v,u);
            vu.id=i;
            tree[u].add(uv);
            tree[v].add(vu);
        }
        if (debug){
            io.println("tree:"+Arrays.toString(tree));
        }
        graph = new ArrayList[N+1];
        for (int i=1;i<=N;i++) graph[i] = new ArrayList<>();
        for (int i=0;i<Q;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            int cost = io.nextInt();
            Edge uv = new Edge(u,v);
            uv.cost = cost;
            Edge vu = new Edge(v,u);
            vu.cost=cost;
            graph[u].add(uv);
            graph[v].add(vu);
        }
        if (debug){
            io.println("graph:"+Arrays.toString(graph));
        }
        //* setup
        p = new int[N+1];
        vis = new boolean[N+1];
        //* floodfill connected components and solve each component separately
        for (int i=1;i<=N;i++) {
            if (!vis[i]){
                component = new ArrayList<>();
                special = 0;
                DFS(i);
                if (special%2==1) specialComponent = component;
            }
        }
        //* change p based on c if necessary
        if (specialComponent!=null){
            for (int i : specialComponent) p[i]^=constant;
        }
        //* construct ans from p
        ans = new int[N-1];
        finalDFS(1,-1);
        //* ret ans
        if (working){
            io.println("YES");
            for (int i=0;i<N-1;i++) io.print(ans[i]+" ");
        } else {
            io.println("NO");
        }
    }
    public static void finalDFS(int node, int par){
        for (Edge childEdge : tree[node]){
            int child = childEdge.v;
            if (child==par) continue;
            ans[childEdge.id]=p[node]^p[child];
            finalDFS(child,node);
        }
    }
    public static void DFS(int node){
        vis[node]=true;
        component.add(node);
        //update constant for special node
        if (tree[node].size()%2==1) {
            constant^=p[node];
            special++;
        }
        //propagation
        for (Edge childEdge : graph[node]){
            int child = childEdge.v;
            int cost = childEdge.cost;
            if (vis[child]){
                if((p[node]^p[child])!=cost) working=false;
            }
            else {
                p[child] = p[node]^cost;
                DFS(child);
            }
        }
    }
    private static class Edge {
        int u;
        int v;
        int id;
        int cost;
        public Edge(int u, int v){
            this.u=u;
            this.v=v;
        }
        public String toString(){
            return "["+u+", "+v+"]";
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

        public String readLine(int len) throws IOException{
            byte[] buf=new byte[len]; // IMPORTANT: read line length
            int cnt=0, c;
            while((c=read())!=-1){
                if(c=='\n'){
                    if(cnt!=0){
                        break;
                    }else{
                        continue;
                    }
                }
                buf[cnt++]=(byte)c;
            }
            return new String(buf,0,cnt);
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
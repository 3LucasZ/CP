import java.io.*;
import java.util.*;
/*
PROB: XORTreeAndQueries
LANG: JAVA
*/
public class XORTreeAndQueries {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N, Q;
    static ArrayList<Edge>[] tree; //init tree
    static ArrayList<Edge>[] graph; //graph of constraints

    static ArrayList<Integer> component; //current DFS component
    static boolean[] vis; //node vis yet in flood fill?
    static int specialOn;
    static int specialOff;

    static int[] pbit; //pbit[node] = 0/1
    static int[] p; //p[node] = 0111001010
    static int[] ans; //ans[edge] = p[i]^p[j]
    static boolean working = true;

    static final int bits = 30;

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
        pbit= new int[N+1];
        p = new int[N+1];
        vis = new boolean[N+1];
        //* floodfill connected components and solve each component separately
        for (int i=1;i<=N;i++) {
            if (!vis[i]) solve(i);
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
    public static void solve(int head){
        //get component
        component = new ArrayList<>();
        DFS(head,0,true);
        for (int bit=0;bit<30;bit++){
            //reset init
            specialOn = 0;
            specialOff = 0;
            for (int node : component) vis[node]=false;
            //dfs0
            pbit[head]=0;
            DFS(head, bit,false);
            //dfs1 if necessary (flip all component)
            if (specialOn%2<specialOff%2) for (int node : component) pbit[node]=1-pbit[node];
            //update p
            for (int node : component) p[node]+=pbit[node]*(1<<bit);
        }
    }
    public static void DFS(int node, int bit, boolean trackComponent){
        vis[node]=true;
        if (trackComponent) component.add(node);
        //special node
        if (tree[node].size()%2==1){
            if (pbit[node]==0) specialOff++;
            else specialOn++;
        }
        //propagation
        for (Edge childEdge : graph[node]){
            int child = childEdge.v;
            int cost = ((1<<bit)&childEdge.cost)==0?0:1;
            if (vis[child]){
                if ((pbit[node]^pbit[child])!=cost) working=false;
            } else {
                pbit[child] = pbit[node]^cost;
                DFS(child,bit,trackComponent);
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
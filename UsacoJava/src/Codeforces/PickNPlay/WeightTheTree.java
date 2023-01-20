package Codeforces.PickNPlay;

import java.io.*;
import java.util.*;
/*
PROB: WeightTheTree
LANG: JAVA
*/
public class WeightTheTree {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static ArrayList<Integer>[] graph;
    static ArrayList<Edge>[][] graph2;

    static int[][] val;
    static int[][] sum;
    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        graph = new ArrayList[N+1]; for (int i=0;i<=N;i++) graph[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }
        //dummy case: N=2
        if (N==2){
            io.println("2 2");
            io.println("1 1");
            return;
        }

        //* dp
        val = new int[N+1][2];
        sum = new int[N+1][2];
        graph2 = new ArrayList[N+1][2];
        for (int i=0;i<=N;i++) for (int j=0;j<2;j++) graph2[i][j]= new ArrayList<>();
        graph[0].add(1);//dummy node
        DFS(0,-1);

        //* ret
        int maxVal = val[0][0];
        int maxSum = sum[0][0]-1;
        io.println(maxVal+" "+maxSum);
//        if (debug){
//            io.println("graph1: "+Arrays.toString(graph));
//            for (int i=0;i<=N;i++){
//                io.println("node: "+i);
//                io.println("white: "+graph2[i][0]);
//                io.println("black: "+graph2[i][1]);
//            }
//
//            io.println(sum[8][1]);
//        }
        weight = new int[N+1];
        DFS2(0,0);
        for (int i=1;i<=N;i++) io.print(weight[i]+" ");
    }
    static int[] weight;
    static void DFS2(int node, int color){
        if (color==0){
            weight[node]=1;
        } else {
            weight[node]=graph[node].size();
        }
        for (Edge child : graph2[node][color]){
            DFS2(child.child,child.childColor);
        }
    }
    static void DFS(int node,int par){
        for (int child : graph[node]){
            if (child==par) continue;
            DFS(child,node);
        }
        //black
        for (int child : graph[node]){
            if (child==par) continue;
            val[node][1]+=val[child][0];
            sum[node][1]+=sum[child][0];
            graph2[node][1].add(new Edge(child,0));
        }
        val[node][1]+=1;
        sum[node][1]+=graph[node].size();

        //white
        for (int child : graph[node]){
            if (child==par) continue;
            val[node][0]+=Math.max(val[child][0],val[child][1]);
            if (val[child][0]>val[child][1]){
                sum[node][0]+=sum[child][0];
                graph2[node][0].add(new Edge(child,0));
            }
            else if (val[child][0]<val[child][1]){
                sum[node][0]+=sum[child][1];
                graph2[node][0].add(new Edge(child,1));
            }
            else {
                sum[node][0]+=Math.min(sum[child][0],sum[child][1]);
                if (sum[child][0]<sum[child][1]){
                    graph2[node][0].add(new Edge(child,0));
                } else {
                    graph2[node][0].add(new Edge(child,1));
                }
            }
        }
        sum[node][0]++;
    }
    private static class Edge {
        int child;
        int childColor;
        public Edge(int child, int childColor){
            this.child=child;
            this.childColor=childColor;
        }
        public String toString(){
            return child+":"+childColor;
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
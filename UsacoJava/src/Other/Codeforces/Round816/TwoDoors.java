package Other.Codeforces.Round816;

import java.io.*;
import java.util.*;
/*
PROB: TwoDoors
LANG: JAVA
*/
public class TwoDoors {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int Q;
    static ArrayList<Edge>[] graph;
    static boolean[][] ans;

    public static void main(String[] args) throws IOException {
        //* parse
        setup("TwoDoors");
        N = br.nextInt();
        Q = br.nextInt();
        graph = new ArrayList[N+1]; for (int i=1;i<=N;i++) graph[i] = new ArrayList<>();
        ans = new boolean[N+1][30]; for (int i=1;i<=N;i++) for (int j=0;j<30;j++) ans[i][j]=true;
        for (int i=0;i<Q;i++){
            int u = br.nextInt();
            int v = br.nextInt();
            int weight = br.nextInt();
            graph[u].add(new Edge(u,v,weight));
            graph[v].add(new Edge(v,u,weight));
        }
        //* solve 30 times (for each bit)
        for (int i=0;i<30;i++) solveBit(i);
        //* ret
        for (int node=1;node<=N;node++){
            int ret = 0;
            for (int bit=0;bit<30;bit++){
                if (ans[node][bit])ret += (1<<bit);
            }
            out.print(ret+" ");
        }
        out.close();
    }
    public static void solveBit(int bit){
        //try to null node bits, unless its impossible
        for (int node=1;node<=N;node++){
            if (ans[node][bit]){
                boolean toggle = true;
                ans[node][bit]=false;
                for (Edge child : graph[node])
                    if (child.on[bit] && !ans[child.v][bit]) toggle=false;
                if (!toggle) ans[node][bit]=true;
            }
        }
    }
    private static class Edge {
        int u;
        int v;
        boolean[] on;
        public Edge(int u, int v, int weight){
            this.u=u;
            this.v=v;
            on = new boolean[30];
            for (int i=0;i<30;i++){
                on[i]=weight%2==1;
                if (!on[i]) {
                    ans[u][i]=false;
                    ans[v][i]=false;
                }
                weight/=2;
            }
        }
    }














        static Reader br;
        static PrintWriter out;
        public static void setup(String fileName) throws IOException {
            if (submission) {
                br = new Reader(fileName+".in");
                out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
            }
            else {
                br = new Reader();
                out = new PrintWriter(System.out);
            }
        }
        static class Reader {
            final private int BUFFER_SIZE = 1 << 16;
            private DataInputStream din;
            private byte[] buffer;
            private int bufferPointer, bytesRead;

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
                byte[] buf = new byte[100]; // line length
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
            }
        }

}
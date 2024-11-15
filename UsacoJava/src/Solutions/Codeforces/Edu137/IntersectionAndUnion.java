package Solutions.Codeforces.Edu137;

import java.io.*;
import java.util.*;
/*
PROB: IntersectionAndUnion
LANG: JAVA
Editorial: Insane segtree where operations are on matrix composition. Learned to multiply matrices (carefully)
*/
public class IntersectionAndUnion {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int[] l;
    static int[] r;
    static int N;
    static int MX= 300000;
    static long MOD = 998244353;

    static long[][] off = {{3,1},{0,2}};
    static long[][] on = {{1,1},{2,2}};
    static long[][] pass ={{1,0},{0,1}};
    static long[][] initOn = {{0},{1}};
    static long[][] initOff = {{1},{0}};

    static boolean firstCovering = false;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        l = new int[N]; r = new int[N];
        for (int i=0;i<N;i++){
            l[i]=io.nextInt();
            r[i]=io.nextInt();
        }
        //* precomp
        ArrayList<Integer>[] add = new ArrayList[MX+1]; for (int i=0;i<=MX;i++) add[i] = new ArrayList<>();
        ArrayList<Integer>[] rem = new ArrayList[MX+1]; for (int i=0;i<=MX;i++) rem[i] = new ArrayList<>();
        for (int i=0;i<N;i++){
            add[l[i]].add(i);
            rem[r[i]].add(i);
        }
        //* insane bulk dp...
        SegTree seg = new SegTree(N-1);
        long ans = 0;
        for (int i=0;i<=MX;i++){
            for (int v : add[i]){
                if (v==0) firstCovering=true;
                else seg.set(v,on);
            }
            long[][] init = firstCovering?initOn:initOff;
            ans = (ans + multMatrix(seg.sum(),init)[1][0])%MOD;
            for (int v : rem[i]){
                if (v==0) firstCovering=false;
                else seg.set(v,off);
            }
        }
        io.println(ans);
    }
    //change to int if necessary
    static long[][] multMatrix(long[][] A,long[][] B){
        //ret = AB = B(A)
        int rA = A.length;
        int cA = A[0].length;
        int rB = B.length;
        int cB = B[0].length;
        if (rB!=cA) throw new RuntimeException("ILLEGAL MATRIX MULT rA:"+rA+", cA:"+cA+", rB:"+rB+", cB:"+cB);
        long[][] ret = new long[rA][cB];
        for (int i=0;i<rA;i++){
            for (int j=0;j<cB;j++){
                for (int k=0;k<rB;k++){
                    //insert mod when necessary
                    ret[i][j] = (ret[i][j]+A[i][k]*B[k][j])%MOD;
                }
            }
        }
        return ret;
    }

    private static class SegTree {
        int size;
        long[][][] tree;
        public SegTree(int n){
            init(n);
        }
        public void init(int n){
            size = 1;
            while (size < n) size *= 2;
            tree = new long[2*size+1][0][0];
            for (int i=1;i<=n;i++) tree[i+size-1]=off;
            for (int i=n+1;i<=size;i++) tree[i+size-1]=pass;
            for (int i=size-1;i>=1;i--) tree[i]=multMatrix(tree[2*i+1],tree[2*i]);
        }
        void set(int i, long[][] x){
            i+=size-1;
            tree[i]=x;
            for (i/=2;i>=1;i/=2) tree[i]=multMatrix(tree[2*i+1],tree[2*i]);
        }
        long[][] sum() {
            return tree[1];
        }
    }













    static IO io;
    public static void main(String[] args) throws IOException {
        if (fileSubmission) {
            io = new IO(fileName);
        }
        else {
            io = new IO();
        }
        solve();
        io.close();
    }
    static class IO{
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
        PrintWriter out;

        public IO()
        {
            din = new DataInputStream(System.in);
            out = new PrintWriter(System.out);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public IO(String file_name) throws IOException
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
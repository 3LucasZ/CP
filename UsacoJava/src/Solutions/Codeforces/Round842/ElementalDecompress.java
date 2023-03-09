package Solutions.Codeforces.Round842;

import java.io.*;
import java.util.*;

public class ElementalDecompress {
    static boolean debug = false;
    
    public static void solve(int tcs) throws IOException{
        if(debug) io.println("Case: "+tcs);
        //* parse
        int N=io.nextInt();
        Integer[] A=new Integer[N];
        for(int i=0;i<N;i++){
            A[i]=io.nextInt();
        }
        Integer[] get = new Integer[N];
        for (int i=0;i<N;i++){
            get[i]=i;
        }
        Arrays.sort(get,Comparator.comparingInt(a->A[a]));
        if (debug){
            io.println("A: "+Arrays.toString(A));
            io.println("get: "+Arrays.toString(get));
        }

        //* make sure A is valid
        for (int i=0;i<N-2;i++){
            if (Objects.equals(A[get[i]],A[get[i+1]])&&Objects.equals(A[get[i+1]],A[get[i+2]])) {
                io.println("NO");
                return;
            }
        }
        for (int i=0;i<N;i++){
            if (A[get[i]]<i+1){
                io.println("NO");
                return;
            }
        }

        //* construct p,q
        int[] p = new int[N]; int pc = 1;
        int[] q = new int[N]; int qc = 1;
        boolean[] pused = new boolean[N+1];
        boolean[] qused = new boolean[N+1];

        //step one layer A
        for (int i=0;i<N;i++){
            int j = get[i];
            if (!pused[A[j]]){
                p[j]=A[j];
                pused[A[j]]=true;
                while (qused[qc]) qc++;
                q[j]=qc;
                qc++;
            }
            else {
                q[j]=A[j];
                qused[A[j]]=true;
                while (pused[pc]) pc++;
                p[j]=pc;
                pc++;
            }
        }
        //step 2 add on the remainder

        //* ret
        io.println("YES");
        for (int i=0;i<N;i++) io.print(p[i]+" ");
        io.println();
        for (int i=0;i<N;i++) io.print(q[i]+" ");
        io.println();
    }


















    static Reader io;
    public static void main(String[] args) throws IOException {
        io = new Reader();
        int T = io.nextInt();
        for (int i=1;i<=T;i++) solve(i);
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
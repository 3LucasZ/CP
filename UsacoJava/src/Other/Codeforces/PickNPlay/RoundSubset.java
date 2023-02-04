package Other.Codeforces.PickNPlay;

import java.io.*;
import java.util.*;
/*
PROB: RoundSubset
LANG: JAVA
*/
public class RoundSubset {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int P = 26;
    public static void solve() throws IOException {
        //* parse
        int N = io.nextInt();
        int K = io.nextInt();

        int[] A = new int[N];
        int[] B = new int[N];

        for (int i=0;i<N;i++){
            long j = io.nextLong();
            B[i]=log2(j);
            A[i]=log5(j);
        }
        if (debug){
            io.println("A: "+Arrays.toString(A));
            io.println("B: "+Arrays.toString(B));
        }

        int[][][] dp = new int[2][K+1][P*2+1];
        for (int i=0;i<2;i++) for (int j=0;j<=K;j++) for (int k=0;k<=P*2;k++) dp[i][j][k]=Integer.MIN_VALUE/10;
        dp[0][0][0]=0;
        dp[0][1][A[0]]=B[0];
        for (int i=0;i<N-1;i++){
            for (int j=0;j<=K;j++){
                for (int two=0;two<=(i+1)*P;two++){
                    dp[1][j][two]=Math.max(dp[1][j][two],dp[0][j][two]);
                    if (j+1<=K && two+A[i+1]<=(i+1)*P)dp[1][j+1][two+A[i+1]]=Math.max(dp[1][j+1][two+A[i+1]],dp[0][j][two]+B[i+1]);
                }
            }
            dp[0]=dp[1];
            dp[1]=new int[K+1][P*(i+3)+1];
            for (int j=0;j<=K;j++) for (int k=0;k<=P*(i+3);k++) dp[1][j][k]=Integer.MIN_VALUE/10;
        }

        int ans = 0;
        for (int i=0;i<=N*P;i++){
            ans=Math.max(ans,Math.min(dp[0][K][i],i));
        }
        io.println(ans);
    }
    static int log2(long a){
        int ret = 0;
        while (a!=0 && a%2==0) {
            ret++;
            a/=2;
        }
        return ret;
    }
    static int log5(long a){
        int ret = 0;
        while (a!=0 && a%5==0){
            ret++;
            a/=5;
        }
        return ret;
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
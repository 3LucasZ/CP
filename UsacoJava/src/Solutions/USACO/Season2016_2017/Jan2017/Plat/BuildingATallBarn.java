package Solutions.USACO.Season2016_2017.Jan2017.Plat;

import java.io.*;

/*
PROB: BuildingATallBarn
LANG: JAVA
*/
public class BuildingATallBarn {
    static boolean fileSubmission = true;
    static String fileName = "tallbarn";
    
    static boolean debug = false;

    static int N;
    static long K;
    static Long[] A;

    static long MAXA = (long) 1e12;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        K = io.nextLong(); K-=N;
        A = new Long[N];
        for (int i=0;i<N;i++) A[i]=io.nextLong();

        //* bin search maxSave
        double lo=0;double hi=MAXA;
        for (int i=0;i<100;i++){
            double mid = (lo+hi)/2;
            double tot = totCows(mid);
            if (tot<K)hi=mid;
            else if (tot>K)lo=mid;
            else {
                lo=mid;
                break;
            }
        }
        if (debug){
            io.println("minSave: "+lo);
        }
        //* ret
        double ans = 0;
        double rem = K;
        for (int i=0;i<N;i++){
            rem-=cows(i,lo);
        }
        for (int i=0;i<N;i++){
            double cows = cows(i,lo);
            if (rem<0){
                cows--;
                rem++;
            }
            ans+=A[i]/(cows+1);
        }
        io.println(Math.round(ans));
    }
    public static double totCows(double minSave){
        double ret = 0;
        for (int i=0;i<N;i++){
            double cows = cows(i,minSave);
            ret+=cows;
        }
        return ret;
    }
    public static double cows(int i, double minSave){
        double quad = (-1+Math.sqrt(1+4*A[i]/minSave))/2;
        return Math.floor(quad);
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
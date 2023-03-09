package Solutions.USACOGuide.UsacoGuideGold.SlidingWindow;

import java.io.*;
import java.util.*;
public class SlidingMedian {
    static boolean submission = false;
    static boolean debug = true;
    static int N;
    static int K;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        N = br.nextInt();
        K = br.nextInt();
        arr = new int[N];
        for (int i=0;i<N;i++){
            arr[i]= br.nextInt();
        }

        //sliding window with 2 queues - init
        PriorityQueue<Integer> left = new PriorityQueue<>((a,b)->b-a);
        PriorityQueue<Integer> right = new PriorityQueue<>();
        for (int i=0;i<K;i++) left.add(arr[i]);
        for (int i=0;i<K/2;i++) right.add(left.poll());
        //slide
        out.print(left.peek()+" ");
        for (int i=1;i<N-K+1;i++){
            //remove
            if (left.contains(arr[i-1])) left.remove(arr[i-1]);
            else right.remove(arr[i-1]);
            //left size is too small
            if (left.size()<=right.size()){
                right.add(arr[i+K-1]);
                left.add(right.poll());
            }
            //right size is too small
            else {
                left.add(arr[i+K-1]);
                right.add(left.poll());
            }
            //print median
            out.print(left.peek()+" ");
        }
        out.close();
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
            byte[] buf = new byte[64]; // line length
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
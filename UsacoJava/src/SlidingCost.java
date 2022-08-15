

import java.io.*;
import java.util.*;
/*
PROB: SlidingCost
LANG: JAVA
*/
public class SlidingCost {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int K;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        //st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
           //arr[i] = Integer.parseInt(st.nextToken());
            arr[i]=br.nextInt();
        }

        //sliding window with 2 queues - init
        PriorityQueue<Integer> left = new PriorityQueue<>((a,b)->b-a);
        long leftSum = 0;
        PriorityQueue<Integer> right = new PriorityQueue<>();
        long rightSum = 0;
        for (int i=0;i<K;i++) {
            left.add(arr[i]);
            leftSum+=arr[i];
        }
        for (int i=0;i<K/2;i++) {
            int add = left.poll();
            right.add(add);
            rightSum+=add;
            leftSum-=add;
        }

        //slide
        long median = left.peek();
        out.print((median*left.size()-leftSum-median*right.size()+rightSum)+" ");
        for (int i=1;i<N-K+1;i++){
            //remove
            if (left.contains(arr[i-1])) {
                left.remove(arr[i-1]);
                leftSum -= arr[i-1];
            }
            else {
                right.remove(arr[i-1]);
                rightSum -= arr[i-1];
            }
            //left size is too small
            if (left.size()<=right.size()){
                right.add(arr[i+K-1]);
                rightSum+=arr[i+K-1];
                int poll = right.poll();
                left.add(poll);
                rightSum-=poll;
                leftSum+=poll;
            }
            //right size is too small
            else {
                left.add(arr[i+K-1]);
                leftSum+=arr[i+K-1];
                int poll = left.poll();
                right.add(poll);
                rightSum+=poll;
                leftSum-=poll;
            }
            //print cost
            median = left.peek();
            out.print((median*left.size()-leftSum-median*right.size()+rightSum)+" ");
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
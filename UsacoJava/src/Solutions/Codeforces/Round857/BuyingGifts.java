package Solutions.Codeforces.Round857;

import java.io.*;
import java.util.*;

public class BuyingGifts {
    static boolean debug = false;

    static int N;
    static int[] A;
    static int[] B;
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        A = new int[N];
        B = new int[N];
        for (int i=0;i<N;i++){
            A[i]=io.nextInt();
            B[i]=io.nextInt();
        }

        //* sort based on A (rank trick)
        Integer[] r = new Integer[N];
        for (int i=0;i<N;i++) r[i]=i;
        Arrays.sort(r,Comparator.comparingInt(a->A[a]));

        //* brute force
        Multiset opt = new Multiset();
        Multiset must = new Multiset(); for (int i=0;i<N;i++) must.add(B[i]);
        int ans = Integer.MAX_VALUE;
        for (int j=0;j<N;j++){
            int i = r[j];
            must.remove(B[i]);
            int mx = must.sz==0?0:must.ms.lastKey();
            Integer leq=opt.ms.floorKey(A[i]);
            Integer geq=opt.ms.ceilingKey(A[i]);
            ans=Math.min(ans,Math.abs(A[i]-mx));
            if (leq!=null && leq>mx){
                ans=Math.min(ans, Math.abs(A[i]-leq));
            }
            if (geq!=null && geq>mx){
                ans=Math.min(ans, Math.abs(A[i]-geq));
            }
            opt.add(B[i]);
        }

        //* ret
        io.println(ans);
    }
    private static class Multiset {
        public TreeMap<Integer, Integer> ms = new TreeMap<>();
        private int sz = 0;
        public boolean contains(int x){
            return ms.containsKey(x);
        }
        public void add(int x){add(x,1);}
        public void add(int x, int freq){
            if (!ms.containsKey(x))ms.put(x,0);
            ms.put(x,ms.get(x)+freq);
            sz+=freq;
        }
        public void remove(int x){
            remove(x,1);}
        public void remove(int x, int freq){
            ms.put(x,ms.get(x)-freq);
            if (ms.get(x)<=0) ms.remove(x);
            sz-=freq;
        }
        public void removeKey(int x){
            ms.remove(x);
        }
        public int get(int x){
            if (ms.containsKey(x)) return ms.get(x);
            return 0;
        }
        public Iterator<Integer> iterator(){
            return ms.keySet().iterator();
        }
        public int size(){
            return sz;
        }
        public Set<Integer> keySet(){
            return ms.keySet();
        }
        public String toString(){
            return ms.toString();
        }
    }




















    static IO io;
    public static void main(String[] args) throws IOException {
        io = new IO(debug);
        int T = io.nextInt();
        for (int i=1;i<=T;i++) solve(i);
        io.close();
    }
    static <T> T last(ArrayList<T> list){
    return list.get(list.size()-1);
}
    static <T> void swap(T a, T b){
        T tmp = a;
        a = b;
        b = tmp;
    }
static String binToStr(int bin,int len){
        String ret="";
        for(int i=0;i<len;i++){
            ret+=bin%2;
            bin/=2;
        }
        return ret;
    }
static int log2(int num){
    return (int)(Math.log(num)/Math.log(2));
}
static void reverse(int[] arr){
    for (int i=0;i<arr.length/2;i++){
        int tmp = arr[i];
        arr[i]=arr[arr.length-1-i];
        arr[arr.length-1-i]=tmp;
    }
}
    static class IO{
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
        PrintWriter out;
        boolean debug;

        public IO(boolean debug)
        {
            din = new DataInputStream(System.in);
            out = new PrintWriter(System.out);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
            this.debug=debug;
        }

        public IO(boolean debug, String file_name) throws IOException
        {
            din = new DataInputStream(
                    new FileInputStream(file_name+".in"));
            out = new PrintWriter(new FileWriter(file_name+".out"));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
            this.debug=debug;
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
package Other.Codeforces.PickNPlay;

import java.io.*;
import java.util.*;
/*
TLE on CF because problem setter is stupid
 */
public class MathTest{
    static boolean debug=false;

    static int N, M;
    static int[] X;
    static boolean[][] right;

    public static void solve(int tcs) throws IOException{
        if(debug) io.println("Case: "+tcs);
        //* parse
        N=io.nextInt();
        M=io.nextInt();
        X=new int[N];
        for(int i=0;i<N;i++) X[i]=io.nextInt();
        right=new boolean[N][M];
        for(int i=0;i<N;i++){
            String str=io.readLine(M+1);
            for(int j=0;j<M;j++){
                right[i][j]=str.charAt(j)=='1';
            }
        }

        //* brute force
        ans=-1;

        for(int mask=0;mask<(1<<N);mask++){
            test(mask);
        }

        //* ret
        for(int a: win) io.print(a+" ");
        io.println();
    }

    static int ans;
    static int[] win;

    static boolean big(int i, int mask){
        return (mask&(1<<i))!=0;
    }
    static void test(int mask){
        //contribution of question i
        int[] val=new int[M];
        for(int j=0;j<M;j++){
            for(int i=0;i<N;i++){
                if (right[i][j]){
                    if(big(i,mask)) val[j]++;
                    else val[j]--;
                }
            }
        }

        //sort of val
        Integer[] r = new Integer[M]; for (int i=0;i<M;i++) r[i]=i;
        Arrays.sort(r,Comparator.comparingInt(a->val[a]));

        //create perm
        int[] perm=new int[M];
        for (int i=0;i<M;i++){
            perm[r[i]]=i+1;
        }

        //calc tot surprise
        int cur = 0;
        for (int i=0;i<N;i++) cur+=big(i,mask)?-X[i]:X[i];
        for(int i=0;i<M;i++)cur += (i+1)*val[r[i]];

        //upd if better answer found
        if(cur>ans){
            ans=cur;
            win=perm;
        }
    }


    static Reader io;

    public static void main(String[] args) throws IOException{
        io=new Reader();
        int T=io.nextInt();
        for(int i=1;i<=T;i++) solve(i);
        io.close();
    }

    static class Reader{
        final private int BUFFER_SIZE=1<<16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
        PrintWriter out;

        public Reader(){
            din=new DataInputStream(System.in);
            out=new PrintWriter(System.out);
            buffer=new byte[BUFFER_SIZE];
            bufferPointer=bytesRead=0;
        }

        public Reader(String file_name) throws IOException{
            din=new DataInputStream(
                    new FileInputStream(file_name+".in"));
            out=new PrintWriter(new FileWriter(file_name+".out"));
            buffer=new byte[BUFFER_SIZE];
            bufferPointer=bytesRead=0;
        }

        public String readLine() throws IOException{
            byte[] buf=new byte[64]; // IMPORTANT: read line length
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

        public int nextInt() throws IOException{
            int ret=0;
            byte c=read();
            while(c<=' '){
                c=read();
            }
            boolean neg=(c=='-');
            if(neg)
                c=read();
            do{
                ret=ret*10+c-'0';
            }while((c=read())>='0'&&c<='9');

            if(neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException{
            long ret=0;
            byte c=read();
            while(c<=' ')
                c=read();
            boolean neg=(c=='-');
            if(neg)
                c=read();
            do{
                ret=ret*10+c-'0';
            }while((c=read())>='0'&&c<='9');
            if(neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException{
            double ret=0, div=1;
            byte c=read();
            while(c<=' ')
                c=read();
            boolean neg=(c=='-');
            if(neg)
                c=read();

            do{
                ret=ret*10+c-'0';
            }while((c=read())>='0'&&c<='9');

            if(c=='.'){
                while((c=read())>='0'&&c<='9'){
                    ret+=(c-'0')/(div*=10);
                }
            }

            if(neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException{
            bytesRead=din.read(buffer,bufferPointer=0,
                    BUFFER_SIZE);
            if(bytesRead==-1)
                buffer[0]=-1;
        }

        private byte read() throws IOException{
            if(bufferPointer==bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException{
            if(din==null)
                return;
            din.close();
            out.close();
        }

        void println(){
            if(debug) System.out.println();
            else out.println();
        }

        void println(Object obj){
            if(debug) System.out.println(obj);
            else out.println(obj);
        }

        void print(Object obj){
            if(debug) System.out.print(obj);
            else out.print(obj);
        }
    }
}
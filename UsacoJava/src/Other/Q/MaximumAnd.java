package Other.Q;

import java.io.*;
import java.util.*;

public class MaximumAnd{
    static boolean debug=false;

    static int N;
    static int[] A;
    static int[] B;
    static final int bits=30;

    public static void solve(int tcs) throws IOException{
        if(debug) io.println("Case: "+tcs);
        //* parse
        N=io.nextInt();
        A=new int[N];
        for(int i=0;i<N;i++){
            int num=io.nextInt();
//            for (int j=0;j<bits;j++){
//                int res = num&(1<<j);
//                A[i][j]=(res==0)?0:1;
//            }
            A[i]=num;
        }
        B=new int[N];
        for(int i=0;i<N;i++){
            int num=io.nextInt();
//            for (int j=0;j<bits;j++){
//                int res = num&(1<<j);
//                B[i][j]=(res==0)?0:1;
//            }
            B[i]=num;
        }
        //* Smart Q
        //init
        boolean[] ok=new boolean[bits+1];
        Arrays.fill(ok,true);
        ok[bits]=false;
        Queue<Item> Q=new LinkedList<>();
        Item first=new Item(bits-1,false);
        for(int i=0;i<N;i++){
            first.Aset.add(i);
            first.Bset.add(i);
        }
        Q.add(first);
        //propagate
        int ans=0;
        while(!Q.isEmpty()){
            Item next=Q.poll();
            if(next.bit<0||next.Bset.size()==0) continue;
            if((ok[next.bit+1]&&next.isSplit)||(!ok[next.bit+1]&&!next.isSplit)){
                Item newNonSplit=new Item(next.bit-1,false);
                newNonSplit.Aset=next.Aset;
                newNonSplit.Bset=next.Bset;
                Item newSplit01=new Item(next.bit-1,true);
                Item newSplit10=new Item(next.bit-1,true);
                for(int a: next.Aset){
                    int res=A[a]&(1<<next.bit);
                    if(res==0) newSplit01.Aset.add(a);
                    else newSplit10.Aset.add(a);
                }
                for(int b: next.Bset){
                    int res=B[b]&(1<<next.bit);
                    if(res!=0) newSplit01.Bset.add(b);
                    else newSplit10.Bset.add(b);
                }
                if(newSplit01.Aset.size()!=newSplit01.Bset.size()||!ok[next.bit]){
                    ok[next.bit]=false;
                } else{
                    Q.add(newSplit10);
                    Q.add(newSplit01);
                }
                Q.add(newNonSplit);
            }
        }
        //* ret
        for(int i=0;i<bits;i++){
            if(ok[i]){
                ans+=(1<<i);
            }
        }
        io.println(ans);
    }

    private static class Item{
        int bit;
        ArrayList<Integer> Aset=new ArrayList<>();
        ArrayList<Integer> Bset=new ArrayList<>();
        boolean isSplit;

        public Item(int bit,boolean isSplit){
            this.bit=bit;
            this.isSplit=isSplit;
        }
    }


    static IO io;

    public static void main(String[] args) throws IOException{
        io = new IO();
        int T=io.nextInt();
        for(int i=1;i<=T;i++) solve(i);
        io.close();
    }

    static class IO{
        final private int BUFFER_SIZE=1<<16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
        PrintWriter out;

        public IO(){
            din=new DataInputStream(System.in);
            out=new PrintWriter(System.out);
            buffer=new byte[BUFFER_SIZE];
            bufferPointer=bytesRead=0;
        }

        public IO(String file_name) throws IOException{
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
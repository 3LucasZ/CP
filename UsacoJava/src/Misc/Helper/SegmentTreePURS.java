package Misc.Helper;
/*
Best Segment Tree Implementation (PURS)
Test using: CSES Dynamic Range Sum Queries
 */
import java.io.*;
import java.util.*;

public class SegmentTreePURS {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    //param
    static int N;
    static int Q;

    public static void main(String[] args) throws IOException {
        //submission();
        test();
        out.close();
    }
    public static void test(){
        N = 8;
        int[] arr = {0, 7, 9, 6, 2, 3, 3, 5, 7};
        SegTree segTree = new SegTree(N, arr);

        for (int i=1;i<=N;i++){
            for (int j=i;j<=N;j++){
                out.println("["+i+", "+j+"]: "+segTree.sum(i,j));
            }
        }
    }
    public static void submission() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        SegTree seg = new SegTree(N);
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            seg.add(i, Integer.parseInt(st.nextToken()));
        }

        for (int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (op==1)seg.set(a, b);
            else out.println(seg.sum(a, b));
        }
    }
    private static class SegTree {
        //1-indexed
        //range is []
        int size;
        long[] tree;
        public SegTree(int n){
            init(n);
        }
        public SegTree(int n, int[] arr){
            init(n);
            for (int i=1;i<=n;i++){
                tree[i+size-1]=arr[i];
            }
            for (int i=size-1;i>=1;i--){
                tree[i]=tree[i*2]+tree[i*2+1];
            }
        }
        public void init(int n){
            size = 1;
            while (size < n) size *= 2;
            tree = new long[2*size+1];
        }
        void set (int k, int x){
            add(k,x-tree[k+size-1]);
        }
        void add(int k, long x){
            k+=size-1;
            tree[k]+=x;
            for (k/=2;k>=1;k/=2){
                tree[k]=tree[2*k]+tree[2*k+1];
            }
        }
        long sum(int a, int b) {
            a+=size-1;
            b+=size-1;
            long ret = 0;
            while (a<=b){
                if (a%2==1) ret+=tree[a++];
                if (b%2==0) ret+=tree[b--];
                a/=2;
                b/=2;
            }
            return ret;
        }
    }
}

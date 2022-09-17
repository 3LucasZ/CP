package Helper.PURS;
/*
Best Segment Tree Implementation (RUPS)
Test using: CSES Range Update Queries
 */
import java.io.*;
import java.util.*;

public class SegmentTreeRUPS {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int Q;
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        SegTree seg = new SegTree(N);
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            seg.add(i,i,Integer.parseInt(st.nextToken()));
        }

        //logic
        for (int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            if (op==2){
                out.println(seg.get(Integer.parseInt(st.nextToken())));
            }
            else {
                seg.add(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
        }
        out.close();
    }
    private static class SegTree {
        //1-indexed
        //range is []
        int size;
        long[] tree;
        public SegTree(int n){
            size = 1;
            while (size < n) size *= 2;
            tree = new long[2*size+1];
        }
        long get(int k){
            long ret = 0;
            for (k+=size-1;k>=1;k/=2){
                ret+=tree[k];
            }
            return ret;
        }
        void add(int a, int b, int x) {
            a+=size-1;
            b+=size-1;
            while (a<=b){
                if (a%2==1) tree[a++]+=x;
                if (b%2==0) tree[b--]+=x;
                a/=2;
                b/=2;
            }
        }
    }
}

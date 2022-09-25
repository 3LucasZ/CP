package USACOGuide.UsacoGuideGold.PointUpdateRangeSum;

import java.io.*;
import java.util.*;

public class DynamicRangeMinimumQueries {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int Q;

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        SegTree seg = new SegTree(N);
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            seg.set(i, Integer.parseInt(st.nextToken()));
        }

        for (int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (op==1)seg.set(a-1, b);
            else out.println(seg.res(a-1, b));
        }
        out.close();
    }
    private static class SegTree {
        int size;
        long[] sums;
        public SegTree(int n){
            size = 1;
            while (size < n) size *= 2;
            sums = new long[2*size];
        }
        void set(int i, int v){
            set(i, v, 0, 0, size);
        }
        void set(int i, int v, int x, int lx, int rx){
            if (rx-lx==1){
                sums[x]=v;
                return;
            }
            int m = (lx+rx)/2;
            if (i<m) set(i,v,2*x+1,lx,m);
            else set(i,v,2*x+2,m,rx);
            sums[x]=op(sums[2*x+1],sums[2*x+2]);
        }
        long res(int l, int r) {
            return res(l,r,0,0,size);
        }
        long res (int l, int r, int x, int lx, int rx){
            if (lx >= r || l >= rx) return Integer.MAX_VALUE;
            if (lx >= l && rx <= r) return sums[x];
            int m = (lx+rx)/2;
            long s1 = res(l,r,2*x+1,lx,m);
            long s2 = res(l,r,2*x+2,m,rx);
            return op(s1,s2);
        }
        long op(long a, long b) {
            return Math.min(a,b);
        }
    }
}

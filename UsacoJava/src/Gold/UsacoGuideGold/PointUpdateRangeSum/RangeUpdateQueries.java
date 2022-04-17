package Gold.UsacoGuideGold.PointUpdateRangeSum;

import java.io.*;
import java.util.*;

/*
CSES Problem Set
Range Update Queries
USACO Gold Guide - PURS - Easy

point get value
range update +

very nice problem!!
 */
public class RangeUpdateQueries {
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
        for (int i=0;i<N;i++){
            seg.update(i,i+1,Integer.parseInt(st.nextToken()));
        }
        //seg.debug();
        //logic
        for (int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            if (op==2){
                out.println(seg.get(Integer.parseInt(st.nextToken())-1));
            }
            else {
                seg.update(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            //seg.debug();
        }
        out.close();
    }
    private static class SegTree {
        int size;
        long[] tree;
        public SegTree(int n){
            size = 1;
            while (size < n) size *= 2;
            tree = new long[2*size];
        }
        long get(int i){
            i+=size-1;
            long sum = 0;
            while (i!=0){
                sum += tree[i];
                i=(i-1)/2;
            }
            sum += tree[0];
            return sum;
        }

        void update(int l, int r, int amt) {
            update(l,r, 0,0,size,amt);
        }
        void update (int l, int r, int x, int lx, int rx, int amt){
            if (lx >= r || l >= rx) return;
            if (lx >= l && rx <= r) {
                tree[x]+=amt;
                return;
            }
            int m = (lx+rx)/2;
            update(l,r,2*x+1,lx,m,amt);
            update(l,r,2*x+2,m,rx,amt);
        }
        void debug(){
            System.out.println(Arrays.toString(tree));
        }
    }
}

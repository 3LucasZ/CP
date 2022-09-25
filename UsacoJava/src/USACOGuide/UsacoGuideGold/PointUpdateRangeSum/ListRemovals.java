package USACOGuide.UsacoGuideGold.PointUpdateRangeSum;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
List Removals
USACO Gold Guide - PURS - Easy
Thoughts:
"Easy"
glimpsed at solution first
Segment tree jumping
custom lx rx
demonstrates REAL understanding of a seg tree,
how to use and implement,
different customizations
very nice problem!
 */
public class ListRemovals {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        SegTree seg = new SegTree(N);
        for (int i=0;i<N;i++){
            seg.add(i);
            A[i]=Integer.parseInt(st.nextToken());
        }
        //seg.debug();

        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
//            System.out.println("ROUND: "+i);
            int index = seg.get(Integer.parseInt(st.nextToken())-1);
//            System.out.println("array index: "+index);
//            System.out.println("value: "+A[index]);
            seg.remove(index);
//            seg.debug();
//            System.out.println();
//            System.out.println();
            out.print(A[index]+" ");
        }
        out.close();
    }
    private static class SegTree {
        int size;
        int n;
        int[] tree;
        public SegTree(int n){
            this.n=n;
            size=1;
            while (size<n) size*=2;
            tree = new int[2*size];
        }
        public void add(int i){
            i+=size-1;
            while (i>0){
                tree[i]++;
                i=(i-1)/2;
            }
            tree[0]++;
        }
        public int get(int i){
            return get(i, 0, 0, n);
        }
        public int get(int i, int x, int lx, int rx){
            if (i<lx||i>=rx) return -1;
            if (rx-lx==1&&x>size-2) return x-size+1;
            int m = lx+tree[2*x+1];
            int a = get(i,2*x+1,lx,m);
            int b = get(i,2*x+2,m,rx);
            return Math.max(a,b);
        }
        public void remove(int i){
            i+=size-1;
            while (i>0){
                tree[i]--;
                i=(i-1)/2;
            }
            tree[0]--;
            n--;
        }
        public void debug(){
            System.out.println(Arrays.toString(tree));
        }
    }
}

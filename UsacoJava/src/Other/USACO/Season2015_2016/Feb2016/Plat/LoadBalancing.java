package Other.USACO.Season2015_2016.Feb2016.Plat;

import java.io.*;
import java.util.*;

/*
PROB: LoadBalancing
LANG: JAVA
*/
public class LoadBalancing {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static Point[] points;

    //BIT
    static BIT lbit;
    static BIT rbit;

    public static void main(String[] args) throws IOException {
        //parse
        setup("balancing");
        N = Integer.parseInt(br.readLine());
        points = new Point[N];
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x,y);
        }
        //compress the y values
        Arrays.sort(points,(a,b)->a.y-b.y);
        int ly=points[0].y;
        int cy=1;
        for (int i=0;i<N;i++){
            if (points[i].y==ly) {
                ly=points[i].y;
                points[i].y=cy;
            }
            else {
                ly=points[i].y;
                points[i].y=++cy;
            }
        }
        //compress the x values and make xth arraylist contain all its y values in increasing order
        Arrays.sort(points,(a,b)->{
            if (a.x==b.x) return a.y-b.y;
            return a.x-b.x;
        });
        ArrayList<Integer>[] ys = new ArrayList[N+1]; for (int i=1;i<=N;i++) ys[i] = new ArrayList<>();
        int lx=points[0].x;
        int cx=1;
        for (int i=0;i<N;i++){
            if (points[i].x==lx) {
                lx=points[i].x;
                points[i].x=cx;
            }
            else {
                lx=points[i].x;
                points[i].x=++cx;
            }
            ys[cx].add(points[i].y);
        }
        if (debug) System.out.println("compressed points: "+Arrays.toString(points));
        if (debug) System.out.println("ys: "+Arrays.toString(ys));
        //create the 2 bits
        lbit = new BIT(N);
        rbit = new BIT(N);
        for (int i=0;i<N;i++) rbit.update(points[i].y,1);
        //search x
        int ans = Integer.MAX_VALUE;
        for (int x=1;x<=cx;x++){
            //update bits
            for (int y : ys[x]){
                lbit.update(y,1);
                rbit.update(y,-1);
            }
            //binary search for optimal
            int lo=1;int hi=cy;
            while (lo<hi){
                //find mid's S12 and S34 and update lo, hi
                int mid = (lo+hi-1)/2;
                int s1 = lbit.rangeSum(mid+1,cy);
                int s2 = rbit.rangeSum(mid+1,cy);
                int s3 = lbit.rangeSum(1,mid);
                int s4 = rbit.rangeSum(1,mid);
                int s12 = Math.max(s1,s2);
                int s34 = Math.max(s3,s4);
                //update lo and hi
                if (s12>=s34){
                    lo=mid+1;
                } else {
                    hi=mid;
                }
            }
            //update ans
            ans=Math.min(Math.min(ans,getM(lo-1,cy)),Math.min(getM(lo,cy),getM(lo+1,cy)));
            if (debug) System.out.println("x: "+x+", y: "+lo);
        }
        //ret
        out.println(ans);
        out.close();
    }
    public static int getM(int y, int cy){
        int s1 = lbit.rangeSum(1,y);
        int s2 = rbit.rangeSum(1,y);
        int s3 = lbit.rangeSum(y+1,cy);
        int s4 = rbit.rangeSum(y+1,cy);
        return Math.max(Math.max(s1,s2),Math.max(s3,s4));
    }
    private static class Point {
        int x;
        int y;
        public Point(int x, int y){
            this.x=x;
            this.y=y;
        }
        public String toString(){
            return "["+ x +", "+y+"]";
        }
    }private static class BIT {
        //must be 1 indexed
        //range sum is inclusive
        int K;
        int[] A;
        int[] bit;

        public BIT(int K) {
            this.K = K;
            bit = new int[K + 1];
            A = new int[K + 1];
        }

        public void set(int i, int val) {
            update(i, val - A[i]);
        }

        public void update(int i, int val) {
            A[i] += val;
            while (i <= K) {
                bit[i] += val;
                i += i & (-i);
                //System.out.println("STUCK IN UPDATE");
            }
        }

        public int preSum(int i) {
            int sum = 0;
            while (i > 0) {
                sum += bit[i];
                i -= i & (-i);
                //System.out.println("STUCK IN PRESUM");
            }
            return sum;
        }

        public int rangeSum(int lo, int hi) {
            return preSum(hi) - preSum(lo - 1);
        }
    }




















    static BufferedReader br;
    static PrintWriter out;
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}
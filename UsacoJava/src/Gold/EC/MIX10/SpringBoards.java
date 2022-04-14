package Gold.EC.MIX10;

import java.io.*;
import java.util.*;
/*
Spring Boards
USACO Gold Advanced B - MIX 10
First every sqrt decomposition problem!
DP problem (optimize)!
VERY VERY FUN!!!
3 hours debugging, while processing interval by end point.
trick was to switch to process by start point and then easy. *roll eyes*
process point by x value
if start point, update SD for it
if end point, update SD for it
print best easily
SD tester and unit testing (BULLET PROOF!!!)
SD standalone copy class made
 */
public class SpringBoards {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    static int N;
    static int P;
    static Point[] points;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        points = new Point[2*P];

        for (int i=0;i<P;i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()); int y1=Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken()); int y2=Integer.parseInt(st.nextToken());
            Point s = new Point(x1,y1);
            Point e = new Point(x2,y2);
            SpringBoard net = new SpringBoard(s,e);
            points[2*i]=s;s.sb=net;
            points[2*i+1]=e;e.sb=net;
        }

        Arrays.sort(points,(a,b)->{
            if (a.y==b.y) return a.x-b.x;
            return a.y-b.y;
        });
        for (int i=0;i<2*P;i++) points[i].id=i;
        if (debug) System.out.println(Arrays.toString(points));

        Arrays.sort(points,(a,b)->{
            if (a.x==b.x) return a.y-b.y;
            return a.x-b.x;
        });
        if (debug) System.out.println(Arrays.toString(points));

        RangeQuery rq = new RangeQuery(2*P);

        for (int i=0;i<2*P;i++){
            if (points[i]==points[i].sb.s){
                rq.update(points[i].id, rq.get(0,points[i].id));
            }
            else {
                rq.update(points[i].id, rq.arr[points[i].sb.s.id]+points[i].sb.len);
            }
        }
        if (debug) System.out.println(rq.get(0,2*P-1));
        out.println(2*N-rq.get(0,2*P-1));
        out.close();
    }
    private static class SpringBoard {
        Point s;Point e;int len;
        public SpringBoard(Point s, Point e){this.s=s;this.e=e;len=e.x-s.x+e.y-s.y;}
        public String toString(){
            return "["+s+" "+e+" "+len+"]";
        }
    }
    private static class Point {
        int x;int y;int id;SpringBoard sb;
        public Point (int x, int y){this.x=x;this.y=y;}
        public String toString(){
            return "["+x+", "+y+": "+id+"]";
        }
    }

    private static class RangeQuery {
        int A;
        int B;
        int[] arr;
        int[] decomp;

        public RangeQuery(int A){
            this.A=A;
            arr = new int[A];
            B = (int)Math.sqrt(A);
            decomp = new int[A];
        }

        public void update(int i, int val){
            arr[i]=val;
            decomp[i/B]=Math.max(decomp[i/B], val);
        }

        public int get(int i, int j){
            int ii=i/B+1;
            int jj=j/B;
            int ret = 0;
            for (int k=i;k<Math.min(B*ii,j);k++) ret=Math.max(ret,arr[k]);
            for (int k=ii;k<jj;k++) ret=Math.max(ret,decomp[k]);
            for (int k=Math.max(B*jj,i);k<=j;k++) ret=Math.max(ret,arr[k]);
            return ret;
        }
    }
}

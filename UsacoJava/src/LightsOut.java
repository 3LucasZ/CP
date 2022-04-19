import java.io.*;
import java.util.*;

public class LightsOut {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static Point[] points;
    static long[] distance;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("lightsout.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("lightsout.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        N = Integer.parseInt(br.readLine());
        points = new Point[N];
        distance = new long[N+1];
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i]=new Point(x,y);
            if (i!=0) distance[i]=distance[i-1]+len(i-1);
        }
        distance[N]=distance[N-1]+len(N-1);

        if (!submission) {
            for (int i=0;i<N;i++) System.out.print(rightTurn(i)+" ");
            System.out.println();
            for (int i=0;i<=N;i++) System.out.print(distance[i]+" ");
            System.out.println();
            System.out.println();
        }

        //preprocess
        MyHasher.init();

        //logic
        long ans = 0;
        for (int i=1;i<N;i++){
            for (int j=i+1;j<=N;j++){
                if (j==N) {
                    long cost1 = Math.min(distance[i], distance[N]-distance[i]);
                    long cost2 = distance[j]-distance[i]+Math.min(distance[j],distance[N]-distance[j]);
                    ans = Math.max(ans,cost2-cost1);
                    if (!submission) System.out.println("i: "+i+", j: "+j+", cost1: "+cost1+", cost2: "+cost2);

                    break;
                }

                long segHash = MyHasher.getHash(i,j);
                int len = j-i;
                boolean unique = true;
                for (int k=0;k<N;k++){
                    if (k!=i&&k+len!=N&&MyHasher.getHash(k,k+len)==segHash) unique = false;
                }

                if (unique){
                    long cost1 = Math.min(distance[i], distance[N]-distance[i]);
                    long cost2 = distance[j]-distance[i]+Math.min(distance[j],distance[N]-distance[j]);
                    ans = Math.max(ans,cost2-cost1);

                    if (!submission) System.out.println("i: "+i+", j: "+j+", cost1: "+cost1+", cost2: "+cost2);
                    break;
                }

            }
        }

        //turn in answer
        out.println(ans);
        out.close();
    }

    //helper methods and classes
    static int rightTurn(int i){
        Point p1 = points[(i-1+N)%N].clone();
        Point p2 = points[i].clone();
        Point p3 = points[(i+1)%N].clone();
        p2.subtract(p1);
        p3.subtract(p1);
        return p3.cross(p2) < 0 ? 100001:1;
    }
    static int len(int i){
        Point p1 = points[i].clone();
        Point p2 = points[(i+1)%N].clone();
        p2.subtract(p1);
        return Math.abs(p2.x+p2.y);
    }

    private static class Point {
        int x;
        int y;
        public Point (int x, int y){
            this.x=x;
            this.y=y;
        }
        public Point clone(){
            return new Point(x,y);
        }
        public int cross(Point other){
            return x*other.x+y*other.y;
        }
        public void subtract(Point other){
            x-=other.x;
            y-=other.y;
        }
    }

    private static class MyHasher {
        //preprocess + hashing
        static final long A = (long) 1e9+7;
        static final long B = (long) 1e9+9;
        static long[] pow;
        static long[] preHash;
        static void init() {
            pow = new long[2*N];
            pow[0]=1;
            for (int i=1;i<2*N;i++){
                pow[i]=(pow[i-1]*A)%B;
            }

            preHash = new long[2*N];
            preHash[0] = rightTurn(0);
            for (int i=1;i<2*N;i++){
                preHash[i] = (preHash[i - 1] * A + len(i%N)*rightTurn(i%N)) % B;
            }
        }
        static long getHash(int l, int r){
            if (l==0) return preHash[r];
            return (((preHash[r]-preHash[l-1]*pow[r-l+1])%B)+B)%B;
        }
    }
}

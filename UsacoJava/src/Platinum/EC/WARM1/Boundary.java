package Platinum.EC.WARM1;

import java.io.*;
import java.util.StringTokenizer;

public class Boundary {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int R;

    static Point fj;
    public static void main(String[] args) throws IOException {
        //parse
        setup("boundary");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        fj = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));

        int[] presum = new int[4*N];
        //rocks
        for (int i=0;i<R;i++){
            int P = Integer.parseInt(br.readLine());
            Point[] points = new Point[P+1];
            for (int j=0;j<P;j++){
                st = new StringTokenizer(br.readLine());
                Point vertex = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
                points[j]=vertex;
            }
            //sentinel
            points[P]=points[0];

            for (int j=0;j<P;j++){
                Point p1 = points[j];
                Point p2 = points[j+1];

                double i1 = getProjectionIndex(p1);
                double i2 = getProjectionIndex(p2);

                //swap i1 and i2 if needed and find actual index
                if (Point.ccw(p1,fj,p2)>0){
                    //swap
                    double tmp = i1;
                    i1=i2;
                    i2=tmp;
                }
                int ii1 = (int) Math.ceil(i1);
                int ii2 = (int) Math.floor(i2);

                //sweep on presum
                presum[(ii1)%(4*N)]++;
                presum[(ii2+1)%(4*N)]--;
            }
        }

        //calculate presum and ans
        int ans = 0;
        int[] visited = new int[4*N];
        int run = 0;
        int min = 0;
        for (int i=0;i<4*N;i++){
            run+=presum[i];
            visited[i]=run;
            min = Math.min(min,run);
        }
        for (int i=0;i<4*N;i++){
            visited[i]-=min;
            if (visited[i]==0) ans++;
        }

        out.println(ans);
        out.close();
    }
    public static double getProjectionIndex(Point vertex){
        Vector v = new Vector(fj,vertex);
        double x,y,k;

        //Bottom
        k=(double)(-fj.y)/v.y;
        x=fj.x+k*v.x;
        if (k>0 && x>=0 && x<=N) return x;


        //Right
        k=(double)(N-fj.x)/v.x;
        y=fj.y+k*v.y;
        if (k>0 && y>=0 && y<=N) return N+y;

        //Top
        k=(double)(N-fj.y)/v.y;
        x=fj.x+k*v.x;
        if (k>0 && x>=0 && x<=N) return 2*N+(N-x);

        //Left
        k=(double)(-fj.x)/v.x;
        y=fj.y+k*v.y;
        if (k>0 && y>=0 && y<=N) return 3*N+(N-y);

        return 0;
    }
    private static class Vector {
        int x;
        int y;
        public Vector(Point init, Point terminal){
            x=terminal.x-init.x;
            y=terminal.y-init.y;
        }
    }
    private static class Point {
        int x;
        int y;
        public Point(int x, int y){
            this.x=x;
            this.y=y;
        }
        public double distanceTo(Point o){
            return Math.hypot(this.x-o.x,this.y-o.y);
        }
        public static int ccw(Point a, Point b, Point c){
            double area =  (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
            if (area < 0) return -1;
            else if (area > 0) return 1;
            else return 0;
        }
        public static boolean collinear(Point a, Point b, Point c){
            return ccw(a,b,c)==0;
        }
        public String toString(){
            return "["+x+", "+y+"]";
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

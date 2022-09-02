package USACO.Platinum.EC.GEO4;

import java.io.*;
import java.util.*;

public class EquilateralTriangles {

    static boolean submission = true;
    static boolean debug = true;

    static int N;
    static int ans = 0;
    static ArrayList<Point> points = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //parse
        setup("triangles");
        N = Integer.parseInt(br.readLine());
        for (int x=0;x<N;x++){
            String str = br.readLine();
            for (int y=0;y<N;y++){
                if (str.charAt(y)=='*'){
                    points.add(new Point(1+N+x-y, 1+x+y));
                }
            }
        }

        //cases: horiz, vert, 45
        for (int i=0;i<2;i++){
            //for each y, store list of x with the y
            //for each y, store list of presums of points on the line
            ArrayList<Integer>[] yxs = new ArrayList[2*N+2]; for (int y=1;y<=2*N+1;y++) yxs[y]=new ArrayList<>();
            int[][] presum = new int[2*N+2][2*N+2];
            for (Point point : points){
                yxs[point.y].add(point.x);
                presum[point.y][point.x]++;
            }
            for (int y=1;y<=2*N+1;y++) for (int x=1;x<=2*N+1;x++) presum[y][x]+=presum[y][x-1];

            //count equilatrial triangles L_infinity
            for (int y=1;y<=2*N+1;y++){
                ArrayList<Integer> xs = yxs[y];
                for (int u:xs){
                    for (int v:xs){
                        if (u < v){
                            int dist = v - u;
                            if (y + dist <= 2*N+1){
                                //points above between
                                ans += 2*(presum[y+dist][v-1]-presum[y+dist][u]);
                                //points above coincident
                                ans += (presum[y+dist][v]-presum[y+dist][v-1]+presum[y+dist][u]-presum[y+dist][u-1]);
                            }
                            if (y - dist >= 1){
                                //points below between
                                ans += 2*(presum[y-dist][v-1]-presum[y-dist][u]);
                                //points below coincident
                                ans += (presum[y-dist][v]-presum[y-dist][v-1]+presum[y-dist][u]-presum[y-dist][u-1]);
                            }
                        }
                    }
                }
            }

            //swap to simulate vertical and horizontal
            for (Point point : points){
                point.swap();
            }
        }

        //ret
        out.println(ans/2);
        out.close();
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
        public void swap(){
            int tmp = x;
            x=y;
            y=tmp;
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

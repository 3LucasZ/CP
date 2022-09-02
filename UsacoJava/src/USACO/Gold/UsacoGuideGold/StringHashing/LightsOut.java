package USACO.Gold.UsacoGuideGold.StringHashing;

import java.io.*;
import java.util.*;

/*
PROB: LightsOut
LANG: JAVA
*/
public class LightsOut {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static Point[] points;

    static long[] dist;
    static int[] angle;

    //hash helpers
    static long A = (long)2e9+11;
    static long B = (long)2e9+33;
    static long[] pow;
    public static void setup(){
        pow = new long[1000];
        pow[0]=1;
        for (int i=1;i<1000;i++){
            pow[i]=(pow[i-1]*A)%B;
        }
    }
    public static void main(String[] args) throws IOException {
        //parse
        setup("lightsout");
        setup();
        N=Integer.parseInt(br.readLine());
        points = new Point[N];
        angle = new int[N];
        dist = new long[N];
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i]=new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
        if (debug) System.out.println(Arrays.toString(points));

        //pre
        for (int i=0;i<N;i++){
            angle[i]=Point.ccw(points[(i-1+N)%N],points[i],points[(i+1)%N]);
        }
        long run;
        run=0;
        for (int i=1;i<N;i++){
            run+=points[i].dist(points[i-1]);dist[i]=run;
        }
        run=0;
        for (int i=N-1;i>=0;i--){
            run+=points[i].dist(points[(i+1)%N]);dist[i]=Math.min(dist[i],run);
        }
        if (debug) {
            System.out.println(Arrays.toString(angle));
            System.out.println(Arrays.toString(dist));
        }

        //prehash
        HashSet<Integer> seen = new HashSet<>();
        HashMap<Integer,Integer> unique = new HashMap<>();
        for (int i=1;i<N;i++){
            int hash=angle[i];
            for (int j=i+1;j<N;j++){
                int len = j-i;
                hash+=pow[len*2]*(points[j].dist(points[(j-1+N)%N]))+pow[len*2+1]*angle[j];
                if (seen.contains(hash)) {
                    unique.remove(hash);
                } else {
                    seen.add(hash);
                    unique.put(hash,i*1000+j);
                }
            }
        }
        HashSet<Integer> uniquePair = new HashSet<>();
        for (Map.Entry<Integer,Integer> path : unique.entrySet()) uniquePair.add(path.getValue());
        if (debug) {
            System.out.println("All Pairs: "+seen.size());
            System.out.println("Unique Pairs: "+uniquePair.size());
        }

        //complete search
        long ans = 0;
        for (int i=1;i<N;i++){
            run = 0;
            for (int j=i+1;j<N;j++){
                run+=points[j].dist(points[(j-1+N)%N]);
                if (uniquePair.contains(i*1000+j)){
                    ans=Math.max(ans,run+dist[j]-dist[i]);
                    break;
                }
            }
        }

        //ret
        out.println(ans);
        out.close();
    }
    private static class Point {
        long x;
        long y;
        public Point(long x, long y){
            this.x=x;
            this.y=y;
        }
        public long dist(Point o){
            return Math.abs(this.y-o.y+this.x-o.x);
        }
        public static int ccw(Point a, Point b, Point c){
            long area = (b.x - a.x) *(c.y-a.y) - (b.y - a.y) *(c.x-a.x);
            if (area < 0) return 3; //hash ccw angle
            else if (area > 0) return 5; //hash cw angle
            else return 0;
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
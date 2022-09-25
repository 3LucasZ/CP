package Training.SilverTraining;

import java.io.*;
import java.util.*;

public class LetterA {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) {
            if (solve()) out.println("YES");
            else out.println("NO");
        }
        out.close();
    }

    public static boolean solve() throws IOException{
        Line[] lines = new Line[3];
        for (int i=0;i<3;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
           lines[i] = new Line(new Point(x1,y1), new Point(x2,y2));
        }
        Line leg1 = null;
        Line leg2 = null;
        Line bridge = null;
        for (int i=0;i<3;i++){
            ArrayList<Integer> pair = new ArrayList<>();
            pair.add(0); pair.add(1); pair.add(2); pair.remove(i);
            if (lines[pair.get(0)].shareEndpoint(lines[pair.get(1)])) {
                leg1 = lines[pair.get(0)];
                leg2 = lines[pair.get(1)];
                bridge = lines[i];
            }
        }
        if (leg1==null) return false;
        if (debug) {
            System.out.println(leg1);
            System.out.println(leg2);
            System.out.println(bridge);
        }
        if (!leg1.acuteTo(leg2)) return false;
        if (leg1.pointOn(bridge.p2)){
            //swap
            Point temp = bridge.p1;
            bridge.p1=bridge.p2;
            bridge.p2=temp;
        }
        if (!(leg1.pointOn(bridge.p1)&&leg2.pointOn(bridge.p2))) return false;

        //FINAL STEP AHHHH SO PAINFUL
        Point vectorLittle1 = new Point(bridge.p1.x-leg1.p1.x, bridge.p1.y-leg1.p1.y);
        Point vectorBig1 = leg1.getVector();
        if (!sameSide(vectorBig1, vectorLittle1)) return false;
        double ratio1 = vectorLittle1.magnitude()/vectorBig1.magnitude();
        if (ratio1<0.25 || ratio1>0.75) return false;
        Point vectorLittle2 = new Point(bridge.p2.x-leg2.p1.x, bridge.p2.y-leg2.p1.y);
        Point vectorBig2 = leg2.getVector();
        if (!sameSide(vectorBig2, vectorLittle2)) return false;
        double ratio2 = vectorLittle2.magnitude()/vectorBig2.magnitude();
        if (ratio2<0.25 || ratio2>0.75) return false;
        return true;
    }

    private static class Line {
        Point p1;
        Point p2;
        public Line(Point p1, Point p2){
            this.p1=p1;
            this.p2=p2;
        }
        public boolean shareEndpoint(Line other) {
            if (p1.isEquals(other.p2) || p2.isEquals(other.p2)) {
                //swap
                Point temp = other.p1;
                other.p1=other.p2;
                other.p2=temp;
            }
            else if (p2.isEquals(other.p1) || p2.isEquals(other.p2)){
                //swap
                Point temp = p1;
                p1=p2;
                p2=temp;
            }
            return p1.isEquals(other.p1);
        }

        //bridge point stuff
        public boolean pointOn(Point other){
            if (p2.x==p1.x) return other.x==p1.x;
            double m = (p2.y-p1.y)/(p2.x-p1.x);
            return floatEquals(other.y-p1.y, m*(other.x-p1.x));
        }

        //angle stuff
        public boolean acuteTo(Line other) {
            return crossProduct(other) >= 0;
        }
        public long crossProduct(Line other){
            return getVector().x*other.getVector().x + getVector().y*other.getVector().y;
        }
        public double getMagnitude() {
            return Math.sqrt((p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y));
        }
        public Point getVector(){
            return new Point(p2.x-p1.x, p2.y-p1.y);
        }

        //verbose
        public String toString(){
            return "["+p1+", "+p2+"]";
        }
    }
    private static class Point {
        int x;
        int y;
        public Point(int x, int y){
            this.x=x;
            this.y=y;
        }
        public boolean isEquals(Point other){
            return this.x==other.x&&this.y==other.y;
        }
        public String toString(){
            return "["+x+", "+y+"]";
        }
        public double magnitude(){
            return Math.sqrt(x*x+y*y);
        }
    }
    public static boolean floatEquals(double d1, double d2){
        double epsilon = 0.0000000001;
        return Math.abs(d1-d2)<epsilon;
    }
    private static boolean sameSide(Point p1, Point p2){
        return p1.x*p2.x>=0 && p2.y*p1.y>=0;
    }
}

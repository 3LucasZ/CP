package Helper;

import java.util.*;

public class CoordinateCompressionWithObjects{
    public static void main(String[] args){
        int N = 5;
        Point[] values = new Point[N];
        values[0] = new Point(1, 5);
        values[1] =  new Point(2, 4);
        values[2] =  new Point(3, 3);
        values[3] =  new Point(4, 2);
        values[4] =  new Point(5, 1);

        Point[] tmpValues = new Point[N]; for (int i=0;i<N;i++) tmpValues[i]=new Point(0,0);

        Arrays.sort(values, (a,b)->a.x-b.x);
        for (int i=1;i<N;i++) {
            if (values[i].x == values[i-1].x) tmpValues[i].x=tmpValues[i-1].x;
            else tmpValues[i].x=tmpValues[i-1].x+1;
        } for (int i=0;i<N;i++) values[i].x=tmpValues[i].x;

        Arrays.sort(values, (a,b)->a.y-b.y);
        for (int i=1;i<N;i++) {
            if (values[i].y == values[i-1].y) tmpValues[i].y=tmpValues[i-1].y;
            else tmpValues[i].y=tmpValues[i-1].y+1;
        } for (int i=0;i<N;i++) values[i].y = tmpValues[i].y;

        System.out.println(Arrays.toString(values));
    }
    private static class Point {
        int x;
        int y;
        public Point(int x, int y){
            this.x=x;
            this.y=y;
        }
        public String toString(){
            return "["+x+", "+y+"]";
        }
    }
}

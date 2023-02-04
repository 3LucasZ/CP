package Other.USACO.Season2015_2016.Open2016.Gold;/*
USACO 2016 US Open Contest, Gold
Problem 1. Splitting the Field
USACO Silver Guide - Custom Comparators and Coordinate Compression - Normal
Good one! Solved without help.
 */
import java.util.*;
import java.io.*;
public class SplittingTheField {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static Point[] field;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("split.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("split.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        n = Integer.parseInt(f.readLine());
        field = new Point[n];
        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            field[i] = new Point(x,y);
        }
        /*-VERT SPLIT-----------------------------------------------*/
        Arrays.sort(field, (a,b)->a.x-b.x);
        //logic
        int bigWidth = field[n-1].x-field[0].x;
        long ans = Long.MAX_VALUE;
        int loY = field[0].y;
        int hiY = field[0].y;
        int[] leftHeight = new int[n];
        int[] rightHeight = new int[n];
        for (int i=0;i<n;i++){
            loY = Math.min(loY, field[i].y);
            hiY = Math.max(hiY, field[i].y);
            leftHeight[i] = hiY-loY;
        }
        loY = field[n-1].y;
        hiY = field[n-1].y;
        for (int i=n-1;i>=0;i--){
            loY = Math.min(loY, field[i].y);
            hiY = Math.max(hiY, field[i].y);
            rightHeight[i] = hiY-loY;
        }
        //unit test
//        out.println(Arrays.toString(leftHeight));
//        out.println(Arrays.toString(rightHeight));
        //smallest when vertical split
        for (int i=0;i<n-1;i++){
            long leftBox = (long)leftHeight[i]*(long)(field[i].x-field[0].x);
            long rightBox = (long)rightHeight[i+1]*(long)(field[n-1].x-field[i+1].x);
//            out.println(leftBox);
//            out.println(rightBox);
//            out.println();
            ans = Math.min(ans, leftBox + rightBox);
        }

        /*-----------------------HORIZ SPLIT---------------------------------*/
        Arrays.sort(field, (a,b)->a.y-b.y);
        int bigHeight = field[n-1].y-field[0].y;
        int loX = field[0].x;
        int hiX = field[0].x;
        int[] bottomWidth = new int[n];
        int[] topWidth = new int[n];
        for (int i=0;i<n;i++){
            loX = Math.min(loX, field[i].x);
            hiX = Math.max(hiX, field[i].x);
            bottomWidth[i] = hiX-loX;
        }
        loX = field[n-1].x;
        hiX = field[n-1].x;
        for (int i=n-1;i>=0;i--){
            loX = Math.min(loX, field[i].x);
            hiX = Math.max(hiX, field[i].x);
            topWidth[i] = hiX-loX;
        }
        //unit test
//        out.println(Arrays.toString(bottomWidth));
//        out.println(Arrays.toString(topWidth));
        //smallest when horizontal split
        for (int i=0;i<n-1;i++){
            long bottomBox = (long)bottomWidth[i]*(long)(field[i].y-field[0].y);
            long topBox = (long)topWidth[i+1]*(long)(field[n-1].y-field[i+1].y);
//            out.println(bottomBox);
//            out.println(topBox);
//            out.println();
            ans = Math.min(ans, bottomBox + topBox);
        }
        //turn in answer
        out.println((long)bigHeight*(long)bigWidth-ans);
        out.close();
        f.close();
    }
    private static class Point {
        int x;
        int y;
        public Point(int x1, int y1){
            x=x1;
            y=y1;
        }
        public String toString(){
            return "["+x+", "+y+"]";
        }
    }
}

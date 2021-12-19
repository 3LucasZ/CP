import java.util.*;
import java.io.*;
public class SplittingTheField {
    //io
    static boolean submission = false;
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
        Arrays.sort(field, (a,b)->a.x-b.x);
        //logic
        int bigWidth = field[n-1].x-field[0].x;
        int ans = Integer.MAX_VALUE;

        //doesnt change
        int loX1 = field[0].x;
        //changes
        int loX2 = field[0].x;
        int loY1 = field[0].y;
        int loY2 = field[0].y;

        int hiX1 = field[n-1].x;
        int hiX2 = field[n-1].x;
        int hiY1 = field[n-1].y;
        int hiY2 = field[n-1].y;

        int area1 = 0;
        int area2 = 0;
        //vertical divider between both fields
        for (int i=0;i<n;i++) {

            area1 = (loX2-loX1)*(loY2-loY1);
            area2 = (hiX2-hiX1)*(hiY2-hiY1);
            ans = Math.min(ans, area1+area2);
        }
        //turn in answer
        out.println();
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

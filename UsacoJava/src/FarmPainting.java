import org.w3c.dom.css.Rect;

import java.io.*;
import java.util.*;
/*

 */
public class FarmPainting {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static Rectangle[] rectangles;
    static TreeSet<Rectangle> active = new TreeSet<Rectangle>((a, b)->{
        return a.y1-b.y1;
    });
    static int ans;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("painting.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("painting.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        rectangles = new Rectangle[N];
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            rectangles[i] = new Rectangle(x1,y1,x2,y2);
        }
        //logic
        Arrays.sort(rectangles,(a,b)->{
            return a.x1-b.x1;
        });
        //out.println(Arrays.toString(rectangles));
        for (int i=0;i<N;i++) {
            Rectangle lower = active.lower(rectangles[i]);
            if (lower != null){
                if (lower.contains(rectangles[i]));
                else if (rectangles[i].contains(lower)) {
                    active.add(rectangles[i]);
                    active.remove(lower);
                }
                else {
                    active.add(rectangles[i]);
                }
            }
            else {
                active.add(rectangles[i]);
            }
        }
        out.println(active.size());
        out.close();
    }
    private static class Rectangle {
        //true -> begin point | false -> end point
        int x1;
        int y1;
        int x2;
        int y2;
        public Rectangle(int xx1, int yy1, int xx2, int yy2){
            x1=xx1;
            y1=yy1;
            x2=xx2;
            y2=yy2;
        }
        public boolean contains(Rectangle other){
            return x1<=other.x1 && y1<=other.y1 && x2>=other.x2 && y2 >= other.y2;
        }
        public String toString(){
            return "[["+x1+", "+y1+"], ["+x2+", "+y2+"]]";
        }
    }
}

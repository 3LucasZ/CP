import java.io.*;
import java.util.StringTokenizer;
/*
PROB: fence9
LANG: JAVA
 */
public class fence9 {
    static boolean submission = true;
    static boolean debug = true;

    static int x0=0;
    static int y0=0;
    static int x1;
    static int y1;
    static int x2;
    static int y2=0;

    public static void main(String[] args) throws IOException {
        setup("fence9");
        StringTokenizer st = new StringTokenizer(br.readLine());
        x1=Integer.parseInt(st.nextToken());
        y1=Integer.parseInt(st.nextToken());
        x2=Integer.parseInt(st.nextToken());

        Line left = new Line(x0,y0,(double)(y1-y0)/(x1-x0));
        Line right = new Line(x2,y2,(double)(y1-y2)/(x1-x2));

        int ans = 0;
        for (int y=1;y<=y1-1;y++){
            int lx = (int) Math.ceil(left.getXFromY(y));
            int rx = (int) Math.floor(right.getXFromY(y));
            ans += rx-lx+1;
        }

        out.println(ans);
        out.close();
    }
    private static class Line {
        int x0;
        int y0;
        double m;
        public Line(int x, int y, double m){
            this.x0=x;this.y0=y;this.m=m;
        }
        public double getXFromY(int y){
            return (y-y0)/m+x0;
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

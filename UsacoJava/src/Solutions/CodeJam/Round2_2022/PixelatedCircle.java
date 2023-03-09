package Solutions.CodeJam.Round2_2022;

import java.io.*;

/*
PROB: Solution
LANG: JAVA
*/
public class PixelatedCircle {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup("Solution");
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    public static void solve(int tcs) throws IOException {
        out.print("Case #" + tcs + ": "); if (debug) out.println();
        //* parse
        int R = Integer.parseInt(br.readLine());
        //* cnt correct area
        long area1 = 0;
        for (int x=-R;x<=R;x++){
            long y = (long)Math.pow(Math.pow(0.5D+(double)R,2)-Math.pow(x,2),0.5);
            area1+=y*2+1;
        }
        if (debug)out.println("area1: "+area1);
        //* cnt wrong area
        long area2=0;
        for (int r=1;r<=R;r++) area2 += getCirclePerimeter(r);
        area2*=4; area2++;
        if (debug) out.println("area2: "+area2);
        //* ret
        out.println(area1-area2);
    }
    public static int getCirclePerimeter(int r){
        int x1 = (int) (r/Math.pow(2,0.5));
        int y1 = (int) Math.round(Math.sqrt(Math.pow(r,2)-Math.pow(x1,2)));
        int x2 = x1+1;
        int y2 = (int) Math.round(Math.sqrt(Math.pow(r,2)-Math.pow(x2,2)));
        int x, y;
        if (y1<x1){
            x=x2;
            y=y2;
        }
        else if (y2<x2){
            x=x1;
            y=y1;
        }
        else {
            x=x1;
            y=y1;
            if (y2-x2<y1-x1) {
                x=x2;
                y=y2;
            }
        }
        return 2*x+1-(x==y?1:0);
    }


    static BufferedReader br;
    static PrintWriter out;

    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName + ".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}
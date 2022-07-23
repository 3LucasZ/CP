package Contests.Codeforces.Round773;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class HardWay {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) out.println(solve());
        out.println();
        out.close();
    }

    public static int solve() throws IOException {
        Point[] pts = new Point[3];
        for (int i=0;i<3;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            pts[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        if (pts[0].y==pts[1].y && pts[2].y < pts[0].y) {
            return Math.abs(pts[0].x-pts[1].x);
        }
        if (pts[0].y==pts[2].y && pts[1].y < pts[0].y) {
            return Math.abs(pts[0].x-pts[2].x);
        }
        if (pts[1].y==pts[2].y && pts[0].y < pts[1].y) {
            return Math.abs(pts[1].x-pts[2].x);
        }
        return 0;
    }

    private static class Point {
        int x;
        int y;
        public Point(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
}

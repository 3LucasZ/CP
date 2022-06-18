package Codeforces.Round801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SubrectangleGuess {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }

    public static void solve() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        Point max = new Point(0,0);
        int maxVal = Integer.MIN_VALUE;
        for (int r=1;r<=R;r++){
            st = new StringTokenizer(br.readLine());
            for (int c=1;c<=C;c++){
                int num = Integer.parseInt(st.nextToken());
                if (num > maxVal) {
                    max = new Point(r,c);
                    maxVal = num;
                }
            }
        }

        if (debug) System.out.println("Size: "+R+" x "+C+" with max: "+maxVal + " at "+max);
        out.println(Math.max(max.r,R-max.r+1)*Math.max(max.c,C-max.c+1));
    }

    private static class Point {
        int r;
        int c;
        public Point(int r, int c){
            this.r=r;
            this.c=c;
        }
        public String toString(){
            return "["+r+", "+c+"]";
        }
    }
}

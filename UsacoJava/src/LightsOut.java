import java.io.*;
import java.util.*;
/*
PROB: LightsOut
LANG: JAVA
*/
public class LightsOut {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static Point[] points;
    static long[] dist;

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
        setup("lightsout");
    }
    private static class Point {
        long x;
        long y;
        public Point(long x, long y){
            this.x=x;
            this.y=y;
        }
        public
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
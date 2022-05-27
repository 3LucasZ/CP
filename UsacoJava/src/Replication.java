import java.io.*;
import java.util.*;

public class Replication {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int D;

    static ArrayList<Point> start = new ArrayList<>();

    boolean[][] board;
    boolean[][] rotatedBoard;

    public static void main(String[] args) throws IOException {
        setup("");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        for (int r=0;r<N;r++){
            String str = br.readLine();
            for (int c=0;c<N;c++){

            }
        }
    }
    private static class Point {
        int r;
        int c;
        public Point(int r, int c){
            this.r=r;
            this.c=c;
        }
        public Point transform(Point other){
            return new Point(N-1-other.c+other.r,other.c+other.r);
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

import java.io.*;
import java.util.StringTokenizer;

public class Permutations {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static Point[] points;

    static long MOD = (long)1e9+7;
    public static void main(String[] args) throws IOException {
        setup("");
        N = Integer.parseInt(br.readLine());
        points = new Point[N];
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Point next = new Point(i,x,y);
            points[i]=next;
        }
        //dp[round][id1][id2][id3]
        long[][][][] dp = new long[N][N][N][N];
        for (int i=0;i<N;i++) for (int j=0;j<N;j++) for (int k=0;k<N;k++){

        }
    }
    private static class Point {
        int id;
        int x;
        int y;
        public Point(int id, int x, int y){
            this.id=id;
            this.x=x;
            this.y=y;
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

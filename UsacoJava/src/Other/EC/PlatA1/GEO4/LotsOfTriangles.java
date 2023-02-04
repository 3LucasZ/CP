package Other.EC.PlatA1.GEO4;

import java.io.*;
import java.util.StringTokenizer;

public class LotsOfTriangles {
    static boolean submission = true;
    static boolean debug = true;

    static int N;
    static Point[] pts;
    static int[][] under;

    public static void main(String[] args) throws IOException {
        //parse
        setup("triangles");
        N = Integer.parseInt(br.readLine());
        pts = new Point[N];
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            pts[i]=new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        //under precomp [i,j) w/o i,j
        under = new int[N][N];
        for (int u=0;u<N;u++){
            for (int v=0;v<N;v++){
                for (int m=0;m<N;m++) {
                    if (u==m || v==m) continue;
                    if (pts[u].x>=pts[v].x) continue;
                    if (pts[m].x>=pts[u].x && pts[m].x<pts[v].x && Point.ccw(pts[m],pts[u],pts[v])==-1) {
                        under[u][v]++;
                    }
                }
            }
        }
//        if (debug){
//            for (int u=0;u<N;u++){
//                for (int v=0;v<N;v++){
//                    System.out.println("["+u+", "+v+"]: "+under[u][v]);
//                }
//            }
//        }

        //triangles
        int[] ans = new int[N-3+1];
        for (int l=0;l<N;l++){
            for (int r=0;r<N;r++){
                for (int m=0;m<N;m++){
                    if (pts[l].x>=pts[r].x) continue;
                    //case 3
                    if (pts[m].x==pts[l].x && pts[m].y<pts[l].y){
                        ans[under[l][r]-under[l][m]-under[m][r]-1]++;
                    }
                    //case 4
                    else if (pts[m].x==pts[r].x && pts[m].y<pts[r].y){
                        ans[under[l][r]-under[l][m]-under[m][r]]++;
                    }
                    //case 1 and 2
                    else if (pts[m].x>pts[l].x && pts[m].x<pts[r].x){
                        ans[Math.max(under[l][r]-under[l][m]-under[m][r]-1, under[l][m]+under[m][r]-under[l][r])]++;
                    }
                }
            }
        }

        //ret
        for (int i=0;i<=N-3;i++){
            out.println(ans[i]);
        }
        out.close();
    }

    private static class Point {
        int x;
        int y;
        public Point(int x, int y){
            this.x=x;
            this.y=y;
        }
        public double distanceTo(Point o){
            return Math.hypot(this.x-o.x,this.y-o.y);
        }
        public static int ccw(Point a, Point b, Point c){
            long area = (long) (b.x - a.x) *(c.y-a.y) - (long) (b.y - a.y) *(c.x-a.x);
            if (area < 0) return -1;
            else if (area > 0) return 1;
            else return 0;
        }
        public static boolean collinear(Point a, Point b, Point c){
            return ccw(a,b,c)==0;
        }
        public String toString(){
            return "["+x+", "+y+"]";
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

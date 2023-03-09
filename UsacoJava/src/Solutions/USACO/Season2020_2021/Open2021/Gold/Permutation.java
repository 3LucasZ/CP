package Solutions.USACO.Season2020_2021.Open2021.Gold;

import java.io.*;
import java.util.*;
/*
PROB: Permutation
LANG: JAVA
*/
public class Permutation {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static Point[] p;

    static long MOD = (long)1e9+7;
    public static void main(String[] args) throws IOException {
        //parse
        setup("Permutation");
        N = Integer.parseInt(br.readLine());
        p = new Point[N+1];
        for (int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            p[i]=new Point(x,y);
        }

        //dp[p1][p2][p3][i] setup
        long[][][][] dp = new long[N+1][N+1][N+1][N+1];
        for (int i=1;i<=N;i++){
            for (int j=i+1;j<=N;j++){
                for (int k=j+1;k<=N;k++){
                    dp[i][j][k][3]=1;
                }
            }
        }

        //dp transitions
        for (int i=3;i<=N-1;i++){
            for (int p1=1;p1<=N;p1++){
                for (int p2=p1+1;p2<=N;p2++){
                    for (int p3=p2+1;p3<=N;p3++){
                        //keep track of outside points
                        int out = 0;
                        //try a new point
                        for (int next=1;next<=N;next++){
                            //throwaway invalid next
                            if (next==p1||next==p2||next==p3) continue;
                            //check if its an inside point
                            int cnt = Point.ccw(p[p1],p[p2],p[next])+Point.ccw(p[p2],p[p3],p[next])+Point.ccw(p[p3],p[p1],p[next]);
                            if (cnt==3||cnt==-3) continue;
                            out++;
                            //try each new frame replacement: p1, p2, p3
                            int[] ps = {p1,p2,p3};
                            for (int tar=0;tar<3;tar++){
                                int t=ps[tar];
                                int l=ps[Math.floorMod(tar-1,3)];
                                int r=ps[Math.floorMod(tar+1,3)];
                                int sign=Point.ccw(p[l],p[t],p[next])*Point.ccw(p[r],p[t],p[next]);
                                int sign2=Point.ccw(p[l],p[r],p[next])*Point.ccw(p[l],p[r],p[t]);
                                if (sign==-1&&sign2==1){
                                    int[] newTri = {tar==0?next:p1,tar==1?next:p2,tar==2?next:p3};
                                    Arrays.sort(newTri);
                                    dp[newTri[0]][newTri[1]][newTri[2]][i+1]=(dp[newTri[0]][newTri[1]][newTri[2]][i+1]+dp[p1][p2][p3][i])%MOD;
                                }
                            }
                        }
                        //inside point transition
                        dp[p1][p2][p3][i+1]=(dp[p1][p2][p3][i+1]+dp[p1][p2][p3][i]*(N-i-out))%MOD;
                        if (debug) System.out.println("p1: "+p1+", p2: "+p2+", p3: "+p3+", i: "+i+" = "+dp[p1][p2][p3][i]);
                    }
                }
            }
        }

        //ret
        long ans = 0;
        for (int p1=1;p1<=N;p1++) for (int p2=1;p2<=N;p2++) for (int p3=1;p3<=N;p3++) ans=(ans+dp[p1][p2][p3][N])%MOD;
        out.println((ans*6)%MOD);
        out.close();
    }
    private static class Point {
        int x;
        int y;
        public Point(int x, int y){
            this.x=x;
            this.y=y;
        }
        public String toString(){
            return "["+x+", "+y+"]";
        }
        public static int ccw(Point a, Point b, Point c){
            long area = (long) (b.x - a.x) *(c.y-a.y) - (long) (b.y - a.y) *(c.x-a.x);
            if (area < 0) return -1;
            else if (area > 0) return 1;
            else return 0;
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
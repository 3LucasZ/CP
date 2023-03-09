package Solutions.USACO.Season2020_2021.Dec2020.Gold;

import java.io.*;
import java.util.*;
/*
PROB: Replication
LANG: JAVA
*/
public class Replication {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int D;
    static boolean[][] rock;
    static ArrayList<Point> sources = new ArrayList<>();

    static int[][] rock_dist;

    //BFS
    static Queue<Point> BFS = new LinkedList<>();
    static int[] dr={0,0,-1,1};
    static int[] dc={1,-1,0,0};

    public static void main(String[] args) throws IOException {
        //parse
        setup("Replication");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        rock = new boolean[N][N];
        for (int r=0;r<N;r++){
            String str = br.readLine();
            for (int c=0;c<N;c++){
                char chr = str.charAt(c);
                if (chr=='#') rock[r][c]=true;
                if (chr=='S') sources.add(new Point(r,c));
            }
        }

        //rock_dist = closest rock distance to cell r,c
        rock_dist = new int[N][N]; for (int i=0;i<N;i++) for (int j=0;j<N;j++) rock_dist[i][j]=-1;
        BFS.clear();
        for (int r=0;r<N;r++){
            for (int c=0;c<N;c++){
                if (rock[r][c]) {
                    BFS.add(new Point(r,c));
                    rock_dist[r][c]=0;
                }
            }
        }
        while (!BFS.isEmpty()){
            Point from = BFS.poll();
            for (int k=0;k<4;k++){
                Point to = new Point(from.r+dr[k],from.c+dc[k]);
                if (to.r<0||to.r>=N||to.c<0||to.c>=N) continue;
                if (rock_dist[to.r][to.c]!=-1) continue;
                if (rock[to.r][to.c]) continue;
                rock_dist[to.r][to.c]=rock_dist[from.r][from.c]+1;
                BFS.add(to);
            }
        }
        if (debug){
            for (int i=0;i<N;i++) {
                for (int j=0;j<N;j++){
                    System.out.print(rock_dist[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }

        //centers[i] = all cells r,c that can have a center bot and has rock_dist of i
        ArrayList<Point>[] centers = new ArrayList[N/2]; for (int i=0;i<N/2;i++) centers[i]= new ArrayList<>();
        int[][] time = new int[N][N]; for (int i=0;i<N;i++) for (int j=0;j<N;j++) time[i][j]=-1;
        BFS.clear();
        for (Point source : sources){
            BFS.add(source);
            time[source.r][source.c]=0;
            centers[rock_dist[source.r][source.c]].add(source);
        }
        while (!BFS.isEmpty()){
            Point from = BFS.poll();
            int t = time[from.r][from.c]+1;
            //replicate into explosion
            if ((long)(t)-1>=(long)D*rock_dist[from.r][from.c]) continue;
            for (int k=0;k<4;k++){
                Point to = new Point(from.r+dr[k],from.c+dc[k]);
                if (to.r<0||to.r>=N||to.c<0||to.c>=N) continue;
                if (time[to.r][to.c]!=-1) continue;
                //move into explosion
                if ((long)t>(long)D*rock_dist[to.r][to.c]) continue;
                time[to.r][to.c]=t;
                BFS.add(to);
                centers[rock_dist[to.r][to.c]].add(to);
            }
        }
        if (debug){
            for (int i=0;i<N;i++) {
                for (int j=0;j<N;j++){
                    System.out.print(1+time[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
        if (debug){
            for (int i=0;i<N/2;i++) System.out.println(centers[i]);
            System.out.println();
        }

        //Weird BFS going from centers[N/2...1] to find bots
        boolean[][] bot = new boolean[N][N];
        BFS.clear();
        for (int i=N/2-1;i>=1;i--){
            for (Point center: centers[i]) {
                BFS.add(center);
                bot[center.r][center.c]=true;
            }
            if (i==1) break;
            Queue<Point> nextBFS = new LinkedList<>();
            for (Point from : BFS){
                for (int k=0;k<4;k++){
                    Point to = new Point(from.r+dr[k],from.c+dc[k]);
                    if (to.r<0||to.r>=N||to.c<0||to.c>=N) continue;
                    if (rock[to.r][to.c]) continue;
                    if (bot[to.r][to.c]) continue;
                    nextBFS.add(to);
                    bot[to.r][to.c]=true;
                }
            }
            BFS = nextBFS;
        }
        if (debug){
            for (int i=0;i<N;i++) {
                for (int j=0;j<N;j++){
                    System.out.print(bot[i][j]?'x':'.');
                }
                System.out.println();
            }
            System.out.println();
        }

        //ret
        int cnt = 0;
        for (int i=0;i<N;i++) for (int j=0;j<N;j++) if (bot[i][j]) cnt++;
        out.println(cnt);
        out.close();
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
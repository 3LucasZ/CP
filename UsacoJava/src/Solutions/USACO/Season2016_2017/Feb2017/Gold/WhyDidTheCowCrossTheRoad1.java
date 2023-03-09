package Solutions.USACO.Season2016_2017.Feb2017.Gold;

import java.io.*;
import java.util.*;

public class WhyDidTheCowCrossTheRoad1 {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int T;
    static int[][] time;
    static int[] dr = {0,0,-1,1};
    static int[] dc = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        //parse
        setup("visitfj");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        time = new int[N][N];
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++){
                time[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        //djikstra
        PriorityQueue<Point> pq = new PriorityQueue<>((a,b)->a.cost-b.cost);
        //least distance to [r][c][k] from (0,0)
        int[][][] dist = new int[3][N][N];
        pq.add(new Point(0,0,0,1));
        while (!pq.isEmpty()){
            Point next = pq.poll();
            if (dist[next.k][next.r][next.c]!=0) continue;
            dist[next.k][next.r][next.c]=next.cost;
            for (int i=0;i<4;i++){
                Point to = new Point(next.r+dr[i],next.c+dc[i],(next.k+1)%3,0);
                if (to.r<0 || to.r>=N || to.c<0 || to.c>=N || dist[to.k][to.r][to.c]!=0) continue;
                to.cost=next.cost+(next.k==2?T+time[to.r][to.c]:T);
                pq.add(to);
            }
        }
        if (debug){
            print(dist[0]);
            print(dist[1]);
            print(dist[2]);
        }

        //ret
        int ans = Integer.MAX_VALUE;
        for (int i=0;i<3;i++) ans=Math.min(ans,dist[i][N-1][N-1]);
        out.println(ans-1);
        out.close();
    }
    public static void print(int[][] arr){
        for (int r=0;r<arr.length;r++){
            for (int c=0;c<arr[r].length;c++){
                String str = ""+arr[r][c];
                while (str.length()<5) str+=" ";
                System.out.print(str);
            }
            System.out.println();
        }
        System.out.println();
    }
    private static class Point {
        int r;
        int c;
        int k;
        int cost;
        public Point(int r, int c,int k, int cost){
            this.r=r;
            this.c=c;
            this.k=k;
            this.cost=cost;
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

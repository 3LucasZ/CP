package Solutions.USACOGuide.UsacoGuideGold.ShortestPath;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
USACO 2012 November Contest, Silver
Problem 2. Distant Pastures
USACO Gold Training
Shortest Path - All points shortest path on sparse graph with O(V(V+ElogV))
grid [r][c] for node id
so many mistakes and degugging made on an easy problem
be careful with r, c, tr, tc, next.r, next.c u confuse urself
almost forgot djistra smh embarassing
DJIKSTRA IS FUNDAMENTAL
 */
public class DistantPastures {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int A;
    static int B;
    static boolean[][] C;
    //helper
    static int INF = Integer.MAX_VALUE;
    static int[] dr = {0,0,-1,1};
    static int[] dc= {1,-1,0,0};
    //glob
    static int worst = 0;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("distant.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("distant.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = new boolean[N][N];
        for (int r=0;r<N;r++){
            String str = br.readLine();
            for (int c=0;c<N;c++){
                C[r][c]=str.charAt(c)=='(';
            }
        }
        if (!submission){
            for (int r=0;r<N;r++){
                for (int c=0;c<N;c++){
                    System.out.print(C[r][c]);
                }
                System.out.println();
            }
            System.out.println();
        }

        //logic
        for (int r=0;r<N;r++)for (int c=0;c<N;c++)djikstra(r,c);

        //turn in answer
        out.println(worst);
        out.close();
    }
    public static void djikstra(int r, int c){
        int[][] dist = new int[N][N];
        for (int tr=0;tr<N;tr++) for (int tc=0;tc<N;tc++) dist[tr][tc]=INF;
        PriorityQueue<Path> pq = new PriorityQueue<>((a,b)->a.dist-b.dist);
        dist[r][c]=0;
        pq.add(new Path(r,c,0));

        while (!pq.isEmpty()){
            Path next = pq.poll();
            if (next.dist != dist[next.r][next.c]) continue;
            for (int i=0;i<4;i++){
                int tr=next.r+dr[i];
                int tc=next.c+dc[i];
                if (tr<0||tr>=N||tc<0||tc>=N) continue;
                int weight = C[next.r][next.c]==C[tr][tc]?A:B;
                if (next.dist+weight < dist[tr][tc]){
                    dist[tr][tc]=next.dist+weight;
                    pq.add(new Path(tr,tc,dist[tr][tc]));
                }
            }
        }
        for (int tr=0;tr<N;tr++) for (int tc=0;tc<N;tc++) worst=Math.max(worst,dist[tr][tc]);
        if (!submission) {
            for (int tr=0;tr<N;tr++){
                for (int tc=0;tc<N;tc++){
                    System.out.print(dist[tr][tc]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
    private static class Path {
        int r;
        int c;
        int dist;
        public Path(int r, int c, int dist){
            this.c=c;this.r=r;this.dist=dist;
        }
    }
}

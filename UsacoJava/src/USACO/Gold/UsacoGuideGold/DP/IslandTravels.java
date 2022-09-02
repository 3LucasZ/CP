package USACO.Gold.UsacoGuideGold.DP;

import java.io.*;
import java.util.*;

public class IslandTravels {
    static boolean submission = false;
    static boolean debug = true;

    //param
    static int R;
    static int C;
    static char[][] map;
    static int[][] id;

    //ff
    static boolean[][] visited;
    static final int[] dr = {1,-1,0,0};
    static final int[] dc = {0,0,1,-1};

    //graph
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        //parse
        setup("island");
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        id = new int[R][C];
        for (int r=0;r<R;r++){
            String str = br.readLine();
            for (int c=0;c<C;c++){
                map[r][c]=str.charAt(c);
            }
        }
        if (debug){
            for (int r=0;r<R;r++){
                for (int c=0;c<C;c++){
                    System.out.print(map[r][c]);
                }
                System.out.println();
            }
            System.out.println();
        }

        //find islands - connected components and floodfill
        ArrayList<ArrayList<Point>> islands = new ArrayList<>();
        visited = new boolean[R][C];
        for (int r=0;r<R;r++){
            for (int c=0;c<C;c++){
                if(!visited[r][c] && map[r][c]=='X'){
                    island = new ArrayList<>();
                    ff(r,c);
                    islands.add(island);
                    idx++;
                }
            }
        }
        int N = islands.size();
        if (debug){
            System.out.println("islands: "+N);
            System.out.println(islands);
            for (int r=0;r<R;r++){
                for (int c=0;c<C;c++){
                    System.out.print(id[r][c]);
                }
                System.out.println();
            }
            System.out.println();
        }

        //multi-source bfs to find edge lengths
        graph = new int[N+1][N+1];
        for (int i=0;i<N;i++){
            idx = i+1;
            if (debug){
                System.out.println("island: "+idx);
            }
            island = islands.get(i);
            visited = new boolean[R][C];
            Queue<Point> BFS = new LinkedList<>();
            for (Point p : island){
                BFS.add(p);
            }
            while (!BFS.isEmpty()){
                Point next = BFS.poll();
                int r = next.r; int c = next.c; int dist = next.dist;
                if (badPoint(r,c)||visited[r][c]||map[r][c]=='.') continue;
                visited[r][c]=true;
                if (map[r][c]=='S' || id[r][c]==idx){
                    for (int j=0;j<4;j++){
                        Point add = new Point(r+dr[j],c+dc[j]); add.dist=dist+1;
                        BFS.add(add);
                    }
                }
                else if (graph[idx][id[r][c]]==0){
                    graph[idx][id[r][c]]=dist-1;
                }
                if (debug){
                    System.out.println("Processing: "+next);
                }
            }
        }
        if (debug){
            for (int u=1;u<=N;u++){
                for (int v=1;v<=N;v++){
                    System.out.print(graph[u][v]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }

        //subset dp
        int sets = 1<<N;
        //dp[set][end node]
        int[][] dp = new int[sets][N]; for (int r=0;r<sets;r++) for (int c=0;c<N;c++) dp[r][c]=Integer.MAX_VALUE;
        for (int i=0;i<N;i++){
            dp[1<<i][i] = 0;
        }

        for (int s=0;s<sets-1;s++){
            for (int last=0;last<N;last++){
                if (dp[s][last]==Integer.MAX_VALUE) continue;
                int bit = 1<<last;
                if ((s&bit)!=0){
                    for (int next=0;next<N;next++){
                        int nextBit = 1<<next;
                        if ((s&nextBit)==0 && graph[last+1][next+1]!=0){
                            dp[s^nextBit][next]=Math.min(dp[s][last]+graph[last+1][next+1],dp[s^nextBit][next]);
                        }
                    }
                }
            }
        }

        //ans
        int ans = Integer.MAX_VALUE;
        for (int i=0;i<N;i++)  ans = Math.min(ans,dp[sets-1][i]);
        out.println(ans);
        out.close();
    }
    static int idx = 1;
    static ArrayList<Point> island;
    public static void ff(int r, int c){
        if (badPoint(r,c)||visited[r][c]||map[r][c]!='X') return;
        island.add(new Point(r,c));
        visited[r][c]=true;
        id[r][c]=idx;
        for (int i=0;i<4;i++){
            ff(r+dr[i],c+dc[i]);
        }
    }
    private static class Point {
        int dist;
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
    public static boolean badPoint(int r, int c){
        return r<0||r>=R||c<0||c>=C;
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

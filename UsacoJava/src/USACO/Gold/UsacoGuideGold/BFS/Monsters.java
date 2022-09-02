package USACO.Gold.UsacoGuideGold.BFS;

import java.io.*;
import java.util.*;
/*
PROB: Monsters
LANG: JAVA
*/
public class Monsters {
    static boolean submission = false;
    static boolean debug = false;

    static int R;
    static int C;

    static boolean[][] blocked;

    static ArrayList<Point> monsters = new ArrayList<>();
    static Point A;
    static Point B;

    //bfs
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static char[] letter = {'R','D','L','U'};

    static int INF = Integer.MAX_VALUE/100;

    static int[][] monster_dist;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        //parse
        setup("Monsters");
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        blocked = new boolean[R][C];
        for (int r=0;r<R;r++){
            String str = br.readLine();
            for (int c=0;c<C;c++){
                char next = str.charAt(c);
                if (next=='#') blocked[r][c]=true;
                else if (next=='A') A = new Point(r,c);
                else if (next=='M') monsters.add(new Point(r,c));
            }
        }
        if (debug) {
            System.out.println("Monsters: "+monsters);
            System.out.println("A: "+A);
            for (int r=0;r<R;r++){
                for (int c=0;c<C;c++){
                    if (blocked[r][c]) System.out.print("#");
                    else System.out.print(".");
                }
                System.out.println();
            }
            System.out.println();
        }
        monster_dist = new int[R][C]; for (int r=0;r<R;r++) for (int c=0;c<C;c++) monster_dist[r][c]=INF;
        dist = new int[R][C]; for (int r=0;r<R;r++) for (int c=0;c<C;c++) dist[r][c]=INF;

        //multi source monster BFS
        Queue<Point> BFS = new LinkedList<>();
        for (Point monster : monsters) {
            BFS.add(monster);
            monster_dist[monster.r][monster.c]=0;
        }
        while (!BFS.isEmpty()){
            Point next = BFS.poll();
            for (int i=0;i<4;i++){
                Point child = new Point(next.r+dr[i],next.c+dc[i]);
                if (child.outBounds()||monster_dist[child.r][child.c]!=INF||blocked[child.r][child.c]) continue;
                monster_dist[child.r][child.c]=monster_dist[next.r][next.c]+1;
                BFS.add(child);
            }
            if (debug) System.out.println("STUCK IN BFS1");
        }

        //A BFS
        BFS.add(A);
        dist[A.r][A.c]=0;
        int[][] last_dir = new int[R][C];
        if (A.r==0||A.c==0||A.r==R-1||A.c==C-1) {
            B=new Point(A.r,A.c);
        } else {
            while (!BFS.isEmpty()) {
                Point node = BFS.poll();
                for (int i = 0; i < 4; i++) {
                    Point child = new Point(node.r + dr[i], node.c + dc[i]);
                    if (child.outBounds() || blocked[child.r][child.c] || dist[child.r][child.c] != INF || dist[node.r][node.c] + 1 >= monster_dist[child.r][child.c])
                        continue;
                    last_dir[child.r][child.c] = i;
                    dist[child.r][child.c] = dist[node.r][node.c] + 1;
                    BFS.add(child);
                    //edge node -> end
                    if (child.r == 0 || child.c == 0 || child.r == R - 1 || child.c == C - 1) {
                        BFS.clear();
                        B = child;
                        break;
                    }
                }
                if (debug) System.out.println("STUCK IN BFS2");
            }
        }

        //ret
        if (B==null) out.println("NO");
        else {
            out.println("YES");
            Point cur = B;
            ArrayList<Character> path = new ArrayList<>();
            while (!cur.equals(A)) {
                int dir = last_dir[cur.r][cur.c];
                path.add(letter[dir]);
                cur.r += dr[(dir+2)%4];
                cur.c += dc[(dir+2)%4];
            }
            out.println(path.size());
            for (int i=path.size()-1;i>=0;i--)out.print(path.get(i));
            out.println();
        }
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
        public boolean equals(Point other){
            return this.r==other.r&&this.c==other.c;
        }
        public boolean outBounds(){
            return r<0||r>=R||c<0||c>=C;
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
package Other.USACOGuide.UsacoGuideGold.BFS;

import java.io.*;
import java.util.*;
/*
PROB: Labyrinth
LANG: JAVA
*/
public class Labyrinth {
    static boolean submission = false;
    static boolean debug = true;

    static int R;
    static int C;

    static Point A;
    static Point B;

    static boolean[][] blocked;

    //bfs
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static char[] letter = {'R','D','L','U'};

    public static void main(String[] args) throws IOException {
        //parse
        setup("Labyrinth");
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        blocked = new boolean[R][C];
        for (int r=0;r<R;r++){
            String str = br.readLine();
            for (int c=0;c<C;c++){
                blocked[r][c]=str.charAt(c)=='#';
                if (str.charAt(c)=='A') A = new Point(r,c);
                else if (str.charAt(c)=='B') B = new Point(r,c);
            }
        }

        //BFS
        Queue<Point> BFS = new LinkedList<>();
        int[][] last_dir = new int[R][C]; for (int r=0;r<R;r++) for (int c=0;c<C;c++) last_dir[r][c]=100;
        BFS.add(A);
        while (!BFS.isEmpty()){
            Point node = BFS.poll();
            for (int i=0;i<4;i++){
                Point child = new Point(node.r+dr[i],node.c+dc[i]);
                if (child.dne()||blocked[child.r][child.c] || last_dir[child.r][child.c]!=100) continue;
                last_dir[child.r][child.c] = i;
                BFS.add(child);
            }
        }

        //ret
        if (last_dir[B.r][B.c]==100) out.println("NO");
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
        public boolean equals(Point other){
            return this.r==other.r&&this.c==other.c;
        }
        public boolean dne(){
            return this.r<0||this.r>=R||this.c<0||this.c>=C;
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
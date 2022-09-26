package EC.GoldB2.DP3;

import java.io.*;
import java.util.*;
/*
Cow Navigation
Gold Advanced B 3
DP - floodfill inspired
Notes:
crazy stackoverflow with recursive floodfill that returns if a state has been visited with a lesser cmd
we fix this by using a BFS queue type approach
floodfill the rest by go(left), go(right), go(forward)
huge state... O[N][N][N][N][4]
Query, visited, state, bfs to track states
 */
public class CowNavigation {
    //io
    static boolean submission = true;
    static boolean debug=false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static boolean[][] blocked;
    //state: path1r,path1c,path2r,path2c,dir1
    static int state[][][][][];
    static boolean visited[][][][][];
    static Queue<Query> bfs = new LinkedList<>();
    //const
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("cownav.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("cownav.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        blocked = new boolean[N][N];
        for (int r=0;r<N;r++) {
            String str = br.readLine();
            for (int c=0;c<N;c++) {
                blocked[r][c]=str.charAt(c)=='H';
            }
        }
        //logic: dp
        state = new int[N][N][N][N][4];
        visited = new boolean[N][N][N][N][4];
        bfs.add(new Query(N-1,0,N-1,0,0,1));
        while (!bfs.isEmpty()) {
            Query next = bfs.poll();
            floodfill(next.r1,next.c1,next.r2,next.c2,next.dir,next.cmd);
        }
        //get best
        int min = Integer.MAX_VALUE;
        for (int d=0;d<4;d++){
            if (state[0][N-1][0][N-1][d]!=0) min = Math.min(min,state[0][N-1][0][N-1][d]);
        }
        //turn in answer
        out.println(min-1);
        out.close();
    }
    public static void floodfill(int r1, int c1, int r2, int c2, int dir, int cmd){
        //state has been visited with better path
        if (visited[r1][c1][r2][c2][dir]) return;
        //set the state
        state[r1][c1][r2][c2][dir] = cmd;
        visited[r1][c1][r2][c2][dir] = true;

        //turn right
        bfs.add(new Query(r1,c1,r2,c2,norm(dir+1),cmd+1));

        //turn left
        bfs.add(new Query(r1,c1,r2,c2,norm(dir-1),cmd+1));

        //go forward
        int[] buf = {r1,c1,r2,c2};
        int nextR1 = r1+dr[dir];
        int nextC1 = c1+dc[dir];
        int nextR2 = r2+dr[norm(dir+1)];
        int nextC2 = c2+dc[norm(dir+1)];
        if (!(r1==0&&c1==N-1) && safePair(nextR1,nextC1)) {
            buf[0]=nextR1;
            buf[1]=nextC1;
        }
        if (!(r2==0&&c2==N-1) && safePair(nextR2,nextC2)){
            buf[2]=nextR2;
            buf[3]=nextC2;
        }
        bfs.add(new Query(buf[0],buf[1],buf[2],buf[3],dir,cmd+1));
    }
    public static boolean safePair(int r, int c){
        if (r < 0 || c < 0 || r >= N || c >= N) return false;
        return !blocked[r][c];
    }
    public static int norm(int k){
        int ret = k%4;
        if (ret < 0) ret += 4;
        return ret;
    }
    private static class Query {
        int r1;
        int r2;
        int c1;
        int c2;
        int dir;
        int cmd;
        public Query(int r1, int c1, int r2, int c2, int dir, int cmd){
            this.r1=r1;
            this.c1=c1;
            this.r2=r2;
            this.c2=c2;
            this.dir=dir;
            this.cmd=cmd;
        }
    }
}

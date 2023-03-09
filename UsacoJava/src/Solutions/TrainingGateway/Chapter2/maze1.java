package Solutions.TrainingGateway.Chapter2;/*
PROB: maze1
LANG: JAVA
Overfencing
 */
import java.io.*;
import java.util.*;

public class maze1 {
    //io
    static boolean submission = true;
    static boolean debug = false;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int C;
    static int R;
    static boolean[][] safe;
    static boolean[][] visited;
    static ArrayList<Position> exit = new ArrayList<>();

    //helper
    static final int[] dr = {0,1,0,-1};
    static final int[] dc = {1,0,-1,0};

    //ans
    static int[][] best;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("maze1.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken())*2+1;
        R = Integer.parseInt(st.nextToken())*2+1;
        safe = new boolean[R][C];
        for (int r=0;r<R;r++){
            String str = br.readLine();
            for (int c=0;c<C;c++){
                if (str.charAt(c)==' '){
                    safe[r][c]=true;
                    if (r==0||c==0||r==R-1||c==C-1) exit.add(new Position(r,c));
                }
            }
        }
        if (debug){
            for (int r=0;r<R;r++) {
                for (int c=0;c<C;c++) System.out.print (safe[r][c]?" ":"*");
                System.out.println();
            }
            System.out.println(exit);
            System.out.println();
        }

        //logic: BFS
        int ans = 0;
        visited = new boolean[R][C];
        Queue<Entry> BFS = new LinkedList<>();
        for (int i=0;i<2;i++) {
            BFS.add(new Entry(exit.get(i).r, exit.get(i).c, 0));
            visited[exit.get(i).r][exit.get(i).c] = true;
        }
            while (!BFS.isEmpty()){
                Entry next = BFS.poll();
                ans = Math.max(ans, (next.dist+1)/2);
                for (int j=0;j<4;j++) {
                    if (Good(next.r+dr[j],next.c+dc[j])) {
                        visited[next.r+dr[j]][next.c+dc[j]]=true;
                        BFS.add(new Entry(next.r+dr[j],next.c+dc[j],next.dist+1));
                    }
                }
            }


        //turn in answer
        out.println(ans);
        out.close();
    }
    public static boolean Good(int r, int c){
        return !(r<0||r>=R||c<0||c>=C||!safe[r][c]||visited[r][c]);
    }
    private static class Entry {
        int r;
        int c;
        int dist;
        public Entry(int r, int c, int d){
            this.r=r;
            this.c=c;
            dist=d;
        }
    }
    private static class Position {
        int r;
        int c;
        public Position(int r, int c){
            this.r=r;
            this.c=c;
        }
        public String toString(){
            return "["+r+", "+c+"]";
        }
    }
}

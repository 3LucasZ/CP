package Solutions.USACOGuide.UsacoGuideSilver.FloodFill;/*
USACO 2015 December Contest, Silver
Problem 1. Switching on the Lights
USACO Guide Silver - Floodfill - Normal
Concept: READ THE PROBLEM CAREFULLY! Visited rooms vs lit rooms.
 */
import java.util.*;
import java.io.*;
public class SwitchingOnTheLights {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int N;
    static int M;
    //track
    static int ans = 1;
    static Room[][] rooms;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("lightson.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        rooms = new Room[N+1][N+1];
        for (int i=1;i<=N;i++) {
            for (int j=1;j<=N;j++) {
                rooms[i][j] = new Room();
            }
        }
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(f.readLine());
            int room_r = Integer.parseInt(st.nextToken());
            int room_c = Integer.parseInt(st.nextToken());
            int switch_r = Integer.parseInt(st.nextToken());
            int switch_c = Integer.parseInt(st.nextToken());
            rooms[room_r][room_c].switches.add(new Switch(switch_r,switch_c));
        }
        //logic
        rooms[1][1].lit = true;
        visit_new(1,1);
        //turn in answer
        out.println(ans);
        out.close();
        f.close();
    }
    private static void visit_new(int r, int c){
        for (int i=1;i<N;i++) {
            for (int j=1;j<N;j++) {
                rooms[i][j].temp_visited = false;
            }
        }
        rooms[r][c].visited = true;
        rooms[r][c].activate_switches();
        visit_old(r,c);
    }
    public static int[] dr = new int[]{0,0,1,-1};
    public static int[] dc = new int[]{1,-1,0,0};
    private static void visit_old(int r, int c) {
        if (r>=1&&r<=N&&c>=1&&c<=N&&rooms[r][c].lit&&!rooms[r][c].temp_visited) {
            if(!rooms[r][c].visited) {
                visit_new(r,c);
                return;
            }
            rooms[r][c].temp_visited = true;
            for (int i=0;i<4;i++) {
                visit_old(r+dr[i],c+dc[i]);
            }
        }
    }
    private static class Room {
        boolean temp_visited = false;
        boolean visited = false;
        boolean lit = false;
        ArrayList<Switch> switches = new ArrayList<>();
        public Room () {}
        public void activate_switches() {
            for (Switch s : switches) s.activate();
        }
    }
    private static class Switch {
        int r;
        int c;
        public Switch(int r1, int c1) {
            r=r1;
            c=c1;
        }
        public void activate() {
            if (!rooms[r][c].lit)ans++;
            rooms[r][c].lit = true;
        }
    }
}

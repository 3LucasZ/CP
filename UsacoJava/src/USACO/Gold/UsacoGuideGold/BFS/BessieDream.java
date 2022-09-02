package USACO.Gold.UsacoGuideGold.BFS;

import java.io.*;
import java.util.*;

public class BessieDream {
    static boolean submission = true;
    static boolean debug = false;

    static int[][] grid;
    static int R;
    static int C;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static int inf = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        //parse
        setup("dream");
        StringTokenizer st = new StringTokenizer(br.readLine());
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        grid = new int[R][C];

        for (int r=0;r<R;r++){
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<C;c++){
                grid[r][c]=Integer.parseInt(st.nextToken());
            }
        }

        //BFS
        Queue<State> BFS = new LinkedList<>();
        State init = new State(0,0,false,4);
        BFS.add(init);
        int[] distance = new int[2*5*1000*1000]; Arrays.fill(distance,inf);
        distance[init.unpack()]=0;

        while (!BFS.isEmpty()){
            State next = BFS.poll();
            int nextVal = next.unpack();

            for (int d=0;d<4;d++){
                if (next.direction!=4) d=next.direction;
                State child;

                int r=next.r+dr[d];
                int c=next.c+dc[d];
                //out of bounds || red || no smell and piranha
                if ((r<0||r>=R||c<0||c>=C) || (grid[r][c]==0) || (!next.smell && grid[r][c]==3)) continue;

                //smelly tile
                else if (grid[r][c]==2) child = new State(r,c,true,4);
                //sliding tile
                else if (grid[r][c]==4) {
                    //if one more slide NOT works
                    int rr=r+dr[d]; int cc=c+dc[d];
                    if ((rr<0||rr>=R||cc<0||cc>=C) || (grid[rr][cc]==0) || (grid[rr][cc]==3))
                        child = new State(r,c,false,4);
                    // one more slide works
                    else child = new State(r,c,false,d);
                }
                //piranha tile
                else if (grid[r][c]==3) child = new State(r,c,next.smell, 4);
                //regular tile
                else child = new State(r,c,next.smell,4);

                //if next state is not found yet
                int childVal = child.unpack();
                if (distance[childVal]==inf){
                    distance[childVal]=distance[nextVal]+1;
                    BFS.add(child);
                }

                //sliding only has one child
                if (next.direction!=4) break;
            }
        }

        //debug
        if (debug){
            State state = new State(2,0,false,4);
            out.println(distance[state.unpack()]);
        }


        //ret
        int ans = inf;
        for (int s=0;s<2;s++){
            State next = new State(R-1,C-1,s==1,4);
            int val = next.unpack();
            ans=Math.min(ans,distance[val]);
        }
        out.println(ans==inf?-1:ans);
        out.close();
    }

    private static class State {
        int r;
        int c;
        boolean smell;
        int direction;

        public State(int r, int c, boolean smell, int direction){
            this.r=r;
            this.c=c;
            this.smell=smell;
            this.direction=direction;
        }

        public int unpack(){
            return (smell?1:0)+2*direction+10*r+10000*c;
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

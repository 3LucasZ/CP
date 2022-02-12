package TrainingGateway.Chapter2;

import java.io.*;
import java.util.*;

/*
LANG: JAVA
TASK: castle
Thoughts:
USACO Gateway Floodfill
O(M*N*M*N*4) because O(M*N) for worst case floodfill and removing O(4*M*N) walls worst case
problem of if wall is torn down, must tear down wall on both rooms
pruning by only floodfilling in the component where we remove the wall. Others irrelevant.
 */
public class castle {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int M;
    static Unit[][] floorPlan;
    static boolean[][] visited;
    static int componentSize = 0;
    //const
    static int[] dr = new int[]{0,-1,0,1};
    static int[] dc = new int[]{-1,0,1,0};
    static char[] dir = new char[]{'W','N','E','S'};
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("castle.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        floorPlan = new Unit[N][M];
        for (int r=0;r<N;r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0;c<M;c++){
                floorPlan[r][c] = new Unit(Integer.parseInt(st.nextToken()));
            }
        }
//        if (!submission) {
//            for (int r=0;r<N;r++){
//                for (int c=0;c<M;c++){
//                    System.out.print(floorPlan[r][c]);
//                }
//                System.out.println();
//            }
//        }
        //logic
        // init ans
        int[] ans = initFind();
        out.println(ans[0]);
        out.println(ans[1]);
        //mini floodfill MN4 times
        int rt = 0;
        int ct = 0;
        int dirt = 0;
        int size = 0;
        for (int c=0;c<M;c++){
            for (int r=N-1;r>=0;r--){
                for (int i=0;i<4;i++){
                    if (!floorPlan[r][c].blocked[i])continue;
                    floorPlan[r][c].blocked[i]=false;
                    int res = getSize(r,c);
                    floorPlan[r][c].blocked[i]=true;
                    if (res > size){
                        rt=r;
                        ct=c;
                        dirt=i;
                        size=res;
                    }
                }
            }
        }
        out.println(size);
        out.println((rt+1)+" "+(ct+1)+" "+dir[dirt]);
        out.close();
    }
    public static int getSize(int r, int c){
        visited = new boolean[N][M];
        componentSize=0;
        floodfill(r,c);
        return componentSize;
    }
    public static int[] initFind(){
        visited = new boolean[N][M];
        int components = 0;
        int largestComponent = 0;
        for (int r=0;r<N;r++){
            for (int c=0;c<M;c++){
                if (!visited[r][c]){
                    components++;
                    componentSize=0;
                    floodfill(r,c);
                    largestComponent=Math.max(componentSize,largestComponent);
                }
            }
        }
        return new int[]{components,largestComponent};
    }
    public static void floodfill(int r, int c){
        if (r<0||c<0||r>=N||c>=M||visited[r][c]) return;
        visited[r][c]=true;
        componentSize++;
        for (int i=0;i<4;i++){
            if (!floorPlan[r][c].blocked[i]) floodfill(r+dr[i],c+dc[i]);
        }
    }
    private static class Unit{
        boolean[] blocked = new boolean[4];
        public Unit(int state){
            for (int i=0;i<4;i++){
                blocked[i]=state%2==1;
                state/=2;
            }
        }
        public String toString(){
            char[][] ret = new char[3][3];
            for (int r=0;r<3;r++){
                for (int c=0;c<3;c++){
                    ret[r][c]=' ';
                }
            }
            if (blocked[0]) {
                for (int r=0;r<3;r++)ret[r][0]='#';
            }
            if (blocked[1]) {
                for (int c=0;c<3;c++)ret[0][c]='#';
            }
            if (blocked[2]) {
                for (int r=0;r<3;r++)ret[r][2]='#';
            }
            if (blocked[3]) {
                for (int c=0;c<3;c++)ret[2][c]='#';
            }
            String str = "";
            for (int r=0;r<3;r++){
                for (int c=0;c<3;c++){
                    str+=ret[r][c];
                }
                str+="\n";
            }
            return str;
        }
    }
    public static void printExplore(){
        for (int r=0;r<N;r++){
            for (int c=0;c<M;c++){
                System.out.print(visited[r][c]?1:0);
            }
            System.out.println();
        }
    }
}

package Codeforces.Edu138;

import java.io.*;
import java.util.*;
/*
PROB: CactusWall
LANG: JAVA
*/
public class CactusWall {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //handle TCS
        setup("CactusWall");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++)solve(i);
        out.close();
    }

    static int R;
    static int C;
    static boolean[][] blocked;
    static boolean[][] cactus;
    static int[] dr = {1,1,-1,-1};
    static int[] dc = {-1,1,-1,1};
    static int INF = Integer.MAX_VALUE/10;

    public static void solve(int q) throws IOException {
        //parse
        if (debug) System.out.println("Case: "+q);
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        blocked = new boolean[R+2][C+2];
        cactus = new boolean[R+2][C+2];
        for (int r=1;r<=R;r++){
            String str = br.readLine();
            for (int c=1;c<=C;c++){
                if (str.charAt(c-1)=='#'){
                    blocked[r][c]=true;
                    blocked[r-1][c]=true;
                    blocked[r+1][c]=true;
                    blocked[r][c-1]=true;
                    blocked[r][c+1]=true;
                    cactus[r][c]=true;
                }
            }
        }
        //0/1 Shortest Path
        int[][] dist = new int[R+2][C+2]; for (int r=1;r<=R;r++) for (int c=1;c<=C;c++) dist[r][c]=INF;
        //trace
        Node[][] before = new Node[R+2][C+2];
        Deque<Node> SP = new LinkedList<>();
        //init SP
        for (int r=1;r<=R;r++) {
            if (cactus[r][1]) {
                dist[r][1]=0;
                SP.addFirst(new Node(r, 1));
            }
            else if (!blocked[r][1]) {
                dist[r][1]=1;
                SP.addLast(new Node(r, 1));
            }
        }
        //propagate
        while (!SP.isEmpty()){
            Node get = SP.pollFirst();
            if (debug) System.out.println("get: "+get);
            for (int i=0;i<4;i++){
                int r = get.r+dr[i];
                int c = get.c+dc[i];
                Node next = new Node(r,c);
                if (r>=1 && r<=R && c>=1 && c<=C && dist[next.r][next.c]==INF){
                    //next node is a cactus
                    if (cactus[next.r][next.c]){
                        dist[next.r][next.c]=dist[get.r][get.c];
                        before[next.r][next.c]=new Node(get.r,get.c);
                        SP.addFirst(next);
                    }
                    //next node is empty
                    else if (!blocked[next.r][next.c]){
                        dist[next.r][next.c]=dist[get.r][get.c]+1;
                        before[next.r][next.c]=new Node(get.r,get.c);
                        SP.addLast(next);
                    }
                }
            }
        }
        if (debug){
            print(dist);
        }
        //find sink node
        int ans = INF;
        Node last = new Node(0,C);
        for (int r=1;r<=R;r++) {
            if (dist[r][C]<ans){
                ans=dist[r][C];
                last.r=r;
            }
        }
        if (ans==INF){
            //no wall
            out.println("NO");
        } else {
            //yes wall + wall path
            out.println("YES");
            while (last.c!=1){
                cactus[last.r][last.c]=true;
                last=before[last.r][last.c];
            }
            cactus[last.r][last.c]=true;
            for (int r=1;r<=R;r++){
                for (int c=1;c<=C;c++){
                    if (cactus[r][c]) out.print("#");
                    else out.print(".");
                }
                out.println();
            }
        }
    }
    private static class Node {
        int r;
        int c;
        public Node(int r, int c){
            this.r=r;
            this.c=c;
        }
        public String toString(){
            return "["+r+", "+c+"]";
        }
    }
    public static void print(int[][] arr){
        for (int r=0;r<arr.length;r++){
            for (int c=0;c<arr[r].length;c++){
                int num = arr[r][c];
                if (num>100) num=-1;
                String str = ""+num;
                while (str.length()<5) str+=" ";
                System.out.print(str);
            }
            System.out.println();
        }
        System.out.println();
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
/*
1
3 8
#..#.#.#
..#...#.
.#.#....
 */
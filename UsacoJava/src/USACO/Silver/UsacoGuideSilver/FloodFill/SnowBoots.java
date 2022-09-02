package USACO.Silver.UsacoGuideSilver.FloodFill;

import java.io.*;
import java.util.*;
/*
USACO 2018 February Contest, Silver
Problem 2. Snow Boots
USACO Guide Silver - UsacoGuideSilver.DFS/Floodfill - HARD
Concepts: Abstract UsacoGuideSilver.DFS with multiple UsacoGuideSilver.DFS cases, clever brute force on small numbers
 */
public class SnowBoots {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int N;
    static int B;
    static int[] tiles;
    static Boot[] boots;
    static boolean[][] visited;
    //ans
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("snowboots.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        tiles = new int[N];
        for (int i=0;i<N;i++) {
            tiles[i] = Integer.parseInt(st.nextToken());
        }
        boots = new Boot[B];
        for (int i=0;i<B;i++) {
            st = new StringTokenizer(f.readLine());
            boots[i] = new Boot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        visited = new boolean[N][B];
        //logic
        dfs(0, 0);
        //turn in answer
        out.println(answer);
        out.close();
        f.close();
    }
    public static void dfs(int tile_i, int boot_i) {
        //already visited
        if (visited[tile_i][boot_i]) return;
        visited[tile_i][boot_i] = true;

        //reached end -> keep track of smallest boot
        if (tile_i == N - 1) {
            answer = Math.min(answer, boot_i);
            return;
        }

        //try all possible steps forward
        for (int i=0;i<=boots[boot_i].stride;i++) {
            if (tile_i+i>=N) break;
            if (boots[boot_i].canStep(tiles[tile_i+i])) dfs(tile_i+i, boot_i);
        }

        //try all possible boot change
        for (int j=boot_i;j<B;j++) {
            if (boots[j].canStep(tiles[tile_i])) {
                dfs(tile_i, j);
            }
        }
    }
    private static class Boot {
        int depth;
        int stride;
        public Boot(int d, int s) {
            depth = d;
            stride = s;
        }
        public boolean canStep(int height) {
            return depth >= height;
        }
        public String toString() {
            return "(D: " + depth + ", S: " + stride + ")";
        }
    }
}

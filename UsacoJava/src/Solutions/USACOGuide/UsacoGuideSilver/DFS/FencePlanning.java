package Solutions.USACOGuide.UsacoGuideSilver.DFS;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FencePlanning {
    //param
    //number cows
    static int n;
    //number edges
    static int m;
    //adjmatrix
    static ArrayList<ArrayList<Integer>> adjMatrix = new ArrayList<>();
    static Coordinate[] positions;
    static boolean[] found;
    static int minPerim = Integer.MAX_VALUE;
    static int x1;
    static int x2;
    static int y1;
    static int y2;
    public static void main(String[] args) throws IOException {
        //default
        PrintWriter out;
        BufferedReader f;
        //setup
        boolean submission = true;
        if (submission) {
            f = new BufferedReader(new FileReader("fenceplan.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("fenceplan.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        positions = new Coordinate[n+1];
        found = new boolean[n+1];
        adjMatrix.add(new ArrayList<Integer>());
        for (int i=1;i<n+1;i++) {
            st = new StringTokenizer(f.readLine());
            positions[i] = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            adjMatrix.add(new ArrayList<Integer>());
        }
        for (int i=0;i<m;i++) {
            st = new StringTokenizer(f.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjMatrix.get(u).add(v);
            adjMatrix.get(v).add(u);
        }
        //System.out.println(adjMatrix);
        //printArr(positions);
        //logic
        for (int i=1;i<n+1;i++) {
            if (!found[i]) {
                reset();
                found[i] = true;
                x1 = Math.min(x1, positions[i].x);
                x2 = Math.max(x2, positions[i].x);
                y1 = Math.min(y1, positions[i].y);
                y2 = Math.max(y2, positions[i].y);
                dfs(i);
                minPerim = Math.min(2 * (x2 - x1 + y2 - y1), minPerim);
            }
        }
        //turn in answer
        out.println(minPerim);
        out.close();
        f.close();
    }
    public static void printArr(Coordinate[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += (arr[i] + ", ");
        }
        System.out.println(str);
    }

    public static void dfs(int head) {
        for (int child : adjMatrix.get(head)) {
            if (!found[child]) {
                found[child] = true;
                x1 = Math.min(x1, positions[child].x);
                x2 = Math.max(x2, positions[child].x);
                y1 = Math.min(y1, positions[child].y);
                y2 = Math.max(y2, positions[child].y);
                dfs(child);
            }
        }
    }
    public static void reset() {
        x1 = Integer.MAX_VALUE;
        x2 = Integer.MIN_VALUE;
        y1 = Integer.MAX_VALUE;
        y2 = Integer.MIN_VALUE;
    }
}
class Coordinate {
    int x;
    int y;
    public Coordinate(int x1, int y1) {
        x = x1;
        y = y1;
    }
    public String toString() {
        return ("(" + x + ", " + y + ")");
    }
}

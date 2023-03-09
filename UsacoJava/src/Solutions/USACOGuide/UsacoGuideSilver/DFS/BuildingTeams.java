package Solutions.USACOGuide.UsacoGuideSilver.DFS;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
CSES Problem Set
Building Teams
USACO Guide Depth First Search Applications Easy
Concepts: Coloring
 */
public class BuildingTeams {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int people;
    static int friendships;
    static ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();
    static boolean[] found;
    static boolean[] teams;
    static boolean impossible;
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        people = Integer.parseInt(st.nextToken());
        friendships = Integer.parseInt(st.nextToken());
        found = new boolean[people+1];
        teams = new boolean[people+1];
        for (int i=0;i<people+1;i++) {
            adjacencyList.add(new ArrayList<Integer>());
        }
        for (int i=0;i<friendships;i++) {
            st = new StringTokenizer(f.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
        }
        //logic
        for (int i=1;i<people+1;i++) {
            if (!found[i]) {
                found[i] = true;
                DFS(i, true);
            }
        }
        if (impossible) out.println("IMPOSSIBLE");
        else {
            //turn in answer
            for (int i = 1; i < people + 1; i++) {
                if (teams[i]) {
                    out.print("1 ");
                } else {
                    out.print("2 ");
                }
            }
            out.println();
        }
        out.close();
        f.close();
    }
    public static void DFS(int node, boolean team) {
        for (int u : adjacencyList.get(node)) {
            if (!found[u]) {
                found[u] = true;
                teams[u] = team;
                DFS(u, !team);
            }
            else {
                if (teams[u] == teams[node]) impossible = true;
            }
        }
    }
}

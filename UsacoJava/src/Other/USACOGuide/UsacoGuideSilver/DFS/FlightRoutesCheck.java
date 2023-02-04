package Other.USACOGuide.UsacoGuideSilver.DFS;/*
USACO Guide Silver Normal
CSES Problem Set
Flight Routes Check
Graphs - UsacoGuideSilver.DFS - Strongly Connected Components
Main concept: strongly connected if every u -> v and every v -> u
 */
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FlightRoutesCheck {
    //param
    //num city
    static int n;
    //num flight
    static int m;
    //each city "A" can fly to city "B"
    static ArrayList<ArrayList<Integer>> adjMatrixAB = new ArrayList<>();
    //each city "B" can fly to city "A"
    static ArrayList<ArrayList<Integer>> adjMatrixBA = new ArrayList<>();
    static boolean[] childTo;
    static boolean[] parentTo;
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        childTo = new boolean[n+1];
        parentTo = new boolean[n+1];
        for (int i=0;i<n+1;i++) {
            adjMatrixAB.add(new ArrayList<Integer>());
            adjMatrixBA.add(new ArrayList<Integer>());
        }
        for (int i=0;i<m;i++) {
            st = new StringTokenizer(f.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjMatrixAB.get(u).add(v);
            adjMatrixBA.get(v).add(u);
        }
        //System.out.println(adjMatrixAB);
        //System.out.println(adjMatrixBA);
        //logic
        childTo[1] = true;
        parentTo[1] = true;
        DFS_AB(1);
        DFS_BA(1);
        boolean good = true;
        String answer = "";
        for (int i=1;i<n+1;i++) {
            if (!childTo[i]) {
                good = false;
                answer = "1 " + i;
                break;
            }
            if (!parentTo[i]) {
                good = false;
                answer = i + " 1";
                break;
            }
        }
        //turn in answer
        if (good) {
            out.println("YES");
        }
        else {
            out.println("NO");
            out.println(answer);
        }
        out.close();
        f.close();
    }
    public static void DFS_AB(int head) {
        for (int child : adjMatrixAB.get(head)) {
            if (!childTo[child]) {
                childTo[child] = true;
                DFS_AB(child);
            }
        }
    }
    public static void DFS_BA(int head) {
        for (int parent : adjMatrixBA.get(head)) {
            if (!parentTo[parent]) {
                parentTo[parent] = true;
                DFS_BA(parent);
            }
        }
    }
    public static void printArr(boolean[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += (arr[i] + ", ");
        }
        System.out.println(str);
    }
}

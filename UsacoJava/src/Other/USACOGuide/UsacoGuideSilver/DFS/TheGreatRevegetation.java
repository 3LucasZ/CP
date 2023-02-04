package Other.USACOGuide.UsacoGuideSilver.DFS;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TheGreatRevegetation {
    //io
    static boolean submission = true;
    static BufferedReader f;
    static PrintWriter out;
    //param
    static int numFields;
    static int numCows;
    //logik
    static ArrayList<ArrayList<ArrayList<Integer>>> adjacencyList= new ArrayList<>();
    static boolean found[];
    static boolean coloring[];
    static boolean possibleComponent;
    static boolean possible = true;
    static int numComponents = 0;
    public static void main(String[] args) throws IOException {
        if (submission) {
            f = new BufferedReader(new FileReader("revegetate.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("revegetate.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        StringTokenizer st = new StringTokenizer(f.readLine());
        numFields = Integer.parseInt(st.nextToken());
        numCows = Integer.parseInt(st.nextToken());
        for (int i=0;i<=numFields;i++) {
            ArrayList<ArrayList<Integer>> base = new ArrayList<>();
            base.add(new ArrayList<Integer>());
            base.add(new ArrayList<Integer>());
            adjacencyList.add(base);
        }
        found = new boolean[numFields+1];
        coloring = new boolean[numFields+1];
        for (int i=0;i<numCows;i++) {
            st = new StringTokenizer(f.readLine());
            int type = st.nextToken().equals("S") ? 0 : 1;
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjacencyList.get(u).get(type).add(v);
            adjacencyList.get(v).get(type).add(u);
        }
        //out.println(adjacencyList);
        for (int i=1;i<=numFields;i++){
            if (!found[i]) {
                //out.println("Checking new component starting at: " + i);
                possibleComponent = true;
                found[i] = true;
                coloring[i] = true;
                DFS(i);
                if (possibleComponent) numComponents++;
                else possible = false;
            }
        }
        //print answer
        if (possible) {
            out.print(1);
            for (int i = 0; i < numComponents; i++) {
                out.print(0);
            }
        }
        else {
            out.print(0);
        }
        out.close();
        f.close();
    }
    public static void DFS(int index) {
        for (int u : adjacencyList.get(index).get(0)) {
            if (!found[u]) {
                found[u] = true;
                coloring[u] = coloring[index];
                DFS(u);
            }
            else {
                if (coloring[u] != coloring[index]) possibleComponent = false;
            }
        }
        for (int u : adjacencyList.get(index).get(1)) {
            if (!found[u]) {
                found[u] = true;
                coloring[u] = !coloring[index];
                DFS(u);
            }
            else {
                if (coloring[u] == coloring[index]) possibleComponent = false;
            }
        }
    }
}

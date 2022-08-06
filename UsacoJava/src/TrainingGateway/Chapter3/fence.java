package TrainingGateway.Chapter3;

import java.io.*;
import java.util.*;
/*
PROB: fence
LANG: JAVA
 */
public class fence {
    static boolean submission = true;
    static boolean debug = true;

    static int F;
    static int[][] graph = new int[501][501];
    static int[] size = new int[501];

    public static void main(String[] args) throws IOException {
        //parse
        setup("fence");
        F = Integer.parseInt(br.readLine());
        for (int i=0;i<F;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u][v]++;
            graph[v][u]++;
            size[u]++;
            size[v]++;
        }

        //get euler path
        for (int i=1;i<=500;i++){
            if (size[i]%2==1) {
                findCircuit(i);
                break;
            }
        }
        //no euler path so get euler circuit
        if (circuit.size()==0) for (int i=1;i<=500;i++) if (size[i]!=0) {
            findCircuit(i);
            break;
        }

        //ret
        Collections.reverse(circuit);
        for (int node : circuit) out.println(node);
        out.close();
    }
    static ArrayList<Integer> circuit = new ArrayList<>();
    public static void findCircuit(int u) {
        for (int v=1;v<=500;v++){
            for (int f=0;f<graph[u][v];f++){
                graph[u][v]--;
                graph[v][u]--;
                findCircuit(v);
            }
        }
        circuit.add(u);
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

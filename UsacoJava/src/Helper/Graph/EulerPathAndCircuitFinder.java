package Helper.Graph;

import java.io.*;
import java.util.*;

public class EulerPathAndCircuitFinder{
    //Description:
    //Visits all the EDGES of a graph either as a PATH or CIRCUIT

    //Usage:
    //find the euler circuit: find_circuit(any node) or getEulerCircuit()
    //find the euler path: find_circuit(odd degree node) or getEulerPath()

    //assumptions:
    //strongly connected, nodes of degree 0 discarded
    //if undirected, euler circuit: every node has even degree
    //if undirected, euler path: every node but 2 has even degree
    //if directed, euler circuit: in degree of each node = out degree
    //in this example its adjSet but adjList, adjMatrix are all okay

    //Complexity:
    //O(N+M)

    //Tested to work 100% of the time

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    static int N;
    static int M;
    static HashSet<Integer>[] graph;

    public static void main(String[] args) throws IOException{
        //parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new HashSet[N+1]; for (int i=1;i<=N;i++) graph[i] = new HashSet<>();
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        //logic
        getEulerCircuit();

        //ret
        out.println(circuit);
        out.close();
    }

    static ArrayList<Integer> circuit = new ArrayList<>();
    public static void getEulerPath(){
        for (int i=1;i<=N;i++){
            if (graph[i].size()%2==1){
                findCircuit(i);
                break;
            }
        }
    }
    public static void getEulerCircuit(){
        findCircuit(1);
    }
    public static void findCircuit(int u) {
        while (graph[u].size() != 0) {
            int v = graph[u].iterator().next();
            graph[u].remove(v);
            graph[v].remove(u);
            findCircuit(v);
        }
        circuit.add(u);
    }
}
/*
7 12
1 5
5 7
7 6
6 4
4 3
3 7
7 2
2 6
6 5
5 2
2 4
4 1
 */

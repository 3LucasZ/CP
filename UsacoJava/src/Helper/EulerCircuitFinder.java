package Helper;

import java.io.*;
import java.util.*;

public class EulerCircuitFinder {
    //find the euler circuit: find_circuit(1)
    //find the euler path: find_circuit(odd node)

    //assumptions: strongly connected, nodes of degree 0 discarded

    //euler circuit must (undirected): every node has even degree
    //euler path must (undirected) every node but 2 has even degree

    //euler circuit must (directed): indegree of each node = outdegree

    //Complexity(O(N+M))

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
        getCircuit();

        //ret
        out.println(circuit);
        out.close();
    }

    static ArrayList<Integer> circuit = new ArrayList<>();
    public static void getTour(){
        for (int i=1;i<=N;i++){
            if (graph[i].size()%2==1){
                findCircuit(i);
                break;
            }
        }
    }
    public static void getCircuit(){
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

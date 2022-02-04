import java.io.*;
import java.util.*;

public class Cereal3 {
    //io
    static boolean debug = true;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int M;
    static ArrayList<Integer>[] chosen;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static boolean[] firstPick;
    static boolean[] cowFinish;
    static ArrayList<Integer> component;
    public static void main(String[] args) throws IOException {
        //parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        chosen = new ArrayList[M+1];
        cowFinish = new boolean[N+1];
        firstPick = new boolean[M+1];
        visited = new boolean[M+1];
        graph = new ArrayList[M+1];
        for (int i=1;i<=M;i++) {
            chosen[i] = new ArrayList<>();
            graph[i] = new ArrayList<>();
        }
        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            firstPick[u] = true;
            chosen[u].add(i);
            chosen[v].add(i);
            graph[u].add(v);
            graph[v].add(u);
        }
        if (debug){
            System.out.println(Arrays.toString(chosen));
            System.out.println(Arrays.toString(graph));
            System.out.println(Arrays.toString(firstPick));
        }
        //min hungry cows?
        int notHungry = 0;
        for (int i=1;i<=M;i++) {
            component = new ArrayList<>();
            dfs(i);
            int cereal = component.size();
            int cows =0;
            for (int j : component){
                cows += chosen[j].size();
            }
            cows/=2;
            if (cows < cereal) notHungry += cows;
            else notHungry += cereal;
        }
        out.println(N-notHungry);
        //assign cow order
        for (int i=1;i<=M;i++){
            if (chosen[i].size()==1 && !cowFinish[chosen[i].get(0)] && firstPick[chosen[i].get(0)]) {
                out.println(chosen[i].get(0));
                cowFinish[chosen[i].get(0)] = true;
            }
        }
        for (int i=1;i<=N;i++) {
            if (!cowFinish[i]) out.println(i);
        }
        out.close();
    }
    public static void dfs(int n){
        if (visited[n]) return;
        component.add(n);
        visited[n]=true;
        for (int child : graph[n]){
            dfs(child);
        }
    }
}

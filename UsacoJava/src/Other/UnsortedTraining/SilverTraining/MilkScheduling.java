package Other.UnsortedTraining.SilverTraining;

import java.io.*;
import java.util.*;
public class MilkScheduling {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int M;
    static int[] time;
    static TreeSet<Integer>[] graph;
    static TreeSet<Integer>[] parents;
    static int[] unlocks;
    static int[] unlockTime;
    static int maxUnlockTime;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("msched.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("msched.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        graph = new TreeSet[N+1];
        parents = new TreeSet[N+1];
        for (int i=1;i<=N;i++) {
            graph[i] = new TreeSet<>();
            parents[i] = new TreeSet<>();
        }
        unlocks = new int[N+1];
        unlockTime = new int[N+1];
        time = new int[N+1];
        M = Integer.parseInt(st.nextToken());
        for (int i=1;i<=N;i++) {
            time[i] = Integer.parseInt(br.readLine());
        }
        for (int i=1;i<=M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            parents[v].add(u);
        }
        //logic
        //find the max unlock time along the path
        for (int i=1;i<=N;i++) {
            //graph root
            if (parents[i].size()==0) {
                unlockTime[i] = time[i];
                maxUnlockTime = Math.max(maxUnlockTime, unlockTime[i]);
                dfs(i);
            }
        }
        //turn in answer
        out.println(maxUnlockTime);
        out.close();
    }
    public static void dfs(int node){
        for (int child : graph[node]) {
            unlocks[child]++;
        }
        for (int child : graph[node]){
            //child is unlocked
            if (unlocks[child]==parents[child].size()) {
                for (int parent : parents[child]) {
                    unlockTime[child] = Math.max(unlockTime[child],unlockTime[parent]+time[child]);
                    maxUnlockTime = Math.max(maxUnlockTime, unlockTime[child]);
                }
                dfs(child);
            }
        }
    }
}

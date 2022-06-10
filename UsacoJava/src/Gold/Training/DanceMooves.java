package Gold.Training;

import java.io.*;
import java.util.*;
/*
USACO 2021 January Contest, Gold
Problem 3. Dance Mooves
Thoughts: EXTREMELY difficult and messy implementation but BEAUTIFUL
Inspiration from:
- Silver version (M = inf) solution
- Gold version (M = 1...10^18) solution
  - big moves and mini moves
  - sliding window on disjoint cycles!!
  - edge cases like 0 big moves and big moves > cycleSize
Disjoint Cycles problem
  - solve cycles independently, visited tracker
  - add mini moves O(2K) using a position arrayList
  - use a union multiset for sliding window O(2K+N) algorithm
 */
public class DanceMooves {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int K;
    static long M;
    //Arraylist of (time, position)
    static ArrayList<Position>positions[];
    //pos[i] is the cow in position i
    static int[] pos;
    //position of cow i is transpose[i], after one move group
    static int[] transpose;

    static boolean[] visited;

    static int[] ans;
    public static void main(String[] args) throws IOException {
        //parse input -> transpose function, positions hashset
        setup("");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());

        positions = new ArrayList[N+1];
        for (int i=1;i<=N;i++) {
            positions[i] = new ArrayList<>();
            positions[i].add(new Position(0,i));
        }
        transpose = new int[N+1];
        pos = new int[N+1]; for (int i=1;i<=N;i++) pos[i]=i;

        for (int i=1;i<=K;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            positions[pos[u]].add(new Position(i,v));
            positions[pos[v]].add(new Position(i,u));

            int temp = pos[u];
            pos[u]=pos[v];
            pos[v]=temp;
        }
        for (int i=1;i<=N;i++){
            transpose[pos[i]]=i;
        }
        if (debug) {
            System.out.println(Arrays.toString(transpose));
            for (int i=1;i<=N;i++) System.out.println(positions[i]);
        }

        //finding cycles
        long bigMoves = M/K;
        long miniMoves = M%K;
        visited = new boolean[N+1];
        ans = new int[N+1];
        for (int i=1;i<=N;i++){
            if (visited[i]) continue;
            //new cycle
            int cycleSize = 0;
            int node = i;
            while(true){
                cycleSize++;
                node=transpose[node];
                visited[node]=true;
                if (node==i) break;
            }

            //all different spots
            Multiset union = new Multiset();
            if (bigMoves>=cycleSize){
                //silver version
                node = i;
                while(true){
                    for (Position p : positions[node]){
                        union.add(p.x);
                    }
                    node=transpose[node];
                    if (node==i) break;
                }
                node = i;
                while (true){
                    ans[node]=union.map.size();
                    node=transpose[node];
                    if (node==i) break;
                }
            }
            else if (bigMoves == 0){
                node = i;
                for (int j=0;j<cycleSize;j++){
                    union = new Multiset();
                    for (Position p: positions[node]){
                        if (p.time<=miniMoves)union.add(p.x);
                    }
                    ans[node]=union.map.size();
                    node=transpose[node];
                }
            }
            else {
                //gold version
                if (debug){
                    System.out.println("Cycle size: "+cycleSize);
                    System.out.println("Big moves: "+bigMoves);
                    System.out.println("Mini moves: "+miniMoves);
                }
                //setup
                //first node of window
                int nodeS = i;
                //last node of window
                int nodeE = i;
                //first node, create window
                for (int j=0;j<bigMoves;j++){
                    for (Position p : positions[nodeE]){
                        union.add(p.x);
                    }
                    nodeE = transpose[nodeE];
                }
                //miniMoves
                for (Position p: positions[nodeE]){
                    if (p.time<=miniMoves) union.add(p.x);
                }
                ans[nodeS]=union.map.size();
                for (Position p: positions[nodeE]){
                    if (p.time<=miniMoves) union.delete(p.x);
                }

                //sliding window for rest of nodes
                for (int j=0;j<cycleSize-1;j++){
                    for (Position p: positions[nodeS]){
                        union.delete(p.x);
                    }
                    for (Position p: positions[nodeE]){
                        union.add(p.x);
                    }

                    //miniMoves
                    nodeE=transpose[nodeE];
                    for (Position p: positions[nodeE]){
                        if (p.time<=miniMoves) union.add(p.x);
                    }
                    nodeS=transpose[nodeS];
                    ans[nodeS]=union.map.size();
                    for (Position p: positions[nodeE]){
                        if (p.time<=miniMoves) union.delete(p.x);
                    }
                }
            }
        }
        for (int i=1;i<=N;i++) out.println(ans[i]);
        out.close();
    }
    private static class Multiset {
        HashMap<Integer, Integer> map = new HashMap<>();
        public void add(int x){
            if (!map.containsKey(x))map.put(x,0);
            map.put(x,map.get(x)+1);
        }
        public void delete(int x){
            if (!map.containsKey(x)) return;
            map.put(x,map.get(x)-1);
            if (map.get(x)==0) map.remove(x);
        }
    }
    private static class Position{
        int time;
        int x;
        public Position(int t, int x){
            this.time=t;
            this.x=x;
        }
        public String toString(){
            return "["+time+", "+x+"]";
        }
    }

/*
5 4 10000000
1 3
1 2
2 3
2 4
 */

















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

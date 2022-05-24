package Gold.UsacoGuideGold.EulerTour;/*
USACO 2019 December Contest, Gold
Problem 2. Milk Visits
Euler Tour Technique "Normal"
RIDICULOUSLY CRAZY HARD COMPLICATED WEIRD PROBLEM
Read official solution 10x times, no goes!
Read usaco guide solution 10x times, no goes!
Alpha star video saved me - 25 min video
offline solution
DFS inorder on tree
for a query (A,B,type)
we have the data:
DFSnext[node]
typeStack[type]
let C = the most recent of visitor_type from typeStack
if C is ancestor of A and not B then it is in path, ret true
if C is LCA of A and B then it is in path, ret true

LEARNED:
1. OFFLINE algorithm to process queries how you want to
2. the power of DFS in order traversal while stack tracing
3. using euler tour to find ancestor-descendant relationship O(1)
4. using (3) to find if C is LCA(A,B) in O(1)
5. power of LCA, info about treePath(u,v) and the nodes on it

CRAZY PROBLEM BUT I LOVE IT! (3 hours? 15/15 TCS)
 */

import java.io.*;
import java.util.*;
public class MilkVisitsFull {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int M;
    static int[] cowType;

    static ArrayList<Integer>[] tree;
    static int timer = 0;
    static int[] start;
    static int[] end;

    public static void main(String[] args) throws IOException {
        //parse
        setup("milkvisits");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cowType = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) cowType[i]=Integer.parseInt(st.nextToken());
        tree = new ArrayList[N+1];
        for (int i=1;i<=N;i++) tree[i]=new ArrayList<>();
        for (int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        //preprocess: euler tour
        start = new int[N+1];
        end = new int[N+1];
        eulerDFS(1,0);
        if (debug) {
            System.out.println(Arrays.toString(tree));
            System.out.println(Arrays.toString(start));
            System.out.println(Arrays.toString(end));
        }
        //answer queries offline :)
        //1. process queries
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int type = Integer.parseInt(st.nextToken());
            if (!nodeQueries.containsKey(u)) nodeQueries.put(u,new ArrayList<>());
            if (!nodeQueries.containsKey(v)) nodeQueries.put(v,new ArrayList<>());
            nodeQueries.get(u).add(new Query(v,type,i));
            nodeQueries.get(v).add(new Query(u,type,i));
        }
        if (debug){
            System.out.println(nodeQueries);
        }

        //2. traverse tree with stacking
        DFSnext = new int[N+1];
        typeStack = new Stack[N+1];
        for (int i=1;i<=N;i++) typeStack[i] = new Stack<>();
        goodQuery = new boolean[M];
        processDFS(1, 0);

        for (boolean good : goodQuery) out.print(good?1:0);
        out.close();
    }
    static HashMap<Integer, ArrayList<Query>> nodeQueries = new HashMap<>();
    static int[] DFSnext;
    static Stack<Integer>[] typeStack;
    static boolean[] goodQuery;
    public static void processDFS(int node, int par){
        //add node
        DFSnext[par]=node;
        typeStack[cowType[node]].add(node);
        //process queries at this node
        if (nodeQueries.containsKey(node)) for (Query q : nodeQueries.get(node)){
            int A = node;
            int B = q.other;
            int type = q.type;
            int id = q.id;
            if (typeStack[type].empty()) continue;
            int C = typeStack[type].peek();
            if (cowType[A]==type) {
                goodQuery[id]=true;
            }
            else if (!uIsAncestor(C,B)){
                goodQuery[id]=true;
            }
            else if (uIsLCA(C,B)){
                goodQuery[id]=true;
            }
        }
        for (int child : tree[node]){
            if (child==par) continue;
            processDFS(child,node);
        }
        //pop node
        typeStack[cowType[node]].pop();
    }
    public static void eulerDFS(int node, int par){
        timer++;
        start[node]=timer;
        for (int child : tree[node]){
            if (child==par) continue;
            eulerDFS(child,node);
        }
        end[node]=timer;
    }
    public static boolean uIsAncestor(int u,int v){
        return start[u]<=start[v]&&end[u]>=end[v];
    }
    public static boolean uIsLCA(int u, int v){
        return !uIsAncestor(DFSnext[u],v);
    }
    private static class Query {
        int other;
        int type;
        int id;
        public Query(int o, int t, int i){
            other=o;
            type=t;
            id=i;
        }
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

package Silver.Training;/*
USACO 2013 December Contest, Bronze
Problem 3. Wormholes
USACO Silver Training
USACO Bronze Guide - Recursive Search - Very Hard
Thoughts:
After some experimentation, created genPerms, which is an extremely lucky and cool function that generates all pairs of numbers without repetitions
This is very useful, important on its derivation though for generalization
try perms function does a parallel functional graph type dfs until a cycle appears (this function might have some overkill in it)
creating inFront functional graph was very cool using a single O(NlogN) sort and looking at adjacent points
Very very fun and difficult problem!
(Had to look at test case #4 to find out what went wrong during debug stage though.)
The problem is that a node could be visited while "entry" and be visited while "teleport" and a cycle is not present.
A node must be visited while "entry" multiple times or by "teleport" multiple times I THINK but this might be overkill
 */
import java.io.*;
import java.util.*;

public class Wormholes {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int[] inFront;
    static int[] partner;
    //ans
    static int ans = 0;
    static int perms = 0;
    //genperms
    static boolean[] used;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("wormhole.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input & setup
        N = Integer.parseInt(br.readLine());
        inFront = new int[N+1];
        partner = new int[N+1];
        Point[] wormholes = new Point[N];
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            wormholes[i] = new Point(i+1,x,y);
        }
        used = new boolean[N+1];
        //sort wormholes to create inFront array
        Arrays.sort(wormholes, (a,b)->{
            if (a.y==b.y) return a.x-b.x;
            return a.y-b.y;
        });
        for (int i=0;i<N-1;i++) {
            if (wormholes[i].y==wormholes[i+1].y) inFront[wormholes[i].i] = wormholes[i+1].i;
        }
        if (!submission) System.out.println(Arrays.toString(inFront));
        //create every permutation of partnerings and try it out
        genPerms(new ArrayList<>());
        if (!submission) System.out.println(perms);
        //logic
        //turn in answer
        out.println(ans);
        out.close();
    }
    //Perm tester O(N^2) time
    public static void tryPerm(ArrayList<Integer> perm){
        //create partner array
        for (int i=0;i<perm.size();i+=2) {
            partner[perm.get(i)]=perm.get(i+1);
            partner[perm.get(i+1)]=perm.get(i);
        }
        if (!submission) System.out.println(Arrays.toString(partner));
        //run dfs until cycle detected
        for (int i=1;i<=N;i++) {
            //[node][entry/teleport]
            boolean[][] visited = new boolean[N+1][2];
            int curr=i;
            visited[curr][0] = true;
            while (true){
                curr = partner[curr];
                if (visited[curr][1]) {
                    ans++;
                    return;
                }
                visited[curr][1] = true;
                curr = inFront[curr];
                if (visited[curr][0]) {
                    ans++;
                    return;
                }
                if (curr==0) break;
                visited[curr][0] = true;
            }
        }
        if (!submission) System.out.println("NO CYCLE DETECTED");
    }
    //AWESOME pair generator WITHOUT duplicates O(N!/(2^(N/2)(N/2)!))
    public static void genPerms(ArrayList<Integer> perm){
        if (perm.size()==N){
            //process perm
            perms++;
            tryPerm(perm);
            return;
        }
        int index = 1;
        if (perm.size()!=0) index = perm.get(perm.size()-2);
        while (used[index]) index++;
        perm.add(index);
        used[index]=true;
        for (int i=index;i<=N;i++) {
            if (!used[i]) {
                used[i]=true;
                perm.add(i);
                genPerms(perm);
                perm.remove(perm.size()-1);
                used[i] = false;
            }
        }
        perm.remove(perm.size()-1);
        used[index]=false;
    }
    //Point class for init
    private static class Point {
        int i;
        int x;
        int y;
        public Point(int i, int x, int y){
            this.i=i;
            this.x=x;
            this.y=y;
        }
    }
}

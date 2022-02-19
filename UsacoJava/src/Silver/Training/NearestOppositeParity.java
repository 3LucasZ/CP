package Silver.Training;

import java.io.*;
import java.util.*;
/*
E. Nearest Opposite Parity
Codeforces Round #605 (Div. 3)
USACO Silver Training
Rating: 1900
Tag Search: Shortest Path
Solution:
multisource BFS (new algorithm!)
finding first connectivity by using inverse graph and bfs from end point instead of start point
beautiful algorithm, new techniques, read solution before doing :|
 */
public class NearestOppositeParity {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;
    //param
    static int N;
    static ArrayList<Integer>[] graphInverse;
    static boolean[] even;
    static int[] distance;
    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(br.readLine());
        graphInverse = new ArrayList[N+1];
        even = new boolean[N+1];

        for (int i=1;i<=N;i++) graphInverse[i] = new ArrayList<Integer>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            int leap = Integer.parseInt(st.nextToken());
            even[i] = leap%2==0;
            if (i+leap<=N) graphInverse[i+leap].add(i);
            if (i-leap>=1) graphInverse[i-leap].add(i);
        }
        if (debug) System.out.println(Arrays.toString(graphInverse));
        //logic
        int[] ans = new int[N+1];
        //Arrays.fill(ans,-1);
        //even -> closest odd -> multisource from all odds
        Queue<Integer> multisourceBFS = new LinkedList<Integer>();
        distance = new int[N+1];
        for (int i=1;i<=N;i++) distance[i]=-1;
        for (int i=1;i<=N;i++){
            if (!even[i]) {
                multisourceBFS.add(i);
                distance[i]=0;
            }
        }
        while (!multisourceBFS.isEmpty()) {
            int next = multisourceBFS.poll();
            for (int child : graphInverse[next]) {
                if (distance[child]==-1) {
                    distance[child] = distance[next]+1;
                    multisourceBFS.add(child);
                }
            }
        }
        if (debug) System.out.println(Arrays.toString(distance));
        for (int i=1;i<=N;i++) if (even[i]) ans[i]=distance[i];

        //odd -> closest even -> multisource from all evens
        multisourceBFS = new LinkedList<Integer>();
        distance = new int[N+1];
        for (int i=1;i<=N;i++) distance[i]=-1;
        for (int i=1;i<=N;i++){
            if (even[i]) {
                multisourceBFS.add(i);
                distance[i]=0;
            }
        }
        while (!multisourceBFS.isEmpty()) {
            int next = multisourceBFS.poll();
            for (int child : graphInverse[next]) {
                if (distance[child]==-1) {
                    distance[child] = distance[next]+1;
                    multisourceBFS.add(child);
                }
            }
        }
        if (debug) System.out.println(Arrays.toString(distance));
        for (int i=1;i<=N;i++) if (!even[i]) ans[i]=distance[i];
        //turn in answer
        for (int i=1;i<=N;i++){
            out.print(ans[i]+" ");
        }
        out.close();
    }
}

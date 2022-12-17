import java.io.*;
import java.util.*;

/*
PROB: GCDandMST
LANG: JAVA
*/
public class GCDandMST {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup("GCDandMST");
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    static int N;
    static int P;
    static int[] A;

    public static void solve(int tcs) throws IOException {
        if (debug) System.out.println("Case: " + tcs);
        //* parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        //PQ from min ->
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparing(a->A[a]));
        for (int i=0;i<N;i++) pq.add(i);
        int edges = 0;
        long ans = 0;
        boolean[] vis = new boolean[N];
        while (!pq.isEmpty()){
            int next = pq.poll();
            if (A[next]>=P) break;
            if (vis[next]) continue;
            vis[next]=true;
            //extend left
            int l = next;
            while (l-1>=0 && A[l-1]%A[next]==0){
                l--;
                edges++;
                ans+=A[next];
                if (vis[l]) break;
                vis[l]=true;
            }
            //extend right
            int r = next;
            while (r+1<N && A[r+1]%A[next]==0){
                r++;
                edges++;
                ans+=A[next];
                if (vis[r]) break;
                vis[r]=true;
            }
        }
        if (debug){
            System.out.println("component sum: "+ans);
            System.out.println("edges used: "+edges);
        }
        //* add missing edges using p
        ans += (long) (N - 1 - edges) *P;
        //* ret
        System.out.println(ans);
    }


    static BufferedReader br;
    static PrintWriter out;

    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName + ".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}
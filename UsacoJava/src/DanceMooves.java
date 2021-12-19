import java.io.*;
import java.util.*;

public class DanceMooves {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int N;
    static int K;
    static Set<Integer>[] canGetTo;
    static int[] transpose;
    static int[] ans;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        canGetTo = new Set[N+1];
        transpose = new int[N+1];
        ans = new int[N+1];
        for (int i=0;i<=N;i++) {
            transpose[i]=i;
        }
        for(int i=1;i<=N;i++) {
            canGetTo[i] = new HashSet<Integer>();
            canGetTo[i].add(i);
        }
        out.println(Arrays.toString(transpose));
        for (int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            canGetTo[transpose[u]].add(v);
            canGetTo[transpose[v]].add(u);
            int temp = transpose[u];
            transpose[u]=transpose[v];
            transpose[v]=temp;
            out.println(Arrays.toString(transpose));
        }
        out.println(Arrays.toString(canGetTo));
        out.close();
    }
}

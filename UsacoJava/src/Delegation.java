import java.io.*;
import java.util.*;

public class Delegation {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static ArrayList<Integer>[] tree;

    static boolean[] ans = new boolean[N];
    static int K = 0;
    static int[] stemLengths;

    public static void main(String[] args) throws IOException {
        setup("deleg");
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        stemLengths = new int[N+1];

        for (K=1;K<=N-1;K++){
            if ((N-1)%K==0 && tryPartition(K)) out.print(1);
            else out.print(0);
        }
        out.close();
    }
    public static boolean tryPartition(int l){
        return DFS(1,0)==0;
    }

    public static int DFS(int node, int par){
        int unPaired = 0;
        ArrayList<Integer> stems = new ArrayList<>();

        for (int child : tree[node]){
            if (child!=par) {
                //get root stem len, if its bad, return -1 all the way to root
                int stem = DFS(child,node);
                if (stem==-1) return -1;
                stem++;
                stems.add(stem);

                //if root stem conjugate is available
                if (stemLengths[len-stem]>0){
                    unPaired--;
                    stemLengths[len-stem]--;
                }
                //root stem conjugate unavailable
                else {
                    unPaired++;
                    stemLengths[stem]++;
                }
            }
        }
        //return based on stems
        if (unPaired == 0) return 0;
        if (unPaired == 1) {
            for (int stem : stems){
                if (stemLengths[stem]!=0) return stem%len;
            }
        }
        return -1;
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

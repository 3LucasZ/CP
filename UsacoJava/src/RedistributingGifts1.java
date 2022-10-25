import java.io.*;
import java.util.*;
/*
Solves 8/18 TCS
Super weird algorithm using cycles, anticycles, and bitsets
 */
public class RedistributingGifts1 {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static boolean[][] graph;
    static long[] dp;
    static boolean[] visited;

    static ArrayList<Integer>[] curGraph;

    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        N = Integer.parseInt(br.readLine());
        graph = new boolean[N][N];
        for (int cow=0;cow<N;cow++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int favor=N;favor>=1;favor--){
                int gift = Integer.parseInt(st.nextToken());
                int giftOwner = gift-1;
                graph[giftOwner][cow]=true;
                if (gift-1==cow) break;
            }
        }
        if (debug){
            for (int i=0;i<N;i++) System.out.println(Arrays.toString(graph[i]));
        }

        //dp init
        int sets = 1<<N;
        dp = new long[sets];
        dp[0]=1;

        //bitset dp
        for (int set=1;set<sets;set++){
            //get top bit
            int bit;
            for (bit=N-1;bit>=0;bit--){
                if (((1<<bit)&set)!=0) break;
            }
            //create a cur graph
            curGraph = new ArrayList[N]; for (int i=0;i<N;i++) curGraph[i] = new ArrayList<>();
            for (int i=0;i<N;i++){
                if ((set&(1<<i))==0) continue;
                for (int j=0;j<N;j++){
                    if ((set&(1<<j))==0) continue;
                    if (graph[i][j]) curGraph[i].add(j);
                }
            }
            //DFS bit node to find all cycles containing node
            visited = new boolean[N];
            if (debug) {
                System.out.println("root: "+bit+", set: "+str(set));
                System.out.println("graph: "+Arrays.toString(curGraph));
            }
            DFS(bit,set,bit,set);
        }
        if (debug) System.out.println("dp: "+Arrays.toString(dp));

        //handle queries
        int Q = Integer.parseInt(br.readLine());
        for (int q=0;q<Q;q++){
            String str = br.readLine();
            int H = 0;
            int G = 0;
            for (int i=0;i<N;i++){
                if (str.charAt(i)=='H') H+=(1<<i);
                else G+=(1<<i);
            }
            out.println(dp[H]*dp[G]);
        }
        out.close();
    }
    public static void DFS(int node, int set, int root, int antipath){
        for (int child:curGraph[node]){
            if (visited[child])continue;
            visited[child] = true;
            antipath-=(1<<child);
            //found loop back to root
            if (child==root){
                //antipath is now a anti cycle path so we update
                dp[set]+=dp[antipath];
                if (debug) System.out.println("anticycle: "+str(antipath));
            }
            //continue modi DFS
            else DFS(child, set, root, antipath);
            visited[child] = false;
            antipath+=(1<<child);
        }
    }
    public static String str(int set){
        String ret = "";
        for (int i=0;i<N;i++){
            if (((1<<i)&set)!=0)ret+=1;
            else ret+=0;
        }
        return ret;
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

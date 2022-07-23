import java.io.*;
import java.util.*;

public class SmallMultiple {
    static boolean submission = false;
    static boolean debug = true;

    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        setup("");
        int K = Integer.parseInt(br.readLine());

        int[] cost = new int[K]; Arrays.fill(cost,INF);
        cost[1]=1;
        Deque<Integer> BFS = new LinkedList<>();
        BFS.add(1);

        while (!BFS.isEmpty()){
            int next = BFS.pollFirst();
            int a = (next+1)%K;
            int b = (next*10)%K;

            if (cost[next]+1<cost[a]){
                cost[a]=cost[next]+1;
                BFS.addLast(a);
            }
            if (cost[next]<cost[b]){
                cost[b]=cost[next];
                BFS.addFirst(b);
            }
        }

        out.println(cost[0]);
        out.close();
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

import java.io.*;
import java.util.*;

public class CowHopscotch {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int K;
    static long[] V;
    static long[] positiveSum;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        //parse
        setup("hop");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        V = new long[N+1];
        positiveSum = new long[N+1];
        for (int i=1;i<=N;i++){
            V[i]=Integer.parseInt(br.readLine());
            positiveSum[i]=positiveSum[i-1]+(V[i]>0?V[i]:0);
        }
        if (debug) System.out.println(Arrays.toString(positiveSum));

        //dp setup
        //dp[j] = best for j squares
        dp = new long[N+1];
        dp[1]=Math.max(dp[0],V[1]);
        //sliding window
        Deque<Integer> deque = new LinkedList<>();
        //deque.add(0);

        //transitions
        for (int i=2;i<=N;i++){
            //slide window
            deque.addLast(i-2);
            if(i-deque.peekFirst()>K) deque.pollFirst();

            //poll useless dp states
            int prev = deque.pollLast();
            while (!deque.isEmpty()) {
                int prevPrev = deque.peekLast();
                if (transition(prev, i) > transition(prevPrev, i)) {
                    deque.pollLast();
                } else {
                    break;
                }
            }
            deque.addLast(prev);

            //updates
            int j = deque.getFirst();
            dp[i]=Math.max(transition(i-1,i),transition(j,i));
        }

        //ret
        long ans = 0;
        for (int i=0;i<=N;i++) ans=Math.max(ans,dp[i]);
        out.println(ans);
        out.close();
    }
    public static long transition(int j, int i){
        long ret = dp[j]+V[j+1]+Math.max(0,positiveSum[i-1]-positiveSum[j+1]);
        if (j!=i-1) ret+=V[i];
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

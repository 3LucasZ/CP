package EC.PlatA1.DP3;

import java.io.*;
import java.util.*;

public class HiddenCodes {

    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static int L;
    static String[] codes;
    static String text;

    public static void main(String[] args) throws IOException {
        //parse
        setup("codes");
        N = Integer.parseInt(br.readLine());
        codes = new String[N];
        for (int i=0;i<N;i++){codes[i]=br.readLine();}
        text = br.readLine();
        L = text.length();

        //find all halfRMC <RMC_idx start, code_i>
        ArrayList<halfRMC> halfRMCS = new ArrayList<>();
        for (int code=0;code<N;code++){
            //create charStack
            Stack<Character> charStack = new Stack();
            for (char c : codes[code].toCharArray()) charStack.add(c);

            //text traverse to fill pairs
            for (int i=L-1;i>=0;i--) {
                if (charStack.size()>1){
                    if (text.charAt(i) == charStack.peek()) {
                        charStack.pop();
                    }
                } else {
                    if (text.charAt(i) == charStack.peek()){
                        halfRMCS.add(new halfRMC(i, code));
                    }
                }
            }
        }

        //find all RMC <RMC_idx start, RMC_idx end, RMC_contrib>
        ArrayList<RMC>[] RMCs = new ArrayList[L]; for (int i=0;i<L;i++) RMCs[i] = new ArrayList<>();
        for (halfRMC half : halfRMCS){
            //create charQueue
            Queue<Character> charQueue = new LinkedList<>();
            for (char c : codes[half.code_i].toCharArray()) charQueue.add(c);

            //text traverse
            int i;
            for (i=half.start;i<=Math.min(half.start+999,L-1);i++){
                if (charQueue.size()>0){
                    if (text.charAt(i) == charQueue.peek()) {
                        charQueue.poll();
                    }
                } else {
                    RMCs[half.start].add(new RMC(half.start,i,codes[half.code_i].length()));
                    break;
                }
            }
        }
        halfRMCS = null;

        //knapsack
        int[] dp = new int[L+1];
        dp[0]=0;
        for (int i=0;i<L;i++){
            dp[i+1]=Math.max(dp[i+1],dp[i]);
            for (RMC rmc : RMCs[i]){
                dp[rmc.end+1] = Math.max(dp[rmc.end+1],dp[i]+rmc.value);
            }
        }

        //ret
        out.println(dp[L]);
        out.close();
    }
    private static class halfRMC {
        int start;
        int code_i;
        public halfRMC (int s, int c){
            start=s;
            code_i=c;
        }
    }
    private static class RMC{
        int start;
        int end;
        int value;
        public RMC(int s, int e, int v){
            this.start=s;
            this.end=e;
            this.value=v;
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

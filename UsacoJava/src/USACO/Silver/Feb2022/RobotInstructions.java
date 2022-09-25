package USACO.Silver.Feb2022;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Meet in the Middle
Notes:
NEW TECHNIQUE: O(N2^N) bash -> O(N(2^N/2)) bash by splitting exponent
try #1: O(N*2^N) bash fail
try #2: meet in the middle with triple nested hash map -> fail (really stupid)
try #3: meet in the middle with hash map, terrible custom hash function, too many collisions
try #4: meet iin the middle with hash map, custom hash function TRUE O(N*2^N/2) TIME

mistake #1: thinking about DP
mistake #2: simple problem statement. limiting eyesight.
mistake #3: complicated implementation with triple nest... decent unneeded accuracy but sucky time... work smarter not harder haha
mistake #4: hash function prime-ing important

really beautiful problem with great learning experience
 */
public class RobotInstructions {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    //param
    static int N;
    static long finX;
    static long finY;
    static long[] x;
    static long[] y;

    //ret
    static long[] ans;

    //hash
    static final int A = 1000000009;
    static final int B = 805306457;
    static final int C = 1000000007;
    static final int D = 1610612741;

    public static void main(String[] args) throws IOException{
        //parse
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        finX = Integer.parseInt(st.nextToken());
        finY = Integer.parseInt(st.nextToken());
        x = new long[N];
        y = new long[N];
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        //left side hashset creation
        HashMap<InstructionNet, Integer> leftInstructions = new HashMap<>();
        for (int i=0;i<Math.pow(2,N/2);i++){
            int j=i;
            int cnt=0;
            long sumX=0;
            long sumY=0;
            while (j>0){
                if (j%2==1) {
                    sumX+=x[cnt];
                    sumY+=y[cnt];
                }
                cnt++;
                j/=2;
            }
            InstructionNet add = new InstructionNet(sumX, sumY, Integer.bitCount(i));
            if (!leftInstructions.containsKey(add)) leftInstructions.put(add, 0);
            leftInstructions.put(add,leftInstructions.get(add)+1);
        }
        if (debug) System.out.println(leftInstructions);

        //right side check with hashset
        ans = new long[N+1];
        for (int i=0;i<Math.pow(2,(N+1)/2);i++){
            int j=i;
            int cnt=N/2;
            long sumX=0;
            long sumY=0;
            while (j>0){
                if (j%2==1) {
                    sumX+=x[cnt];
                    sumY+=y[cnt];
                }
                cnt++;
                j/=2;
            }
            for (int k=0;k<=N/2;k++){
                InstructionNet add = new InstructionNet(finX-sumX,finY-sumY,k);
                if (leftInstructions.containsKey(add)){
                    ans[Integer.bitCount(i)+k]+=leftInstructions.get(add);
                }
            }
        }

        //print ans
        for (int i=1;i<=N;i++) {
            out.println(ans[i]);
        }
        out.close();
    }

    private static class InstructionNet {
        long x;
        long y;
        int instructions;

        public InstructionNet(long x, long y, int i){
            this.x=x;
            this.y=y;
            this.instructions=i;
        }

        @Override
        public boolean equals(Object o){
            InstructionNet other = (InstructionNet) o;
            return x==other.x&&y==other.y&&instructions==other.instructions;
        }

        @Override
        public int hashCode(){
            return (int)((x*A+y*B+instructions*C)%D);
        }

        public String toString(){
            return "["+x+", "+y+", "+instructions+"]";
        }
    }
}

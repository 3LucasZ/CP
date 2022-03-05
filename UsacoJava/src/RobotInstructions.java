import java.io.*;
import java.util.*;
/*
CSES Problem Set
Meet in the Middle
Notes:
NEW TECHNIQUE: O(N2^N) bash -> O(N(2^N/2)) bash by splitting exponent
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

        Multiset sums = new Multiset();

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
            sums.add(sumX, sumY, Integer.bitCount(i));
        }
        if (debug) System.out.println(sums);

        long ans[] = new long[N+1];
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
            HashMap<Integer, Integer> res = sums.get(finX-sumX,finY-sumY);
            for (Integer instructions : res.keySet()){
                ans[instructions+Integer.bitCount(i)]+=res.get(instructions);
            }
        }
        for (int inst=1;inst<=N;inst++){
            out.println(ans[inst]);
        }
        out.close();
    }
    private static class Multiset {
        HashMap<Long, HashMap<Long, HashMap<Integer, Integer>>> set = new HashMap<>();

        public void add(long x, long y, int instructions){
            if (!set.containsKey(x)) set.put(x, new HashMap<>());
            if (!set.get(x).containsKey(y)) set.get(x).put(y,new HashMap<>());
            if (!set.get(x).get(y).containsKey(instructions)) set.get(x).get(y).put(instructions, 0);
            set.get(x).get(y).put(instructions,set.get(x).get(y).get(instructions)+1);
        }
        public HashMap<Integer, Integer> get(long x, long y){
            if (!set.containsKey(x))  return new HashMap<>();
            if (!set.get(x).containsKey(y)) return new HashMap<>();
            return set.get(x).get(y);
        }
        public String toString() {
            return set.toString();
        }
    }
}

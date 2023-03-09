package Solutions.UnsortedTraining.SilverTraining;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Meet in the Middle
Notes:
NEW TECHNIQUE: O(N2^N) bash -> O(N(2^N/2)) bash by splitting exponent
 */
public class MeetInTheMiddle {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    //param
    static int N;
    static int X;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        //parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Multiset sums = new Multiset();

        for (int i=0;i<Math.pow(2,N/2);i++){
            int j=i;
            int cnt=0;
            int sum=0;
            while (j>0){
                if (j%2==1) sum+=arr[cnt];
                cnt++;
                j/=2;
                if (sum>X) break;
            }
            if (sum>X) continue;
            sums.add(sum);
        }
        if (debug) System.out.println(sums);

        long ans=0;
        for (int i=0;i<Math.pow(2,(N+1)/2);i++){
            int j=i;
            int cnt=N/2;
            int sum=0;
            while (j>0){
                if (j%2==1) sum+=arr[cnt];
                cnt++;
                j/=2;
                if (sum>X) break;
            }
            if (sum>X) continue;
            if (sums.set.containsKey(X-sum)){
                ans+=sums.set.get(X-sum);
            }
        }
        out.println(ans);
        out.close();
    }

    private static class Multiset {
        HashMap<Integer, Integer> set = new HashMap<>();

        public void add(int num){
            if (!set.containsKey(num)) set.put(num, 0);
            set.put(num,set.get(num)+1);
        }

        public String toString() {
            return set.toString();
        }
    }
}

package Other.Codeforces.Round797;

import java.io.*;
import java.util.*;

public class CountTheTrains {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) solve();
        out.close();
    }

    public static void solve() throws IOException{
        //parse
        br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] limit = new int[N+1];
        for (int i=1;i<=N;i++) limit[i] = Integer.parseInt(st.nextToken());
        int[] speed = new int[N+1];
        speed[0]=-1;
        speed[1]=limit[1];
        for (int i=2;i<=N;i++) speed[i] = Math.min(speed[i-1],limit[i]);

        //logic
        BIT bit = new BIT(N);
        TreeMap<Integer, Integer> speedCheckpoints = new TreeMap<>();
        for (int i=1;i<=N;i++){
            if (speed[i]!=speed[i-1]) {
                speedCheckpoints.put(i,speed[i]);
                bit.set(i,1);
            }
        }

        if (debug){
            System.out.println(Arrays.toString(limit));
            System.out.println(Arrays.toString(speed));
            System.out.println(speedCheckpoints);
            System.out.println(bit);
        }

        //answer queries
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int delta = Integer.parseInt(st.nextToken());
            limit[id]-=delta;

            Integer point = speedCheckpoints.floorKey(id);
            Integer pointSpeed = speedCheckpoints.get(point);
            if (limit[id] < pointSpeed) {
                speedCheckpoints.put(id, limit[id]);
                bit.set(id,1);
                Integer next = id;
                while (true){
                    next = speedCheckpoints.higherKey(next);
                    if (next==null || speedCheckpoints.get(next) < limit[id]) break;
                    speedCheckpoints.remove(next);
                    bit.set(next,0);
                }
            }

            out.print(bit.rangeSum(1,N)+" ");
        }
        out.println();
    }
    private static class BIT {
        //must be 1 indexed
        int K;
        int[] A;
        int[] bit;

        public BIT(int K){
            this.K=K;
            bit = new int[K+1];
            A = new int[K+1];
        }

        public void set(int i, int val){
            update(i, val-A[i]);
        }

        public void update(int i, int val){
            A[i]+=val;
            while (i <= K){
                bit[i]+=val;
                i += i&(-i);
                //System.out.println("STUCK IN UPDATE");
            }
        }

        public int preSum(int i){
            int sum=0;
            while (i > 0){
                sum += bit[i];
                i -= i&(-i);
                //System.out.println("STUCK IN PRESUM");
            }
            return sum;
        }

        public int rangeSum(int lo, int hi){
            return preSum(hi)-preSum(lo-1);
        }

        public String toString(){
            return Arrays.toString(A);
        }
    }
}

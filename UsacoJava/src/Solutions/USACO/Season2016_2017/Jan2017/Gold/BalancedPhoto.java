package Solutions.USACO.Season2016_2017.Jan2017.Gold;

import java.io.*;
import java.util.*;
public class BalancedPhoto {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static Integer[] TMP;
    static Integer[] A;
    static Integer[] arr;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("bphoto.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("bphoto.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        N = Integer.parseInt(br.readLine());
        TMP = new Integer[N+1];
        TMP[0]=-1; for (int i=1;i<=N;i++) TMP[i] = Integer.parseInt(br.readLine());

        //Coordinate compress
        A = new Integer[N+1];
        for (int i=0;i<=N;i++) A[i]=i;
        Arrays.sort(A,(a,b)->TMP[a]-TMP[b]);
        arr = new Integer[N+1];
        for (int i=0;i<=N;i++) arr[A[i]]=i;
        if (!submission) System.out.println(Arrays.toString(arr));

        //logic: BIT
        BIT left = new BIT(N);
        BIT right = new BIT(N);
        for (int i=1;i<=N;i++) right.update(i, 1);

        int ans = 0;
        for (int i=1;i<=N;i++){
            int lcnt = left.rangeSum(arr[i]+1, N);
            int rcnt = right.rangeSum(arr[i]+1, N);
            if (lcnt > rcnt*2 || rcnt > lcnt*2) ans++;
            left.update(arr[i],1);
            right.update(arr[i],-1);
        }

        //turn in answer
        out.println(ans);
        out.close();
    }

    private static class BIT {
        int K;
        int[] bit;

        public BIT(int K){
            this.K=K;
            bit = new int[K+1];
        }

        public void update(int i, int val){
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
    }
}

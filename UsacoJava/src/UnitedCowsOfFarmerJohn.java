import java.io.*;
import java.util.*;

public class UnitedCowsOfFarmerJohn {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int[] breed;
    static int[] lastOfBreed;
    static int[] left;
    static int[] right;
    public static void main(String[] args) throws IOException {
        setup("");
        N = Integer.parseInt(br.readLine());
        breed = new int[N+1];
        lastOfBreed = new int[N+1];
        left = new int[N+1];
        right = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            breed[i] = Integer.parseInt(st.nextToken());
            left[i]=lastOfBreed[breed[i]];
            right[lastOfBreed[breed[i]]]=i;
            lastOfBreed[breed[i]]=i;
        }
        if (debug){
            System.out.println(Arrays.toString(breed));
            System.out.println(Arrays.toString(left));
            System.out.println(Arrays.toString(right));
            System.out.println(Arrays.toString(lastOfBreed));
        }

        long ans = 0;
        BIT cnt = new BIT(N);
        for (int i=1;i<=N;i++){
            ans += cnt.rangeSum(left[i]+1,N);
            cnt.update(i,1);
            if (left[i]!=0) cnt.update(left[i],-1);
        }

        out.println(ans);
        out.close();
    }
    private static class BIT {
        //must be 1 indexed
        //range[i,j] inclusive
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

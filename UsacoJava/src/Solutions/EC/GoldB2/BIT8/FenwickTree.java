package Solutions.EC.GoldB2.BIT8;

public class FenwickTree {
    static int N;
    static int[] arr;
    static int[] bit;

    public static void main(String[] args){
        N=8;
        arr = new int[]{0,6,4,8,3,2,3,5,1};
        bit = new int[N+1];

        for (int i=1;i<=N;i++){
            update(i, arr[i]);
            System.out.println(rangeSum(1, N));
        }
    }

    public static void update(int i, int val){
        while (i <= N){
            bit[i]+=val;
            i += i&(-i);
            //System.out.println("STUCK IN UPDATE");
        }
    }

    public static int preSum(int i){
        int sum=0;
        while (i > 0){
            sum += bit[i];
            i -= i&(-i);
            //System.out.println("STUCK IN PRESUM");
        }
        return sum;
    }

    public static int rangeSum(int lo, int hi){
        return preSum(hi)-preSum(lo-1);
    }
}

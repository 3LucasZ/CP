package Misc.Boilerplate.Helper;

public class BIT {
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

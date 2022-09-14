package Helper;

public class BIT {
    //must be 1 indexed
    //range sum is inclusive
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
}

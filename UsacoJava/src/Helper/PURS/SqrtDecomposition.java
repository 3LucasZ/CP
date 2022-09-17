package Helper.PURS;

public class SqrtDecomposition {
    private static class RangeQuery {
        int A;
        int B;
        int[] arr;
        int[] decomp;

        public RangeQuery(int A){
            this.A=A;
            arr = new int[A];
            B = (int)Math.sqrt(A);
            decomp = new int[A];
        }

        public void update(int i, int val){
            arr[i]=val;
            decomp[i/B]=Math.max(decomp[i/B], val);
        }

        public int get(int i, int j){
            int ii=(i+B)/B;
            int jj=j/B;
            int ret = Integer.MIN_VALUE;
            for (int k=i;k<Math.min(B*ii,j);k++) ret=Math.max(ret,arr[k]);
            for (int k=ii;k<jj;k++) ret=Math.max(ret,decomp[k]);
            for (int k=Math.max(B*jj,i);k<=j;k++) ret=Math.max(ret,arr[k]);
            return ret;
        }
    }
}

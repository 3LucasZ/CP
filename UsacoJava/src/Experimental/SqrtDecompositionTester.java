package Experimental;

public class SqrtDecompositionTester {
    static boolean debug = true;
    public static void main(String[] args){
        //In this example, we are using RangeQuery class update and get on array maximum queries (i,j)
        int N = 5000;
        RangeQueryFast rqf = new RangeQueryFast(N);
        RangeQuerySlow rqs = new RangeQuerySlow(N);

        for (int i=0;i<N;i++){
            int num = (int)(Math.random()*2000000000);
            rqf.update(i, num);
            rqs.update(i, num);
        }

        for (int i=0;i<N;i++){
            for (int j=i;j<N;j++){
                if (rqf.get(i,j)!=rqs.get(i,j)) System.out.println("BROKEN: "+i+", "+j);
            }
        }
    }
    private static class RangeQuerySlow {
        int A;
        int[] arr;
        public RangeQuerySlow(int A){
            this.A=A;
            arr = new int[A];
        }

        public void update(int i, int val){
            arr[i]=val;
        }

        public int get(int i, int j){
            int ret = 0;
            for (int k=i;k<=j;k++) ret=Math.max(ret,arr[k]);
            return ret;
        }
    }
    private static class RangeQueryFast {
        int A;
        int B;
        int[] arr;
        int[] decomp;

        public RangeQueryFast(int A){
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
            for (int k=i;k<Math.min(B*ii,j);k++) {ret=Math.max(ret,arr[k]);}
            for (int k=ii;k<jj;k++) { ret=Math.max(ret,decomp[k]);}
            for (int k=Math.max(B*jj,i);k<=j;k++) {ret=Math.max(ret,arr[k]);}
            return ret;
        }
    }
}

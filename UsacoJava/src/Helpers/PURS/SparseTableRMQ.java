package Helpers.PURS;

import java.util.Arrays;

public class SparseTableRMQ {
    public static void main(String[] args){
        int[] arr = {1,2,3,6,3,4,-1,8,83,2,6,2,7,9,9,2,7,5,34,7,2,2,6,7,3,5};
        RMQ rmq = new RMQ(arr);

        for (int i=0;i<arr.length;i++)System.out.println(Arrays.toString(rmq.rangeMin[i]));
        System.out.println(rmq.query(arr.length-5,arr.length-1));
    }
    /*
    Goal: Range queries
    Arguments: [any array]
    Time Complexity: O(NlgN+Q)
    Space Complexity: O(NlgN)
     */
    private static class RMQ{
        int lgSz;
        int[] arr;
        int[][] rangeMin;
        public RMQ(int[] arr){
            this.arr=arr;
            this.lgSz=log2(arr.length)+1;
            rangeMin=new int[arr.length][lgSz+1];
            for(int i=0;i<arr.length;i++) rangeMin[i][0]=arr[i];
            int range=1;
            for(int bin=1;bin<=lgSz;bin++){
                for(int i=0;i<arr.length;i++){
                    int j=i+range;
                    if(i+2*range>arr.length) break;
                    rangeMin[i][bin]=Math.min(rangeMin[i][bin-1],rangeMin[j][bin-1]);
                }
                range*=2;
            }
        }
        public int query(int l,int r){
            int len=r-l+1;
            int bits=log2(len);
            int subLen=1<<bits;
            return Math.min(rangeMin[l][bits],rangeMin[r-subLen+1][bits]);
        }
    }
    static int log2(int num){
        return (int)(Math.log(num)/Math.log(2));
    }
}

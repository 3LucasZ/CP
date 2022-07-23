package Misc.Helper;

import java.util.Arrays;

public class SparseTableRMQ {
    public static void main(String[] args){
        int[] arr = {1,2,3,6,3,4,-1,8,83,2,6,2,7,9,9,2,7,5,34,7,2,2,6,7,3,5};
        int size = arr.length;
        RMQ rmq = new RMQ(arr,size);

        System.out.println("Size: "+size);
        System.out.println("Log: "+rmq.log);
        for (int i=0;i<size;i++)System.out.println(Arrays.toString(rmq.rangeMin[i]));
        System.out.println(rmq.get(size-5,size-1));
    }
    private static class RMQ{
        int log;
        int size;
        int[] arr;
        int[][] rangeMin;
        public RMQ(int[] arr, int size){
            this.arr=arr;
            this.size=size;
            this.log=(int)Math.ceil(Math.log(size)/Math.log(2))-1;
            rangeMin = new int[size][log+1];
            for (int i=0;i<size;i++) rangeMin[i][0]=arr[i];
            int range = 1;
            for (int bin=1;bin<=log;bin++){
                for (int i=0;i<size;i++){
                    int j=i+range;
                    if (i+2*range>size) break;
                    rangeMin[i][bin]=Math.min(rangeMin[i][bin-1],rangeMin[j][bin-1]);
                }
                range*=2;
            }
        }
        public int get(int i, int j){
            int len = j-i+1;
            int bits = (int)Math.ceil(Math.log(len)/Math.log(2))-1;
            int subLen = 1<<bits;
            int a = i;
            int b = j-subLen;
            System.out.println("bits: "+bits);
            System.out.println("sublen: "+subLen);
            return Math.min(rangeMin[a][bits],rangeMin[b][bits]);
        }
    }
}

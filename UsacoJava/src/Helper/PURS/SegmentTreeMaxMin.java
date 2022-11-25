package Helper.PURS;

import java.util.*;

public class SegmentTreeMaxMin {
    public static void main(String[] args){
        SegTree tree = new SegTree(10, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        System.out.println(tree.treeMax[1]);
        System.out.println(tree.treeMax[2]+", "+ tree.treeMax[3]);
        System.out.println(tree.treeMax[4]+", "+ tree.treeMax[5]+", "+tree.treeMax[6]+", "+tree.treeMax[7]);
        System.out.println(tree.treeMax[8]+", "+ tree.treeMax[9]+", "+tree.treeMax[10]+", "+tree.treeMax[11]+", "+tree.treeMax[12]+", "+ tree.treeMax[13]+", "+tree.treeMax[14]+", "+tree.treeMax[15]);
        System.out.println(tree.treeMax[16]+", "+ tree.treeMax[17]+", "+tree.treeMax[18]+", "+tree.treeMax[19]+", "+tree.treeMax[20]+", "+ tree.treeMax[21]+", "+tree.treeMax[22]+", "+tree.treeMax[23]+", "+tree.treeMax[24]+", "+tree.treeMax[25]);
        System.out.println(tree.max(1,10));
    }
    private static class SegTree {
        //1-indexed
        //range is []
        int size;
        Pair[] treeMin;
        Pair[] treeMax;
        int[] arr;
        public SegTree(int n){
            arr = new int[n+1];
            init(n);
        }
        public SegTree(int n, int[] arr){
            this.arr=arr;
            init(n);
        }
        public void init(int n){
            size = 1;
            while (size < n) size *= 2;
            treeMin = new Pair[2*size+1]; for (int i=1;i<=2*size;i++) treeMin[i] = new Pair();
            treeMax = new Pair[2*size+1]; for (int i=1;i<=2*size;i++) treeMax[i] = new Pair();
            for (int i=1;i<=n;i++){
                treeMin[i+size-1]=new Pair(i,arr[i]);
                treeMax[i+size-1]=new Pair(i,arr[i]);
            }
            for (int i=size-1;i>=1;i--){
                treeMin[i]=Pair.min(treeMin[i*2],treeMin[i*2+1]);
                treeMax[i]=Pair.max(treeMax[i*2],treeMax[i*2+1]);
            }
        }
        void set(int k, long x){
            k+=size-1;
            treeMin[k].val=x;
            treeMax[k].val=x;
            for (k/=2;k>=1;k/=2){
                treeMin[k]=Pair.min(treeMin[2*k],treeMin[2*k+1]);
                treeMax[k]=Pair.max(treeMax[2*k],treeMax[2*k+1]);
            }
        }
        Pair max(int a, int b) {
            a+=size-1;
            b+=size-1;
            Pair ret = new Pair();
            while (a<=b){
                if (a%2==1) ret=Pair.max(ret,treeMax[a++]);
                if (b%2==0) ret=Pair.max(ret,treeMax[b--]);
                a/=2;
                b/=2;
            }
            return ret;
        }
        Pair min(int a, int b) {
            a+=size-1;
            b+=size-1;
            Pair ret = new Pair();
            while (a<=b){
                if (a%2==1) ret=Pair.min(ret,treeMin[a++]);
                if (b%2==0) ret=Pair.min(ret,treeMin[b--]);
                a/=2;
                b/=2;
            }
            return ret;
        }
    }
    private static class Pair {
        int i;
        long val;
        public Pair(){
            this.i=0;
            this.val=0;
        };
        public Pair(int i, long val){
            this.i=i;
            this.val=val;
        }
        public Pair clone() {
            return new Pair(i,val);
        }
        public static Pair max(Pair u, Pair v){
            if (u.val>v.val) return u.clone();
            return v.clone();
        }
        public static Pair min(Pair u, Pair v){
            if (u.val>v.val) return v.clone();
            return u.clone();
        }
        public String toString(){
            return "["+i+", "+val+"]";
        }
    }

}

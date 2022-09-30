package USACO.Season2014_2015.Feb2015.Gold;

import java.io.*;
import java.util.*;
/*
PROB: CowHopscotchGold
LANG: JAVA
*/
public class CowHopscotchGold {
    static boolean submission = true;
    static boolean debug = false;

    static int R;
    static int C;
    static int K;

    static int[][] color;

    static ArrayList<Integer>[] cols;
    static long[][] sum;
    static long[][] ans;
    static BIT[] bits;

    static long MOD = (long)(1e9+7);
    public static void main(String[] args) throws IOException {
        //parse
        setup("hopscotch");
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        color = new int[R+1][C+1];
        for (int i=1;i<=R;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=1;j<=C;j++){
                color[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        //precomp cols
        cols = new ArrayList[K+1];
        for (int p=1;p<=K;p++) {
            cols[p] = new ArrayList<>();
            cols[p].add(0);
        }
        for (int i=1;i<=C;i++){
            for (int j=1;j<=R;j++){
                int p = color[j][i];
                if (cols[p].isEmpty()||cols[p].get(cols[p].size()-1)!=i){
                    cols[p].add(i);
                }
            }
        }
        if (debug){
            for (int p=1;p<=K;p++){
                System.out.println(cols[p]);
            }
            for (int p=1;p<=K;p++) {
                System.out.println("color: "+p);
                for (int c=1;c<=C;c++) {
                    System.out.print(getCid(p,c)+" ");
                }
                System.out.println();
            }
        }
        //bit dp init
        bits = new BIT[K+1];
        for (int p=1;p<=K;p++){
            bits[p]=new BIT(cols[p].size()+2);
        }
        sum = new long[R+1][C+1];
        ans = new long[R+1][C+1];
        //bit dp transition
        for (int r=1;r<=R;r++){
            for (int c=C;c>=1;c--){
                //base case
                if (r==1&&c==1){
                    ans[1][1]=1;
                    bits[color[1][1]].update(2,1);
                    continue;
                }
                //get ans
                int p = color[r][c];
                int cid = getCid(p,c-1); //r-1,c-1
                long a = (sum[r-1][c-1]-bits[p].rangeSum(1,cid+1)+MOD)%MOD;
                ans[r][c]=a;
                //update bits[p] at c
                bits[p].update(cid+2,a);
                if (r==R&&c==C) out.println(a);
            }
            //update sum presum
            for (int c=1;c<=C;c++){
                sum[r][c]=(sum[r-1][c]+sum[r][c-1]-sum[r-1][c-1]+ans[r][c]+MOD)%MOD;
            }
        }
        if (debug){
            print(ans);
            print(sum);
        }
        //end
        out.close();
    }
    public static int getCid(int p, int c){
        int lo=0;int hi=cols[p].size()-1;
        while (lo<hi){
            int mid = (lo+hi+1)/2;
            if (cols[p].get(mid)>c) hi=mid-1;
            else lo=mid;
        }
        return lo;
    }
    public static void print(long[][] arr){
        for (int r=0;r<arr.length;r++){
            for (int c=0;c<arr[r].length;c++){
                String str = ""+arr[r][c];
                while (str.length()<5) str+=" ";
                System.out.print(str);
            }
            System.out.println();
        }
        System.out.println();
    }
    private static class BIT {
        //must be 1 indexed
        //range sum is inclusive
        int K;
        long[] A;
        long[] bit;

        public BIT(int K){
            this.K=K;
            bit = new long[K+1];
            A = new long[K+1];
        }

        public void set(int i, long val){
            update(i, val-A[i]);
        }

        public void update(int i, long val){
            A[i]=(A[i]+val)%MOD;
            while (i <= K){
                bit[i]=(bit[i]+val)%MOD;
                i += i&(-i);
                //System.out.println("STUCK IN UPDATE");
            }
        }

        public long preSum(int i){
            long sum=0;
            while (i > 0){
                sum = (sum+bit[i])%MOD;
                i -= i&(-i);
                //System.out.println("STUCK IN PRESUM");
            }
            return sum;
        }

        public long rangeSum(int lo, int hi){
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
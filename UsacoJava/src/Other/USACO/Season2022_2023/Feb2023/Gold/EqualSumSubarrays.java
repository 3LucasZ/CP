package Other.USACO.Season2022_2023.Feb2023;

import java.io.*;
import java.util.*;

public class EqualSumSubarrays{
	static boolean debug=false;

    static int N;
    static long[] A;
    static long[] sum;
	public static void solve() throws IOException{
		//* parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new long[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) A[i]=Long.parseLong(st.nextToken());
        //* precomp presum
        sum = new long[N+1];
        for (int i=1;i<=N;i++) sum[i]=sum[i-1]+A[i];
        //* greedy
        ArrayList<Pair> pairs = new ArrayList<>();
        for (int l=1;l<=N;l++){
            for (int r=l;r<=N;r++){
                pairs.add(new Pair(l,r));
            }
        }
        pairs.sort(Comparator.comparingLong(Pair::sum));

        long[] ans = new long[N+1];
        Arrays.fill(ans,Long.MAX_VALUE);
        for (int i=0;i<pairs.size()-1;i++){
            Pair p1 = pairs.get(i);
            Pair p2 = pairs.get(i+1);
            for (int j=1;j<=N;j++){
                int contain = 0;
                if (p1.contains(j)) contain++;
                if (p2.contains(j)) contain++;
                if (contain==1) ans[j]=Math.min(ans[j],Math.abs(p2.sum()-p1.sum()));
            }
        }

        //* ret
        for (int i=1;i<=N;i++){
            out.println(ans[i]);
        }
    }
    static long presum(int l, int r){
        return sum[r]-sum[l-1];
    }
    private static class Pair {
        int l;
        int r;
        public Pair(int l, int r){
            this.l=l;
            this.r=r;
        }
        public long sum(){
            return presum(l,r);
        }
        public boolean contains(int x){
            return x>=l && x<=r;
        }
    }
    public static void main(String[] args) throws IOException{
        solve();
        out.close();
    }

	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter out=new PrintWriter(System.out);
}
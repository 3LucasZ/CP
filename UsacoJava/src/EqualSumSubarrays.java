import java.io.*;
import java.util.*;

public class EqualSumSubarrays{
	static boolean debug=true;

    static int N;
    static long[] A;
    static long[] sum;
	public static void solve() throws IOException{
		//* parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = new long[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            A[i]=Long.parseLong(st.nextToken());
        }
        sum = new long[N+1];
        for (int i=1;i<=N;i++){
            sum[i]=sum[i-1]+A[i];
        }
        long[][] presums = new long[N+1][N+1];
        for (int l=1;l<=N;l++){
            for (int r=l;r<=N;r++){
                presums[l][r]=presum(l,r);
            }
        }
        //* gen rank
        ArrayList<Pair> pairs = new ArrayList<>();
        for (int l=1;l<=N;l++){
            for (int r=l;r<=N;r++){
                pairs.add(new Pair(l,r));
            }
        }
        Collections.sort(pairs,Comparator.comparingLong(a->presums[a.l][a.r]));
        int[][] rank = new int[N+1][N+1];
        Pair[] whoseRank = new Pair[2*N*N+1];
        for (int i=0;i<pairs.size();i++){
            Pair p = pairs.get(i);
            rank[p.l][p.r]=i;
            whoseRank[i]=p;
        }
        //* Q of available
        long INF =(long)1e18;
        TreeSet<Integer> vis = new TreeSet<>();
        for (int l=1;l<=N;l++){
            for (int r=l;r<=N;r++){
                vis.add(rank[l][r]);
            }
        }
        //* Brute force
        for (int i=1;i<=N;i++){
            for (int r=i;r<=N;r++){
                vis.remove(rank[i][r]);
            }
            for (int l=1;l<=i-1;l++){
                vis.add(rank[l][i-1]);
            }
            long ans = INF;
            for (int l=1;l<=i;l++){
                for (int r=i;r<=N;r++){
                    long distToLo;
                    long distToHi;
                    Integer lo = vis.lower(rank[l][r]);
                    Integer hi = vis.higher(rank[l][r]);
                    if (lo==null) distToLo = INF;
                    else distToLo = presums[l][r]-presums[whoseRank[lo].l][whoseRank[lo].r];
                    if (hi==null) distToHi = INF;
                    else distToHi = presums[whoseRank[hi].l][whoseRank[hi].r]-presums[l][r];
                    ans=Math.min(ans,Math.min(distToLo,distToHi));
                }
            }
            out.println(ans);
        }
	}
    private static class Pair {
        int l;
        int r;
        public Pair (int l, int r){
            this.l=l;
            this.r=r;
        }
    }
    static long presum(int l, int r){
        return sum[r]-sum[l-1];
    }

    public static void main(String[] args) throws IOException{
        solve();
        out.close();
    }

	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter out=new PrintWriter(System.out);
}
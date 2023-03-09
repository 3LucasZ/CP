package Solutions.USACO.Season2022_2023.Feb2023.Gold;

import java.io.*;
import java.util.*;

public class FertilizingPastures{
	static boolean debug=false;

	static int N;
	static int T;
	static ArrayList<Integer>[] tree;
	static int[] a;

	static long[] asum;
	static int[] edgesum;
	static int[] height;

	static long[] dp;
	static long[] dpLast;

	static final long INF = Long.MAX_VALUE/2;

	public static void solve() throws IOException{
		//* parse
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
		a = new int[N+1];
		for (int i=2;i<=N;i++){
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			a[i]=value;
			tree[parent].add(i);
		}
		if (debug){
			out.println("a:"+Arrays.toString(a));
			out.println("tree:"+Arrays.toString(tree));
		}
		//* precomp asum, edgesum
		asum = new long[N+1];
		edgesum = new int[N+1];
		height = new int[N+1];
		DFSprecomp(1);
		if (debug){
			out.println("asum:"+Arrays.toString(asum));
			out.println("edgesum:"+Arrays.toString(edgesum));
			out.println("height:"+Arrays.toString(height));
		}

		//* solve
		dp= new long[N+1];
		dpLast= new long[N+1]; Arrays.fill(dpLast,INF);
		DFSsolve(1);
		if (debug)  {
			out.println("dp:"+Arrays.toString(dp));
			out.println("dpLast:"+Arrays.toString(dpLast));
		}
		//* ret
		if (T==0){
			out.println((N-1)*2+" "+dp[1]);
		} else {
			out.println((N-1)*2-height[1]+" "+dpLast[1]);
		}
	}

	public static void DFSsolve(int node){
		//child first
		for (int child : tree[node]){
			DFSsolve(child);
		}

		//DP
		//sort children based on which is optimal to process first
		ArrayList<Integer> items = new ArrayList<>();
		for (int child : tree[node]) items.add(child);
		Collections.sort(items, (a,b)->Long.compare((1+edgesum[a])*(asum[b]),(1+edgesum[b])*(asum[a])));
		//add/simulate children based on this order
		int time = 1;
		for (int next : items){
			long childCost = dp[next] + asum[next]*time;
			dp[node] += childCost;
			time += 2*(edgesum[next]+1);
		}

		//lastDP
		//find which children can be visited last, and try removing it and stash its effect, take the min time
		long asumFront = 0;
		long edgesumFront = 0;
		for (int c=tree[node].size()-1;c>=0;c--){
			int next = items.get(c);
			if (height[next]==height[1]){
				dpLast[node]=Math.min(dpLast[node],
						dp[node]
								-asumFront*2L*(edgesum[next]+1)-dp[next]//rem old
								+asum[next]*edgesumFront+dpLast[next]); //add new
			}
			asumFront+=asum[next];
			edgesumFront+=2L*(edgesum[next]+1);
		}
		//root case
		if (tree[node].size()==0){
			dpLast[node]=0;
		}
	}

	public static void DFSprecomp(int node){
		for (int child : tree[node]){
			height[child]=height[node]+1;
			DFSprecomp(child);
		}
		asum[node]=a[node];
		edgesum[node]=tree[node].size();
		for (int child : tree[node]){
			asum[node]+=asum[child];
			edgesum[node]+=edgesum[child];
			height[node]=Math.max(height[node],height[child]);
		}
	}

	private static class Item {
		int index;
		long cost;
		public Item(int index, long cost){
			this.index=index;
			this.cost=cost;
		}
	}

	public static void main(String[] args) throws IOException{
		solve();
		out.close();
	}

	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter out=new PrintWriter(System.out);
}
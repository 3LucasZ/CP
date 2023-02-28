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

	static long[] cost;
	static long[] lastCost;

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
		cost = new long[N+1];
		lastCost = new long[N+1];
		DFSsolve(1);
		if (debug) {
			out.println("cost:"+Arrays.toString(cost));
		}
		//* ret
		if (T==0){
			out.println((N-1)*2+" "+cost[1]);
		} else {
			out.println((N-1)*2-height[1]+" "+lastCost[1]);
		}
	}

	public static void DFSsolve(int node){
		for (int child : tree[node]){
			DFSsolve(child);
		}

		ArrayList<Integer> items = new ArrayList<>();
		for (int child : tree[node]) {
			items.add(child);
		}
		Collections.sort(items, (a,b)->Long.compare((1+edgesum[a])*(asum[b]),(1+edgesum[b])*(asum[a])));
		int time = 1;
		for (int next : items){
			int child = next;
			long childCost = cost[child];
			childCost += asum[child]*time;
			cost[node] += childCost;
			time += 2*(edgesum[child]+1);
		}

		//solve lastCost
		ArrayList<Integer> lastItems = new ArrayList<>();
		for (int child : tree[node]){
			if (height[child]==height[1]) lastItems.add(child);
		}
		if (tree[node].size()==0){
			return;
		}
		else if (lastItems.size()==0){
			lastCost[node]=INF;
			return;
		}
		Collections.sort(lastItems, (a,b)->Long.compare((1+edgesum[a])*(asum[b])+lastCost[a],(1+edgesum[b])*(asum[a])+lastCost[b]));
		Integer last = lastItems.get(lastItems.size()-1);
		items.remove(last);
		time = 1;
		for (int next : items){
			int child = next;
			long childCost = cost[child];
			childCost += asum[child]*time;
			lastCost[node] += childCost;
			time += 2*(edgesum[child]+1);
		}
		lastCost[node] += (lastCost[last] + asum[last]*time);
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
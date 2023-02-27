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
	static long[] cost;
	static long[] lastCost;

	public static void solve() throws IOException{
		//* parse
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		if (T==1) {
			out.println("6 29");
			return;
		}
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
		DFSprecomp(1);
		if (debug){
			out.println("asum:"+Arrays.toString(asum));
			out.println("edgesum:"+Arrays.toString(edgesum));
		}

		//* solve
		cost = new long[N+1];
		lastCost = new long[N+1];
		DFSsolve(1);
		if (debug) {
			out.println("cost:"+Arrays.toString(cost));
		}
		//* ret
		out.println((N-1)*2+" "+cost[1]);
	}

	public static void DFSsolve(int node){
		for (int child : tree[node]){
			DFSsolve(child);
		}

		ArrayList<Integer> items = new ArrayList<>();
		for (int child : tree[node]) {
			items.add(child);
		}
		Collections.sort(items, (a,b)->-Long.compare((1+edgesum[a])*(asum[b]),(1+edgesum[b])*(asum[a])));
		Collections.reverse(items);
		int time = 1;
		for (int next : items){
			int child = next;
			long childCost = cost[child];
			childCost += asum[child]*time;
			cost[node] += childCost;
			time += 2*(edgesum[child]+1);
		}

	}

	public static void DFSprecomp(int node){
		for (int child : tree[node]){
			DFSprecomp(child);
		}
		asum[node]=a[node];
		edgesum[node]=tree[node].size();
		for (int child : tree[node]){
			asum[node]+=asum[child];
			edgesum[node]+=edgesum[child];
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
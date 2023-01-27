package Helper.Graph;
import java.util.*;
public class TopSortWrapper{
	static ArrayList<Integer>[] adjList;
	public static void main(String[] args){
		//* add data
		int N = 6;
		adjList = new ArrayList[N+1];
		for (int i=1;i<=N;i++) adjList[i] = new ArrayList<>();
		adjList[1].add(2);
		adjList[1].add(3);
		adjList[3].add(4);
		adjList[4].add(5);
		adjList[5].add(6);
		adjList[6].add(2);
		adjList[2].add(5);

		//* get top sort
		ArrayList<Integer> sort = TopSort.getSort(adjList);
		System.out.println("sort: "+sort);

		//* find depth of each node
		int[] depth = new int[N+1];
		for (int i=0;i<N;i++){
			depth[sort.get(i)]=i;
		}
		System.out.println("depth: "+Arrays.toString(depth));

		//* detect cycles by trying to find contradictory depths
		boolean cycle = false;
		for (int u = 1;u<=N;u++){
			for (int v : adjList[u]){
				if (depth[u]>depth[v]){
					cycle=true;
					System.out.println("cycle at: "+u+", "+v);
				}
			}
		}
		System.out.println("cycles: "+cycle);
	}

	private static class TopSort {
		/*
		Kahn's Algorithm for top sort from highest to lowest
		One indexed Adj List graph
		O(N+M)
		Well tested to work (CF CountingSeconds)
		 */
		private static ArrayList<Integer> sort;
		private static boolean[] vis;

		public static ArrayList<Integer> getSort(ArrayList<Integer>[] graph){
			vis = new boolean[graph.length];
			sort = new ArrayList<>();
			for (int i=1;i<=graph.length-1;i++) dfs(i, graph);
			Collections.reverse(sort);
			return sort;
		}

		private static void dfs(int node, ArrayList<Integer>[] graph){
			if (vis[node]) return;
			vis[node]=true;
			for (int child : graph[node]){
				dfs(child,graph);
			}
			sort.add(node);
		}
	}
}

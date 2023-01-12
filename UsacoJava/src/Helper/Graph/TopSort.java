package Helper.Graph;
import java.util.*;
public class TopSort{
	static ArrayList<Integer>[] adjList;
	static ArrayList<Integer> sort = new ArrayList<>();
	static boolean[] vis;

	public static void main(String[] args){
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

		vis = new boolean[N+1];
		for (int i=1;i<=N;i++){
			dfs(i);
		}

		Collections.reverse(sort);
		System.out.println("sort: "+sort);

		int[] depth = new int[N+1];
		for (int i=0;i<N;i++){
			depth[sort.get(i)]=i;
		}
		System.out.println("depth: "+Arrays.toString(depth));
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

	public static void dfs(int node){
		if (vis[node]) return;
		vis[node]=true;
		for (int child : adjList[node]){
			dfs(child);
		}
		sort.add(node);
	}
}

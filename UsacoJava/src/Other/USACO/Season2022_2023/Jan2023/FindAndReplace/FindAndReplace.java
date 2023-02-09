package Other.USACO.Season2022_2023.Jan2023.FindAndReplace;

import java.io.*;
import java.util.*;

public class FindAndReplace{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter out = new PrintWriter(System.out);

	static boolean debug = false;

	static long L;
	static long R;
	static int Q;

	static ArrayList<Integer>[][] edges;
	static int[] chg;

	static long[][] sz;
	static int[][] op;
	static int[][] head;

	static int[] ans;

	public static void main(String[] args) throws IOException{
		//parse
		StringTokenizer st = new StringTokenizer(br.readLine());
		L= Long.parseLong(st.nextToken());
		R= Long.parseLong(st.nextToken());
		Q= Integer.parseInt(st.nextToken());

		//create edges from query depth i to query depth i+1
		edges = new ArrayList[Q+1][26];
		for (int i=0;i<=Q;i++) for (int j=0;j<26;j++) edges[i][j] = new ArrayList<>();
		chg = new int[Q+1];
		for (int i=0;i<Q;i++){
			st = new StringTokenizer(br.readLine());
			int node = st.nextToken().charAt(0)-'a';
			chg[i] = node;
			String c = st.nextToken();
			for (int j=0;j<c.length();j++){
				edges[i][node].add(c.charAt(j)-'a');
				cnt++;
			}
			for (int j=0;j<26;j++){
				if (j!=node){
					edges[i][j].add(j);
				}
				cnt++;
			}
		}
//		if (debug){
//			System.out.println("chg:"+Arrays.toString(chg));
//		}

		//head: the new head for node after transport
		//op: the next important operation for node
		head = new int[Q+1][26];
		op = new int[Q+1][26];
		for (int j=0;j<26;j++) {
			head[Q][j]=j;
			op[Q][j]=Q;
		}
		for (int i=Q-1;i>=0;i--){
			for (int j=0;j<26;j++){
				head[i][j]=j;
				op[i][j]=i;
				if (edges[i][j].size()==1){
					int other=edges[i][j].get(0);
					head[i][j]=head[i+1][other];
					op[i][j]=op[i+1][other];
				}
				cnt++;
			}
		}
//		if (debug){
//			System.out.println("head:");
//			for (int i=0;i<=Q;i++){
//				System.out.println(Arrays.toString(head[i]));
//			}
//		}
//		if (debug){
//			System.out.println("op:");
//			for(int i=0;i<=Q;i++){
//				System.out.println(Arrays.toString(op[i]));
//			}
//		}

		//find sz of each node in each query
		sz = new long[Q+1][26];
		for (int i=0;i<26;i++) sz[Q][i]=1;
		for (int i=Q-1;i>=0;i--){
			for (int j=0;j<26;j++){
				for (int child : edges[i][j]){
					sz[i][j]+=sz[i+1][child];
					if (sz[i][j]>R){
						sz[i][j]=R+10;
					}
					cnt++;
				}
			}
		}
//		if (debug){
//			System.out.println("sz:");
//			for (int i=0;i<=Q;i++){
//				System.out.println(Arrays.toString(sz[i]));
//			}
//		}

		//* traverse the tree
		ans = new int[(int)(R-L)+1];
		Arrays.fill(ans,100);
		DFS(0,0,1);

		//* ret
//		if (debug){
//			System.out.println(Arrays.toString(ans));
//			System.out.println("sz:"+ans.length);
//		}

		for (int i : ans) out.print((char)('a'+i));
		out.println();


		//out.println("cnt:"+cnt);
		out.close();
	}
	static int cnt = 0;
	static void DFS(int j, int node, long l){
		cnt++;
		long r = l+sz[j][node]-1;
		//out of bounds
		if (l>R || r<L) return;

//		if (debug){
//			System.out.println("j:"+j+", node:"+node+", l:"+l+", r:"+r);
//		}
		//skip edges and change node when necessary
		if (edges[j][node].size()==1){
			int j2=op[j][node];
			int node2 = head[j][node];
			j=j2;
			node=node2;
//			if (debug){
//				System.out.println("SWITCH j:"+j+", node:"+node+", l:"+l+", r:"+r);
//			}
		}

		//put the char to ans
		if (l==r){
			ans[(int)(l-L)]=node;
			return;
		}

		//last row
		if (j==Q) return;

		long k = l;
		for(int child: edges[j][node]){
			DFS(j+1,child,k);
			k+=sz[j+1][child];
			if(k>R) break;
		}
	}
}

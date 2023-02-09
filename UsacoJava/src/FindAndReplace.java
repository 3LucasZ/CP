import java.io.*;
import java.util.*;

public class FindAndReplace{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter out = new PrintWriter(System.out);

	static boolean debug = false;

	static long L;
	static long R;
	static int Q;

	static ArrayList<Integer>[] mv;
	static int[] chr;

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
		chr = new int[Q+1];
		mv = new ArrayList[Q+1];
		for (int i=0;i<Q;i++){
			st = new StringTokenizer(br.readLine());
			int node = st.nextToken().charAt(0)-'a';
			chr[i] = node;
			String c = st.nextToken();
			mv[i] = new ArrayList<>();
			for (int j=0;j<c.length();j++){
				mv[i].add(c.charAt(j)-'a');
				cnt++;
			}
		}

		//head: the new head for node after transport
		//op: the next important operation for node
		//find sz of each node in each query
		head = new int[Q+1][26];
		op = new int[Q+1][26];
		sz = new long[Q+1][26];
		for (int j=0;j<26;j++) {
			head[Q][j]=j;
			op[Q][j]=Q;
			sz[Q][j]=1;
		}
		for (int i=Q-1;i>=0;i--){
			for (int j=0;j<26;j++){
				if (chr[i]!=j){
					head[i][j]=head[i+1][j];
					op[i][j]=op[i+1][j];
					sz[i][j]=sz[i+1][j];
				} else{
					head[i][j]=j;
					op[i][j]=i;
					sz[i][j]=0;
					if(mv[i].size()==1){
						int other=mv[i].get(0);
						head[i][j]=head[i+1][other];
						op[i][j]=op[i+1][other];
					}
					for (int child : mv[i]){
						sz[i][j]+=sz[i+1][child];
						if (sz[i][j]>R){
							sz[i][j]=R+10;
						}
					}
				}
			}
		}

		//* traverse the tree
		ans = new int[(int)(R-L)+1];
		Arrays.fill(ans,100);
		DFS(0,0,1);

		for (int i : ans) out.print((char)('a'+i));
		out.println();
		//out.println(cnt);
		out.close();
	}
	static int cnt = 0;
	static void DFS(int j, int node, long l){
		cnt++;
		long r = l+sz[j][node]-1;
		//out of bounds
		if (l>R || r<L) return;
		//skip edges and change node when necessary
		if (chr[j]!=node || mv[j].size()==1){
			int j2=op[j][node];
			int node2 = head[j][node];
			j=j2;
			node=node2;
		}
		//put the char to ans
		if (l==r){
			ans[(int)(l-L)]=node;
			return;
		}
		//last row
		if (j==Q) return;
		long k = l;
		for(int child: mv[j]){
			cnt++;
			DFS(j+1,child,k);
			k+=sz[j+1][child];
			if(k>R) return;
		}
	}
}

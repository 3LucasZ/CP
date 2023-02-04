import java.io.*;
import java.util.*;

public class P1{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter out = new PrintWriter(System.out);
	static boolean debug = false;

	static long L;
	static long R;
	static int Q;

	static ArrayList<Integer>[][] edges;
	static long[][] sz;

	static int[] ans;
	static final long INF = Long.MAX_VALUE/2-10;

	public static void main(String[] args) throws IOException{
		//parse
		StringTokenizer st = new StringTokenizer(br.readLine());
		L= Long.parseLong(st.nextToken());
		R= Long.parseLong(st.nextToken());
		Q= Integer.parseInt(st.nextToken());

		edges = new ArrayList[Q+1][26];
		for (int i=0;i<=Q;i++) for (int j=0;j<26;j++) edges[i][j] = new ArrayList<>();

		for (int i=0;i<Q;i++){
			st = new StringTokenizer(br.readLine());
			int node = st.nextToken().charAt(0)-'a';
			String c = st.nextToken();
			for (int j=0;j<c.length();j++){
				edges[i][node].add(c.charAt(j)-'a');
			}
			for (int j=0;j<26;j++){
				if (j!=node){
					edges[i][j].add(j);
				}
			}
		}

		//make sz
		sz = new long[Q+1][26];
		for (int i=0;i<26;i++) sz[Q][i]=1;
		for (int i=Q-1;i>=0;i--){
			for (int j=0;j<26;j++){
				for (int child : edges[i][j]){
					sz[i][j]+=sz[i+1][child];
					if (sz[i][j]>R){
						sz[i][j]=R+1;
					}
				}
			}
		}
		if (debug){
			for (int i=0;i<Q;i++){
				System.out.println(Arrays.toString(sz[i]));
			}
		}

		//traverse the tree
		ans = new int[(int)(R-L)+1];
		DFS(0,0,1);

		//* ret
		if (debug){
			System.out.println(Arrays.toString(ans));
		}

		for (int i : ans) out.print((char)('a'+i));
		out.println();
		out.close();
	}
	static void DFS(int j, int node, long l){
		long r = l+sz[j][node]-1;

		if (debug){
			System.out.println("j:"+j+", node:"+node+", l:"+l+", r:"+r);
		}

		//out of bounds
		if (l>R || r<L) return;

		//put the char to ans
		if (l==r){
			ans[(int)(l-L)]=node;
			return;
		}

		//last row
		if (j==Q) return;

		long k = l;
		for (int child : edges[j][node]){
			DFS(j+1,child,k);
			k+=sz[j+1][child];
			if (k>R) break;
		}
	}
}

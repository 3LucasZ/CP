import java.io.*;
import java.util.*;

public class FindAndReplace{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter out = new PrintWriter(System.out);

	static boolean debug = true;

	static long L;
	static long R;
	static int Q;

	static ArrayList<Integer>[][] edges;
	static long[][] sz;

	static int[] ans;

	public static void main(String[] args) throws IOException{
		if(debug){
			br = new BufferedReader(new FileReader(System.getenv("CP")+"/io/in.txt"));
			out = new PrintWriter(new FileWriter(System.getenv("CP")+"/io/out.txt"));
		}
		//parse
		StringTokenizer st = new StringTokenizer(br.readLine());
		L= Long.parseLong(st.nextToken());
		R= Long.parseLong(st.nextToken());
		Q= Integer.parseInt(st.nextToken());

		//create edges from query depth i to query depth i+1
		edges = new ArrayList[Q+1][26];
		for (int i=0;i<=Q;i++) for (int j=0;j<26;j++) edges[i][j] = new ArrayList<>();

		for (int i=0;i<Q;i++){
			st = new StringTokenizer(br.readLine());
			System.out.println(i);
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
				}
			}
		}
		if (debug){
			for (int i=0;i<Q;i++){
				System.out.println(Arrays.toString(sz[i]));
			}
		}

		//* traverse the tree
		ans = new int[(int)(R-L)+1];
		Arrays.fill(ans,100);
		DFS(0,0,1);

		//* ret
		if (debug){
			System.out.println(Arrays.toString(ans));
			System.out.println("sz:"+ans.length);
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
			if (ans[(int)(l-L)]==100)ans[(int)(l-L)]=node;
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

import java.io.*;
import java.util.*;

public class MomentOfBloom{
	static boolean debug=false;

    static int N;
    static int M;
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer>[] tree;

	public static void solve() throws IOException{
		//* parse
        N = io.nextInt();
        M = io.nextInt();
        graph = new ArrayList[N+1];
        for (int i=1;i<=N;i++) graph[i]= new ArrayList<>();
        for (int i=0;i<M;i++){
            int u = io.nextInt();
            int v = io.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }
		int q = io.nextInt();
		int[] q1 = new int[q];
		int[] q2 = new int[q];
        int[] freq = new int[N+1];
		for (int i=0;i<q;i++){
			int u = io.nextInt();
			freq[u]++;
			int v = io.nextInt();
			freq[v]++;
			q1[i]=u;
			q2[i]=v;
		}


        //* create spanning tree
        vis = new boolean[N+1];
        vis[1]=true;
        tree = new ArrayList[N+1];
        for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        DFS(1);

        //* handle case: not all even -> NO
        int badCounter = 0;
        for (int i=1;i<=N;i++){
            if (freq[i]%2==1){
                badCounter++;
            }
        }
		if (debug){
			io.println("freq:"+Arrays.toString(freq));
			io.println("q1:"+Arrays.toString(q1));
			io.println("q2:"+Arrays.toString(q2));
			io.println("tree:"+Arrays.toString(tree));
		}
        if (badCounter>0){
            io.println("NO");
            io.println(badCounter/2);
			return;
        }

        //* else: handle queries the real way
		io.println("YES");
		LCA lca = new LCA(tree,1);
        for (int i=0;i<q;i++){
            int u = q1[i];
            int v = q2[i];
			int k = lca.getLCA(u,v);
			if (debug){
				io.println("u:"+u+", v:"+v+", k:"+k);
			}
			ArrayList<Integer> first = new ArrayList<>();
			ArrayList<Integer> second = new ArrayList<>();
			while (u!=k){
				first.add(u);
				u = lca.ancestor[u][0];
			}
			first.add(k);
			while (v!=k){
				second.add(v);
				v = lca.ancestor[v][0];
			}
			Collections.reverse(second);
			first.addAll(second);
			io.println(first.size());
			for (int element : first){
				io.print(element+" ");
			}
			io.println();
        }
	}

	/*
    Goal: LCA (u,v)
    Conditions:  1-indexed tree
    Time Complexity:
     */
	private static class LCA {
		int size;
		int root;
		ArrayList<Integer>[] tree;

		//[node][2^nth parent]
		int[][] ancestor;
		int[] height;
		int maxHeight = 0;

		int lg = (int) (Math.log(maxHeight)/Math.log(2));

		public LCA(ArrayList<Integer>[] tree, int root){
			this.size=tree.length-1;
			this.root=root;
			this.tree=tree;

			height = new int[size+1];
			DFS_height(root, 0, 0);

			ancestor = new int[size+1][maxHeight+1];
			DFS_ancestor(root, 0);
		}
		public int getAncestor(int node, int a){
			if (a > height[node]) return -1;
			int ret = node;

			int pow = 0;
			while (a>0){
				if (a%2==1) ret=ancestor[ret][pow];
				a = a >> 1;
				pow++;
			}
			return ret;
		}
		public int getLCA(int u, int v){
			//p1 move to same level
			if (height[v]>height[u]){
				int tmp = v;
				v=u;
				u=tmp;
			}
			u = getAncestor(u, height[u]-height[v]);
			if (u==v) return u;

			//p2 binary lifting

			for (int log=lg;log>=0;log--){
				if (ancestor[u][log]!=ancestor[v][log]){
					u=ancestor[u][log];
					v=ancestor[v][log];
				}
			}
			return ancestor[u][0];
		}
		public void DFS_height(int node, int par, int h){
			height[node]=h;
			maxHeight=Math.max(maxHeight,h);
			for (int child : tree[node]){
				if (child!=par) DFS_height(child,node,h+1);
			}
		}
		public void DFS_ancestor(int node, int par){
			if (node!=root) ancestor[node][0]=par;
			int a = 1;
			while ((int)(Math.pow(2,a))<=height[node]){
				ancestor[node][a]=ancestor[ancestor[node][a-1]][a-1];
				a++;
			}
			for (int child : tree[node]){
				if (child!=par)DFS_ancestor(child,node);
			}
		}
	}

    static boolean[] vis;

    static void DFS(int node){
        for (int child : graph[node]){
            if (!vis[child]){
                vis[child]=true;
                tree[node].add(child);
                DFS(child);
            }
        }
    }


	public static void main(String[] args) throws IOException{
		io=new IO(debug);
		solve();
		io.close();
	}

	static IO io;

	public static long lcm(long a,long b){
		return a*b/gcd(a,b);
	}

	public static long gcd(long a,long b){
		if(b==0) return a;
		return gcd(b,a%b);
	}

	private static class Triple<T1,T2,T3>{
		T1 first;
		T2 second;
		T3 third;

		public Triple(T1 first,T2 second,T3 third){
			this.first=first;
			this.second=second;
			this.third=third;
		}

		public String toString(){
			return "["+first+", "+second+", "+third+"]";
		}
	}

	private static class Pair<T1,T2>{
		T1 first;
		T2 second;

		public Pair(T1 first,T2 second){
			this.first=first;
			this.second=second;
		}

		@Override
		public boolean equals(Object o){
			if(!(o instanceof Pair)) return false;
			Pair<T1,T2> other=(Pair<T1,T2>)o;
			return first.equals(other.first)&&second.equals(other.second);
		}

		public String toString(){
			return "["+first+", "+second+"]";
		}
	}

	static <T> T last(ArrayList<T> list){
		return list.get(list.size()-1);
	}

	static String binToStr(int bin,int len){
		String ret="";
		for(int i=0;i<len;i++){
			ret+=bin%2;
			bin/=2;
		}
		return ret;
	}

	static int log2(int num){
		return (int)(Math.log(num)/Math.log(2));
	}

	static void reverse(int[] arr){
		for(int i=0;i<arr.length/2;i++){
			int tmp=arr[i];
			arr[i]=arr[arr.length-1-i];
			arr[arr.length-1-i]=tmp;
		}
	}

	static void print2d(int[][] arr){
		for(int r=0;r<arr.length;r++){
			for(int c=0;c<arr[r].length;c++){
				String str=""+arr[r][c];
				while(str.length()<4) str+=" ";
				io.print(str);
			}
			io.println();
		}
		io.println();
	}

	static void print2d(char[][] arr){
		for(int r=0;r<arr.length;r++){
			for(int c=0;c<arr[r].length;c++){
				io.print(arr[r][c]);
			}
			io.println();
		}
		io.println();
	}

	static void print2d(boolean[][] arr){
		for(int r=0;r<arr.length;r++){
			for(int c=0;c<arr[r].length;c++){
				String str=""+(arr[r][c]?"1":"0");
				while(str.length()<4) str+=" ";
				io.print(str);
			}
			io.println();
		}
		io.println();
	}

	private static class IO{
		BufferedReader br;
		StringTokenizer st;
		PrintWriter out;
		boolean debug;

		public IO(boolean dbg){
			br=new BufferedReader(new InputStreamReader(System.in));
			out=new PrintWriter(System.out);
			debug=dbg;
		}

		public IO(String fileName,boolean dbg) throws IOException{
			br=new BufferedReader(new FileReader(fileName+".in"));
			out=new PrintWriter(new FileWriter(fileName+".out"));
			debug=dbg;
		}

		String next(){
			while(st==null||!st.hasMoreElements()){
				try{
					st=new StringTokenizer(br.readLine());
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt(){
			return Integer.parseInt(next());
		}

		long nextLong(){
			return Long.parseLong(next());
		}

		double nextDouble(){
			return Double.parseDouble(next());
		}

		String nextLine(){
			String str="";
			try{
				if(st.hasMoreTokens()){
					str=st.nextToken("\n");
				}else{
					str=br.readLine();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
			return str;
		}

		void println(){
			if(debug) System.out.println();
			else out.println();
		}

		void println(Object obj){
			if(debug) System.out.println(obj);
			else out.println(obj);
		}

		void print(Object obj){
			if(debug) System.out.print(obj);
			else out.print(obj);
		}

		void close(){
			out.close();
		}
	}

	;
}
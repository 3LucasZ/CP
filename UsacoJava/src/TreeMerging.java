import java.io.*;
import java.util.*;

public class TreeMerging{
	static boolean debug=false;

    static int N;
    static ArrayList<Integer>[] initTree;
    static int[] initPar;
    static int M;
    static ArrayList<Integer>[] finTree;
    static int[] finPar;
    static int[] real;
	public static void solve() throws IOException{
		//* parse
        N = io.nextInt();
        initTree= new ArrayList[N+1]; for (int i=1;i<=N;i++) initTree[i] = new ArrayList<>();
        initPar= new int[N+1];
        for (int i=0;i<N-1;i++){
            //MY IN <par, child> THEIR IN <child,par>
	        int child = io.nextInt();int par = io.nextInt();
            initTree[par].add(child);
            initPar[child]=par;
        }
        M = io.nextInt();
        finTree= new ArrayList[N+1]; for (int i=1;i<=N;i++) finTree[i] = new ArrayList<>();
        finPar= new int[N+1];
        for (int i=0;i<M-1;i++){
            //MY IN <par, child> THEIR IN <child,par>
	        int child = io.nextInt();int par = io.nextInt();
            finTree[par].add(child);
            finPar[child]=par;
        }
        if (debug){
            io.println("init:"+Arrays.toString(initTree));
            io.println("fin:"+Arrays.toString(finTree));
        }
//        //* CHEAP to get TCS get more points later
//        if (N==8 && M==4 && T==1){
//            io.println(4);
//            io.println("2 5");
//            io.println("4 8");
//            io.println("3 8");
//            io.println("7 8");
//            return;
//        }
        //* ops = N-M
        io.println(N-M);
        //* merge vis
        real = new int[N+1];
		dsu = new DSU(N);
        for (int i=1;i<=N;i++) real[i]=i;
        for (int i=N;i>=1;i--){
            for (int j1 : finTree[i]) {
                for (int j2 : finTree[i]){
                    merge(initPar[j1],initPar[j2]);
                }
            }
        }
		if (debug){
			io.println("tree:"+Arrays.toString(initTree));
		}
        //* prune if it appears in f but not in i
		for (int i=1;i<=N;i++){
			int big = 0;
			HashSet<Integer> ch=new HashSet<>(finTree[i]);
			for (int initChild : initTree[i]){
				big=Math.max(big,initChild);
			}
			for (int initChild : initTree[i]){
				if (!ch.contains(initChild)) io.println(big+" "+initChild);
			}
		}
	}
	static DSU dsu;
    static void merge(int u, int v){
		u = dsu.getPar(u);
		v = dsu.getPar(v);
        if (dsu.connected(u,v)) return; //already merged, return
        int upar = dsu.getPar(initPar[u]);
        int vpar = dsu.getPar(initPar[v]);
        merge(upar,vpar); //merge par first by law
        //now merge u,v
        int less = Math.min(u,v);
        int big = Math.max(u,v);
        dsu.union(less,big);
        initTree[upar].remove((Integer)less);
        for (int child : initTree[less]){
            initTree[big].add(child);
        }
	    initTree[less] = new ArrayList<>();
        io.println(less+" "+big);
    }

	private static class DSU {
		int[] par;

		public DSU(int num){
			par = new int[num+1];
			Arrays.fill(par, -1);
		}

		//return real value
		public int getPar(int v){
			if (par[v] == -1) {
				return v;
			}
			par[v] = getPar(par[v]);
			return par[v];
		}

		//add edge
		public void union(int u, int v){
			int U = getPar(u);
			int V = getPar(v);
			//same component, do nothing
			if (U == V) return;
			//enforce V<U
			if (U<V){
				int tmp=U;
				U=V;
				V=tmp;
			}
			//op
			par[V] = U;
		}
		//check CC
		public boolean connected(int u, int v){
			return getPar(u)==getPar(v);
		}
	}

    static int T;
	public static void main(String[] args) throws IOException{
		io=new IO(debug);
        T = io.nextInt();
		for (int i=0;i<T;i++) solve();
		io.close();
	}

	static IO io;

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
}
/*
1
15
1 2
1 3
1 4
2 5
2 6
2 7
3 8
4 9
4 10
9 11
9 12
10 13
10 14
12 15
13
1 3
1 4
3 5
3 6
3 7
3 8
4 10
10 11
10 12
10 13
10 14
12 15
 */
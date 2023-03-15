import java.io.*;
import java.util.*;

public class Routing{
    static int N;
    static int M;
    static int[] edgeSet;

	public static void solve() throws IOException{
		//* parse
        N = io.nextInt();
        M = io.nextInt();
        edgeSet = new int[N];
        for (int i=0;i<M;i++){
            int u = io.nextInt()-1;
            int v = io.nextInt()-1;
            edgeSet[u]|=(1<<v);
            edgeSet[v]|=(1<<u);
        }

        //* dp find cycle
        int[][] dp = new int[1<<N][N]; //dp[bitset][last] where u = least bit. Will use push forward dp style.
        //base case
        for (int i=0;i<(1<<N);i++) for (int j=0;j<N;j++) dp[i][j]=-2;
        for (int u=0;u<N;u++) dp[1<<u][u]=-1;
        //push forward transitions
        for (int set=0;set<(1<<N);set++){
            for (int v=0;v<N;v++){
                if (dp[set][v]==-2) continue;
                for (int nextV=0;nextV<N;nextV++){
                    if ((1<<nextV) < (set&-set)) continue;
                    if (edge(v,nextV) && !nodeIn(set,nextV)){
                        dp[set|(1<<nextV)][nextV]=v;
                    }
                }
            }
        }

        //find cycle if exists
        int cycle = 0;
        int[] ans = new int[N];
        boolean done = false;
        for (int set=0;set<(1<<N);set++){
            if (Integer.bitCount(set)==set) continue;
            for (int v=0;v<N;v++){
                if (dp[set][v]==-2) continue;
                int u = (int)(Math.log(Integer.lowestOneBit(set))/Math.log(2));
                if (!edge(u,v)) continue;
                //found cycle! calculate reach
                int reach = set;
                for (int cycleMember=0;cycleMember<N;cycleMember++){
                    if (nodeIn(set,cycleMember)){
                        reach|=edgeSet[cycleMember];
                    }
                }
                //found the answer! lets construct it now!
                if (reach==((1<<N)-1)){
                    ans[v]=u;
                    cycle=set;
                    while (true){
                        int newV = dp[set][v];
                        if (newV==-1 || newV==-2) break;

                        ans[newV]=v;
                        set = set^(1<<v);
                        v = newV;
                    }
                    for (int outsideNode=0;outsideNode<N;outsideNode++){
                        for (int cycleNode=0;cycleNode<N;cycleNode++){
                            if (!nodeIn(cycle,outsideNode) && nodeIn(cycle,cycleNode) && edge(cycleNode,outsideNode)){
                                ans[outsideNode]=cycleNode;
                            }
                        }
                    }
                    done=true;
                }
                if (done) break;
            }
            if (done) break;
        }

        //* ret
        if (cycle==0){
            io.println("NO");
        } else {
            io.println("YES");
            for (int i=0;i<N;i++){
                io.print(ans[i]+1+" ");
            }
        }
	}
    static boolean nodeIn(int set, int u){
        return (set&(1<<u))>0;
    }
    static boolean edge(int u, int v){
        return (edgeSet[u]&(1<<v))>0;
    }


	public static void main(String[] args) throws IOException{
		io=new IO(false);
		solve();
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

		void print2d(int[][] arr){
			for(int r=0;r<arr.length;r++){
				for(int c=0;c<arr[r].length;c++){
					String str=""+arr[r][c];
					while(str.length()<4) str+=" ";
					print(str);
				}
				println();
			}
			println();
		}

		void print2d(char[][] arr){
			for(int r=0;r<arr.length;r++){
				for(int c=0;c<arr[r].length;c++){
					String str=""+arr[r][c];
					while(str.length()<4) str+=" ";
					print(str);
				}
				println();
			}
			println();
		}

		void print2d(boolean[][] arr){
			for(int r=0;r<arr.length;r++){
				for(int c=0;c<arr[r].length;c++){
					String str=""+(arr[r][c]?"1":"0");
					while(str.length()<4) str+=" ";
					print(str);
				}
				println();
			}
			println();
		}

		String binToStr(int bin,int len){
			String ret="";
			for(int i=0;i<len;i++){
				ret+=bin%2;
				bin/=2;
			}
			return ret;
		}

		void reverse(int[] arr){
			for(int i=0;i<arr.length/2;i++){
				int tmp=arr[i];
				arr[i]=arr[arr.length-1-i];
				arr[arr.length-1-i]=tmp;
			}
		}

		void close(){
			out.close();
		}
	}

	;
}
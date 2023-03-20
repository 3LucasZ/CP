import java.io.*;
import java.util.*;

public class TwoArrays{
	static boolean debug=false;

    static int N;
    static int M;
    static int[] A;
    static int[] B;
    static int[] left;
    static int[] right;

	public static void solve() throws IOException{
		//* parse
        N = io.nextInt();
        M = io.nextInt();
        A = new int[N];
        B = new int[M];
        for (int i=0;i<N;i++){
            A[i]=io.nextInt();
        }
        for (int i=0;i<M;i++){
            B[i]=io.nextInt();
        }
        if (debug){
            io.println("A:"+Arrays.toString(A));
            io.println("B:"+Arrays.toString(B));
        }
        //* Bi Mapping to <i, rightmost Aj>
        HashMap<Integer,Pair<Integer,Integer>> Bset = new HashMap<>();
        for (int i=0;i<M;i++) Bset.put(B[i],new Pair<>(i,-1));
        for (int i=0;i<N;i++){
            if (Bset.containsKey(A[i])) Bset.get(A[i]).second=i;
        }
        if (debug){
            io.println("Bset:"+Bset);
        }
        //* find right
        right = new int[N];
        for (int i=0;i<N;i++){
            if (Bset.containsKey(A[i])){
                right[Bset.get(A[i]).second]=i;
            }
        }
        if (debug){
            io.println("right:"+Arrays.toString(right));
        }
        //* ensure possible
        int aMin = Integer.MAX_VALUE;
        for (int i=N-1;i>=0;i--){
            aMin=Math.min(aMin,A[i]);
            if (Bset.containsKey(A[i]) && Bset.get(A[i]).second == i && aMin != A[i]){
                io.println(0);
                return;
            }
        }
        if (B[0]!=aMin){
            io.println(0);
            return;
        }
        for (int key : Bset.keySet()){
            if (Bset.get(key).second==-1){
                io.println(0);
                return;
            }
        }
        //* find left
        left = new int[N];
        Stack<Integer> s = new Stack<>();
        for (int i=0;i<N;i++){
            while (!s.isEmpty() && A[i]<=A[s.peek()]){
                s.pop();
            }
            if (s.isEmpty()){
                left[i]=-1;
            } else {
                left[i]=s.peek();
            }
            s.add(i);
        }
        if (debug){
            io.println("left:"+Arrays.toString(left));
        }
        //* multiply
        long MOD = 998244353;
        long ans = 1;
        for (int i=1;i<M;i++){
            int j = Bset.get(B[i]).second;
            ans=(ans*(right[j]-left[j]))%MOD;
        }
        //* ret
        io.println(ans);
	}


	public static void main(String[] args) throws IOException{
		io=new IO(debug);
		solve();
		io.close();
	}

	static IO io;

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
					print(arr[r][c]);
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

		void close(){
			out.close();
		}
	}

	;
}
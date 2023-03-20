package Solutions.Codeforces.Edu89;

import java.io.*;
import java.util.*;

public class TwoDivisors{
	static boolean debug=false;

    static int N;
    static int[] A;
    static int[] d1;
    static int[] d2;
	public static void solve() throws IOException{
		//* parse
        N = io.nextInt();
        A = new int[N];
        d1 = new int[N];
        d2 = new int[N];
        for (int i=0;i<N;i++) A[i]=io.nextInt();
        //* handle
        ArrayList<Integer> primes = primesLEQ(10000000);
        for (int i=0;i<N;i++){
            d1[i]=primeFactorize(A[i],primes);
            d2[i]=A[i]/d1[i];
            if (d1[i]==A[i]){
                d1[i]=-1;
                d2[i]=-1;
            }
        }
        //* ret
        for (int i=0;i<N;i++) io.print(d1[i]+" ");
        io.println();
        for (int i=0;i<N;i++) io.print(d2[i]+" ");
        io.println();
	}
    public static ArrayList<Integer> primesLEQ(int n){
        //is prime boolean array
        boolean[] prime = new boolean[n+1]; for (int i=2;i<=n;i++) prime[i]=true;
        for (int p=2;p*p<=n;p++){
            if (prime[p]){
                for (int i=p*p;i<=n;i+=p){
                    prime[i]=false;
                }
            }
        }
        //convert array to list
        ArrayList<Integer> ret = new ArrayList<>();
        for (int p=2;p<=n;p++){
            if (prime[p]) ret.add(p);
        }
        return ret;
    }
    static int primeFactorize(int val,ArrayList<Integer> primes){
        for (int i=0;primes.get(i)*primes.get(i)<=val;i++){
            int p = primes.get(i);
            if (val%p==0){
                int ret = 1;
                while(val%p==0){
                    ret*=p;
                    val/=p;
                }
                return ret;
            }
        }
        return val;
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
}
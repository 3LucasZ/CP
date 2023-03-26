package Solutions.Retry;

import java.io.*;
import java.util.*;

public class CountTheCows {
    static boolean debug = false;

    static int BITS = 40;
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        long dd = io.nextLong();
        long xx = io.nextLong();
        long yy = io.nextLong();

        int[] d = bin(dd);
        int[] x = bin(xx);
        int[] y = bin(yy);

        if (debug){
            io.println("x:"+Arrays.toString(x));
            io.println("y:"+Arrays.toString(y));
            io.println("d:"+Arrays.toString(d));
        }
        //* dp
        long[][][][] dp = new long[BITS][3][2][2];
        //base
        for (int c=0;c<3;c++){
            int xval = x[0]+c;
            int yval = y[0]+c;
            int xcur = xval%3;
            int ycur = yval%3;
            if (xcur%2==ycur%2) dp[0][cmp(c,d[0])][xval/3][yval/3]+=1;
        }
        //trans
        for (int bit=0;bit<BITS-1;bit++){
            for (int q=0;q<3;q++){
                for (int xc=0;xc<2;xc++){
                    for (int yc=0;yc<2;yc++){
//                        if (debug){
//                            if (dp[bit][q][xc][yc]>0 && dp[bit][q][xc][yc] <3) io.println("bit:"+bit+", q:"+q+", xc:"+xc+", yc:"+yc+" = "+dp[bit][q][xc][yc]);
//                        }
                        for (int c=0;c<3;c++){
                            int xval = x[bit+1]+xc+c;
                            int yval = y[bit+1]+yc+c;
                            int xcur = xval%3;
                            int ycur = yval%3;
                            int q2;
                            if (c < d[bit+1]) q2=0;
                            else if (c > d[bit+1]) q2=2;
                            else q2 = q;
                            if (xcur%2==ycur%2) dp[bit+1][q2][xval/3][yval/3]+=dp[bit][q][xc][yc];
                        }
                    }
                }
            }
        }
        //* ret
        io.println(dp[BITS-1][0][0][0]+dp[BITS-1][1][0][0]);
    }
    static int cmp(int a,int b){
        if (a<b) return 0;
        else if (a==b) return 1;
        else return 2;
    }
    static int[] bin(long a){
        int[] ret = new int[BITS];
        for (int i=0;i<BITS;i++){
            ret[i]=(int)(a%3);
            a/=3;
        }
        return ret;
    }




















    static IO io;
    public static void main(String[] args) throws IOException {
        io = new IO(debug);
        int T = io.nextInt();
        for (int i=1;i<=T;i++) solve(i);
        io.close();
    }
        public static long lcm(long a, long b){
        return a*b/gcd(a,b);
    }
    public static long gcd(long a, long b){
        if (b==0) return a;
        return gcd(b,a%b);
    }
    private static class Triple <T1, T2, T3> {
        T1 first;
        T2 second;
        T3 third;
        public Triple(T1 first,T2 second, T3 third){
            this.first=first;
            this.second=second;
            this.third=third;
        }
        public String toString(){
            return "["+first+", "+second+", "+third+"]";
        }
    } 
   private static class Pair <T1, T2> {
        T1 first;
        T2 second;
        public Pair(T1 first,T2 second){
            this.first=first;
            this.second=second;
        }
        @Override
        public boolean equals(Object o){
            if (!(o instanceof Pair)) return false;
            Pair<T1,T2> other = (Pair<T1,T2>) o;
            return first.equals(other.first) && second.equals(other.second);
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
        for (int i=0;i<arr.length/2;i++){
            int tmp = arr[i];
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
    private static class IO {
        BufferedReader br;
        StringTokenizer st;
        PrintWriter out;
        boolean debug;
        public IO(boolean dbg)  {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
            debug=dbg;
        }
        public IO(String fileName, boolean dbg) throws IOException {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new FileWriter(fileName+".out"));
            debug=dbg;
        }
        String next()
        {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() {return Double.parseDouble(next());}
        String nextLine() {
            String str = "";
            try {
                if(st.hasMoreTokens()){
                    str = st.nextToken("\n");
                }
                else{
                    str = br.readLine();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
        void println(){
            if (debug) System.out.println();
            else out.println();
        }
        void println(Object obj){
            if (debug) System.out.println(obj);
            else out.println(obj);
        }
        void print(Object obj){
            if (debug) System.out.print(obj);
            else out.print(obj);
        }
        void close(){
            out.close();
        }
    }
}
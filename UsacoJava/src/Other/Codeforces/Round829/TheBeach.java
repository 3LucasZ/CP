package Other.Codeforces.Round829;

import java.io.*;
import java.util.*;
/*
PROB: TheBeach
LANG: JAVA
*/
public class TheBeach {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int R;
    static int C;
    static long p;
    static long q;
    static char[][] map;
    //                 R L D U
    static int[] dr = {0,0,1,-1};
    static int[] dc = {1,-1,0,0};

    static final long INF = Long.MAX_VALUE/100;
    public static void solve() throws IOException {
        //* parse
        R = io.nextInt();
        C = io.nextInt();
        p = io.nextInt();
        q = io.nextInt();
        map = new char[R][C];
        for (int r=0;r<R;r++){
            String str  = io.nextLine();
            for (int c=0;c<C;c++){
                map[r][c]=str.charAt(c);
            }
        }
        if (debug){
            io.print2d(map);
        }
        //* mpsp
        long[][] cost = new long[R][C]; for (int r=0;r<R;r++) for (int c=0;c<C;c++) cost[r][c]=INF;
        PriorityQueue<Item> pq = new PriorityQueue<>(Comparator.comparingLong(a->a.cost));
        for (int r=0;r<R;r++) for (int c=0;c<C;c++) if (map[r][c]=='.') {
            pq.add(new Item(r,c,0));
            cost[r][c]=0;
        }


        while (!pq.isEmpty()){
            Item next = pq.poll();
            if (next.cost!=cost[next.r][next.c]) continue;
            if (debug){
                io.println("processing:"+next);
            }
            for (int i=0;i<4;i++){
                //find adjacent node
                int tryR = next.r+dr[i];
                int tryC = next.c+dc[i];
                if (bad(tryR,tryC)) continue;
                int newR = tryR;
                int newC = tryC;
                long newCost = next.cost;
                if (map[tryR][tryC]=='D')  newR--;
                else if (map[tryR][tryC]=='U') newR++;
                else if (map[tryR][tryC]=='R') newC--;
                else if (map[tryR][tryC]=='L') newC++;
                else continue;
                //find edge cost
                if (Math.abs(newR-next.r)==1) newCost+=p;
                else newCost+=q;
                //update
                if (newCost<cost[newR][newC]){
                    cost[newR][newC]=newCost;
                    Item add = new Item(newR,newC,newCost);
                    pq.add(add);
                    if (debug) io.println("adding:"+add);
                }
            }
        }

        //* ret comb
        long ans = INF;
        for (int r=0;r<R;r++){
            for (int c=0;c<C;c++){
                for (int i=0;i<4;i++){
                    int tr = r+dr[i];
                    int tc = c+dc[i];
                    if (bad(tr,tc)) continue;
                    ans=Math.min(ans,cost[r][c]+cost[tr][tc]);
                }
            }
        }
        if (ans==INF){
            io.println(-1);
        } else{
            io.println(ans);
        }
    }
    static boolean bad(int r, int c){
        return r<0||r>=R ||c<0||c>=C;
    }
    private static class Item {
        int r;
        int c;
        long cost;
        public Item(int r, int c, long cost){
            this.r=r;
            this.c=c;
            this.cost=cost;
        }
        public String toString() {
            return "["+r+", "+c+"]";
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) throws IOException {
        if (fileSubmission){
            io = new IO(fileName, debug);
        } else {
            io = new IO(debug);
        }
        solve();
        io.close();
    }
    static IO io;
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
    void print2d(int[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + arr[r][c];
                while (str.length() < 4) str += " ";
                print(str);
            }
           println();
        }
        println();
    }
    void print2d(char[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + arr[r][c];
                while (str.length() < 4) str += " ";
                print(str);
            }
            println();
        }
        println();
    }
    void print2d(boolean[][] arr) {
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[r].length; c++) {
                String str = "" + (arr[r][c]?"1":"0");
                while (str.length() < 4) str += " ";
                print(str);
            }
            println();
        }
        println();
    }
    void printBin(int bin,int len){
        String ret = "";
        for (int i=0;i<len;i++){
            ret+=bin%2;
            bin/=2;
        }
        println(ret);
    }
    void reverse(int[] arr){
		for (int i=0;i<arr.length/2;i++){
			int tmp = arr[i];
			arr[i]=arr[arr.length-1-i];
			arr[arr.length-1-i]=tmp;
		}
	}
    void close(){
        out.close();
    }
};
}
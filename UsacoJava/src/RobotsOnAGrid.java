import java.io.*;
import java.util.*;

public class RobotsOnAGrid {
    static boolean debug = false;

    static int R;
    static int C;

    static boolean[] black;

    static ArrayList<Integer>[] in;
    static int[] out;

    //U=0, R=1, D=2, L=3
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};

    static int pack(int r, int c){
        return r*C+c;
    }
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        R = io.nextInt();
        C = io.nextInt();
        black = new boolean[R*C];
        for (int r=0;r<R;r++){
            String str = io.nextLine();
            for (int c=0;c<C;c++){
                black[pack(r,c)]=str.charAt(c)=='0';
            }
        }
        out=new int[R*C];
        in= new ArrayList[R*C]; for (int i=0;i<R*C;i++) in[i] = new ArrayList<>();
        for (int r=0;r<R;r++){
            String str = io.nextLine();
            for (int c=0;c<C;c++){
                char k = str.charAt(c);
                int num = -1;
                if (k=='U') num=0;
                else if (k=='R') num=1;
                else if (k=='D') num=2;
                else num=3;
                out[pack(r,c)]=pack(r+dr[num],c+dc[num]);
                in[pack(r+dr[num],c+dc[num])].add(pack(r,c));
            }
        }
        if (debug){
            io.println("out:"+Arrays.toString(out));
        }
        //* find components
        vis = new boolean[R*C];
        tin = new int[R*C];
        cycle = new boolean[R*C];
        robots = 0;
        blackRobots = 0;
        for (int i=0;i<R*C;i++){
            if (vis[i]) continue;
            DFS(i);
        }
        //* ret
        io.println(robots+" "+blackRobots);
    }
    static boolean[] vis;
    static boolean[] cycle;
    static int[] tin;
    static int robots;
    static int blackRobots;
    static void DFS(int node){
        while (true){
            //if we found the cycle
            if(vis[node]){
                //get sz
                int cur=node;
                int sz=0;
                do{
                    sz++;
                    cycle[cur]=true;
                    cur=out[cur];
                }while(cur!=node);
                //find tin, blackTin
                HashSet<Integer> blackTin=new HashSet<>();
                cur=node;
                do{
                    DFS2(cur,sz,blackTin);
                    tin[out[cur]]=tin[cur]+1;
                    cur=out[cur];
                }while(cur!=node);
                //stache
                robots+=sz;
                blackRobots+=blackTin.size();
                return;
            }
            //else continue trying to find cycle
            vis[node]=true;
            node=out[node];
        }
    }
    static void DFS2(int node, int sz, HashSet<Integer> blackTin){
        Queue<Integer> BFS = new LinkedList<>();
        BFS.add(node);
        while (!BFS.isEmpty()){
            node = BFS.poll();
            //process
            vis[node]=true;
            if(black[node]) blackTin.add(tin[node]%sz);
            //propagate children
            for(int child: in[node]){
                if(!cycle[child]){
                    tin[child]=(tin[node]+sz-1)%sz;
                    BFS.add(child);
                }
            }
        }
    }




















    static IO io;
    public static void main(String[] args) throws IOException {
        io = new IO(debug);
        int T = io.nextInt();
        for (int i=1;i<=T;i++) solve(i);
        io.close();
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
                    print(arr[r][c]);
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
        void close(){
            out.close();
        }
    }
}
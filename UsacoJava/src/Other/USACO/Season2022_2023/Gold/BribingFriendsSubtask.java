package Other.USACO.Season2022_2023.Gold;

import java.io.*;
import java.util.*;
/*
PROB: BribingFriends
LANG: JAVA
*/
public class BribingFriendsSubtask {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N,A,B; //friends, money, cones
    static Friend[] friend; //popularity, cost, accept cones


    static int INF = Integer.MAX_VALUE;
    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        A = io.nextInt();
        B = io.nextInt();
        friend = new Friend[N+1];
        for (int i=1;i<=N;i++){
            int P = io.nextInt();
            int C = io.nextInt();
            int X = io.nextInt();
            friend[i] = new Friend(P,C,X);
        }
        //* greedy sort on X
        friend[0] = new Friend(0,0,-1);
        Arrays.sort(friend, Comparator.comparingInt(a -> a.X));
        if (debug) io.println(Arrays.toString(friend));
        //* dp
        //init: dp[i][j][k] = max popularity for 1..ith cow segment, using j coins and k cones
        int[][][] dp = new int[N+1][A+1][B+1];
        for (int i=0;i<=N;i++) for (int j=0;j<=A;j++) for (int k=0;k<=B;k++) dp[i][j][k]=-INF;
        dp[0][0][0]=0;
        //transitions dp[i][j][k] => dp[i+1][j'][k']
        for (int i=0;i<N;i++){
            for (int j=0;j<=A;j++){
                for (int k=0;k<=B;k++){
                    //dont take cow i+1
                    dp[i+1][j][k]=Math.max(dp[i+1][j][k],dp[i][j][k]);
                    //take cow i+1
                    int bribeCones = Math.min(B-k,friend[i+1].C*friend[i+1].X);
                    int remainder = bribeCones%friend[i+1].X;
                    bribeCones-=remainder;
                    int bribeMoney = bribeCones/friend[i+1].X;

                    int payCoins = friend[i+1].C-bribeMoney;
                    int payCones = bribeCones;
                    if (j+payCoins<=A && k+payCones<=B) dp[i+1][j+payCoins][k+payCones]=Math.max(dp[i+1][j+payCoins][k+payCones], dp[i][j][k]+friend[i+1].P);
                }
            }
        }
        if (debug){
            for (int i=0;i<=N;i++){
                for (int j=0;j<=A;j++){
                    for (int k=0;k<=B;k++){
                        io.println("i: "+i+", j: "+j+", k: "+k+" = "+dp[i][j][k]);
                    }
                }
            }
        }
        if (debug){
            io.println(dp[1][5][6]);
        }
        //collect ans
        int ans = 0;
        for (int j=0;j<=A;j++){
            for (int k=0;k<=B;k++){
                ans=Math.max(ans,dp[N][j][k]);
            }
        }
        //* ret
        io.println(ans);
    }

    private static class Friend {
        int P;
        int C;
        int X;
        public Friend(int P, int C, int X){
            this.P=P;
            this.C=C;
            this.X=X;
        }
        public String toString(){
            return "["+P+", "+C+", "+X+"]";
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
    void close(){
        out.close();
    }
};;
}
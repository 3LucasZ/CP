package Other.USACO.Season2022_2023.Gold;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
PROB: BribingFriends
LANG: JAVA
*/
public class BribingFriends {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N,A,B; //friends, money, cones
    static Friend[] friend; //popularity, cost, accept cones


    static int INF = 100;
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

        //* greedy sort by X
        friend[0] = new Friend(0,0,-1);
        Arrays.sort(friend, Comparator.comparingInt(a -> a.X));
        if (debug) io.println(Arrays.toString(friend));

        //* coneDp ->
        //init: dp[i][j] = max popularity for 1..ith cow segment, using j cones
        int[][] coneDP = new int[N+2][B+1];
        //for (int i=0;i<=N;i++) for (int j=0;j<=B;j++) coneDP[i][j]=-INF;
        coneDP[0][0]=0;
        //transitions dp[i][j] => dp[i+1][j']
        for (int i=0;i<N;i++){
            for (int j=0;j<=B;j++){
                    //dont take cow i+1
                    coneDP[i+1][j]=Math.max(coneDP[i+1][j],coneDP[i][j]);
                    //take cow i+1
                    int payCones = friend[i+1].C*friend[i+1].X;
                    if (j+payCones<=B) coneDP[i+1][j+payCones]=Math.max(coneDP[i+1][j+payCones], coneDP[i][j]+friend[i+1].P);
            }
        }
        if (debug){
            io.print2d(coneDP);
        }

        //* moneyDP <-
        int[][] moneyDP = new int[N+2][A+1];
        //for (int i=0;i<=N+1;i++) for (int j=0;j<=A;j++) moneyDP[i][j]=-INF;
        moneyDP[N+1][0]=0;
        //transitions dp[i][j] => dp[i+1][j']
        for (int i=N+1;i>1;i--){
            for (int j=0;j<=A;j++){
                //dont take cow i-1
                moneyDP[i-1][j]=Math.max(moneyDP[i-1][j],moneyDP[i][j]);
                //take cow i-1
                int payMoney = friend[i-1].C;
                if (j+payMoney<=A) moneyDP[i-1][j+payMoney]=Math.max(moneyDP[i-1][j+payMoney], moneyDP[i][j]+friend[i-1].P);
            }
        }
        if (debug){
            io.print2d(moneyDP);
        }

        //* try every split point
        //take 1..i-1 for cones, i for cones AND money, i+1..N for money
        int ans = 0;
        for (int i=1;i<=N;i++){
            for (int cones=0;cones<=B;cones++){
                int payCones = Math.min(B-cones,friend[i].C*friend[i].X);
                int saveMoney = payCones/friend[i].X;
                int payMoney = friend[i].C-saveMoney;
                if (A-payMoney<0||A-payMoney>A) continue;
                int l = coneDP[i-1][cones];
                int r = moneyDP[i+1][A-payMoney];
                ans=Math.max(ans,l+r+friend[i].P);
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
    void print2d(int[][] arr) {
            for (int r = 0; r < arr.length; r++) {
                for (int c = 0; c < arr[r].length; c++) {
                    String str = "" + arr[r][c];
                    while (str.length() < 5) str += " ";
                    System.out.print(str);
                }
                System.out.println();
            }
            System.out.println();
        }
    void close(){
        out.close();
    }
};;
}
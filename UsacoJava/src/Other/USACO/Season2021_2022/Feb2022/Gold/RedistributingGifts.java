package Other.USACO.Season2021_2022.Feb2022.Gold;

import java.io.*;
import java.util.*;
/*
PROB: RedistributingGifts
LANG: JAVA
*/
public class RedistributingGifts {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;

    static int N;
    static boolean[][] canTake;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        canTake = new boolean[N][N];
        for (int cow=0;cow<N;cow++){
            StringTokenizer st = new StringTokenizer(io.nextLine());
            for (int favor=0;favor<N;favor++){
                int next = Integer.parseInt(st.nextToken())-1;
                canTake[cow][next]=true;
                if (next==cow) break;
            }
        }
        if (debug){
            for (int cow=0;cow<N;cow++){
                for (int gift=0;gift<N;gift++){
                    if (canTake[cow][gift]) io.print(1);
                    else io.print(0);
                }
                io.println();
            }
        }

        //* dp
        //init: dp[cow set][last cow added] = number of working reassignments
        int sets = 1<<N;
        long[][] dp = new long[sets][N];
        long[] ans = new long[sets];
        //base case: dp[1 cow set][last cow added] = 1
        for (int i=0;i<N;i++) dp[1<<i][i]=1;
        //transitions: dp[set+(e<i)][e] and dp[set+(e>i)][e]
        for (int set=1;set<sets;set++){
            for (int last=0;last<N;last++){
                //make sure last is in the set
                if (((1<<last)&set)==0) continue;
                //find running cycle head
                int head = N-1; for (;head>=0;head--) if (((1<<head)&set)!=0) break;
                for (int add=0;add<N;add++){
                    //make sure add is new to set
                    if (((1<<add)&set)!=0) continue;
                    //set + add
                    int newSet = (1<<add)^set;
                    //case: continue cycle
                    if (add<head){
                        if (canTake[last][add]) {
                            dp[newSet][add]+=dp[set][last];
                        }
                    }
                    //case: end cycle and create new one
                    if (add>head){
                        if (canTake[last][head]){
                            dp[newSet][add]+=dp[set][last];
                        }
                    }
                }
                //collect ans
                if (canTake[last][head]){
                    ans[set]+=dp[set][last];
                }
            }
        }
        ans[0]=1;
        if (debug){
            io.println(Arrays.toString(ans));
        }

        //* handle queries
        //handle queries
        int Q = Integer.parseInt(io.nextLine());
        for (int q=0;q<Q;q++){
            String str = io.nextLine();
            int H = 0;
            int G = 0;
            for (int i=0;i<N;i++){
                if (str.charAt(i)=='H') H+=(1<<i);
                else G+=(1<<i);
            }
            io.println(ans[H]*ans[G]);
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
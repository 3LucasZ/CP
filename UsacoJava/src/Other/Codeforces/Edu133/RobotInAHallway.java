package Other.Codeforces.Edu133;

import java.io.*;
import java.util.*;

public class RobotInAHallway {
    static boolean debug = false;

    static int N;
    static int[][] t;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        t = new int[3][N+1];
        for (int r=1;r<=2;r++){
            for (int c=1;c<=N;c++){
                t[r][c]=io.nextInt();
            }
        }

        //* time to form a snake until len i=1..N
        int[] snake = new int[N+1];
        int snakeRun = -1;
        for (int c=1;c<=N;c++){
            //even run bottom top
            if (c%2==0){
                snakeRun=Math.max(snakeRun+1,t[2][c]+1);
                snakeRun=Math.max(snakeRun+1,t[1][c]+1);
                snake[c]=snakeRun;
            }
            //odd run top bottom
            else {
                if (c!=1) snakeRun=Math.max(snakeRun+1,t[1][c]+1);
                snakeRun=Math.max(snakeRun+1,t[2][c]+1);
                snake[c]=snakeRun;
            }
        }
        if (debug){
            io.println("snake: "+Arrays.toString(snake));
        }

        //* minimum time to travel hook seamless from 1..N
        int[] hook = new int[N+1];

        //even hooks
        int max = -1000;
        for (int i=N;i>=1;i--){
            max--;
            int newBot = 0;
            int newTop = 2*(N-i+1)-1;
            max=Math.max(max,t[2][i]-newBot);
            max=Math.max(max,t[1][i]-newTop);
            if (i%2==0) hook[i]=max;
        }
        //odd hooks
        max = -1000;
        for (int i=N;i>=1;i--){
            max--;
            int newTop = 0;
            int newBot = 2*(N-i+1)-1;
            max=Math.max(max,t[2][i]-newBot);
            max=Math.max(max,t[1][i]-newTop);
            if (i%2==1) hook[i]=max;
        }
        if (debug){
            io.println("hook: "+Arrays.toString(hook));
        }

        //* combine with sweep line
        //init ans should just be one massive hook
        int ans = 0;
        for (int i=2;i<=N;i++){
            ans=Math.max(ans+1,t[1][i]+1);
        }
        for (int i=N;i>=1;i--){
            ans=Math.max(ans+1,t[2][i]+1);
        }
        //try all splits
        for (int i=1;i<N;i++){
            int snakePart = snake[i];
            int hookPart = hook[i+1];
            int time = Math.max(snakePart,hookPart)+2*(N-i);
            ans=Math.min(ans,time);
        }

        //* ret
        io.println(ans);
    }
    /*
    1
    2
    0 1
    1 0
     */




















    static IO io;
    public static void main(String[] args) throws IOException {
        io = new IO(debug);
        int T = io.nextInt();
        for (int i=1;i<=T;i++) solve(i);
        io.close();
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
    public static void print(int[][] arr) {
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
};}
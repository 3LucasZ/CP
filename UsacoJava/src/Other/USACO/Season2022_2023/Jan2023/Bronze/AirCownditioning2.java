package Other.USACO.Season2022_2023.Jan2023.Bronze;

import java.io.*;
import java.util.*;
/*
PROB: AirCownditioning2
LANG: JAVA
*/
public class AirCownditioning2 {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = true;

    public static void solve() throws IOException {
        //* parse
        int N = io.nextInt();
        int M = io.nextInt();
        int[] l = new int[N];
        int[] r = new int[N];
        int[] c = new int[N];
        for (int i=0;i<N;i++){
            l[i]=io.nextInt();
            r[i]=io.nextInt();
            c[i]=io.nextInt();
        }
        int[] a = new int[M];
        int[] b = new int[M];
        int[] p = new int[M];
        int[] m = new int[M];
        for (int i=0;i<M;i++){
            a[i]=io.nextInt();
            b[i]=io.nextInt();
            p[i]=io.nextInt();
            m[i]=io.nextInt();
        }

        //* try everything
        int ans = Integer.MAX_VALUE;
        for (int mask=0;mask<(1<<M);mask++){
            int[] cooled = new int[101];
            int money = 0;
            for (int ac=0;ac<M;ac++){
                if ((mask&(1<<ac))!=0){
                    money+=m[ac];
                    for (int i=a[ac];i<=b[ac];i++){
                        cooled[i]+=p[ac];
                    }
                }
            }
            for (int cow=0;cow<N;cow++){
                for (int i=l[cow];i<=r[cow];i++){
                    if (cooled[i]<c[cow]) money = Integer.MAX_VALUE;
                }
            }
            ans=Math.min(ans,money);
        }

        //* ret
        io.println(ans);
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
};;
}
package Codeforces.Edu141;

import java.io.*;
import java.util.*;
/*
PROB: C
LANG: JAVA
*/
public class C {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = true;
    
    public static void solve() throws IOException {
        //* parse
        int N = io.nextInt();
        int M = io.nextInt();
        int[] A = new int[N];
        for (int i=0;i<N;i++) A[i]=io.nextInt();

        //* order
        Integer[] o = new Integer[N];
        for (int i=0;i<N;i++)o[i]=i;
        Arrays.sort(o,(a,b)->A[a]-A[b]);

        int ans = -1;
        //dummy case
        if (N==1){
            if (M>=A[0])ans=0;
        }
        //* normal play and forced play
        int sum = 0;
        HashSet<Integer> seen = new HashSet<>();
        for (int j=0;j<N-1;j++){
            int i = o[j];
            sum+=A[i];
            seen.add(i);
            if (seen.contains(j+1)){
                if (M>=sum){
                    ans=Math.max(ans,j+1);
                }
            } else {
                if (M>=sum){
                    ans=Math.max(ans,j);
                }
                if (M>=sum-A[i]+A[j+1]){
                    ans=Math.max(ans,j+1);
                }
            }
        }

        io.println(N-ans);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) throws IOException {
        if (fileSubmission){
            io = new IO(fileName, debug);
        } else {
            io = new IO(debug);
        }
        int T = io.nextInt();
        for (int i=0;i<T;i++)solve();
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
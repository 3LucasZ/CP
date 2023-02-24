package Other.Codeforces.Round852;

import java.io.*;
import java.util.*;
/*
PROB: MoscowGorillas
LANG: JAVA
*/
public class MoscowGorillas {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = false;
    
    public static void solve() throws IOException {
        //* parse
        int N = io.nextInt();
        int[] A = new int[N+1];
        int[] B = new int[N+1];
        for (int i=1;i<=N;i++){
            A[i]=io.nextInt();
        }
        for (int i=1;i<=N;i++){
            B[i]=io.nextInt();
        }
        int[] Aindex = new int[N+1];
        int[] Bindex = new int[N+1];
        for (int i=1;i<=N;i++){
            Aindex[A[i]]=i;
            Bindex[B[i]]=i;
        }
        if (debug){
            io.println("Aindex:"+Arrays.toString(Aindex));
            io.println("Bindex:"+Arrays.toString(Bindex));
        }

        //* precomp min, max
        int[] min = new int[N+1]; Arrays.fill(min,Integer.MAX_VALUE);
        int[] max = new int[N+1];
        for (int i=1;i<=N;i++){
            min[i]=Math.min(min[i-1],Math.min(Aindex[i],Bindex[i]));
            max[i]=Math.max(max[i-1],Math.max(Aindex[i],Bindex[i]));
        }
        if (debug){
            io.println("min:"+Arrays.toString(min));
            io.println("max:"+Arrays.toString(max));
        }

        //* bash sets
        long ans = 1; //the full set is always good
        for (int MX=2;MX<=N;MX++){
            int set = MX-1;
            int lA = 1;
            int lB = 1;
            int rA = N;
            int rB = N;
            if (Aindex[MX]<min[set]){
                lA = Aindex[MX]+1;
            } else if (Aindex[MX]>max[set]){
                rA = Aindex[MX]-1;
            } else {
                continue;
            }

            if (Bindex[MX]<min[set]){
                lB = Bindex[MX]+1;
            } else if (Bindex[MX]>max[set]){
                rB = Bindex[MX]-1;
            } else {
                continue;
            }

            int finL = Math.max(lA,lB);
            int finR = Math.min(rA,rB);
            ans +=(long)(min[set]-finL+1)*(finR-max[set]+1);
        }
        int l = Math.min(Aindex[1],Bindex[1]);
        int r = Math.max(Aindex[1],Bindex[1]);
        ans+=cut(l-1)+cut(N-r)+cut(r-l-1);

        //* ret
        io.println(ans);
    }
    static long cut(int x){
        return (long)x*(x+1)/2;
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
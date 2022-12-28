import java.io.*;
import java.util.*;
/*
PROB: HighCardLowCard
LANG: JAVA
*/
public class HighCardLowCard {
    static boolean fileSubmission = true;
    static String fileName = "cardgame";
    static boolean debug = false;

    static int N;
    static boolean[] typeE;

    public static void solve() throws IOException {
        //* parse
        N = io.nextInt();
        typeE = new boolean[2*N+1];
        Integer[] E = new Integer[N+1];
        for (int i=0;i<N;i++){
            int j = io.nextInt();
            typeE[j]=true;
            E[i+1]=j;
        }
        TreeSet<Integer> B;

        //* hi wins 1..i
        int[] hi = new int[N+2];
        B = new TreeSet<>();
        for (int i=1;i<=2*N;i++) if (!typeE[i])B.add(i);
        if (debug) io.println("B: "+B);
        for (int i=1;i<=N;i++){
            hi[i]=hi[i-1];
            Integer next = B.higher(E[i]);
            if (next==null) continue;
            hi[i]++;
            B.remove(next);
        }
        if (debug) io.println("hi: "+Arrays.toString(hi));

        //* lo wins i..N
        int[] lo = new int[N+2];
        B = new TreeSet<>();
        for (int i=1;i<=2*N;i++) if (!typeE[i])B.add(i);
        if (debug) io.println("B: "+B);
        for (int i=N;i>=1;i--){
            lo[i]=lo[i+1];
            Integer next = B.lower(E[i]);
            if (next==null) continue;
            lo[i]++;
            B.remove(next);
        }
        if (debug) io.println("lo: "+Arrays.toString(lo));

        //* sweep
        int ans = 0;
        for (int i=0;i<=N;i++){
            ans=Math.max(ans,hi[i]+lo[i+1]);
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
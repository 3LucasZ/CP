package Solutions.Codeforces.Round794;

import java.io.*;
import java.util.*;

public class Linguistics {
    static boolean debug = false;

    static int N;
    static int A;
    static int B;
    static int AB;
    static int BA;
    static char[] str;
    static int cmb;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        A = io.nextInt();
        B = io.nextInt();
        AB = io.nextInt();
        BA = io.nextInt();
        N = A+B+2*AB+2*BA;
        str = new char[N];
        String st = io.nextLine();
        for (int i=0;i<N;i++){
            str[i]=st.charAt(i);
        }
        if (debug) io.println(Arrays.toString(str));

        //* make sure sum(A) = sum(B) to begin with
        int Acnt = 0;
        int Bcnt = 0;
        for (int i=0;i<N;i++){
            if (str[i]=='A') Acnt++;
            else Bcnt++;
        }
        if (Acnt != A+AB+BA || Bcnt != B+AB+BA){
            io.println("NO");
            return;
        }

        //* split into segments
        ArrayList<Integer> l = new ArrayList<>();
        ArrayList<Integer> r = new ArrayList<>();
        boolean inSegment = false;
        for (int i=0;i<N;i++){
            //not in segment -> in segment
            if (!inSegment && (i==0 || str[i]==str[i-1])){
                inSegment=true;
                l.add(i);
            }
            //in segment -> not in segment
            if (inSegment && (i==N-1 || str[i]==str[i+1])){
                inSegment=false;
                r.add(i);
            }
        }
        if (debug) {
            io.println("l: "+l);
            io.println("r: "+r);
        }

        //* handle even segments
        int M = l.size();
        Integer[] x = new Integer[M];
        for (int i=0;i<M;i++) x[i]=i;
        Arrays.sort(x,(a,b)->r.get(a)-l.get(a)-r.get(b)+l.get(b));
        for (int j=0;j<M;j++){
            int i = x[j];
            if ((r.get(i)-l.get(i)+1)%2==0){
                handleEven(l.get(i),r.get(i));
            }
        }
        if (debug){
            io.println("trim even");
            io.println("AB: "+AB);
            io.println("BA: "+BA);
        }

        //* handle odd segments
        cmb = AB+BA;
        for (int i=0;i<M;i++){
            if ((r.get(i)-l.get(i)+1)%2==1){
                handleOdd(l.get(i),r.get(i));
            }
        }
        if (debug){
            io.println("trim odd");
            io.println("cmb: "+cmb);
        }

        //* ret
        if (cmb<=0){
            io.println("YES");
        } else {
            io.println("NO");
        }
    }
    static void handleOdd(int l, int r){
        int sz = r-l+1;
        cmb-=sz/2;
    }
    static void handleEven(int l, int r){
        int sz = r-l+1;
        boolean headA =str[l]=='A';

        if (headA){
            int take = Math.max(0,Math.min(sz/2,AB));
            sz-=take*2;
            AB-=take;
            BA-=Math.max(0,sz/2-1);
        }
        else {
            int take = Math.max(0,Math.min(sz/2,BA));
            sz-=take*2;
            BA-=take;
            AB-=Math.max(0,sz/2-1);
        }
    }




















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
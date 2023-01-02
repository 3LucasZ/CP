package Codeforces.Round829;

import java.io.*;
import java.util.*;

public class MakeNonzeroSumHard {
    static boolean debug = false;

    static int N;
    static int[] A;

    static ArrayList<Pair> ans;
    static int sum;

    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        A = new int[N+1];
        ans = new ArrayList<>();
        sum = 0;

        int s = 0;
        for (int i=1;i<=N;i++) {
            A[i]=io.nextInt();
            s+=Math.abs(A[i]);
        }
        if (debug) io.println("s: "+s);
        if (s%2==1) {
            io.println(-1);
            return;
        }

        //* group split and search
        for (int i=0;i<=N;i++){
            if (A[i]==0) {
                if (i == N || A[i + 1] == 0) {
                    ans.add(new Pair(i, i));
                } else {
                    int r = N;
                    for (int j = i + 1; j <= N; j++) {
                        if (A[j] == 0) {
                            r = j - 1;
                            break;
                        }
                    }
                    seg(i, r);
                    i=r;
                }
            }
        }


        //* ret
        io.println(ans.size()-1);
        for (Pair p : ans){
            if (p.x==0) continue;
            io.println(p.x+" "+p.y);
        }
    }

    public static void seg(int l, int r){
        if (debug){
            io.println("seg: "+l+", "+r);
            io.println("sum: "+sum);
        }
        //even case relax to 0
        if ((r-l)%2==0){
            ans.add(new Pair(l,l));
            for (int i=l+1;i<r;i+=2){
                if (A[i]==A[i+1]) ans.add(new Pair(i,i+1));
                else Collections.addAll(ans,new Pair(i,i),new Pair(i+1,i+1));
            }
        }
        //odd case try to get to -1 if sum is 1, and 1 if sum is -1, and let fate decide if sum is 0
        else {
           if (sum==1){
               if (A[l+1]==-1){
                   Collections.addAll(ans,new Pair(l,l),new Pair(l+1,l+1));
               } else {
                   ans.add(new Pair(l,l+1));
               }
               sum--;
           } else if (sum==-1){
               if (A[l+1]==-1){
                   ans.add(new Pair(l,l+1));
               } else {
                   Collections.addAll(ans,new Pair(l,l),new Pair(l+1,l+1));
               }
               sum++;
           } else {
               Collections.addAll(ans,new Pair(l,l),new Pair(l+1,l+1));
               sum+=A[l+1];
           }
           //relax to 0
            for (int i=l+2;i<r;i+=2){
                if (A[i]==A[i+1]) ans.add(new Pair(i,i+1));
                else Collections.addAll(ans,new Pair(i,i),new Pair(i+1,i+1));
            }
        }
    }

    private static class Pair {
        int x;
        int y;
        public Pair(int x, int y){
            this.x=x;
            this.y=y;
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

/*
4
2
0 0
1
0
1
1
1
-1
 */
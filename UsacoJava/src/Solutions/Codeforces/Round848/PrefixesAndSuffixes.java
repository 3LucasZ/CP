package Solutions.Codeforces.Round848;

import java.io.*;
import java.util.*;

public class PrefixesAndSuffixes {
    static boolean debug = false;

    static int N;
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        N = io.nextInt();
        String str1 = io.nextLine();
        String str2 = io.nextLine();
        HashMap<Integer,Integer> freq = new HashMap<>();
        for (int i=0;i<N;i++){
            add(freq,hash(str1.charAt(N-1-i),str2.charAt(i)));
        }
        int odds = 0;
        int odd = -1;
        for (int i : freq.keySet()) {
            if (freq.get(i)%2==1) {
                odd=i;
                odds++;
            }
        }
        if (odds==1 && odd/1000==odd%1000 && N%2==1) io.println("YES");
        else if (odds==0 && N%2==0) io.println("YES");
        else io.println("NO");
    }
    static void add(HashMap<Integer,Integer> map, int x){
        if (!map.containsKey(x)) map.put(x,0);
        map.put(x,map.get(x)+1);
    }
    static int hash(char a, char b){
        if (a>b){
            char tmp = a;
            a=b;
            b=tmp;
        }
        return a*1000+b;
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
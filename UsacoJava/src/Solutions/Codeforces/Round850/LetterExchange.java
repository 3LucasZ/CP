package Solutions.Codeforces.Round850;

import java.io.*;
import java.util.*;

public class LetterExchange {
    static boolean debug = false;
    //w = 0, i = 1, n = 2
    //stack[give][take]
    static int N;
    static Stack<Integer>[][] stack;
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        stack = new Stack[3][3]; for (int i=0;i<3;i++) for (int j=0;j<3;j++) stack[i][j] = new Stack();
        //* parse
        N = io.nextInt();
        int print = 0;
        for (int i=0;i<N;i++){
            String str = io.nextLine();
            Multiset ms = new Multiset();
            for (int j=0;j<3;j++){
                char s = str.charAt(j);
                if (s=='w') s=0;
                else if (s=='i') s=1;
                else s=2;
                ms.add(s);
            }
            int surplus = -1;
            for (int j=0;j<3;j++){
                if (ms.get(j)>1){
                    surplus = j;
                }
            }
            for (int j=0;j<3;j++) if (ms.get(j)==0) {
                stack[surplus][j].add(i+1);
                if (debug) io.println("i:"+i+" adding to:["+j+"]["+surplus+"]");
                print++;
            }
        }

        //* brute force
        ArrayList<String> toPrint = new ArrayList<>();
        char[] toChar = new char[] {'w','i','n'};
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                while (!stack[i][j].isEmpty()){
                    int k = 3 - i - j;
                    Integer pop1 = stack[i][j].pop();
                    if (!stack[j][i].isEmpty()){
                        Integer pop2=stack[j][i].pop();
                        toPrint.add(pop1+" "+toChar[i]+" "+pop2+" "+toChar[j]);
                    } else {
                        Integer pop2=stack[j][k].pop();
                        Integer pop3=stack[k][i].pop();
                        toPrint.add(pop1+" "+toChar[i]+" "+pop2+" "+toChar[j]);
                        toPrint.add(pop2+" "+toChar[i]+" "+pop3+" "+toChar[k]);
                    }
                }
            }
        }
        io.println(toPrint.size());
        for (String str : toPrint) io.println(str);
    }

    private static class Multiset {
        /*
        Overloaded TreeMap functioning as Multiset
        TreeMap private
         */
        private TreeMap<Integer, Integer> ms = new TreeMap<>();
        private int sz = 0;
        public boolean contains(int x){
            return ms.containsKey(x);
        }
        public void add(int x){add(x,1);}
        public void add(int x, int freq){
            if (!ms.containsKey(x))ms.put(x,0);
            ms.put(x,ms.get(x)+freq);
            sz+=freq;
        }
        public void remove(int x){
            remove(x,1);}
        public void remove(int x, int freq){
            ms.put(x,ms.get(x)-freq);
            if (ms.get(x)<=0) ms.remove(x);
            sz-=freq;
        }
        public void removeKey(int x){
            ms.remove(x);
        }
        public int get(int x){
            if (ms.containsKey(x)) return ms.get(x);
            return 0;
        }
        public Iterator<Integer> iterator(){
            return ms.keySet().iterator();
        }
        public int size(){
            return sz;
        }
        public Set<Integer> keySet(){
            return ms.keySet();
        }
        public String toString(){
            return ms.toString();
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
        void print2d(int[][] arr) {
            for (int r = 0; r < arr.length; r++) {
                for (int c = 0; c < arr[r].length; c++) {
                    String str = "" + arr[r][c];
                    while (str.length() < 4) str += " ";
                    print(str);
                }
               println();
            }
            println();
        }
        void print2d(char[][] arr) {
            for (int r = 0; r < arr.length; r++) {
                for (int c = 0; c < arr[r].length; c++) {
                    String str = "" + arr[r][c];
                    while (str.length() < 4) str += " ";
                    print(str);
                }
                println();
            }
            println();
        }
        void print2d(boolean[][] arr) {
            for (int r = 0; r < arr.length; r++) {
                for (int c = 0; c < arr[r].length; c++) {
                    String str = "" + (arr[r][c]?"1":"0");
                    while (str.length() < 4) str += " ";
                    print(str);
                }
                println();
            }
            println();
        }
        void printBin(int bin,int len){
            String ret = "";
            for (int i=0;i<len;i++){
                ret+=bin%2;
                bin/=2;
            }
            println(ret);
        }
        void close(){
            out.close();
        }
    }
}
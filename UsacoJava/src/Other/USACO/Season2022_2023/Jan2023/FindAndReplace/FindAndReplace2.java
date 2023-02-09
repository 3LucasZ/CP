package Other.USACO.Season2022_2023.Jan2023.FindAndReplace;

import java.io.*;
import java.util.*;
/*
PROB: FindAndReplace2
LANG: JAVA
*/
public class FindAndReplace2 {
    static boolean fileSubmission = false;
    static String fileName = "";
    
    static boolean debug = true;

    static long L,R;
    static int Q;

    static int[] opsChr;
    static ArrayList<Integer>[] opsStr;

    static long[][] sz;
    static int[][] head;
    static int[][] next;

    static char[] ans;

    static int cnt = 0;

    static int ops = 0;

    public static void solve() throws IOException {
        //* parse
        L = io.nextLong()-1;
        R = io.nextLong()-1;
        Q = io.nextInt();

        opsChr = new int[Q];
        opsStr = new ArrayList[Q];
        for (int i=0;i<Q;i++){
            opsChr[i]=io.next().charAt(0)-'a';
            String str = io.next();
            opsStr[i]=new ArrayList<>();
            for (int c=0;c<str.length();c++)opsStr[i].add(str.charAt(c)-'a');
        }

        //head: the new head for node after transport
        //next: the next important operation for node
        //sz: the size of the node's subtree
        sz = new long[Q+1][26];
        next = new int[Q+1][26];
        head = new int[Q+1][26];

        for (int i=0;i<26;i++){
            sz[Q][i]=1;
            next[Q][i]=Q;
            head[Q][i]=i;
        }

        for (int level=Q-1;level>=0;level--){
            for (int x=0;x<26;x++){
                if (opsChr[level]==x){
                    if(opsStr[level].size()==1){
                        sz[level][x]=sz[level+1][opsStr[level].get(0)];
                    } else {
                        next[level][x]=level;
                        head[level][x]=x;
                        for (int c : opsStr[level]){
                            sz[level][x]+=sz[level+1][c];
                            if (sz[level][x]>R) break;
                        }
                    }
                }
                else {
                    sz[level][x]=sz[level+1][x];
                    next[level][x]=next[level+1][x];
                    head[level][x]=head[level+1][x];
                }
            }
        }

        //* traverse the tree
        ans = new char[(int)(R-L+1)];
        search(0,0,0);
        for (char i:ans)io.print(i);
        io.println();
        io.println(ops);
    }
    static void search(long lb, int cur, int level){
        ops++;
        if (lb+sz[level][cur]<=L || lb>R) return;
        if (level==Q){
            ans[cnt]=(char)('a'+cur);
            cnt++;
            return;
        }
        if (next[level][cur]==Q){
            ans[cnt]=(char)('a'+head[level][cur]);
            cnt++;
            return;
        }
        for (int c : opsStr[next[level][cur]]){
            search(lb,c,next[level][cur]+1);
            lb+=sz[next[level][cur]+1][c];
            if (lb>R) break;
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
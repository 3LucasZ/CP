package Other.USACO.Season2022_2023.Jan2023.Silver;

import java.io.*;
import java.util.*;

public class FindAndReplace {
    static boolean debug = false;
    
    public static void solve(int tcs) throws IOException {
        if (debug) io.println("Case: "+tcs);
        //* parse
        String origStr = io.nextLine();
        int N =origStr.length();
        int[] orig = new int[N];
        for (int i=0;i<N;i++) {
            orig[i]=origStr.charAt(i);
            if (orig[i]>='a') orig[i]=orig[i]-'a'+26;
            else orig[i]=orig[i]-'A';
        }
        String transStr = io.nextLine();
        int[] trans = new int[N];
        for (int i=0;i<N;i++) {
            trans[i]=transStr.charAt(i);
            if (trans[i]>='a') trans[i]=trans[i]-'a'+26;
            else trans[i]=trans[i]-'A';
        }

        //grab head of each node, if node has multiple return -1
        int[] head = new int[52]; Arrays.fill(head,-1);
        HashSet<Integer> set = new HashSet<>();
        for (int i=0;i<N;i++){
            int curOrig = orig[i];
            int curTrans = trans[i];
            if (head[curOrig]==-1 || head[curOrig]==curTrans){
                head[curOrig]=curTrans;
                set.add(head[curOrig]);
            } else {
                io.println(-1);
                return;
            }
        }
        int[] in = new int[52];
        for (int i=0;i<52;i++){
            if (head[i]!=-1){
                in[head[i]]++;
            }
        }
        if (debug){
            io.println("orig:"+Arrays.toString(orig));
            io.println("trans:"+Arrays.toString(trans));
            io.println("head:"+Arrays.toString(head));
            io.println("inEdges:"+Arrays.toString(in));
        }

        //if every node has a head, then orig must equal trans, or else there exists one big cycle that can not be converted.
        if (set.size()==52 && !Arrays.equals(orig,trans)) {
            io.println(-1);
            return;
        }

        //get ans: #edges - self-loops + CCsize > 1 that are complete cycles
        int ans = 0;
        for (int i=0;i<52;i++){
            if (head[i]!=i && head[i]!=-1) ans++;
        }

        boolean[] vis = new boolean[52];
        for (int i=0;i<52;i++){
            if (vis[i]) continue;
            int most = 0;
            int cur = i;
            for (int j=0;j<60;j++){
                //stop cur==-1
                if (cur==-1) {
                    most=2;
                    break;
                }
                //update most
                most=Math.max(most,in[cur]);
                //if head[cur]=cur self edge, then stop
                if (head[cur]==cur){
                    most=2;
                    break;
                }
                cur=head[cur];
            }
            if (most==1) {
                ans++;
                for (int j=0;j<60;j++){
                    cur=head[cur];
                    vis[cur]=true;
                }
            }
        }

        //ret
        io.println(ans);
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
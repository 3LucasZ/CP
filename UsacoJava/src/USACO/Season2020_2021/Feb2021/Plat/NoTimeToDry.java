package USACO.Season2020_2021.Feb2021.Plat;

import java.io.*;
import java.util.*;
/*
PROB: NoTimeToDry
LANG: JAVA
*/
public class NoTimeToDry {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int Q;
    static int[] color;
    public static void main(String[] args) throws IOException {
        //*parse
        setup("NoTimeToDry");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        color = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) color[i] = Integer.parseInt(st.nextToken());
        /*
        if (N<=5000) solve1();
        else solve2();
        */
        solve3();
        out.close();
    }
    //*full solve: NlgN + Q algorithm
    public static void solve3() throws IOException {
        //*precomp number of segments this color contributes at each position
        int[] strokes1 = new int[N+1];
        int[] strokes2 = new int[N+1];
        //tree set of active colors waiting to get "finished" by a smaller number
        int[] last = new int[N+1];
        //keep track of #layers at every given moment
        int[] layers = new int[N+1];
        TreeSet<Integer> active = new TreeSet<>();
        for (int i=1;i<=N;i++){
            //remove larger
            while (true){
                Integer larger = active.higher(color[i]);
                if (larger==null) break;
                active.remove(larger);
                strokes2[last[larger]]++;
                layers[last[larger]+1]--;
            }
            //add self if not active
            if (!active.contains(color[i])){
                active.add(color[i]);
                strokes1[i]++;
                layers[i]++;
            }
            //update last seen of the color
            last[color[i]]=i;
        }
        //*presum switch strokes1,2,layers
        for (int i=1;i<=N;i++){
            strokes1[i]+=strokes1[i-1];
            strokes2[i]+=strokes2[i-1];
            layers[i]+=layers[i-1];
        }
        if (debug) {
            System.out.println("strokes1 presum: "+Arrays.toString(strokes1));
            System.out.println("strokes2 presum: "+Arrays.toString(strokes2));
            System.out.println("layers: "+Arrays.toString(layers));
        }
        //*allow Range Minimum Queries on the layers
        SegTree layersSeg = new SegTree(N, layers);
        //*handle queries
        for (int q=0;q<Q;q++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int ret = strokes1[r]-strokes2[l-1];
            long hidden = layersSeg.min(l,r)-1;
            ret-=hidden;
            out.println(ret);
        }
    }
    //*subtask2: Ci<=10 so CNlgN + CQ algorithm
    public static void solve2() throws IOException {
        int MAXCOLOR=10;
        //*presum of color appearances in an interval
        int[][] presum = new int[MAXCOLOR+1][N+1];
        for (int i=1;i<=N;i++){
            for (int c=1;c<=MAXCOLOR;c++){
                presum[c][i]=presum[c][i-1];
            }
            presum[color[i]][i]++;
        }
        //*precomp number of segments this color contributes at each position
        int[][] strokes1 = new int[MAXCOLOR+1][N+1];
        int[][] strokes2 = new int[MAXCOLOR+1][N+1];
        //tree set of active colors waiting to get "finished" by a smaller number
        int[] last = new int[MAXCOLOR+1];
        TreeSet<Integer> active = new TreeSet<>();
        for (int i=1;i<=N;i++){
            //remove larger
            while (true){
                Integer larger = active.higher(color[i]);
                if (larger==null) break;
                active.remove(larger);
                strokes2[larger][last[larger]]++;
            }
            //add self if not active
            if (!active.contains(color[i])){
                active.add(color[i]);
                strokes1[color[i]][i]++;
            }
            last[color[i]]=i;
        }
        //*presum switch strokes1,2
        for (int i=1;i<=N;i++){
            for (int c=1;c<=MAXCOLOR;c++) {
                strokes1[c][i]+=strokes1[c][i-1];
                strokes2[c][i]+=strokes2[c][i-1];
            }
        }
        //*handle queries
        for (int q=0;q<Q;q++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int ret = 0;
            for (int c=1;c<=MAXCOLOR;c++){
                //color segment must be present
                if (presum[c][r]-presum[c][l-1]>0){
                    //presums of strokes1, strokes2
                    ret+=strokes1[c][r]-strokes2[c][l-1];
                }
            }
            out.println(ret);
        }
    }
    //*subtask1: N,Q<=5000 so NQlgN algorithm
    public static void solve1() throws IOException {
        //*handle queries online: NlgN per query
        for (int q=0;q<Q;q++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            //*tree set trick to find number of active segments
            TreeSet<Integer> active = new TreeSet<>();
            int ret = 0;
            for (int i=l;i<=r;i++){
                //remove larger
                while (true){
                    Integer larger = active.higher(color[i]);
                    if (larger==null) break;
                    active.remove(larger);
                }
                //add self if not active
                if (!active.contains(color[i])){
                    active.add(color[i]);
                    ret++;
                }
            }
            out.println(ret);
        }
    }
    private static class SegTree {
        //1-indexed
        //range is []
        int size;
        long[] tree;
        public SegTree(int n, int[] arr){
            init(n);
            for (int i=1;i<=n;i++){
                tree[i+size-1]=arr[i];
            }
            for (int i=size-1;i>=1;i--){
                tree[i]=Math.min(tree[i*2],tree[i*2+1]);
            }
        }
        public void init(int n){
            size = 1;
            while (size < n) size *= 2;
            tree = new long[2*size+1];
        }
        long min(int a, int b) {
            a+=size-1;
            b+=size-1;
            long ret = Integer.MAX_VALUE;
            while (a<=b){
                if (a%2==1) ret=Math.min(ret,tree[a++]);
                if (b%2==0) ret=Math.min(ret,tree[b--]);
                a/=2;
                b/=2;
            }
            return ret;
        }
    }




















    static BufferedReader br;
    static PrintWriter out;
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}
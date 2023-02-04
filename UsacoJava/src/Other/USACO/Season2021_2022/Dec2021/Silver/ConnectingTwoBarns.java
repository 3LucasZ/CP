package Other.USACO.Season2021_2022.Dec2021.Silver;/*
USACO 2021 December Contest, Silver
Problem 2. Connecting Two Barns
In-Contest - 100%
So many concepts haha :)
 */

import java.io.*;
import java.util.*;

public class ConnectingTwoBarns {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int T;
    static int N;
    static int M;
    static ArrayList<Integer>[] barns;
    static TreeSet<Integer> blue;
    static TreeSet<Integer> red;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) {
            solve();
        }
        out.close();
    }
    public static void solve() throws IOException {
        //init
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        barns = new ArrayList[N+1];
        for (int i=1;i<=N;i++) {
            barns[i] = new ArrayList<>();
        }
        visited = new boolean[N+1];
        //fill graph
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            barns[u].add(v);
            barns[v].add(u);
        }
        //out.println(Arrays.toString(barns));
        //dfs from 1 and N to get the blue and red nodes
        blue = new TreeSet<>();
        red = new TreeSet<>();
        dfs(1, 1);
        //farm already connected case
        if (visited[N]) {
            out.println(0);
            return;
        }
        dfs(N, 2);
        //out.println(blue);
        //out.println(red);
        long ans = Long.MAX_VALUE;
        for (int i=1;i<=N;i++) {
            if (!visited[i]) {
                closestBlue = Integer.MAX_VALUE;
                closestRed = Integer.MAX_VALUE;
                dfs(i, 3);
                ans = Math.min(ans, (long)closestBlue*(long)closestBlue + (long)(closestRed)*(long)(closestRed));
            }
        }
        //if (ans == Integer.MAX_VALUE) ans = 1;
        for (int b : blue) {
            if (red.higher(b)-b==1 || (red.lower(b)!=null && b-red.lower(b)==1)) { ans = 1; break;}
        }
        out.println(ans);
    }
    static int closestBlue;
    static int closestRed;
    public static void dfs(int n, int color){
        if (visited[n]) return;
        if (color == 1){
            blue.add(n);
        }
        else if (color == 2){
            red.add(n);
        }
        else {
            closestBlue = Math.min(Math.abs(n-blue.lower(n)), closestBlue);
            if (blue.higher(n)!=null) closestBlue = Math.min(Math.abs(n-blue.higher(n)), closestBlue);
            if (red.lower(n)!=null) closestRed = Math.min(Math.abs(n-red.lower(n)), closestRed);
            closestRed = Math.min(Math.abs(n-red.higher(n)), closestRed);
        }
        visited[n] = true;
        for (int child : barns[n]){
            dfs(child, color);
        }
    }
//    public static int smallestGap(){
//        boolean lastColor = coloring.first().partOfOne;
//        int lastPosition = coloring.first().pos;
//        coloring.pollFirst();
//        int min = Integer.MAX_VALUE;
//        for (barn color : coloring){
//            if (color.partOfOne == lastColor) {
//
//            }
//            else {
//                min = Math.min(min, color.pos-lastPosition);
//            }
//            lastColor = color.partOfOne;
//            lastPosition = color.pos;
//        }
//        return min;
//    }
//    private static class barn {
//        int pos;
//        boolean partOfOne;
//        public barn(int p, boolean a){
//            partOfOne = a;
//            pos = p;
//        }
//        public String toString(){
//            return "["+pos+": "+partOfOne+"]";
//        }
//    }
}
/*
base case:
1
2 1
1 2

1
2 0

N=1 M=0
1
1 0

N=1000 M=0
1
1000 0
 */
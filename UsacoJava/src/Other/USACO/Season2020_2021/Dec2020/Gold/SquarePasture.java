package Other.USACO.Season2020_2021.Dec2020.Gold;

import java.io.*;
import java.util.*;
/*
PROB: SquarePasture
LANG: JAVA
*/
public class SquarePasture {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static Point[] cows;

    static int ans = 0;
    static int eq = 0;

    static int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        //input
        setup("SquarePasture");
        N = Integer.parseInt(br.readLine());
        cows = new Point[N];
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            cows[i] = new Point(x,y);
        }

        //double solve
        for (int i=0;i<2;i++){
            solve();
            //swap x y for all points
            for (Point p : cows) {
                int tmp=p.x;
                p.x=p.y;
                p.y=tmp;
            }
        }

        //ret: account for single points, empty subset, over counted h==w cases
        out.println(ans-(eq/2)+N+1);
        out.close();
    }
    public static void solve(){
        //sort points on x
        Arrays.sort(cows,Comparator.comparingLong(a->a.x));
        if (debug) System.out.println(Arrays.toString(cows));

        //search pair (a,b) bounding box - a is leftmost, b is rightmost
        for (int a=0;a<N;a++){
            TreeSet<Integer> sorted_y = new TreeSet<>();
            for (int b=a+1;b<N;b++){
                if (a<b-1) sorted_y.add(cows[b-1].y);
                //square length, lowest bottom, highest bottom
                int len = cows[b].x-cows[a].x;
                int lo = Math.max(cows[a].y,cows[b].y)-len;
                int hi = Math.min(cows[a].y,cows[b].y);
                if (lo > hi) continue;

                Integer[] y = sorted_y.toArray(new Integer[sorted_y.size()]);
                int K = sorted_y.size();

                //init square as [a.x,b.x] x [lo,lo+len]
                //square bottom cow
                int l = 0;
                while (l<K&&y[l]+1<=lo) l++;

                //square top cow
                int r = -1;
                while (r+1<K&&y[r+1]<=lo+len) r++;

                while (true){
                    ++ans;
                    int yl = Math.min(cows[a].y,cows[b].y);
                    int yr = Math.max(cows[a].y,cows[b].y);
                    if (l<=r) {
                        yl=Math.min(yl,y[l]);
                        yr = Math.max(yr,y[r]);
                    }
                    //case: width == height
                    if (yr-yl==len) eq++;

                    //bounding rectangle: [a.x, b.x] x [yl,yr]
                    int leave_bottom = (l<K?y[l]+1:MAX); //set no longer has l
                    int enter_top = (r+1<K?y[r+1]-len:MAX); //set no longer has r+1
                    int dy = Math.min(leave_bottom,enter_top); //min y for set change
                    if (dy>hi) break;
                    if (leave_bottom==dy) l++;
                    if (enter_top==dy) r++;
                }
            }
        }
    }
    private static class Point {
        int x;
        int y;
        public Point(int x, int y){
            this.x=x;
            this.y=y;
        }
        public String toString(){
            return "["+x+", "+y+"]";
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
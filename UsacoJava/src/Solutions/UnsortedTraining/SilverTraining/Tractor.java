package Solutions.UnsortedTraining.SilverTraining;/*
USACO 2013 February Contest, Silver
Problem 2. Tractor
USACO Silver Training
Glanced at solution, ashamedly :(
Binary Search + Floodfill + Connected Components
Very easy imho
 */
import java.io.*;
import java.util.StringTokenizer;

public class Tractor {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int[][] elevation;
    static boolean[][] visited;
    static int componentSize;
    static int half;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("tractor.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("tractor.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        elevation = new int[N][N];
        for (int r=0;r<N;r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c=0;c<N;c++)  {
                int d = Integer.parseInt(st.nextToken());
                elevation[r][c] = d;
            }
        }
        //logic
        half = (N*N+1)/2;
        int lo = 0;
        int hi = (int)1e6;
        while (lo<hi){
            int mid = (lo + hi)/2;
            if (works(mid)) hi=mid;
            else lo=mid+1;
        }
        //turn in answer
        out.println(lo);
        out.close();
    }
    public static boolean works(int cost){
        visited = new boolean[N][N];
        int maxComponentSize = 0;
        for (int r=0;r<N;r++){
            for (int c=0;c<N;c++){
                if (!visited[r][c]) {
                    componentSize = 0;
                    floodfill(r,c,elevation[r][c],cost);
                    maxComponentSize = Math.max(componentSize, maxComponentSize);
                }
            }
        }
        return maxComponentSize >= half;
    }
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public static void floodfill(int r, int c, int last, int cost){
        if (r<0||r>=N||c<0||c>=N||visited[r][c]||Math.abs(elevation[r][c]-last)>cost) return;
        visited[r][c] = true;
        componentSize++;
        for (int i=0;i<4;i++){
            floodfill(r+dx[i],c+dy[i], elevation[r][c],cost);
        }
    }
}

package FloodFill;

import java.io.*;
import java.util.ArrayList;

/*
USACO 2017 US Open Contest, Silver
Problem 3. Where's Bessie?
USACO Guide Silver Floodfill Normal
 */
public class WheresBessie {
    static boolean submission = false;
    //param
    static int N;
    static int[][] grid;
    //logic
    static boolean[][] visited;
    static boolean[][] used;
    static ArrayList<PCL> PCLS = new ArrayList<PCL>();
    //global
    static final int[] dr = {0,0,1,-1};
    static final int[] dc = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        //default
        PrintWriter out;
        BufferedReader f;
        //setup
        if (submission) {
            f = new BufferedReader(new FileReader("where.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("where.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(f.readLine());
        used = new boolean[N][N];
        grid = new int[N][N];
        for (int r=0;r<N;r++) {
            String str = f.readLine();
            for (int c=0;c<N;c++) {
                grid[r][c] = str.charAt(c) - 'A';
            }
        }
        //logik
        out.println(CompleteSearch());
        out.close();
        f.close();
    }
    public static int CompleteSearch() {
        for (int x1=0;x1<N;x1++) {
            for (int x2=x1;x2<N;x2++) {
                for (int y1=0;y1<N;y1++) {
                    for (int y2=y1;y2<N;y2++) {
                        if (isPCL(x1, x2, y1, y2)) PCLS.add(new PCL(x1, x2, y1, y2));
                    }
                }
            }
        }
        int answer = 0;
        for (int i=0;i<PCLS.size();i++) {
            if (PCL_is_good(i)) answer ++;
        }
        return answer;
    }
    public static boolean isPCL(int r1, int r2, int c1, int c2) {
        visited = new boolean[r2-r1+1][c2-c1+1];
        int[] colorCount = new int[26];
        int numColors = 0;
        for (int r=r1;r<=r2;r++) {
            for (int c=c1;c<=c2;c++) {
                if (!visited[r-r1][c-c1]) {
                    int color = grid[r][c];
                    if (colorCount[color] == 0) numColors++;
                    colorCount[color]++;
                    floodfill(r, c, r1, r2, c1, c2);
                }
            }
        }
        if (numColors != 2) return false;
        boolean found_one=false, found_many = false;
        for (int i=0;i<26;i++) {
            if (colorCount[i] == 1) found_one = true;
            if (colorCount[i] > 1) found_many = true;
        }
        return found_one && found_many;
    }
    public static void floodfill(int r, int c, int r1, int r2, int c1, int c2) {
        if (r < r1 || r > r2 || c < c1 || c > c2 || visited[r-r1][c-c1]){
            return;
        }
        visited[r-r1][c-c1] = true;
        for (int i=0;i<4;i++) {
            floodfill(r+dr[i], c+dc[i], r1, r2, c1, c2);
        }
    }
    public static boolean PCL_in_PCL(PCL a, PCL b) {
        return (a.x1 >= b.x1 && a.x2 <= b.x2 && a.y1 >= b.y1 && a.y2 <= b.y2);
    }
    public static boolean PCL_is_good(int pclIndex) {
        for (int i=0;i<PCLS.size();i++) {
            if (PCL_in_PCL(PCLS.get(pclIndex), PCLS.get(i)) && pclIndex != i) return false;
        }
        return true;
    }
    public static void printArr(int[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += (arr[i] + ", ");
        }
        System.out.println(str);
    }
    public static void debugPrintln(String str) {
        if (!submission) {
            System.out.println(str);
        }
    }
}
class PCL {
    int x1, x2, y1, y2;
    public PCL(int a, int b, int c, int d) {
        x1 = a; x2 = b; y1 = c; y2 = d;
    }
}

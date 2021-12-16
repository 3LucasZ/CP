package Silver.UsacoGuideSilver.PrefixSums;

import java.io.*;
import java.util.*;
/*
USACO 2014 March Contest, Silver
Problem 2. The Lazy Cow
USACO Guide Silver - More on Prefix Sums - Easy
 */
public class TheLazyCow {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static int k;
    static int[][] grid;
    static int[][] presum;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("lazy.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("lazy.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
       StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        grid = new int[n+1][n+1];
        //logic
        for (int i=1;i<=n;i++) {
            st = new StringTokenizer(f.readLine());
            for (int j=1;j<=n;j++) {
                int grass = Integer.parseInt(st.nextToken());
                grid[i][j] = grid[i][j-1] + grass;
            }
        }
//        for (int i=0;i<=n;i++) {
//            for (int j=0;j<=n;j++) {
//                out.print(grid[i][j] + " ");
//            }
//            out.println();
//        }
        int ans = 0;
        //rc is where we bash place bessie
        for (int br=1;br<=n;br++){
            for (int bc=1;bc<=n;bc++) {
                int sum = 0;
                for (int t=k;t>=0;t--){
                    int left = Math.max(0, bc - t - 1);
                    int right = Math.min(n, bc + t);
                    if (br-(k-t) >= 1) {
                        sum += grid[br-(k-t)][right] - grid[br-(k-t)][left];
                    }
                    if (t!= k && br+(k-t) <= n) {
                        sum += grid[br+(k-t)][right] - grid[br+(k-t)][left];
                    }
                    //out.println("left: " + left + " right: " + right);
                }
                ans = Math.max(sum, ans);
                //out.println(sum);
            }
        }
        //turn in answer
        out.println(ans);
        out.close();
        f.close();
    }
}

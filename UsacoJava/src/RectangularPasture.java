import java.io.*;
import java.util.*;

public class RectangularPasture {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int n;
    static int[] xs;
    static int[] ys;
    static Integer[] cows;
    //logik
    static int[][] sums;
    public static void main(String[] args) throws IOException {
        //parse input
        n = Integer.parseInt(f.readLine());
        xs = new int[n];
        ys = new int[n];
        cows = new Integer[n];
        for (int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            xs[i] = Integer.parseInt(st.nextToken());
            ys[i] = Integer.parseInt(st.nextToken());
            cows[i] = i;
        }
        //logik
        Arrays.sort(cows, (a, b) -> xs[a] - xs[b]);
        for (int i=0;i<n;i++) {
            xs[cows[i]] = i;
        }
        Arrays.sort(cows, (a, b) -> ys[a] - ys[b]);
        for (int i=0;i<n;i++) {
            ys[cows[i]] = i;
        }
        out.println(Arrays.toString(xs));
        out.println(Arrays.toString(ys));
        sums = new int[n + 1][n + 1];
        for (int j = 0; j < n; j++) {
            sums[xs[j]][ys[j]]++;
        }
        //turn in answer
        out.println();
        out.close();
        f.close();
    }
}

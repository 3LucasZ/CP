package Bronze.Usaco2019_2020_Bronze.Usaco2020_3;

import  java.io.*;
import java.util.*;
/*
USACO 2020 February Contest, Bronze
Problem 3. Swapity Swap
Speedrun
 */
public class SwapitySwap {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static int k;
    static int[] orig;
    static int[] len;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("swap.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        orig = new int[n+1];
        for (int i=1;i<=n;i++) {
            orig[i] = i;
        }
        //out.println(Arrays.toString(orig));
        for (int i=0;i<2;i++) {
            st = new StringTokenizer(f.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            for (int j=start;j<=(end+start)/2;j++) {
                int u = orig[j];
                int v = orig[end-(j-start)];
                orig[j] = v;
                orig[end-(j-start)] = u;
            }
        }
        //out.println(Arrays.toString(orig));
        len = new int[n+1];
        for (int i=1;i<=n;i++) {
            int t=orig[i];
            int cycle=1;
            while (t!=i){
                t=orig[t];
                cycle++;
            }
            len[i]=cycle;
        }
        //out.println(Arrays.toString(len));
        //logic
        for (int i=1;i<=n;i++) {
            int t=i;
            for (int c=0;c<k%len[i];c++) {
                t=orig[t];
            }
            out.println(t);
        }
        out.close();
        f.close();
    }
}

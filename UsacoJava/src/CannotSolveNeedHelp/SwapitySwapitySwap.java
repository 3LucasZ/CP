package CannotSolveNeedHelp;

import java.io.*;
import java.util.*;
public class SwapitySwapitySwap {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int N;
    static int M;
    static int K;
    static int[] orig;
    static int[] transpose;
    //logik
    static int[] next;
    static int[] componentSize;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("swap.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));
        } else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        orig = new int[N];
        transpose = new int[N];
        for (int i=0;i<N;i++) {
            orig[i] = i;
            transpose[i] = i;
        }
        //transposing - works!
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(f.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            for (int m=0;m<=(v-u)/2;m++) {
                int tmp = transpose[u+m];
                transpose[u+m] = transpose[v-m];
                transpose[v-m] = tmp;
            }
        }
        out.println(Arrays.toString(orig));
        out.println(Arrays.toString(transpose));
        //nexts - works!
        next = new int[N];
        for (int i=0;i<N;i++) {
            next[transpose[i]] = i;
        }
        out.println(Arrays.toString(next));
        //component sizes - works!
        componentSize = new int[N];
        for (int i=0;i<N;i++) {
            int size = 1;
            if (componentSize[i] == 0) {
                int curr = next[i];
                while (curr != i) {
                    size ++;
                    curr = next[curr];
                }
                curr = next[i];
                componentSize[i] = size;
                while (curr != i) {
                    componentSize[curr] = size;
                    curr = next[curr];
                }
            }
        }
        out.println(Arrays.toString(componentSize));
        //turn in answer
        for (int i=0;i<N;i++) {
            int moves = K % componentSize[i];
            int curr = i;
            for (int j=0;j<moves;j++) {
                curr = next[curr];
            }
            out.println(curr+1);
        }
        out.close();
        f.close();
    }
}

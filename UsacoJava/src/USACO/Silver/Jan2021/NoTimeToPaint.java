package USACO.Silver.Jan2021;

import java.io.*;
import java.util.*;
/*
USACO 2021 January Contest, Silver
Problem 2. No Time to Paint
*/

public class NoTimeToPaint {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int Q;
    static int[] fence;
    //logik
    static int[] fPreSum;
    static int[] bPreSum;
    static boolean[] found;
    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        fence = new int[N];
        fPreSum = new int[N+1];
        bPreSum = new int[N+1];
        String str = f.readLine();
        for (int i=0;i<N;i++) {
            fence[i] = str.charAt(i) - 'A';
        }
        //logic
        //create fPreSum
        found = new boolean[26];
        fPreSum[0] = 0;
        fPreSum[1] = 1;
        found[fence[0]] = true;
        for (int i=1;i<N;i++) {
            fPreSum[i+1] = fPreSum[i];
            if (fence[i] < fence[i-1]) for (int c=fence[i]+1;c<26;c++) found[c] = false;
            if (!found[fence[i]]) {
                found[fence[i]] = true;
                fPreSum[i+1]++;
            }
        }
        //create bPreSum
        found = new boolean[26];
        bPreSum[N] = 0;
        bPreSum[N-1] = 1;
        found[fence[N-1]] = true;
        for (int i=N-2;i>=0;i--) {
            bPreSum[i] = bPreSum[i+1];
            if (fence[i] < fence[i+1]) for (int c=fence[i]+1;c<26;c++) found[c] = false;
            if (!found[fence[i]]) {
                found[fence[i]] = true;
                bPreSum[i]++;
            }
        }
        for (int i=0;i<Q;i++) {
            st = new StringTokenizer(f.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken());
            out.println(fPreSum[u] + bPreSum[v]);
        }
        //out.println(Arrays.toString(fPreSum));
        //out.println(Arrays.toString(bPreSum));
        out.close();
        f.close();
    }
}

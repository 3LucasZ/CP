package CannotSolveNeedHelp;

import java.io.*;
import java.util.*;

public class DiamondCollector {
    //io
    static BufferedReader f;
    static PrintWriter out;
    static boolean submission = false;
    //param
    static int n;
    static int k;
    static int[] sizes;
    //to turn in
    static int largest1 = 0;
    static int largest2 = 0;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("diamond.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        sizes = new int[n];
        for (int i=0;i<n;i++) {
            sizes[i] = Integer.parseInt(f.readLine());
        }
        Arrays.sort(sizes);
        //logic
        int pointer1 = 0;
        int pointer2 = 0;
        int count = 0;
        while (pointer2 < n) {
            if (sizes[pointer2] - sizes[pointer1] <= k) {
                count ++;
                pointer2 ++;
            }
            else {
                pointer1 ++;
                largest2 = largest1;
                largest1 = Math.max(largest1, count);
                count = 0;
            }
        }
        //turn in answer
        out.println(largest1 + largest2);
        out.close();
        f.close();
    }
}

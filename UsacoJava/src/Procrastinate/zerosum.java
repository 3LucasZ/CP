package Procrastinate;

import java.io.*;
import java.util.*;
/*
LANG: JAVA
TASK: zerosum
 */
public class zerosum {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("zerosum.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        int[] temp = new int[2 * N];
        for (int i = 0; i < 2 * N; i += 2) {
            temp[i] = i;
            //temp[i+1]=
        }
        //logic
        for (int i = 0; i < Math.pow(3, N - 1); i++) {

        }
        //turn in answer
        out.println();
        out.close();
    }
}
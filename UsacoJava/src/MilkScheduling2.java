/*
USACO 2013 December Contest, Silver
Problem 1. Milk Scheduling
 */
import java.io.*;
import java.util.*;
public class MilkScheduling2 {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        //logic
        //turn in answer
        out.println();
        out.close();
    }
}

package USACO.Silver.Training;

import java.io.*;
import java.util.*;
/*
USACO 2018 US Open Contest, Silver
Problem 1. Out of Sorts
USACO Silver Training
Thoughts: Very wacky - read the solution and came back to it a few weeks later and it works
Patterns, problem-solving heavy, trial and error, "proof by guess"
 */
public class OutOfSorts {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static Integer arr[];
    static Integer ref[];
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("sort.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        arr = new Integer[N];
        ref = new Integer[N];
        for (int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(br.readLine());
            ref[i] = i;
        }
        //logic
        //coordinate compression
        Arrays.sort(ref, (a,b)->arr[a]-arr[b]);
        //out.println(Arrays.toString(arr));
        for (int i=0;i<N;i++) {
            arr[ref[i]]=i+1;
        }
        //out.println(Arrays.toString(arr));
        int max_distance = 0;
        for (int i=0;i<N;i++) {
            int distance = i-arr[i]+1;
            max_distance = Math.max(distance, max_distance);
        }
        //turn in answer
        out.println(max_distance+1);
        out.close();
    }
}

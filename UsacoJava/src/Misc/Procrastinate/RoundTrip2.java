package Misc.Procrastinate;

import java.io.*;
import java.util.*;

/*
CSES Problem Set
Round Trip II
USACO Gold Guide - Topological Sort - Easy
Using Topological sort to find a cycle and its path
INCOMPLETE CUZ LAZY
 */
public class RoundTrip2 {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static int M;
    //top sort
    static ArrayList<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1];for (int i=1;i<=N;i++) adjList[i] = new ArrayList<>();
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList[u].add(v);
        }
        //logic
        //turn in answer
        out.println();
        out.close();
    }
}

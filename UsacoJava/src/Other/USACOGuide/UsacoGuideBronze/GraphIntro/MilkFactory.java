package Other.USACOGuide.UsacoGuideBronze.GraphIntro;

import java.io.*;
import java.util.*;

/*
METADATA
Date: 11/3/21 5:47PM
Problem: USACO 2019 US Open Bronze
Difficulty: Hard
From: USACO Guide
Concept: Graphs
*/
public class MilkFactory {
    //default
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static int[][] entries;
    //yum
    //initialize grass graph (represented as adjacent lists)
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>(n);

    public static void main(String[] args) throws IOException {
        //setup
        if (submission) {
            f = new BufferedReader(new FileReader("factory.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("factory.out")));
            n = Integer.parseInt(f.readLine());
            entries = new int[n][2];
            for (int i=0;i<n-1;i++) {
                StringTokenizer st = new StringTokenizer(f.readLine());
                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());
                entries[i] = new int[]{node1, node2};
            }
        }
        else {
            n = 3;
            entries = new int[][]{{1, 2}, {3, 2}};
        }
        System.out.println(n);
        print2DArray(entries);

        //logic
        for (int i=0;i<n+1;i++) {
            graph.add(new ArrayList<>());
        }

        //fill the graph
        for (int[] entry : entries) {
            int node1 = entry[0];
            int node2 = entry[1];
            graph.get(node2).add(node1);
        }
        System.out.println(graph);

        int answer = -1;
        //brute force each node... can every node get to this node?
        for (int i=1;i<n+1;i++) {
            if (traverse(i) == n) {
                answer = i;
            }
        }

        //print
        if (submission) {
            out.println(answer);
            f.close();
            out.close();
        }
        else {
            System.out.println(answer);
        }
    }
    public static int traverse(int starting) {
        int canReach = 1;
        for (int node : graph.get(starting)) {
            canReach += traverse(node);
        }
        System.out.println(canReach);
        return canReach;
    }
    public static void print2DArray(int[][] arr) {
        for (int i=0;i<arr.length;i++) {
            print1DArray(arr[i]);
        }
    }
    public static void print1DArray(int[] arr) {
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += (arr[i] + ", ");
        }
        System.out.println(str);
    }

}


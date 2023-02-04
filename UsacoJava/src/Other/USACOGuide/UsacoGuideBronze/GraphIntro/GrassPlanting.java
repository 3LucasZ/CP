package Other.USACOGuide.UsacoGuideBronze.GraphIntro;

import java.io.*;
import java.util.*;

/*
METADATA
Date: 11/3/21 5:00PM
Problem: USACO 2019 January Contest Silver
Difficulty: Normal
From: USACO Guide
Concept: Graphs
*/
public class GrassPlanting {
    //default
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static int[][] entries;


    public static void main(String[] args) throws IOException {
        //setup
        if (submission) {
            f = new BufferedReader(new FileReader("planting.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));
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
            n = 4;
            entries = new int[][]{{1, 2}, {4, 3}, {2, 3}};
        }
        //logic

        //initialize grass graph (represented as adjacent lists)
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(n + 1);
        for (int i=0;i<n+1;i++) {
            graph.add(new ArrayList<>());
        }

        //fill the graph
        for (int[] entry : entries) {
            int node1 = entry[0];
            int node2 = entry[1];
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }

        //which node has the most connections?
        int max = 0;
        for (ArrayList<Integer> nodeConnections : graph) {
            if (nodeConnections.size() > max) {
                max = nodeConnections.size();
            }
        }

        //print
        if (submission) {
            out.println(max+1);
            f.close();
            out.close();
        }
        else {
            System.out.println(max+1);
        }
    }
}

package USACOGuide.UsacoGuideGold.DSU;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Road Construction
USACO Gold Guide - Disjoin Set Union - Easy
Thoughts:
2nd problem solved using DSU, trying to get experience and fun! :)
100% first try
addition to DSU: keep size of each representative node tree, largest component, number of components heuristically
 */

public class RoadConstruction {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;

    //param
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        DSU cities = new DSU(N);
        //logic
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            cities.join(u, v);
            out.println(cities.components+" "+cities.largestSize);
        }
        out.close();
    }

    private static class DSU {
        int[] parent;
        int[] size;
        int largestSize;
        int components;
        public DSU(int nodes){
            parent = new int[nodes+1];
            size = new int[nodes+1];
            largestSize=1;
            components=nodes;
            for (int i=1;i<=nodes;i++)parent[i]=i;
            for (int i=1;i<=nodes;i++)size[i]=1;
        }
        public int getRepresentative(int node){
            if (parent[node]==node) return node;
            parent[node]=getRepresentative(parent[node]);
            return parent[node];
        }
        public void join(int u, int v){
            if (connected(u,v)) return;
            int uRep = getRepresentative(u);
            int vRep = getRepresentative(v);
            if (Math.random()%2==0){
                parent[uRep]=vRep;
                size[vRep]+=size[uRep];
                largestSize=Math.max(largestSize,size[vRep]);
            }
            else {
                parent[vRep]=uRep;
                size[uRep]+=size[vRep];
                largestSize=Math.max(largestSize,size[uRep]);
            }
            components--;
        }
        public boolean connected(int u, int v){
            return (getRepresentative(u)==getRepresentative(v));
        }
    }
}

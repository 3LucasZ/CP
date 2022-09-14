package USACO.Gold.Dec2017;

import java.io.*;
import java.util.*;
/*
USACO 2017 December Contest, Gold
Problem 3. Haybale Feast
USACO Gold Guide - Sliding Window - Easy
Thoughts:
2 pointer, keep track of spicyness via multiset and flavor via sum
easy peasy
 */

public class HaybaleFeast {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static long M;
    static int[] spice;
    static int[] flavor;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("hayfeast.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("hayfeast.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        flavor = new int[N+1];
        spice = new int[N+1];
        for (int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            flavor[i] = Integer.parseInt(st.nextToken());
            spice[i] = Integer.parseInt(st.nextToken());
        }
        //logic
        int l=1;
        int r=1;
        long flavorSum = 0;
        Multiset spicyTracker = new Multiset();
        int minSpicy = Integer.MAX_VALUE;
        for (;l<=N;l++){
            flavorSum-=flavor[l-1];
            spicyTracker.remove(spice[l-1]);
            while (r<=N&&flavorSum<M) {
                flavorSum+=flavor[r];
                spicyTracker.add(spice[r]);
                r++;
            }
            if (r>N && flavorSum<M) break;
            if (!spicyTracker.ms.isEmpty()) minSpicy = Math.min(minSpicy, spicyTracker.ms.lastKey());
            if (!submission){
                System.out.println(l+" "+r);
                System.out.println(flavorSum);
                System.out.println(spicyTracker.ms);
                System.out.println(minSpicy);
            }
        }
        //turn in answer
        out.println(minSpicy);
        out.close();
    }
    private static class Multiset {
        TreeMap<Integer, Integer> ms = new TreeMap<>();
        public void add(int n){
            if (!ms.containsKey(n)) ms.put(n,0);
            ms.put(n,ms.get(n)+1);
        }
        public void remove(int n){
            if (!ms.containsKey(n)) return;
            ms.put(n,ms.get(n)-1);
            if (ms.get(n)==0) ms.remove(n);
        }
    }
}

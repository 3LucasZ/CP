package Solutions.USACO.Season2020_2021.Jan2021.Silver;

import java.io.*;
import java.util.*;
/*
USACO 2021 January Contest, Silver
Problem 1. Dance Mooves
Concepts: One to one correspondence, cycles, sets
 */

public class DanceMooves {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int N;
    static int K;
    static int[] transpose;
    static Set<Integer>[] canReach;
    static int[] cycleId;
    public static void main(String[] args) throws IOException {
        //parse input and setup
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        transpose = new int[N+1];
        canReach = new HashSet[N+1];
        cycleId = new int[N+1];
        for (int i=1;i<=N;i++) {
            canReach[i] = new HashSet<>();
            canReach[i].add(i);
        }
        for (int i=0;i<=N;i++) {
            transpose[i]=i;
        }
        for (int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            //update canreach
            canReach[transpose[u]].add(v);
            canReach[transpose[v]].add(u);
            int temp = transpose[u];
            //switch u and v
            transpose[u]=transpose[v];
            transpose[v]=temp;
        }
        //System.out.println(Arrays.toString(canReach));
        //System.out.println(Arrays.toString(transpose));
        //find cycles, assign them id
        for (int i=1;i<=N;i++) {
            int id = 0;
            Set<Integer> union = new HashSet<>();
            ArrayList<Integer> inCycle = new ArrayList<>();
            if (cycleId[i]==0){
                int curr = i;
                while (true){
                    for (int reach : canReach[curr]) {
                        union.add(reach);
                    }
                    inCycle.add(curr);
                    id++;
                    curr=transpose[curr];
                    if(curr==i) break;
                }
                for (int c : inCycle) {
                    cycleId[c]=union.size();
                }
            }
        }
        //output answer
        for (int i=1;i<=N;i++) {
            out.println(cycleId[i]);
        }
        out.close();
    }
}

package Other.USACOGuide.UsacoGuideSilver.SilverGreedy;

import java.io.*;
import java.util.*;

/*
CSES Problem Set
Towers
USACO Silver Guide - Greedy + Sorting - Easy
 */
public class Towers {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static MultiSet towers = new MultiSet();

    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ans = 0;
        for (int i=0;i<N;i++) {
            int size = Integer.parseInt(st.nextToken());
            Integer base = towers.ms.higherKey(size);
            if (base != null) {
                ans--;
                towers.remove(base);
            }
            ans++;
            towers.add(size);
        }
        out.println(ans);
        out.close();
    }
    private static class MultiSet {
        TreeMap<Integer, Integer> ms = new TreeMap<>();
        public MultiSet(){}
        public void add(int e){
            if (!ms.containsKey(e)) {
                ms.put(e,1);
            }
            else {
                ms.put(e, ms.get(e) + 1);
            }
        }
        public void remove(int e){
            if (ms.get(e)==1) ms.remove(e);
            else ms.put(e,ms.get(e)-1);
        }
        public String toString(){
            return ms.toString();
        }
    }
}

package USACOGuide.UsacoGuideSilver.MoreOpsOnSets;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Concert Tickets
USACO Silver Guide - Ops on Sets - Easy
9/11 because of TLE
 */
public class ConcertTickets {
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String args[]) throws IOException {
        MultiSet ms = new MultiSet();
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        for(int i=0;i<N;i++) {
            ms.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(f.readLine());
        for (int i=0;i<M;i++) {
            out.println(ms.takeTicket(Integer.parseInt(st.nextToken())));
        }
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
        public int takeTicket(int maxPrice){
            Integer ret = ms.floorKey(maxPrice);
            if (ret == null) {
                return -1;
            }
            remove(ret);
            return ret;
        }
        public String toString(){
            return ms.toString();
        }
    }
}

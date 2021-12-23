/*
USACO 2018 February Contest, Silver
Problem 3. Teleportation
USACO Silver Practice
 */

import java.io.*;
import java.util.*;

public class Teleportation {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("teleport.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        N = Integer.parseInt(br.readLine());
        SlopeMap slope = new SlopeMap();
        //total without portal
        long tot = 0;
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            //from
            int u = Integer.parseInt(st.nextToken());
            //to
            int v = Integer.parseInt(st.nextToken());
            tot+=(long)Math.abs(v-u);
            //smaller
            int a = Math.min(u,v);
            //bigger
            int b = Math.max(u,v);
            //0 advantage cases
            if (u>=0 && v>=0 && (u >= v || b-a <= a)) continue;
            if (u<=0 && v<=0 && (u <= v || a-b >= b)) continue;
            //case 1: u,v are on the same side
            if (u*v >= 0) {
                //to the right
                if (u>=0){
                    slope.change(2*u,1);
                    slope.change(v,-2);
                    slope.change(2*v-2*u,1);
                }
                //to the left
                else if (v<=0){
                    slope.change(2*v-2*u, 1);
                    slope.change(v,-2);
                    slope.change(2*u,1);
                }
            }
            //case 2: u,v are on different sides
            else {
                slope.change(2*v,1);
                slope.change(v,-2);
                slope.change(0,1);
            }
        }
        //logic
        //out.println(slope);
        //no advantage case:
        if (slope.map.size()==0) {
            out.println(tot);
            out.close();
            return;
        }
        long runSlope = 0;
        long prevCP = slope.map.firstKey();
        long runSum = 0;
        long ans = 0;
        for (Integer slopePoint : slope.map.keySet()) {
            runSum+=(runSlope*((long)slopePoint-prevCP));
            runSlope+=(long)slope.map.get(slopePoint);
            prevCP=(long)slopePoint;
            //out.println(runSum);
            ans = Math.max(ans, runSum);
        }
        //out.println(ans);
        //turn in answer
        out.println(tot-ans);
        out.close();
    }
    private static class SlopeMap {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        public SlopeMap(){};
        public void change(int key, int update){
            if (!map.containsKey(key)){
                map.put(key, 0);
            }
            map.put(key, map.get(key)+update);
        }
        public String toString(){
            return map.toString();
        }
    }
}

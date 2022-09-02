package USACO.Silver.UsacoGuideSilver.MoreOpsOnSets;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Traffic Lights
USACO Guide Silver - Ops on Sets - Normal
 */
public class TrafficLights {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int x;
    static int n;
    static TreeSet<Integer> lights = new TreeSet<>();
    static MultiSet gaps = new MultiSet();
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        lights.add(0);lights.add(x);gaps.add(x);
        for (int i=0;i<n;i++) {
            int p = Integer.parseInt(st.nextToken());
            gaps.remove(lights.higher(p)-lights.lower(p));
            lights.add(p);
            gaps.add(p-lights.lower(p));
            gaps.add(lights.higher(p)-p);
            out.print(gaps.ms.lastKey() + " ");
        }
        out.close();
    }
    public static class MultiSet {
        TreeMap<Integer, Integer> ms = new TreeMap<>();
        public MultiSet(){}
        public void add(int e){
            if (ms.containsKey(e)){
                ms.put(e,ms.get(e)+1);
            }
            else {
                ms.put(e,1);
            }
        }
        public void remove(int e){
            if (ms.get(e)==1) {
                ms.remove(e);
            }
            else {
                ms.put(e,ms.get(e)-1);
            }
        }
        public String toString(){
            return ms.toString();
        }
    }
}

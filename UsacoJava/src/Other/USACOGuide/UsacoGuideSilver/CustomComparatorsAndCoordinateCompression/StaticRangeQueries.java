package Other.USACOGuide.UsacoGuideSilver.CustomComparatorsAndCoordinateCompression;

import java.io.*;
import java.util.*;

/*
Code Forces
Static Range Queries
USACO Silver Guide - Custom Comparators and Coordinate Compression - Hard
 */
public class StaticRangeQueries {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static int N;
    static int Q;
    static TreeMap<Integer, Integer> UpdateCheckpoints = new TreeMap<>();
    static TreeMap<Integer, Integer> ValueCheckpoints = new TreeMap<>();
    static TreeMap<Integer, Long> RangeQuery = new TreeMap<>();
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int amt = Integer.parseInt(st.nextToken());
            if (!UpdateCheckpoints.containsKey(u)) UpdateCheckpoints.put(u, 0);
            if (!UpdateCheckpoints.containsKey(v)) UpdateCheckpoints.put(v, 0);
            UpdateCheckpoints.put(u, UpdateCheckpoints.get(u)+amt);
            UpdateCheckpoints.put(v, UpdateCheckpoints.get(v)-amt);
        }
        //'base case'
        RangeQuery.put(-1,0L);
        ValueCheckpoints.put(-1,0);
        RangeQuery.put((int) 1e9, 0L);
        int lastValue = 0;
        int lastCheckpoint = -1;
        for (int Checkpoint : UpdateCheckpoints.keySet()) {
            RangeQuery.put(Checkpoint, RangeQuery.get(lastCheckpoint) + (long) (Checkpoint-lastCheckpoint)*lastValue);
            lastValue += UpdateCheckpoints.get(Checkpoint);
            lastCheckpoint = Checkpoint;
            ValueCheckpoints.put(Checkpoint, lastValue);
        }
//        //update checkpoints perfect
//        out.println(UpdateCheckpoints);
//        //value checkpoints perfect
//        out.println(ValueCheckpoints);
//        //range query perfect
//        out.println(RangeQuery);
        for (int i=0;i<Q;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int u_floor = ValueCheckpoints.floorKey(u);
            int v_floor = ValueCheckpoints.floorKey(v);
            long preSumStart = RangeQuery.get(u_floor) + (long)(u-u_floor+1)*ValueCheckpoints.get(u_floor);
            long preSumEnd = RangeQuery.get(v_floor) + (long)(v-v_floor+1)*ValueCheckpoints.get(v_floor);
            long ans = preSumEnd - preSumStart;
//            out.println(preSumStart);
//            out.println(preSumEnd);
            out.println(ans);
//            out.println();
        }
        out.close();
    }
}

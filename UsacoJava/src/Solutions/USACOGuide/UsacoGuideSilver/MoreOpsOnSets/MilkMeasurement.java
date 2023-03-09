package Solutions.USACOGuide.UsacoGuideSilver.MoreOpsOnSets;

import java.io.*;
import java.util.*;
/*
USACO 2017 December Contest, Silver
Problem 2. Milk Measurement
USACO Silver Guide - More operations on sorted sets - Normal
Kinda copy cat :( Sad
Concepts: Custom comparator on TreeMap (although I didn't do this), using multiple Maps that correspond to each other
 */
public class MilkMeasurement {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static Entry[] entries;
    //hash bc we don't care about id order
    static HashMap<Integer, Integer> ids = new HashMap<>();
    //tree bc we care about the highest milk
    static TreeMap<Integer, Integer> values = new TreeMap<>();
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("measurement.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        entries = new Entry[N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int id = Integer.parseInt(st.nextToken());
            int delta = Integer.parseInt(st.nextToken());
            entries[i] = new Entry(day, id, delta);
            ids.put(id, 0);
        }
        //logic
        Arrays.sort(entries, (a,b)->a.day-b.day);
        //out.println(Arrays.toString(entries));
       values.put(0, N+1);
        int ans = 0;
        for (int i=0;i<N;i++) {
            int change = ids.get(entries[i].cow);
            boolean wasTop = change == values.lastKey();
            int prevCnt = values.get(change);
            values.put(change, values.get(change)-1);
            if (values.get(change)==0){
                values.remove(change);
            }
            change += entries[i].delta;
            ids.put(entries[i].cow, change);
            values.put(change, values.getOrDefault(change,0)+1);

            boolean isTop = change == values.lastKey();
            int curCnt = values.get(change);

            if (wasTop){
                if (isTop && curCnt == prevCnt && curCnt == 1){
                    continue;
                }
                //if was the highest and now its not or there are now multiple highest cows
                ans++;
            }
            else if (isTop){
                //it wasnt the highest but now it is
                ans++;
            }
        }
        //turn in answer
        out.println(ans);
        out.close();
    }
    private static class Entry {
        int day;
        int cow;
        int delta;
        public Entry(int d, int c, int elt) {
            day=d;cow=c;delta=elt;
        }
        public String toString(){
            return "\n["+day+", "+cow+", "+delta+"]";
        }
    }
}

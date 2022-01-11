package Silver.Training;/*
USACO 2013 January Contest, Silver
Problem 1. Painting the Fence
USACO Silver Training
Concepts: presum, POINT vs BLOCK, POINTS vs AREA
*/
import java.io.*;
import java.util.*;
public class PaintingTheFence {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int K;
    static TreeMap<Integer, Integer> presum = new TreeMap<>();
    static TreeMap<Integer, Integer> coats = new TreeMap<>();
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("paint.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("paint.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        //construct presum
        int position = 0;
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            boolean left = st.nextToken().charAt(0) == 'L';
            //right
            if (!left) {
                update(presum, position, 1);
                position += len;
                update(presum, position, -1);
            }
            //left
            else {
                update(presum, position, -1);
                position -= len;
                update(presum, position, 1);
            }
        }
        //out.println(presum);
        //assemble presum
        int paint = 0;
        for (Map.Entry<Integer, Integer> entry : presum.entrySet()){
            paint += entry.getValue();
            coats.put(entry.getKey(), paint);
        }
        //out.println(coats);
        //sum of lengths of intervals between checkpoints that have >= K coats
        int sum = 0;
        boolean prev = false;
        boolean curr = false;
        int l = 0;
        int r = 0;
        for (Map.Entry<Integer, Integer> entry : coats.entrySet()){
            curr = entry.getValue() >= K;
            if (curr && !prev) l=entry.getKey();
            if (!curr && prev) {
                r=entry.getKey();
                sum += r-l;
            }
            prev = curr;
        }
        //turn in answer
        if (K==0) out.println((int)2e9);
        else out.println(sum);
        out.close();
    }
    public static void update(Map<Integer, Integer> map, int key, int value){
        if (!map.containsKey(key)) map.put(key, 0);
        map.put(key, map.get(key)+value);
    }
}

import java.io.*;
import java.util.*;

/*
PROB: humble
LANG: JAVA
 */
public class humble {
    static boolean submission = true;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        setup("humble");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] ps = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<K;i++){
            ps[i] = Integer.parseInt(st.nextToken());
        }

        HashSet<Long> seen = new HashSet<>();
        PriorityQueue<Long> pq = new PriorityQueue<>();

        pq.add(1L);
        for (int i=0;i<N;i++){
            long next = pq.poll();
            if (debug) System.out.println(next);
            for (int p : ps){
                if (seen.contains(next*p));
                else {
                    seen.add(next*p);
                    pq.add(next*p);
                }
            }
        }

        out.println(pq.poll());
        out.close();
    }




















    static BufferedReader br;
    static PrintWriter out;
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}

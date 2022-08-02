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
        long[] ps = new long[K];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<K;i++)ps[i] = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> min = new PriorityQueue<>(Comparator.comparingLong(a->a));
        PriorityQueue<Long> max = new PriorityQueue<>(Comparator.comparingLong(a->-a));
        min.add(1L);
        max.add(1L);

        for (int i=0;i<N;i++) {
            long next = min.peek();
            min.remove(next);
            max.remove(next);
            for (long p : ps){
                long add = next*p;
                min.add(add);
                max.add(add);
                if (min.size()>N){

                }
            }
        }



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

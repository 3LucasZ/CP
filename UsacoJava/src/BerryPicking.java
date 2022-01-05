import java.io.*;
import java.util.*;
/*
USACO 2020 January Contest, Silver
Problem 1. Berry Picking
USACO Silver Guide - Greedy + Sorting - Normal
 */
public class BerryPicking {
    //io
    static boolean submission = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int K;
    static int berries[];
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("berries.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("berries.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        //trees
        N = Integer.parseInt(st.nextToken());
        berries = new int[N];
        //baskets
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            berries[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(berries);
        out.println(Arrays.toString(berries));
//        for (int i=1;i<=5;i++) {
//            tryMin(i);
//        }
        //logic
        for (int i=1;i<=1000;i++) {
            tryMin(i);
        }
        //turn in answer
        out.println(ans);
        out.close();
    }
    public static void tryMin(int min){
        ArrayList<Integer> remainder = new ArrayList<>();
        int baskets_left = K;
        int tree = N-1;
        int bessie_berries = 0;
        while (tree > 0) {
            int baskets = Math.min(baskets_left,berries[tree]/min);
            out.println("On tree: "+tree+", will take " + baskets + " baskets.");
            if (baskets == 0) return;

            int rem = berries[tree] - baskets*min;
            int xtra = rem%baskets;
            for (int i=0;i<baskets-xtra;i++) {
                remainder.add(rem/baskets);
            }
            for (int i=0;i<xtra;i++) {
                remainder.add(rem/baskets + 1);
            }
            baskets_left -= baskets;
            tree--;
            out.println("Current remainders: "+remainder);
            if (baskets_left == 0) break;
            if (tree < 0) return;
        }
        bessie_berries += min * (K/2);
        Collections.sort(remainder);
        for (int i=0;i<remainder.size()-K/2;i++) {
            bessie_berries += remainder.get(i);
        }
        out.println("for min: "+min+", bessie gets: "+bessie_berries);
        ans = Math.max(ans, bessie_berries);
    }
}

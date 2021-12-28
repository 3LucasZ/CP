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
        N = Integer.parseInt(st.nextToken());
        berries = new int[N];
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            berries[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(berries);
        out.println(Arrays.toString(berries));
        //tryMin(1);
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
        int left = K;
        int i = N-1;
        int cnt = 0;
        while (left > 0) {
            int takes = berries[i]/min;
            if (takes > left) {
                takes = left;
            }
            remainder.add(berries[i]-min*takes);
            left -= takes;
            i--;
            if (i < 0) {
                out.println("ILLEGAL");
                return;
            }
        }
        cnt += min * (K/2);
        if (left == 0) {
            Collections.sort(remainder);
            for (int j=0;j<remainder.size()-(K/2);j++) {
                cnt += remainder.get(j);
            }
        }
        out.println(min);
        out.println(remainder);
        out.println(cnt);
        out.println();
        ans = Math.max(ans, cnt);
    }
}

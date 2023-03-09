package Solutions.Codeforces.Edu138;

import java.io.*;
import java.util.*;
/*
PROB: NumberGame
LANG: JAVA
*/
public class NumberGame {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        //TCS
        setup("NumberGame");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            solve();
        }
        out.close();
    }

    static int N;
    static int[] A;
    public static void solve() throws IOException{
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++){
            A[i]=Integer.parseInt(st.nextToken());
        }
        for (int K=N;K>=0;K--){
            if (tryK(K)) {
                out.println(K);
                return;
            }
        }
    }
    public static boolean tryK(int K){
        Multiset board = new Multiset();
        for (int i=0;i<N;i++) board.add(A[i]);
        for (int i=1;i<=K;i++){
            //A's turn: pick highest
            Integer choose = board.ms.floorKey(K-i+1);
            if (choose==null) return false;
            board.rem(choose);
            //B's turn: pick lowest
            if (!board.ms.isEmpty()) board.rem(board.ms.firstKey());
        }
        return true;
    }
    private static class Multiset {
        TreeMap<Integer, Integer> ms = new TreeMap<>();
        public void add(int x){
            if (!ms.containsKey(x))ms.put(x,0);
            ms.put(x,ms.get(x)+1);
        }
        public void rem(int x){
            ms.put(x,ms.get(x)-1);
            if (ms.get(x)==0) ms.remove(x);
        }
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
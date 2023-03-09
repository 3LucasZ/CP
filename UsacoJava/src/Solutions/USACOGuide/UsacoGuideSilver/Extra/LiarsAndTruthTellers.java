package Solutions.USACOGuide.UsacoGuideSilver.Extra;/*
USACO 2013 January Contest, Bronze
Problem 3. Liars and Truth Tellers
USACO Guide - Silver - Additional Practice
Concepts: Biparte Graph
***no cheats :)
 */
import java.io.*;
import java.util.*;
public class LiarsAndTruthTellers {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int M;
    static Cow[] cows;
    static boolean bad = false;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("truth.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("truth.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cows = new Cow[N+1];
        for (int i=1;i<=N;i++) {
            cows[i] = new Cow();
        }
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            boolean same = st.nextToken().equals("L")?false:true;
            bad = false;
            for (int j=1;j<=N;j++) {
                cows[j].visited = false;
                cows[j].color = 0;
            }
            cows[u].add(v, same);
            cows[v].add(u, same);
            cows[u].color = 1;
            cows[u].dfs();
            if (bad) {
                out.println(i);
                break;
            }
        }
        if (!bad) out.println(M);
        out.close();
    }
    private static class Cow {
        ArrayList<Cow> same = new ArrayList<>();
        ArrayList<Cow> diff = new ArrayList<>();
        boolean visited = false;
        int color = 0;
        public Cow(){}
        public void add(int v, boolean s) {
            if (s) same.add(cows[v]);
            else diff.add(cows[v]);
        }
        public void dfs(){
            visited = true;
            for (Cow cow : same) {
                if (cow.color != 0 && cow.color != color) bad = true;
                cow.color = color;
                if (!cow.visited) cow.dfs();
            }
            for (Cow cow : diff){
                if (cow.color != 0 && cow.color == color) bad = true;
                cow.color = 3-color;
                if (!cow.visited) cow.dfs();
            }
        }
    }
}

package Solutions.USACO.Season2020_2021.Open2021.Silver;

import java.io.*;
import java.util.*;
/*
USACO 2021 US Open, Silver
Problem 2. Do You Know Your ABCs?
USACO Silver Training - Ad hoc
Thoughts:
CF like
pigeon hole
clever trial and error with gen confirmation
A 0
A+B B
A+C C
A+B+C B+C
with at least 4 given we can determine all possible A,B,C and put this in expanded array
easy check to see for all A,B,C combo if they work
O(T*(N^8))
 */
public class DoYouKnowYourABCsREDO {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) out.println(solve());
        out.close();
    }

    public static int solve() throws IOException {
        //parse
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        //preprocess
        HashSet<Integer> expanded = new HashSet<>();
        for (int x : arr) {
            expanded.add(x);
            for (int y : arr) {
                if (x < y) {
                    expanded.add(y - x);
                }
            }
        }

        //clever trial and error with gen check
        int ret = 0;
        for (int a : expanded) {
            for (int b : expanded) {
                for (int c : expanded) {
                    if (a > b || b > c) continue;
                    ArrayList<Integer> gen = new ArrayList<>(Arrays.asList(a,b,c,a+b,b+c,a+c,a+b+c));
                    boolean good = true;
                    for (int x : arr) {
                        if (!gen.contains(x)) good = false;
                    }
                    if (good) ret++;
                }
            }
        }
        return ret;
    }
}

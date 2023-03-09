package Solutions.Codeforces.Round771;

import java.io.*;
import java.util.*;
/*
Codeforces Round #771 (Div. 2)
C. Inversion Graph
Thoughts:
Hard but somehow first try... :) yay
add next element as new component
for all current components bigger than cur, remove it
add back the biggest component that was removed
repeat this for all elements
max: N components. O(NlogN) solution.
 */
public class InversionGraph {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++){
            out.println(solve());
        }
        out.close();
    }
    public static int solve() throws IOException{
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        TreeSet<Integer> components = new TreeSet<>();
        for (int i=0;i<N;i++) {
            int num = Integer.parseInt(st.nextToken());
            components.add(num);
            int newNode = components.last();
            while (components.ceiling(num)!=null) {
                components.pollLast();
            }
            components.add(newNode);

            if (debug) System.out.println(components);
        }
        return components.size();
    }
}

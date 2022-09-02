package USACO.Silver.Contest.Practice.Usaco2020_2021_Silver.Silver2021_4;

import java.io.*;
import java.util.*;
/*
USACO 2021 US Open, Silver
Problem 2. Do You Know Your ABCs?
Concepts: pigeon hole principle, contradiction
Revisited - copy cat solution - sad :(
Bery hard >:|
 */

public class DoYouKnowYourABCs {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        out.println(solve());
        out.close();
    }
    public static int solve() throws IOException {
        int ans = 0;
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Set<Integer> expanded = new HashSet<>();
        for (int x : arr){
            expanded.add(x);
            for (int y : arr){
                if (x<y){
                    expanded.add(y-x);
                }
            }
        }
        out.println(expanded);
        for (int A : expanded) {
            for (int B : expanded) {
                for (int C : expanded) {
                    if (A <= B && B <= C) {
                        List<Integer> numbers = Arrays.asList(A,B,C,A+B,A+C,B+C,A+B+C);
                        boolean works = true;
                        for (int x : arr) {
                            if (!numbers.contains(x)){
                                works = false;
                            }
                        }
                        if (works) ans++;
                    }
                }
            }
        }
        return ans;
    }
}
